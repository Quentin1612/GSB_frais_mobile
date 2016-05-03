package com.example.quentin.gsb_frais_mobile;

/**
 * Created by Quentin on 03/05/2016.
 */
public class Carburant {
    private int id_carburant;
    private String libelle_carburant;

    public int getId_carburant() { return id_carburant; }
    public void setId_carburant(int id) { this.id_carburant = id; }

    public String getLibelle_carburant() { return libelle_carburant; }
    public void setLibelle_carburant(String libelle) { this.libelle_carburant = libelle; }

    public Carburant(int id, String libelle) {
        this.id_carburant = id;
        this.libelle_carburant = libelle;
    }
}
