package com.example.user.cantoneselearningplatform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 22/3/16.
 */
public class TmpHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "tmp.db";
    private static final Integer DATABASE_VERSION = 1;

    public TmpHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TMP_TABLE "
                + "( s_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "syllable TEXT,"
                + "consonant TEXT,"
                + "vowel TEXT"
                + " )";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS TMP_TABLE");

        // Create tables again
        onCreate(db);

    }
}
