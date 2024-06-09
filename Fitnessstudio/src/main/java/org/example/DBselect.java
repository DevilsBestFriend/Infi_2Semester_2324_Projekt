package org.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DBselect {
    Scanner scn = new Scanner(System.in);

    public static void selmitglied(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, " +
                "mitgliedschft Mitgliedschaft, checkin Checkin, vertragsstart 'Mitglied seit'" +
                "FROM mitglieder ORDER BY nname ASC");
        formatResult(rs);
    }

    public static void seltrainer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, gehalt Gehalt, einstelldatum Einstelldatum " +
                "FROM trainer ORDER BY nname ASC");
        formatResult(rs);
    }

    public static void selkurs(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kursname, trainer.nname Trainer, startzeit Startzeit, endzeit Endzeit, tag Tag" +
                "FROM kurs INNER JOIN trainer ON kurs.trainer = trainer.id GROUP BY kurs.tag order by kurs.tag ASC");
        formatResult(rs);
    }

    public static void selteilnehmer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kurs, mitglieder.nname Nachname, mitglieder.vname Vorname, mitligeder.checkin Checkin" +
                "FROM teilnehmer INNER JOIN mitglieder ON teilnehmer.mitglied = mitglieder.id INNER JOIN kurs ON teilnehmer.kurs = kurs.id" +
                "GROUP BY kurs.name ORDER BY kurs.name ASC");
        formatResult(rs);
    }

    private static void formatResult(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String [] columnNames = new String[((ResultSetMetaData) rsmd).getColumnCount()];
        for (int i = 1; i <= columnNames.length; i++) {
            columnNames[i] = rsmd.getColumnName(i);
        }

        try {
            while (rs.next()) {

            }
            /*String name = db.getRs().getString("name");
                        String bezeichnung = db.getRs().getString("bezeichnung");
                        double preis = db.getRs().getDouble("preis");
                        int anzahl = db.getRs().getInt("anzahl");

                        System.out.println("CUSTOMER NAME = " + name);
                        System.out.println("ITEM NAME = " + bezeichnung);
                        System.out.println("PRICE = " + preis);
                        System.out.println("COUNT = " + anzahl);
                        System.out.println();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
