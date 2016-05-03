package com.example.quentin.gsb_frais_mobile;

/**
 * Created by Quentin on 03/05/2016.
 */
public class Tarif {
    private double montant;

    public double getMintantPF() { return montant; }

    public Tarif(double valeur) {
        this.montant = valeur;
    }
}