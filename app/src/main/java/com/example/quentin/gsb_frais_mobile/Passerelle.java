package com.example.quentin.gsb_frais_mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.net.URL;

public class Passerelle {
    /*private double coutDieselCV4 = 0.52;
    private double coutDieselCV5Et6 = 0.56;
    private double coutEssenceCV4 = 0.56;
    private double coutEssenceCV5Et6 = 0.58;

    public double GetCoutDieselCV4() {
        return coutDieselCV4;
    }

    public double GetCoutDieselCV5Et6() {
        return coutDieselCV5Et6;
    }

    public double GetCoutEssenceCV4() {
        return coutEssenceCV4;
    }

    public double GetCoutEssenceCV5Et6() {
        return coutEssenceCV5Et6;
    }*/

    private static String base = "db_visiteur";
    private static int version = 1;
    private BdSQLiteOpenHelper acces_db;

    public Passerelle (Context ct) {
        acces_db = new BdSQLiteOpenHelper(ct, base, null, version);
    }
}