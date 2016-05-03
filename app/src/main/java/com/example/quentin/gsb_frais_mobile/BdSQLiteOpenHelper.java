package com.example.quentin.gsb_frais_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {
    private String requeteCarburant = "CREATE TABLE carburant("
            + "id_carburant INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "libelle_carburant TEXT NOT NULL);";

    private String requetePuissanceFiscale = "CREATE TABLE puissanceFiscale("
            + "id_puissanceFiscale INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "puissance_fiscale TEXT NOT NULL);";

    private String requeteTarif = "CREATE TABLE tarif("
            + "idPuissanceFiscale INTEGER,"
            + "idCarburant INTEGER,"
            + "montant INTEGER NOT NULL,"
            + "CONSTRAINT FOREIGN KEY idPuissanceFiscale REFERENCES puissanceFiscale.id_puissanceFIscale,"
            + "CONSTRAINT FOREIGN KEY idCarburant REFERENCES carburant.id_carburant,"
            + "CONSTRAINT PRIMARY KEY (idPuissanceFiscale, idCarburant);";

    public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(requeteCarburant);
        db.execSQL("INSERT INTO carburant (libelle_carburant) VALUES('Essence');");
        db.execSQL("INSERT INTO carburant (libelle_carburant) VALUES('Diesel');");

        db.execSQL(requetePuissanceFiscale);
        db.execSQL("INSERT INTO puissanceFiscale (puissance_fiscale) VALUES('4');");
        db.execSQL("INSERT INTO puissanceFiscale (puissance_fiscale) VALUES('5');");
        db.execSQL("INSERT INTO puissanceFiscale (puissance_fiscale) VALUES('6');");

        db.execSQL(requeteTarif);
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(1, 1, 0.56);");
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(1, 2, 0.52);");
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(2, 1, 0.58);");
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(2, 2, 0.56);");
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(3, 1, 0.58);");
        db.execSQL("INSERT INTO tarif (idPuissanceFiscale, idCarburant, montant) VALUES(3, 2, 0.56);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
