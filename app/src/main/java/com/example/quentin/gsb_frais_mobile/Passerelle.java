package com.example.quentin.gsb_frais_mobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

public class Passerelle {
    private static String base = "bd_frais";
    private static int version = 1;
    private BdSQLiteOpenHelper acces_db;

    public Passerelle (Context ct) {
        acces_db = new BdSQLiteOpenHelper(ct);
    }

    public double getMontantAuKilometre(int CV, String carbu) {
        String request = "SELECT montant "
                + "FROM carburant c "
                + "INNER JOIN tarif t "
                + "ON c.id_carburant = t.idCarburant "
                + "INNER JOIN puissanceFiscale pf "
                + "ON t.idPuissanceFiscale = pf.id_puissanceFiscale "
                + "WHERE libelle_carburant = " + carbu
                + " AND puissance_fiscale = " + CV + ";";
        Cursor curseur = acces_db.getReadableDatabase().rawQuery(request, null);
        return curseur.getDouble(0);
    }

    public ArrayList<PuissanceFiscale> getPuissancesFiscales(){
        String request = "SELECT * FROM puissanceFiscale;";
        Cursor curseur = acces_db.getReadableDatabase().rawQuery(request, null);
        return cursorToPuissanceFiscaleArrayList(curseur);
    }

    private ArrayList<PuissanceFiscale> cursorToPuissanceFiscaleArrayList(Cursor curseur) {
        ArrayList<PuissanceFiscale> listePuissancesFiscales = new ArrayList<PuissanceFiscale>();
        int id;
        String valeur;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            valeur = curseur.getString(1);
            listePuissancesFiscales.add(new PuissanceFiscale(id, valeur));
            curseur.moveToNext();
        }
        return listePuissancesFiscales;
    }

    public ArrayList<Carburant> getCarburants(){
        String request = "SELECT * FROM carburant;";
        Cursor curseur = acces_db.getReadableDatabase().rawQuery(request, null);
        return cursorToCarburantArrayList(curseur);
    }

    private ArrayList<Carburant> cursorToCarburantArrayList(Cursor curseur) {
        ArrayList<Carburant> listeCarburant = new ArrayList<Carburant>();
        int id;
        String libelle;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            libelle = curseur.getString(1);
            listeCarburant.add(new Carburant(id, libelle));
            curseur.moveToNext();
        }
        return listeCarburant;
    }
}