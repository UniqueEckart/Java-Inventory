package de.eckart.inv.records;

public class Item {

    private int id;
    private String name;
    private String beschreibung;
    private int anzahl;
    private String raum;

    // Constructor
    public Item(int id, String name, String beschreibung, int anzahl, String raum) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
        this.raum = raum;
    }

    // Getter
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public String getRaum() {
        return raum;
    }

    // Setter
    public void setID(int new_ID) {
        this.id = new_ID;
    }

    public void setName(String new_Name) {
        this.name = new_Name;
    }

    public void setBeschreibung(String new_Beschreibung) {
        this.beschreibung = new_Beschreibung;
    }

    public void setAnzahl(int new_Anzahl) {
        this.anzahl = new_Anzahl;
    }

    public void setRaum(String new_Raum) {
        this.raum = new_Raum;
    }
}
