package org.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBselect {

    public static void selmitglied(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, " +
                "mitgliedschaft Mitgliedschaft, checkin Checkin, vertragsstart \"Mitglied seit\" " +
                "FROM mitglieder ORDER BY nname ASC");
        Database.formatResult(rs);
    }

    public static void seltrainer(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, gehalt Gehalt, einstelldatum Einstelldatum " +
                "FROM trainer ORDER BY nname ASC");
        Database.formatResult(rs);
    }

    public static void selkurs(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT k.tag Tag, k.name Kursname, t.nname Trainer, k.startzeit Startzeit, k.endzeit Endzeit " +
                "FROM kurs k INNER JOIN trainer t ON k.trainer = t.id " +
                "ORDER BY k.tag ASC");
        Database.formatResult(rs);
    }

    public static void selteilnehmer(Database db) throws SQLException {
        ResultSet rs = db.execQueryno("SELECT k.name Kurs, m.nname \"Teilnehmer n\", m.vname \"Teilnehmer v\", m.checkin Checkin " +
                "FROM teilnehmer t INNER JOIN mitglieder m ON t.mitglied = m.id " +
                "INNER JOIN kurs k ON t.kurs = k.id " +
                "ORDER BY k.name ASC");
        Database.formatResult(rs);
    }
}