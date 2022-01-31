package de.eckart.inv.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    public static Connection ConnectDB() throws SQLException {
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:Inventory.db");
        }
        catch (Exception e) {
            e.printStackTrace();
            return DriverManager.getConnection("jdbc:sqlite:Inventory.db");
        }

    }
}
