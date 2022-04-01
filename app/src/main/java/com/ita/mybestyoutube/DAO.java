package com.ita.mybestyoutube;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {

    protected SQLiteDatabase db = null;
    protected SQLiteOpenHelper baseHelper = null;

    public DAO(SQLiteOpenHelper baseHelper) {
        this.baseHelper = baseHelper;
    }

    public SQLiteDatabase open() {
        // créé la base de donnés avec la structure si elle n'existe pas
        db = baseHelper.getWritableDatabase();

        return db;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
