package org.example;


import java.sql.SQLException;
import java.util.Scanner;
//
// DB STARTEN NICHT VERGESSEN
//

// TODO import/export , update, delete, find fertigstellen

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
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
        boolean continueLoop = true;
        System.out.println("Command line tool started (h help)");

        while (continueLoop) {
            String cmd = scn.nextLine();
            if (cmd.equals("exit") || cmd.equals("e")) continueLoop = false;

            switch (cmd) {
                case "h", "help": {
                    System.out.println("Commands:");
                    System.out.println("e exit: Exit the program");
                    System.out.println("h help: Show this help message");
                    System.out.println("i insert: Insert a new entry");
                    System.out.println("s select f find: Select/find entries");
                    System.out.println("u update: Update an entry");
                    System.out.println("d delete: Delete an entry");
                    System.out.println("b backup: Backup database as JSON");
                    System.out.println("r restore: Restore database from a JSON backup");
                    break;
                }
                case "i", "insert", "s", "select", "u", "update", "d", "delete", "f", "find":
                    System.out.println("Enter table name (mi mitglieder, tr trainer, kr kurs, te teilnehmer");
                    String table = scn.nextLine();
                    switch (cmd) {
                        case "i", "insert":
                            insert(db, table);
                            break;
                        case "s", "select":
                            select(db, table);
                            break;
                        case "u", "update":
                            break;
                        case "d", "delete":
                            break;
                        case "f", "find":
                            find(db, table);
                            break;
                    }
                    break;
                case "b", "backup":
                    System.out.println("Backup will be saved as backup.json");
                    db.backup();
                    break;
                case "r", "restore":
                    System.out.println("Backup will be restored from backup.json");
                    db.restore();
                    break;
                default:
                    if (continueLoop) System.out.println("Invalid command (h help)");
                    break;
            }
        }
    }

    private static void insert(Database db, String table) throws SQLException {
        switch (table) {
            case "mi", "mitglieder":
                DBinsert.insmitglied(db);
                break;
            case "tr", "trainer":
                DBinsert.instrainer(db);
                break;
            case "kr", "kurs":
                DBinsert.inskurs(db);
                break;
            case "te", "teilnehmer":
                DBinsert.insteilnehmer(db);
                break;
        }
    }

    private static void select(Database db, String table) throws SQLException {
        switch (table) {
            case "mi", "mitglieder":
                DBselect.selmitglied(db);
                break;
            case "tr", "trainer":
                DBselect.seltrainer(db);
                break;
            case "kr", "kurs":
                DBselect.selkurs(db);
                break;
            case "te", "teilnehmer":
                DBselect.selteilnehmer(db);
                break;
        }
    }

    private static void find(Database db, String table) throws SQLException {
        switch (table) {
            case "mi", "mitglieder":
                DBfind.findmitglied(db);
                break;
            case "tr", "trainer":
                DBfind.findtrainer(db);
                break;
            case "kr", "kurs":
                DBfind.findkurs(db);
                break;
            case "te", "teilnehmer":
                DBfind.findteilnehmer(db);
                break;
        }
    }

    public static void dbinit(Database db) {
        //CREATE TYPE wochentag AS ENUM ('Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag');
        //CREATE TYPE mitgliedschaft AS ENUM ('Schüler', 'Standard', 'Premium', 'VIP', 'Gast', 'Abgemeldet');
        //in psql commandline einfügen

        //erstellung der Tabellen
        try {
            db.execStmt("CREATE TABLE IF NOT EXISTS mitglieder (" +
                    "id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(), " +
                    "vname VARCHAR(255), " +
                    "nname VARCHAR(255), " +
                    "gbd DATE, " +
                    "checkin BOOLEAN NOT NULL DEFAULT TRUE, " +
                    "mitgliedschaft MITGLIEDSCHAFT NOT NULL, " +
                    "vertragsstart DATE NOT NULL DEFAULT CURRENT_DATE)");
            db.execStmt("CREATE TABLE IF NOT EXISTS trainer (" +
                    "id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(), " +
                    "vname VARCHAR(255), " +
                    "nname VARCHAR(255), " +
                    "gbd DATE, " +
                    "gehalt NUMERIC(10, 2) NOT NULL, " +
                    "einstelldatum DATE NOT NULL DEFAULT CURRENT_DATE)");
            db.execStmt("CREATE TABLE IF NOT EXISTS kurs (" +
                    "id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(), " +
                    "name VARCHAR(255), " +
                    "trainer UUID, " +
                    "startzeit time, " +
                    "endzeit time, " +
                    "tag WOCHENTAG NOT NULL," +
                    "FOREIGN KEY (trainer) REFERENCES trainer(id))");
            db.execStmt("CREATE TABLE IF NOT EXISTS teilnehmer (" +
                    "id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(), " +
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