package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-13
 * Time: 20:35
 * Project: Project2003-G07
 * Copyright: MIT
 */
public class importSQLData {
    Connection con = null;
    ResultSet rs;
    Statement stmt;
    List<Customer> shunos = new ArrayList<>();

    public List<Customer> getShunos() {
        return shunos;
    }

    public void connectToAndQueryDatabase(String username, String
            password) throws SQLException {




        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/gr7", username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }





    }
public void findAllCustomer() throws SQLException {

    stmt = con.createStatement();

    rs = stmt.executeQuery("select customer_id,customer_fname,customer_lname,customer_tel,customer_email,customer_address_id from customer");

    while (rs.next()) {
        Customer temp = new Customer();
        temp.setId(rs.getInt("customer_id"));
        temp.setFirstName(rs.getString("customer_fname"));
        temp.setLastName(rs.getString("customer_lname"));
        temp.setTel(rs.getString("customer_tel"));
        temp.setEmail(rs.getString("customer_email"));
        temp.setAddressId(rs.getInt("customer_address_id"));


        shunos.add(temp);
    }

}

public void addCustomer(Customer customer) throws SQLException {
        CallableStatement stm = con.prepareCall("insert into customer(customer_fname,customer_lname,customer_tel,customer_email,customer_address_id) values (?,?,?,?,1)");
        stm.setString(1,customer.firstName);
        stm.setString(2,customer.lastName);
        stm.setString(3,customer.tel);
        stm.setString(4, customer.email);
        stm.execute();

}




}
