package de.eckart.inv.inventory;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
// Lokale Imports
import de.eckart.inv.utils.*;
import de.eckart.inv.records.User;

public class Users {

    static Scanner scn = new Scanner(System.in);

    public static void Create_User_Information() throws SQLException {
        System.out.println("Bitte gebe einen Nutzernamen ein: ");
        String user = scn.next();
        System.out.println("Bitte gebe eine Password ein");
        String password = scn.next();
        System.out.println("Bitte gebe eine Berechtigungsstufe ein: ");
        String berechtigung = scn.next();
        User user1 = new User(user, password, berechtigung);
        Create_User(user1);
    }

    private static void Create_User(User user) throws SQLException {
        Connection create_User = DatabaseUtil.ConnectDB();
        String query = "INSERT INTO user (name, password, berechtigung) VALUES (?, ?, ?);";
        PreparedStatement pst = create_User.prepareStatement(query);
        pst.setString(1, user.getName());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getBerechtigung());
        SQLUtils.executeSQL(pst);
        create_User.close();
        Main.cases();
    }

    public static void Delete_User() throws SQLException {
        System.out.println("Welchen user willst du löschen: ");
        String user = scn.next();


        Connection delete_User = DatabaseUtil.ConnectDB();
        String query = "DELETE FROM items WHERE username = ?;";
        PreparedStatement pst = delete_User.prepareStatement(query);
        pst.setString(1, user);
        SQLUtils.executeSQL(pst);
        delete_User.close();
        Main.cases();
    }
}
