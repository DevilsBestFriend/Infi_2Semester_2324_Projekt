package org.example;

import java.sql.*;

public class Database {
    Connection con;

    public Database(String url) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url);
            System.out.println("Connection established hoffentlich");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void execStmt(String stmt) {
        try {
            Statement st = con.createStatement();
            st.execute(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
