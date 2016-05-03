package com.example.quentin.gsb_frais_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

abstract public class BdSQLiteOpenHelper extends SQLiteOpenHelper {
    private String requeteCarburant = "CREATE TABLE carburant("
            + "id_carburant INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "libelle_carburant TEXT NOT NULL";

    private String requetePuissanceFiscale = "CREATE TABLE puissanceFiscale("
            + "id_puissanceFiscale INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "puissance_fiscale INTEGER NOT NULL";

    private String requeteTarif = "CREATE TABLE tarif("
            + "idPuissanceFiscale INTEGER,"
            + "idCarburant INTEGER,"
            + "tarif INTEGER NOT NULL,"
            + "CONSTRAINT FOREIGN KEY idPuissanceFiscale REFERENCES puissanceFiscale.id_puissanceFIscale,"
            + "CONSTRAINT FOREIGN KEY idCarburant REFERENCES carburant.id_carburant,"
            + "CONSTRAINT PRIMARY KEY (idPuissanceFiscale, idCarburant)";

    public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(requeteCarburant);
        // db.execSQL(requetePuissanceFiscale);
        // db.execSQL(requeteTarif);

        db.execSQL("INSERT INTO carburant (libelle_carburant) VALUES('Essence');");
    }
}
