package com.example.demo1;

import com.example.demo1.models.Customer;

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






}
