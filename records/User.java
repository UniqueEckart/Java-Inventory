package de.eckart.inv.records;

public class User {

    private String name;
    private String password;
    private String berechtigung;

    public User(String name, String password, String berechtigung) {
        this.name = name;
        this.password = password;
        this.berechtigung = berechtigung;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getBerechtigung() {
        return berechtigung;
    }

    public void setName(String new_Name) {
        this.name = new_Name;
    }

    public void setPassword(String new_Password) {
        this.password = new_Password;
    }

    public void setBerechtigung(String new_Berechtigung) {
        this.berechtigung = new_Berechtigung;
    }
}
