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

    public ResultSet execQuery(String query, boolean prepared) throws SQLException {
        if (!prepared) {
            Statement st = con.createStatement();
            return st.executeQuery(query);
        } else {
            PreparedStatement pst = con.prepareStatement(query);
            return pst.executeQuery();
        }
    }

    public static void formatResult(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] columnNames = new String[((ResultSetMetaData) rsmd).getColumnCount()];
        for (int i = 1; i <= columnNames.length; i++) {
            columnNames[i] = rsmd.getColumnName(i);
        }

        try {
            while (rs.next()) {
                int i = 1;
                for (String columnName : columnNames) {
                    int type = rsmd.getColumnType(i);
                    switch (type) {
                        case java.sql.Types.INTEGER:
                            System.out.println(columnName + ": " + rs.getInt(columnName));
                            break;
                        case java.sql.Types.DOUBLE:
                            System.out.println(columnName + ": " + rs.getDouble(columnName));
                            break;
                        case java.sql.Types.DATE:
                            System.out.println(columnName + ": " + rs.getDate(columnName));
                            break;
                        case java.sql.Types.TIME:
                            System.out.println(columnName + ": " + rs.getTime(columnName));
                            break;
                        case java.sql.Types.BOOLEAN:
                            System.out.println(columnName + ": " + rs.getBoolean(columnName));
                            break;
                        default:
                            System.out.println(columnName + ": " + rs.getString(columnName));
                            break;
                    }
                    i++;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
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
