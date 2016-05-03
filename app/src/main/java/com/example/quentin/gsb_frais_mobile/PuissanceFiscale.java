package com.example.quentin.gsb_frais_mobile;

/**
 * Created by Quentin on 03/05/2016.
 */
public class PuissanceFiscale {
    private int id_puissanceFiscale;
    private int puissance_fiscale;

    public int getId_carburant() { return id_puissanceFiscale; }
    public void setId_carburant(int id) { this.id_puissanceFiscale = id; }

    public int getLibelle_carburant() { return puissance_fiscale; }
    public void setLibelle_carburant(int puissanceFiscale) { this.puissance_fiscale = puissanceFiscale; }

    public PuissanceFiscale(int id, int puissanceFiscale) {
        this.id_puissanceFiscale = id;
        this.puissance_fiscale = puissanceFiscale;
    }
}
