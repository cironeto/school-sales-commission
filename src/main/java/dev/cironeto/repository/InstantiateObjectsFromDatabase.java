package dev.cironeto.repository;

import dev.cironeto.domain.Seller;
import dev.cironeto.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstantiateObjectsFromDatabase {

    protected static Seller instantiateSellerFromStudentTable(ResultSet rs) throws SQLException {
        Seller seller = Seller.SellerBuilder.seller()
                .id(rs.getInt("seller_id"))
                .build();
        return seller;
    }

    protected static Seller instantiateSeller(ResultSet rs) throws SQLException {
        Seller seller = Seller
                .SellerBuilder.seller()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .salary(rs.getDouble("salary"))
                .build();
        return seller;
    }

    protected static Student instantiateStudent(ResultSet rs) throws SQLException {
        Seller seller = instantiateSeller(rs);
        Student student = Student
                .StudentBuilder.student()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .course(rs.getString("course"))
                .fee(rs.getDouble("fee"))
                .tuition(rs.getDouble("tuition"))
                .seller(seller)
                .build();
        return student;
    }
}
