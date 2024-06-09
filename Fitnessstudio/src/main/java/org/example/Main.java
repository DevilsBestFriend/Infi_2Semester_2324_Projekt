package org.example;

import java.sql.SQLException;
import java.util.Scanner;

//
// DB STARTEN NICHT VERGESSEN
//
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String url = "jdbc:postgresql:fitness";
        System.out.println("Enter username and password");
        url += ("?user=" + scn.nextLine() + "&password=" + scn.nextLine());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Database db = new Database(url);
        try {
            code(db);
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void code(Database db) throws SQLException {
        dbinit(db);


    }


    public static void dbinit(Database db) {
        //CREATE TYPE wochentag AS ENUM ('Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag');
        //CREATE TYPE mitgliedschaft AS ENUM ('Schüler', 'Standard', 'Premium', 'VIP', 'Gast', 'Abgemeldet');
        //in psql commandline einfügen

        //erstellung der Tabellen
        try {
            db.execStmt("CREATE TABLE IF NOT EXISTS mitglieder (" +
                    "id UUID PRIMARY KEY NOT NULL, " +
                    "vname VARCHAR(255) , " +
                    "nname VARCHAR(255) , " +
                    "gbd DATE, " +
                    "mitgliedschaft MITGLIEDSCHAFT NOT NULL, " +
                    "vertragsstart DATE NOT NULL DEFAULT CURRENT_DATE)");
            db.execStmt("CREATE TABLE IF NOT EXISTS trainer (" +
                    "id UUID PRIMARY KEY NOT NULL, " +
                    "vname VARCHAR(255) , " +
                    "nname VARCHAR(255) , " +
                    "gbd DATE, " +
                    "gehalt NUMERIC(10, 2) NOT NULL, " +
                    "einstelldatum DATE NOT NULL DEFAULT CURRENT_DATE)");
            db.execStmt("CREATE TABLE IF NOT EXISTS kurs (" +
                    "id UUID PRIMARY KEY NOT NULL, " +
                    "name VARCHAR(255) , " +
                    "trainer UUID, " +
                    "startzeit time, " +
                    "endzeit time, " +
                    "tag WOCHENTAG NOT NULL," +
                    "FOREIGN KEY (trainer) REFERENCES trainer(id))");
            db.execStmt("CREATE TABLE IF NOT EXISTS teilnehmer (" +
                    "id UUID PRIMARY KEY NOT NULL, " +
                    "mitglied UUID, " +
                    "kurs UUID, " +
                    "FOREIGN KEY (mitglied) REFERENCES mitglieder(id), " +
                    "FOREIGN KEY (kurs) REFERENCES kurs(id))");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabellen glaube ich");
            e.printStackTrace();
        }
    }
}