package de.eckart.inv.utils;

public enum PermissionsHandler {
    ADMINISTRATOR,
    USER;


    public boolean hasPermissions(PermissionsHandler rank) {
        return this.ordinal() <= rank.ordinal();
    }
}
