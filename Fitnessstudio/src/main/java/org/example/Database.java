package org.example;

import java.io.StringWriter;
import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

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

    public void restore() {
        // TODO
        // Store file in src/main/resources
    }

    public void backup() {
        // TODO
        // Store file in src/main/resources
        FileWriter file = null;
        try {
            // initialize File writer to write the backup to a file and remove previous backup
            file = new FileWriter("src/main/resources/backup.json");
            file.flush();

            JSONObject jsonObject = null;

            // sql for tables
            String[] tablessql = {"SELECT * FROM mitglieder", "SELECT * FROM trainer", "SELECT * FROM kurs", "SELECT * FROM teilnehmer"};

            for (int i = 0; i < tablessql.length; i++) {
                jsonObject = new JSONObject();
                ResultSet rs = execQueryno(tablessql[i]);
                ResultSetMetaData rsmd = rs.getMetaData();
                String[] columnNames = new String[rsmd.getColumnCount()];
                for (int j = 0; j < columnNames.length; j++) {
                    columnNames[j] = rsmd.getColumnName(j + 1);
                }
                while (rs.next()) {
                    int j = 1;
                    for (String columnName : columnNames) {
                        int type = rsmd.getColumnType(j);
                        switch (type) {
                            case java.sql.Types.INTEGER:
                                jsonObject.put(columnName, rs.getInt(columnName));
                                break;
                            case java.sql.Types.DOUBLE:
                                jsonObject.put(columnName, rs.getDouble(columnName));
                                break;
                            /*case java.sql.Types.BOOLEAN:
                                jsonObject.put(columnName, rs.getBoolean(columnName));
                                break;*/
                            default:
                                jsonObject.put(columnName, rs.getString(columnName));
                                break;
                        }
                        j++;
                    }
                    jsonObject.writeJSONString(file);
                }

                String jsonText = file.toString();
                System.out.print(jsonText);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error");
        } finally {
            try {
                file.close();
            } catch (IOException ignored) {
            }
        }

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
