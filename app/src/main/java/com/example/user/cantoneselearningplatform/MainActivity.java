package com.example.user.cantoneselearningplatform;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Answer> abc = new ArrayList<Answer>();
    public Boolean test = false;
    String[] init_array = {"b","p","m","f","d","t","n","l","g","k","ng","h","gw","kw","w","z","c","s","j"};
    String[] vowel_array = {"aa","aai","aau","aan","aang","aap","aat","aak","ai","au","am","an","ang","ap","at","ak","e","ei","eng","ek","i","iu","im","in","ing","ip","it","ik","o","oi","ou","on","ong","ot","ok","oe","oeng","oek","eoi","eon","eot","u","ui","un","ung","ut","uk","yu","yun","yut"};
    Integer total_number_init;
    Integer total_number_vowel;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button  play_button ;
        Button  setting_button;
        Button db_button;
        Log.d("MainActvitiy", "OnCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total_number_init = init_array.length;


        ContentValues values = new ContentValues();



        setting_button = (Button)findViewById(R.id.setting_button);
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        play_button = (Button)findViewById(R.id.play_button);

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInit()){
                    if (checkVowel()){
                        if (checkExer()){
                            if(checkTask()){
                                if(checkQuantityInt()){
                                    if (checkMode()){
                                        test = true;
                                    }
                                }
                            }
                        }
                    }
                }

                if (test) {
                    Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                    startActivity(intent);

                } else {

                }
            }
        });
        db_button = (Button)findViewById(R.id.db_button);
        db_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DBActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
//        ((MyApp)this.getApplication())
        checkInternet();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void checkInternet(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null){
        }else {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("無法連到網路")
                    .setMessage("請開啟Wi-Fi或數據連線 ")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        }

    }
    public Boolean checkInit(){
        Boolean checked;
        if(((MyApp)this.getApplication()).init_List.isEmpty()){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定聲母")
                    .setMessage("未設定練習聲母")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public Boolean checkVowel(){
        Boolean checked;
        if(((MyApp)this.getApplication()).vowel_List.isEmpty()){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定韻母")
                    .setMessage("未設定練習韻母")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public Boolean checkTask(){
        Boolean checked;
        if(((MyApp)this.getApplication()).globalTaskString.equalsIgnoreCase("請選擇任務") ){
            AlertDialog dialog =new  AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定任務")
                    .setMessage("未設定練習任務")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public void checkHints(){
        if(((MyApp)this.getApplication()).globalHintsString.equalsIgnoreCase("請選擇提示")){
            test = false;
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定提示")
                    .setMessage("未設定練習提示")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        }else{test = true;}
    }

    public Boolean checkMode(){
        Boolean checked;
        if(((MyApp)this.getApplication()).globalModeString.equalsIgnoreCase("請選擇模式")){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定模式")
                    .setMessage("未設定練習模式")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public Boolean checkExer(){
        Boolean checked;
        if(((MyApp)this.getApplication()).getRandomlizeList().size() == 0) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定練習")
                    .setMessage("未設定練習")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public Boolean checkQuantityInt(){
        Boolean checked;
        if(((MyApp) this.getApplication()).quantityInt ==0){
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("未設定數量")
                    .setMessage("未設定練習數量")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            checked = false;
        }else{checked = true;}
        return checked;
    }
    public Boolean checkSetting(){

        return true;
    }
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}
