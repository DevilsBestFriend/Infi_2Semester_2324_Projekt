package org.example;

import java.sql.SQLException;
import java.util.Scanner;

//
// TODO DB STARTEN NICHT VERGESSEN
//
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String url = "jdbc:postgresql:fitness";
        System.out.println("Enter username and password");
        url += ("?user=" + scn.nextLine() + "&password=" + scn.nextLine());

        Database db = new Database(url);
        try {
            code(db);
            db.getCon().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void code(Database db) throws SQLException {
        dbinit(db);
    }


    public static void dbinit(Database db) {
        db.execStmt("CREATE TYPE mitgliedschaft AS ENUM " +
                "('Schüler', 'Standart', 'Premium', 'VIP', 'Gast')");
        db.execStmt("CREATE TABLE IF NOT EXISTS mitglieder (" +
                "id UUID PRIMARY KEY NOT NULL, " +
                "vname VARCHAR(255) , " +
                "nname VARCHAR(255) , " +
                "gbd DATE, " +
                "mitgliedschaft MITGLIEDSCHAFT NOT NULL, " +
                "vertragsstart DATE NOT NULL DEFAULT CURRENT_DATE)");
    }
}