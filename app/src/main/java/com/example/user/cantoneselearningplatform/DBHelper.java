package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by user on 13/3/16.
 */
public class DBHelper extends SQLiteOpenHelper  {

    private static final String DATABASE_NAME = "production.sqlite";
    private static String DATABASE_PATH = "";
    private final static int DATABASE_VERSION = 3;
    private SQLiteDatabase mDataBase;
    private final Context mContext;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
            Log.d("External folder path2", DATABASE_PATH);
        }
        else
        {
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        try {
//            createDataBase();
//        }catch (IOException e){
//            Log.d("onCreate",""+e);
//        }
    }

    public void createDataBase() throws IOException
    {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        System.out.println("mDataBaseExist: " + mDataBaseExist);
        if(!mDataBaseExist)
        {
            System.out.println("copy db");
            this.getWritableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                System.out.println("copy db2");

                copyDataBase();
            }
            catch (IOException mIOException)
            {
                System.out.println("db error");

                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase()
    {
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        if(((MyApp)mContext.getApplicationContext()).getDbUri() == null){
            System.out.println("NEW DB GOGO");
        }else{
            System.out.println("old db exists.");
        }

        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return ((MyApp)mContext.getApplicationContext()).getDbUri() == null;
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        System.out.println("Copying DB");

        InputStream mInput;
        try{
            mInput = mContext.getContentResolver().openInputStream(((MyApp) mContext).getDbUri());
//            mInput = new FileInputStream(((MyApp) (mContext).getApplicationContext()).getDbUri().getPath());
//            mInput = new FileInputStream("/document/production.sqlite");
//            mInput = new FileInputStream(mContext.getDatabasePath(((MyApp) (mContext).getApplicationContext()).getDbUri().getPath()));
            System.out.println("new db!!");
        }catch (Exception e){
            System.out.println("db error!!: " + e.getMessage());
            e.printStackTrace();
            mInput = mContext.getAssets().open(DATABASE_NAME);
            Log.d("OLD DB!!!!:","");
            System.out.println("old db!!");

        }
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DATABASE_PATH + DATABASE_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS COMBINATION_TABLE");
        db.execSQL("DROP TABLE IF EXISTS DETAIL_TABLE");

        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }


    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

}
