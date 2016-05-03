package com.example.quentin.gsb_frais_mobile;

/**
 * Created by Quentin on 03/05/2016.
 */
public class Carburant {
    private int id_carburant;
    private String libelle_carburant;

    public int getId_carburant() { return id_carburant; }
    public String getLibelle_carburant() { return libelle_carburant; }

    public Carburant(int id, String libelle) {
        this.id_carburant = id;
        this.libelle_carburant = libelle;
    }
}
