package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBfind {
    public static void findmitglied(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, " +
                "mitgliedschft Mitgliedschaft, checkin Checkin, vertragsstart 'Mitglied seit'" +
                "FROM mitglieder ORDER BY nname ASC", true);
        Database.formatResult(rs);
    }

    public static void findtrainer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, gehalt Gehalt, einstelldatum Einstelldatum " +
                "FROM trainer ORDER BY nname ASC", true);
        Database.formatResult(rs);
    }

    public static void findkurs(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kursname, trainer.nname Trainer, startzeit Startzeit, endzeit Endzeit, tag Tag" +
                "FROM kurs INNER JOIN trainer ON kurs.trainer = trainer.id" +
                "GROUP BY kurs.tag order by kurs.tag ASC", true);
        Database.formatResult(rs);
    }

    public static void findteilnehmer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kurs, mitglieder.nname Nachname, mitglieder.vname Vorname, mitligeder.checkin Checkin" +
                "FROM teilnehmer INNER JOIN mitglieder ON teilnehmer.mitglied = mitglieder.id " +
                "INNER JOIN kurs ON teilnehmer.kurs = kurs.id" +
                "GROUP BY kurs.name ORDER BY kurs.name ASC", true);
        Database.formatResult(rs);
    }
}
