package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBfind {
    private static Scanner scn = new Scanner(System.in);

    public static void findmitglied(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, " +
                "mitgliedschaft Mitgliedschaft, checkin Checkin, vertragsstart \"Mitglied seit\" " +
                "FROM mitglieder ORDER BY nname ASC");
        Database.formatResult(rs);
    }

    public static void findtrainer(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, gehalt Gehalt, einstelldatum Einstelldatum " +
                "FROM trainer ORDER BY nname ASC");
        Database.formatResult(rs);
    }

    public static void findkurs(Database db) throws SQLException {
        System.out.println("Enter course name");
        PreparedStatement preparedStatement = db.getCon().prepareStatement("SELECT k.tag Tag, k.name Kursname, t.nname Trainer, k.startzeit Startzeit, k.endzeit Endzeit " +
                "FROM kurs k INNER JOIN trainer t ON k.trainer = t.id " +
                "WHERE k.name LIKE ? " +
                "ORDER BY k.tag ASC");
        preparedStatement.setString(1, "%" + scn.nextLine() + "%");
        try {
            ResultSet rs = db.execQueryye(preparedStatement);
            Database.formatResult(rs);
        } catch (SQLException e) {
            System.out.println("Fehler beim Suchen des Kurses finde ich");
            e.printStackTrace();
        }
    }

    public static void findteilnehmer(Database db) throws SQLException {
        System.out.println("Search by course name or participant name? (c/p - p defautl)");
        String sql = "SELECT k.name Kurs, m.nname \"Teilnehmer n\", m.vname \"Teilnehmer v\", m.checkin Checkin " +
                "FROM teilnehmer t INNER JOIN mitglieder m ON t.mitglied = m.id " +
                "INNER JOIN kurs k ON t.kurs = k.id ";
        PreparedStatement prst = null;
        if (scn.nextLine().equals("c")) {
            System.out.println("Enter search term");
            sql += "WHERE k.name LIKE ? ORDER BY k.name ASC";
            prst = db.getCon().prepareStatement(sql);
            prst.setString(1, "%" + scn.nextLine() + "%");
        } else {
            sql += "WHERE m.nname LIKE ? and m.vname LIKE ?";
            prst = db.getCon().prepareStatement(sql);
            System.out.println(prst.toString());
            System.out.println("Enter last name");
            prst.setString(1, "%" + scn.nextLine() + "%");
            System.out.println("Enter first name");
            prst.setString(2, "%" + scn.nextLine() + "%");
        }


        ResultSet rs = db.execQueryye(prst);
        Database.formatResult(rs);
    }
}
