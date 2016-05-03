package com.example.quentin.gsb_frais_mobile;

/**
 * Created by Quentin on 03/05/2016.
 */
public class PuissanceFiscale {
    private int id_puissanceFiscale;
    private String puissance_fiscale;

    public int getId_puissanceFiscale() { return id_puissanceFiscale; }
    public String getValeurPuissanceFiscale() { return puissance_fiscale; }

    public PuissanceFiscale(int id, String puissanceFiscale) {
        this.id_puissanceFiscale = id;
        this.puissance_fiscale = puissanceFiscale;
    }
}
