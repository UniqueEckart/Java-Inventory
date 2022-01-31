package de.eckart.inv.inventory;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
// Lokale Impors
import de.eckart.inv.utils.DatabaseUtil;
import de.eckart.inv.utils.SQLUtils;
import de.eckart.inv.records.Item;

public class Items {

    static Scanner scn = new Scanner(System.in);

    public static void Create_Item_Info() throws SQLException {

        Connection itemConnection = DatabaseUtil.ConnectDB();

        String query = "Select COUNT(id) as new_id FROM items;";
        PreparedStatement pst = itemConnection.prepareStatement(query);
        ResultSet rs = SQLUtils.runSQL(pst);
        rs.next();
        int id = rs.getInt(1) + 1;

        System.out.println("Bitte gebe den Namen ein: ");
        String name = scn.nextLine();
        System.out.println("Bitte gebe eine Beschreibung an: ");
        String desc = scn.nextLine();
        System.out.println("Bitte gebe die aktuelle anzahl an: ");
        int anzahl = Integer.parseInt(scn.nextLine());
        System.out.println("Bitte gebe den akutelle Raum an: ");
        String raum = scn.nextLine();

        Item item = new Item(id, name, desc, anzahl, raum);
        itemConnection.close();
        Create_Item(item);
    }


    private static void Create_Item(Item item) throws SQLException {

        Connection createItem = DatabaseUtil.ConnectDB();
        String query = "INSERT INTO items (id, name, beschreibung, anzahl, raum) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement pst = createItem.prepareStatement(query);


        pst.setInt(1, item.getID());
        pst.setString(2, item.getName());
        pst.setString(3, item.getBeschreibung());
        pst.setInt(4, item.getAnzahl());
        pst.setString(5, item.getRaum());

        SQLUtils.executeSQL(pst);
        createItem.close();
        Main.cases();
    }


    public static void Delete_Item() throws SQLException {
        System.out.println("Bitte gebe die ID des zu löschenden Items an: ");
        int item_ID = scn.nextInt();

        Connection deleteItem = DatabaseUtil.ConnectDB();
        String query = "DELETE FROM items WHERE id = ?;";
        PreparedStatement pst = deleteItem.prepareStatement(query);
        pst.setInt(1, item_ID);
        SQLUtils.executeSQL(pst);
        deleteItem.close();
        Main.cases();
    }


    public static void Get_Item_Information() throws SQLException {
        System.out.println("Gebe die ID des zu bearbeiten Items ein: ");
        int item_ID = Integer.parseInt(scn.nextLine());

        Connection getInfo = DatabaseUtil.ConnectDB();
        String query = "SELECT * FROM items WHERE id = ?;";
        PreparedStatement pst = getInfo.prepareStatement(query);
        pst.setInt(1, item_ID);
        ResultSet rs = SQLUtils.runSQL(pst);

        if (rs.next()) {
            String name = rs.getString("name");
            String beschreibung = rs.getString("Beschreibung");
            int anzahl = rs.getInt("anzahl");
            String raum = rs.getString("Raum");
            getInfo.close();
            Item item = new Item(item_ID, name, beschreibung, anzahl, raum);
            Edit_Item(item);
        }
        else {
            System.out.println("Dieses Item wurde nicht gefunden!");
        }


    }


    private static void Edit_Item(Item item) throws SQLException {

        Connection edit_Item = DatabaseUtil.ConnectDB();
        String query = "UPDATE items SET name = ?, beschreibung = ?, anzahl = ?, raum = ? WHERE id = ?";
        PreparedStatement pst = edit_Item.prepareStatement(query);

        System.out.println("Gebe den Namen ein: ");
        String name = scn.nextLine();
        if (!name.equals("")) {
            item.setName(name);
        }
        System.out.println("Gebe eine Beschreibung an: ");
        String beschreibung = scn.nextLine();
        if (!beschreibung.equals("")) {
            item.setBeschreibung(beschreibung);
        }
        System.out.println("Gebe eine Anzahl an: ");
        int anzahl = Integer.parseInt(scn.nextLine());
        if (anzahl != 0) {
            item.setAnzahl(anzahl);
        }
        System.out.println("Gebe einen Raum an: ");
        String raum = scn.nextLine();
        if (!raum.equals("")) {
            item.setRaum(raum);
        }

        pst.setString(1, item.getName());
        pst.setString(2, item.getBeschreibung());
        pst.setInt(3, item.getAnzahl());
        pst.setString(4, item.getRaum());
        pst.setInt(5, item.getID());
        SQLUtils.updateSQL(pst);
        edit_Item.close();
        Main.cases();
    }

}
