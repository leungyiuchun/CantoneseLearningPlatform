package com.example.user.cantoneselearningplatform;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user on 22/3/16.
 */
public class dataAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;

    public dataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
    }

    public dataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public dataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getWordList(String product){
        String cardProduct = product;
        String query = "SELECT CHAR_TABLE.chin_word  FROM CHAR_TABLE LEFT JOIN SYLLABLE_TABLE ON SYLLABLE_TABLE.s_id = CHAR_TABLE.s_id WHERE SYLLABLE_TABLE.syllable = " + "'"+product+"'";
            Cursor cursor = mDb.rawQuery(query,null);
        cursor.moveToFirst();
            if (cursor!=null)
            {
                cursor.moveToNext();
            }
            return cursor;
    }
    public Cursor getWord_Tone(String product){
        String cardProduct = product;
        String query = "SELECT CHAR_TABLE.chin_word,CHAR_TABLE.tone  FROM CHAR_TABLE LEFT JOIN SYLLABLE_TABLE ON SYLLABLE_TABLE.s_id = CHAR_TABLE.s_id WHERE SYLLABLE_TABLE.syllable = " + "'"+product+"'";
        Cursor cursor = mDb.rawQuery(query,null);
        cursor.moveToFirst();
        if (cursor!=null)
        {
            cursor.moveToNext();
        }
        return cursor;
    }
}
