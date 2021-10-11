package dev.cironeto.repository;

import connection.ConnectionFactory;
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
        } catch (SQLException e) {
            log.error("Can not find the seller");
            e.printStackTrace();
        }
        return sellers;
    }


    public static PreparedStatement preparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM `school`.`seller` WHERE name LIKE (?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

}
