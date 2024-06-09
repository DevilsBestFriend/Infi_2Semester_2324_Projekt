package org.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBselect {

    public static void selmitglied(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, " +
                "mitgliedschft Mitgliedschaft, checkin Checkin, vertragsstart 'Mitglied seit'" +
                "FROM mitglieder ORDER BY nname ASC", false);
        Database.formatResult(rs);
    }

    public static void seltrainer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT vname Vorname, nname Nachname, gbd Geburtsdatum, gehalt Gehalt, einstelldatum Einstelldatum " +
                "FROM trainer ORDER BY nname ASC", false);
        Database.formatResult(rs);
    }

    public static void selkurs(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kursname, trainer.nname Trainer, startzeit Startzeit, endzeit Endzeit, tag Tag" +
                "FROM kurs INNER JOIN trainer ON kurs.trainer = trainer.id GROUP BY kurs.tag order by kurs.tag ASC", false);
        Database.formatResult(rs);
    }

    public static void selteilnehmer(Database db) throws SQLException {
        ResultSet rs = db.execQuery("SELECT kurs.name Kurs, mitglieder.nname Nachname, mitglieder.vname Vorname, mitligeder.checkin Checkin" +
                "FROM teilnehmer INNER JOIN mitglieder ON teilnehmer.mitglied = mitglieder.id INNER JOIN kurs ON teilnehmer.kurs = kurs.id" +
                "GROUP BY kurs.name ORDER BY kurs.name ASC", false);
        Database.formatResult(rs);
    }
}