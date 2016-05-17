package com.example.quentin.gsb_frais_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gsb_frais";

    private final String CARBURANT_KEY = "id_carburant";
    private final String CARBURANT_LIBELLE = "libelle_carburant";

    private final String PUISSANCE_KEY = "id_puissanceFiscale";
    private final String PUISSANCE_VALUE = "puissance_fiscale";

    private final String TARIF_CARBURANT = "idCarburant";
    private final String TARIF_PUISSANCE = "idPuissanceFiscale";
    private final String TARIF_VALUE = "montant";

    private final String CARBURANT_TABLE_NAME = "carburant";
    private final String PUISSANCE_TABLE_NAME = "puissanceFiscale";
    private final String TARIF_TABLE_NAME = "tarif";

    public BdSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CARBURANT_TABLE_CREATE =
                "CREATE TABLE " + CARBURANT_TABLE_NAME + " (" +
                        CARBURANT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CARBURANT_LIBELLE + " TEXT);";

        String PUISSANCE_TABLE_CREATE =
                "CREATE TABLE " + PUISSANCE_TABLE_NAME + " (" +
                        PUISSANCE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PUISSANCE_VALUE + " INTEGER);";

        String TARIF_TABLE_CREATE =
                "CREATE TABLE " + TARIF_TABLE_NAME + " (" +
                        TARIF_CARBURANT + " INTEGER NOT NULL, " +
                        TARIF_PUISSANCE + " INTEGER NOT NULL, " +
                        TARIF_VALUE + " DOUBLE, CONSTRAINT pk_TARIF PRIMARY KEY (" +
                        TARIF_CARBURANT + ", " +
                        TARIF_PUISSANCE + "), CONSTRAINT fk1_TARIF FOREIGN KEY (" +
                        TARIF_CARBURANT + ") REFERENCES " +
                        CARBURANT_TABLE_NAME + "(" +
                        CARBURANT_KEY + "), CONSTRAINT fk2_TARIF FOREIGN KEY (" +
                        TARIF_PUISSANCE + ") REFERENCES " +
                        PUISSANCE_TABLE_NAME + "(" +
                        PUISSANCE_KEY + "));";

        db.execSQL(CARBURANT_TABLE_CREATE);
        db.execSQL(PUISSANCE_TABLE_CREATE);
        db.execSQL(TARIF_TABLE_CREATE);

        db.execSQL("INSERT INTO " + CARBURANT_TABLE_NAME + " VALUES(1, 'Diesel')");
        db.execSQL("INSERT INTO " + CARBURANT_TABLE_NAME + " VALUES(2, 'Essence')");
        db.execSQL("INSERT INTO " + PUISSANCE_TABLE_NAME + " VALUES(1, 4)");
        db.execSQL("INSERT INTO " + PUISSANCE_TABLE_NAME + " VALUES(2, 5)");
        db.execSQL("INSERT INTO " + PUISSANCE_TABLE_NAME + " VALUES(3, 6)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(1, 1, 0.52)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(1, 2, 0.58)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(1, 3, 0.58)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(2, 1, 0.62)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(2, 2, 0.67)");
        db.execSQL("INSERT INTO " + TARIF_TABLE_NAME + " VALUES(2, 3, 0.67)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS carburant");
        db.execSQL("DROP TABLE IF EXISTS puissanceFiscale");
        db.execSQL("DROP TABLE IF EXISTS tarif");

        this.onCreate(db);
    }
}
