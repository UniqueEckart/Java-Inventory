package de.eckart.inv.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtils {

    public static class UncheckedSQLException extends RuntimeException {
        public UncheckedSQLException(SQLException ex) {
            super(ex);
        }
    }

    public static ResultSet runSQL(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        }
        catch (SQLException ex) {
            throw new UncheckedSQLException(ex);
        }
    }

    public static void executeSQL(PreparedStatement ps) {
        try {
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateSQL(PreparedStatement ps) {
        try {
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new UncheckedSQLException(ex);
        }
    }
}
