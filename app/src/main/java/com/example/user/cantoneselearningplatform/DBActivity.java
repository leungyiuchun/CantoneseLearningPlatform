package com.example.user.cantoneselearningplatform;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Picture;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBActivity extends AppCompatActivity{
    public String table1_name = "COMBINATION_TABLE";
    public String table2_name = "DETAIL_TABLE";
    Spinner spinnerInit;
    Spinner spinnerVowel;
    TextView combination;
    TableLayout tl_record;
    private String displayInit = "";
    private String displayVowel = "";
    String display;
    String[] init_array = {"b","p","m","f","d","t","n","l","g","k","ng","h","gw","kw","w","z","c","s","j"};
    String[] vowel_array = {"aa","aai","aau","aam","aan","aang","aap","aat","aak","ai","au","am","an","ang","ap","at","ak","e","ei","eng","ek","i","iu","im","in","ing","ip","it","ik","o","oi","ou","on","ong","ot","ok","oe","oeng","oek","eoi","eon","eot","u","ui","un","ung","ut","uk","yu","yun","yut"};
    ArrayAdapter<String> initList;
    ArrayAdapter<String> vowelList;
    ArrayList<Record> recordList;
//    Button add_record;
    Button edit_record;
    Button del_record;
    dataAdapter mDbHelper;
    TmpHelper tmpHelper;
    FragmentPagerAdapter adapterViewPager;

    //HAVE TO CLOSE THE DB BY ADDING .CLOSE() IN SOMEWHERE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        spinnerInit = (Spinner)findViewById(R.id.init_spinner);
        spinnerVowel = (Spinner)findViewById(R.id.vowel_spinner);
        combination = (TextView)findViewById(R.id.db_combination);

        initList = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner,init_array);
        spinnerInit.setAdapter(initList);

        ViewPager vpPager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        tmpHelper = new TmpHelper(this.getApplicationContext());
//        SQLiteDatabase db = tmpHelper.getWritableDatabase();
//        mDbHelper = new dataAdapter(this.getApplicationContext());
//        try{
//            mDbHelper.createDatabase();
//            mDbHelper.open();
//        }catch (SQLException e){
//            Log.d("mDbhelper",""+e);
//        }
/*        for(int i=0;i<init_array.length;i++){
//            for (int j=0;j<vowel_array.length;j++){
//                Log.d("",""+init_array[i].toString() + " " + vowel_array[i] );
//                String product = init_array[i] + vowel_array[j];
//                ContentValues values = new ContentValues();
//                values.put("syllable",product);
//                values.put("consonant",init_array[i]);
//                values.put("vowel",vowel_array[j]);
//                db.insert("TMP_TABLE", null, values);
//            }
//        }
//        db.close();
*/
        vowelList = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner,vowel_array);

        Button pass_button = (Button)this.findViewById(R.id.setting_button);
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DBActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        spinnerVowel.setAdapter(vowelList);

        spinnerInit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayInit = init_array[position];
                if (displayVowel.isEmpty()) {
                    spinnerVowel.requestFocus();
                } else {
                    display = displayInit + displayVowel;
                    combination.setText(display);
//                    getTable(display) ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerVowel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayVowel = vowel_array[position];
                if (displayInit.isEmpty()) {
                    spinnerInit.requestFocus();
                } else {
                    display = displayInit + displayVowel;
                    combination.setText(display);
//                    getTable(display) ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //    public void getTable(String product){
//        tl_record.removeAllViews();
//        Integer sum = cursor.getCount();
//        Log.d("sumsums",""+sum.toString());
//        if(sum != 0) {
//            cursor.moveToFirst();
//            for (int i = 0; i < sum; i++) {
//                String strCr = cursor.getString(0);
//
//                Record record = new Record(cursor.getString(cursor.getColumnIndex("combination")),cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("chin_word")),cursor.getInt(cursor.getColumnIndex("tone")),cursor.getBlob(cursor.getColumnIndex("picture")));
//                TableRow row = new TableRow(this.getApplicationContext());
//                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                row.setLayoutParams(lp);
//
//                final Button cp = new Button(this.getApplicationContext());
//                cp.setAllCaps(false);
//                cp.setText(record.getCombination());
//                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
//                cp.setTextSize(40);
//                cp.setGravity(Gravity.CENTER);
//                row.addView(cp);
//                cp.setPadding(10, 0, 10, 0);
//
//                final TextView tv1 = new TextView(this.getApplicationContext());
//                tv1.setText(record.getChin_word());
//                tv1.setTextSize(40);
//                tv1.setGravity(Gravity.CENTER);
//                tv1.setPadding(10, 0, 10, 0);
//                tv1.setTextColor(Color.BLACK);
//                row.addView(tv1);
//
//                final TextView tv2 = new TextView(this.getApplicationContext());
//                if (record.getPicture() == null){
//                    tv2.setText("沒有圖片");
//                }else{
//                    tv2.setText("已有圖片");
//                }
//                tv2.setPadding(10, 0, 10, 0);
//                tv2.setTextColor(Color.BLACK);
//                tv2.setTextSize(40);
//                tv2.setGravity(Gravity.CENTER);
//                row.addView(tv2);
//                tl_record.addView(row);
//                cursor.moveToNext();
//            }
//        }
//    }

//    public String getData(String combination_text){
//        Cursor cursor = db.rawQuery("SELECT chin_word FROM COMBINATION_TABLE,DETAIL_TABLE WHERE COMBINATION_TABLE.combination = " + combination_text + " AND COMBINATION_TABLE.id" ,null);
//    }



}


