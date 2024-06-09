package org.example;

import java.sql.*;

public class Database {
    Connection con;
    ResultSet rs;

    public Database(String url) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url);
            System.out.println("Connection established hoffentlich");
            rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore() throws SQLException {
        // TODO
    }

    public void backup() throws SQLException {
        // TODO
    }

    public void execStmt(String stmt) throws SQLException {
            Statement st = con.createStatement();
            st.execute(stmt);
    }

    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Kommpletteerino (JAAA, ja, ja komplett) Verbindung geschlossen");
    }

    public Connection getCon() {
        return con;
    }
}
