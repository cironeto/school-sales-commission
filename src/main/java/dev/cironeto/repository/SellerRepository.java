package dev.cironeto.repository;

import dev.cironeto.connection.ConnectionFactory;
import dev.cironeto.domain.Seller;
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
        log.info("Finding Seller by name '{}'\n", name);
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
//            System.out.println(sellers);
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

    public static void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = preparedStatementDelete(conn, id))
             {
                 ps.execute();
                 log.info("Seller ID '{}' removed from database", id);
                 System.out.println(String.format("Seller ID %s removed from database", id));
        } catch (SQLException e) {
            log.error("Error while deleting Seller ID '{}'", id);
            e.printStackTrace();
        }
    }


    public static PreparedStatement preparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM `school`.`seller` WHERE name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static PreparedStatement preparedStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `school`.`seller` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

}
