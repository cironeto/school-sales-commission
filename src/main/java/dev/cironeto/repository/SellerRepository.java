package dev.cironeto.repository;

import dev.cironeto.connection.ConnectionFactory;
import dev.cironeto.domain.Seller;
import dev.cironeto.domain.Student;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class SellerRepository {

    public static List<Seller> findByName(String name) {
        log.info("Finding Seller by name '{}'", name);
        List<Seller> sellers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementFindByName(conn, name);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Seller seller = Seller.SellerBuilder.seller()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .salary(rs.getDouble("salary"))
                        .build();
                sellers.add(seller);
            }
            for (int i = 0; i < sellers.size(); i++) {
                Seller seller = sellers.get(i);
                System.out.println(seller);
            }
        } catch (SQLException e) {
            log.error("Can not find the seller");
            e.printStackTrace();
        }
        return sellers;
    }

    public static PreparedStatement preparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM `school`.`seller` WHERE name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static Seller findById(int id) {
        log.info("Finding Seller by id '{}'", id);
        Seller seller = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) throw new IllegalArgumentException("ID does not exist");
            seller = Seller
                    .SellerBuilder.seller()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .salary(rs.getDouble("salary"))
                    .build();
            System.out.println(seller);
        } catch (SQLException e) {
            log.error("Can not find the seller");
            e.printStackTrace();
        }
        return seller;
    }

    public static PreparedStatement preparedStatementFindById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM `school`.`seller` WHERE id LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }


    public static void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementDelete(conn, id)) {
            ps.execute();
            log.info("Seller ID '{}' removed from database", id);
        } catch (SQLException e) {
            log.error("Error while deleting Seller ID '{}'", id);
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `school`.`seller` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void save(Seller seller) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementSave(conn, seller)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while saving seller");
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementSave(Connection conn, Seller seller) throws SQLException {
        String sql = "INSERT INTO `school`.`seller` (`name`, `salary`) VALUES (? , ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, seller.getName());
        ps.setDouble(2, seller.getSalary());
        return ps;
    }

    public static void update(Seller seller) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementUpdate(conn, seller)) {
            ps.executeUpdate();
            log.info("Seller ID '{}' updated", seller.getId());
        } catch (SQLException e) {
            log.error("Error while updating seller");
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementUpdate(Connection conn, Seller seller) throws SQLException {
        String sql = "UPDATE `school`.`seller` SET `name` = ?, `salary` = ? WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, seller.getName());
        ps.setDouble(2, seller.getSalary());
        ps.setInt(3, seller.getId());
        return ps;
    }

//-----------------------------------------------------------------------------------------

    public static List<Student> filterSalesById(int id) {
        System.out.println(String.format("Filtering sales of Seller %s", id));
        Student student;
        List<Student> students = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementFilterSalesById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Seller seller = Seller.SellerBuilder.seller()
                        .id(rs.getInt("seller_id"))
                        .build();
                student = Student
                        .StudentBuilder.student()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .course(rs.getString("course"))
                        .fee(rs.getDouble("fee"))
                        .tuition(rs.getDouble("tuition"))
                        .seller(seller)
                        .build();
                students.add(student);
            }
            for (int i = 0; i < students.size(); i++) {
                Student student1 = students.get(i);
                System.out.println(student1);
            }

        } catch (SQLException e) {
            log.error("Can not find the student");
            e.printStackTrace();
        }
        return students;
    }

    public static PreparedStatement preparedStatementFilterSalesById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM school.student\n" +
                "INNER JOIN school.seller on student.seller_id = seller.id\n" +
                "WHERE seller.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        return ps;
    }


    public static double showTotalCommissionById(int id) {
        System.out.println(String.format("Total sales commission of seller ID %d : ", id));
        Seller seller;
        double totalCommission = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementShowTotalCommissionById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) throw new IllegalArgumentException("ID does not exist");
            seller = Seller.SellerBuilder.seller()
                    .id(rs.getInt("seller_id"))
                    .build();
            totalCommission = rs.getDouble("Total sales commission");
            System.out.println(totalCommission);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCommission;
    }


    public static PreparedStatement preparedStatementShowTotalCommissionById(Connection conn, int id) throws SQLException {
        String sql = "SELECT SUM(student.fee) AS 'Total sales commission', student.seller_id FROM school.student\n" +
                "INNER JOIN school.seller on student.seller_id = seller.id\n" +
                "WHERE seller.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }


}
