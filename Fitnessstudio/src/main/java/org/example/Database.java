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
        // Store file in src/main/resources
    }

    public void backup() throws SQLException {
        // TODO
        // Store file in src/main/resources
    }

    public void execStmt(String stmt) throws SQLException {
        Statement st = con.createStatement();
        st.execute(stmt);
    }

    public ResultSet execQueryno(String query) throws SQLException {
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }

    public ResultSet execQueryye(PreparedStatement pst) throws SQLException {
        return pst.executeQuery();
    }

    public static void formatResult(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] columnNames = new String[rsmd.getColumnCount()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = rsmd.getColumnName(i + 1);
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
                            String bool;
                            if (rs.getBoolean(columnName)) {
                                bool = ("true");
                            } else {
                                bool = ("false");
                            }
                            System.out.println(columnName + ": " + bool);
                            break;
                        default:
                            System.out.println(columnName + ": " + rs.getString(columnName));
                            break;
                    }
                    i++;
                }
                System.out.println();
            }
        } catch (SQLException e) {
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
