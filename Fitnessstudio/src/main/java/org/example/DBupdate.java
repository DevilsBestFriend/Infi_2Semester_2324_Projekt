package org.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DBupdate {
    private static Scanner scn = new Scanner(System.in);

    public static void updteilnehmer(Database db) throws SQLException {

        String[] columnNames = getColumns(db);
        System.out.print("Enter first and last name of the participant: ");
        String fName = Main.scn.nextLine();
        String lName = Main.scn.nextLine();
        String updateC = updateC(db, columnNames);
        switch (updateC) {
        }

        }

        public static void checkInOut (Database db, String mId){

        }

        public static void updkurs (Database db){
        }

        public static void updmitglied (Database db){
        }

        public static void updtrainer (Database db){
        }

        public static String[] getColumns (Database db) throws SQLException {
            ResultSet rs = db.execQueryno("SELECT * FROM teilnehmer where id = null");
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] columnNames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            return columnNames;
        }

        public static String updateC (Database db, String[]columnNames) throws SQLException {
            System.out.println("Enter the column you want to update: ");
            for (int i = 0; i < columnNames.length; i++) {
                if (i == columnNames.length - 1) {
                    System.out.println(columnNames[i]);
                } else {
                    System.out.print(columnNames[i] + ", ");
                }
            }
            return scn.nextLine();
        }
    }
