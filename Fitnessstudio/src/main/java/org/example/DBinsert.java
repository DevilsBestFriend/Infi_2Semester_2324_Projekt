package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DBinsert {
    private static Scanner scn = new Scanner(System.in);

    public static void insmitglied(Database db) throws SQLException {
        System.out.println("Enter first name");
        String vname = scn.nextLine();
        System.out.println("Enter last name");
        String nname = scn.nextLine();
        System.out.println("Enter birthdate (yyyy-mm-dd)");
        LocalDate localDateTime = LocalDate.parse(scn.nextLine());
        java.sql.Date gbd = java.sql.Date.valueOf(localDateTime.toString());
        System.out.println("Type of membership (Schüler, Standard, Premium, VIP, Gast, Abgemeldet)");
        String mitgliedschaft = scn.nextLine();
        PreparedStatement pst = db.getCon().prepareStatement("INSERT INTO mitglieder (vname, nname, gbd, mitgliedschaft) VALUES (?, ?, ?, ?)");
        pst.setString(1, vname);
        pst.setString(2, nname);
        pst.setDate(3, gbd);
        pst.setObject(4, mitgliedschaft, java.sql.Types.OTHER);
        pst.executeUpdate();
        pst.close();
    }

    public static void instrainer(Database db) throws SQLException {
        System.out.println("Enter first name");
        String vname = scn.nextLine();
        System.out.println("Enter last name");
        String nname = scn.nextLine();
        System.out.println("Enter birthdate (yyyy-mm-dd)");
        LocalDate localDateTime = LocalDate.parse(scn.nextLine());
        java.sql.Date gbd = java.sql.Date.valueOf(localDateTime.toString());
        System.out.println("Enter salary");
        double gehalt = Double.parseDouble(scn.nextLine());
        PreparedStatement pst = db.getCon().prepareStatement("INSERT INTO trainer (vname, nname, gbd, gehalt) VALUES (?, ?, ?, ?)");
        pst.setString(1, vname);
        pst.setString(2, nname);
        pst.setDate(3, gbd);
        pst.setDouble(4, gehalt);
        pst.executeUpdate();
        pst.close();
    }

    public static void inskurs(Database db) throws SQLException {
        System.out.println("Enter name of course");
        String name = scn.nextLine();
        System.out.println("Enter trainer first name");
        String trfs = scn.nextLine();
        System.out.println("Enter trainer last name");
        String trls = scn.nextLine();
        PreparedStatement psttemp = db.getCon().prepareStatement("SELECT id FROM trainer WHERE vname = ? AND nname = ?");
        psttemp.setString(1, trfs);
        psttemp.setString(2, trls);
        ResultSet temprs = db.execQueryye(psttemp);
        temprs.next();
        String tid = temprs.getString("id");
        System.out.println("Enter start time (hh:mm:ss)");
        LocalTime localDateTime = LocalTime.parse(scn.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        java.sql.Time startzeit = java.sql.Time.valueOf(localDateTime);
        System.out.println("Enter end time (hh:mm:ss)");
        localDateTime = LocalTime.parse(scn.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        java.sql.Time endzeit = java.sql.Time.valueOf(localDateTime);
        System.out.println("Enter day of the week (Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag)");
        String tag = scn.nextLine();
        PreparedStatement pst = db.getCon().prepareStatement("INSERT INTO kurs (name, trainer, startzeit, endzeit, tag) VALUES (?, ?, ?, ?, ?)");
        pst.setString(1, name);
        pst.setObject(2, tid, Types.OTHER);
        pst.setTime(3, startzeit);
        pst.setTime(4, endzeit);
        pst.setObject(5, tag, java.sql.Types.OTHER);
        pst.executeUpdate();
        pst.close();
    }

    public static void insteilnehmer(Database db) throws SQLException {
        System.out.println("Enter member first name");
        String mifs = scn.nextLine();
        System.out.println("Enter member last name");
        String mils = scn.nextLine();
        PreparedStatement temppst = db.getCon().prepareStatement("SELECT id FROM mitglieder WHERE vname = ? AND nname = ?");
        temppst.setString(1, mifs);
        temppst.setString(2, mils);
        ResultSet temprs = db.execQueryye(temppst);
        temprs.next();
        String mid = temprs.getString("id");
        System.out.println("Enter course name");
        String krname = scn.nextLine();
        temppst = db.getCon().prepareStatement("SELECT id FROM kurs WHERE name = ?");
        temppst.setString(1, krname);
        temprs = db.execQueryye(temppst);
        temprs.next();
        String kid = temppst.getResultSet().getString("id");
        PreparedStatement pst = db.getCon().prepareStatement("INSERT INTO teilnehmer (mitglied, kurs) VALUES (?, ?)");
        pst.setObject(1, mid, java.sql.Types.OTHER);
        pst.setObject(2, kid, java.sql.Types.OTHER);
        pst.executeUpdate();
        pst.close();
    }
}