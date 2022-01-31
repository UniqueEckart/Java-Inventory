package de.eckart.inv.inventory;

// Java Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
// Lokale Imports
import de.eckart.inv.utils.DatabaseUtil;
import de.eckart.inv.utils.SQLUtils;
import de.eckart.inv.records.Current_User;


public class Main {
    static Scanner scn = new Scanner(System.in);
    static Current_User cu;

    public static void main(String[] args) throws SQLException {
        login();
    }

    private static void login() throws SQLException {
        Connection loginConnection = DatabaseUtil.ConnectDB();

        System.out.println("Bitte einen Nutzernamen eingeben: ");
        String user = scn.nextLine();
        System.out.println("Bitte Password eingeben: ");
        String pass = scn.nextLine();

        String query = "Select berechtigung FROM user WHERE username = ? AND password = ?;";
        PreparedStatement pst = loginConnection.prepareStatement(query);
        pst.setString(1, user);
        pst.setString(2, pass);
        ResultSet rs = SQLUtils.runSQL(pst);

        if (rs.next()) {
            String berechtigung = rs.getString("berechtigung");
            cu = new Current_User(user, berechtigung);
            loginConnection.close();
            cases();
        }
        else {
            System.out.println("Nicht da");
        }

    }

    public static void cases() throws SQLException {
        if (cu.berechtigung().equals("1")) {
            System.out.println("(User) Bitte gebe ein: ");
            String wahl = scn.nextLine();
            switch (wahl) {
                case "Create Item" -> Items.Create_Item_Info();
                case "Edit Item" -> Items.Get_Item_Information();
                case "Delete Item" -> Items.Delete_Item();
                case "Logout" -> Logout();
            }
        }
        else if (cu.berechtigung().equals("2")){
            System.out.println("(Admin) Bitte gebe ein:");
            String wahl = scn.nextLine();
            switch (wahl) {
                case "Create Item" -> Items.Create_Item_Info();
                case "Edit Item" -> Items.Get_Item_Information();
                case "Delete Item" -> Items.Delete_Item();
                case "Create Account" -> Users.Create_User_Information();
                case "Delete Account" -> Users.Delete_User();
                case "Logout" -> Logout();
            }
        }
        else {
            System.out.println("Fehler");
        }

    }

    public static void Logout() throws SQLException {
        cu = null;
        System.out.println("Erfolgreich ausgeloggt!");
        login();
    }
}
