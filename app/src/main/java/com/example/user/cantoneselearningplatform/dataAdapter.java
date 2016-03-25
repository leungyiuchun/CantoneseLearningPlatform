package com.example.user.cantoneselearningplatform;

import android.content.ContentValues;
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
            mDb = mDbHelper.getWritableDatabase();
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
        String query = "SELECT * FROM CHAR_TABLE LEFT JOIN SYLLABLE_TABLE ON SYLLABLE_TABLE.s_id = CHAR_TABLE.s_id WHERE SYLLABLE_TABLE.syllable = " + "'"+product+"'" + "ORDER BY CHAR_TABLE.tone ASC";
        Cursor cursor = mDb.rawQuery(query,null);
        cursor.moveToFirst();
        if (cursor!=null)
        {
            cursor.moveToNext();
        }
        return cursor;
    }
    public Boolean insertWord(String chin_word1,Integer tone1,Integer s_id){
        Boolean successful;
        ContentValues cv = new ContentValues();
        cv.put("chin_word",chin_word1);
        cv.put("tone", tone1);
        cv.put("s_id", s_id);
        Long long1 = mDb.insert("CHAR_TABLE",null,cv);
        if(long1 == -1){
            successful = false;
        }else {
            successful = true;
        }
        return successful;
    }
    public Boolean delWord(Integer id1){
        Boolean successful;

        Integer c_id1 = id1;
        Integer long1 = mDb.delete("CHAR_TABLE","c_id " + "=" + c_id1,null);
        if(long1 == -1){
            successful = false;
        }else {
            successful = true;
        }
        return successful;
    }
}
