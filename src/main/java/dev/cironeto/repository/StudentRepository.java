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
public class StudentRepository {

    public static List<Student> findByName(String name) {
        log.info("Finding Student by name '{}'", name);
        List<Student> students = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementFindByName(conn, name);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Seller seller = Seller.SellerBuilder.seller()
                        .id(rs.getInt("seller_id"))
                        .name(rs.getString("seller_name"))
                        .build();
                Student student = Student.StudentBuilder.student()
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
                Student student = students.get(i);
                System.out.println(student);
            }
        } catch (SQLException e) {
            log.error("Can not find the student");
            e.printStackTrace();
        }
        return students;
    }

    public static PreparedStatement preparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT student.id, student.name, student.course, student.fee, student.tuition,  seller.name as 'seller_name', seller.id as seller_id\n" +
                "FROM school.student INNER JOIN school.seller on student.seller_id = seller.id\n" +
                "WHERE student.name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static Student findById(int id) {
        log.info("Finding Student by id '{}'", id);
        Student student = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) throw new IllegalArgumentException("ID does not exist");
            Seller seller = Seller.SellerBuilder.seller()
                    .name(rs.getString("seller_name"))
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
            System.out.println(student);
        } catch (SQLException e) {
            log.error("Can not find the student");
            e.printStackTrace();
        }
        return student;
    }

    public static PreparedStatement preparedStatementFindById(Connection conn, int id) throws SQLException {
        String sql = "SELECT student.id, student.name, student.course, student.fee, student.tuition,  seller.name as 'seller_name', seller.id as seller_id\n" +
                "FROM school.student INNER JOIN school.seller on student.seller_id = seller.id\n" +
                "WHERE student.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }


    public static void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementDelete(conn, id)) {
            ps.execute();
            log.info("Student ID '{}' removed from database", id);
        } catch (SQLException e) {
            log.error("Error while deleting Student ID '{}'", id);
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `school`.`student` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void save(Student student) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementSave(conn, student)) {
            ps.executeUpdate();
            log.info("Student saved with success");
        } catch (SQLException e) {
            log.error("Error while saving student");
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementSave(Connection conn, Student student) throws SQLException {
        String sql = "INSERT INTO `school`.`student` (`name`, `course`, `fee`, `tuition`, `seller_id`) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setString(2, student.getCourse());
        ps.setDouble(3, student.getFee());
        ps.setDouble(4, student.getTuition());
        ps.setInt(5, student.getSeller().getId());
        return ps;
    }

    public static void update(Student student) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementUpdate(conn, student)) {
            ps.executeUpdate();
            log.info("Student ID '{}' updated", student.getId());
        } catch (SQLException e) {
            log.error("Error while updating student");
            e.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatementUpdate(Connection conn, Student student) throws SQLException {
        String sql = "UPDATE `school`.`student` SET `course` = ?, `fee` = ?, `tuition` = ? WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getCourse());
        ps.setDouble(2, student.getFee());
        ps.setDouble(3, student.getTuition());
        ps.setInt(4, student.getId());
        return ps;
    }

//-----------------------------------------------------------------------------------------



    public static PreparedStatement preparedStatementTotalSalesCommissionById(Connection conn, Seller seller) throws SQLException {
        String sql = "SELECT student.id, student.name, student.fee, student.seller_id FROM school.student\n" +
                "INNER JOIN school.seller on student.seller_id = seller.id\n" +
                "WHERE seller.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, seller.getId());

        return ps;
    }


}
