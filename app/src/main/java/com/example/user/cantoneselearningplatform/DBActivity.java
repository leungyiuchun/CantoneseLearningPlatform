package com.example.user.cantoneselearningplatform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DBActivity extends AppCompatActivity {
    Spinner spinnerInit;
    Spinner spinnerVowel;
    TextView combination;
    Button mgt_word;
    Button mgt_picture;
    private String displayInit = "";
    private String displayVowel = "";
    String display;
    String[] init_array = {"b","p","m","f","d","t","n","l","g","j","ng","h","gw","kw","w","z","c","s","j"};
    String[] vowel_array = {"aa","aai","aau","aan","aang","aap","aat","aak","ai","au","am","an","ang","ap","at","ak","e","ei","eng","ek","i","iu","im","in","im","in","ing","ip","it","ik","o","oi","ou","on","ong","ot","ok","oe","oeng","oek","eoi","eon","eot","u","ui","un","ung","ut","uk","yu","yun","yut"};
    ArrayAdapter<String> initList;
    ArrayAdapter<String> vowelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        spinnerInit = (Spinner)findViewById(R.id.init_spinner);
        spinnerVowel = (Spinner)findViewById(R.id.vowel_spinner);
        combination = (TextView)findViewById(R.id.db_combination);
        mgt_picture = (Button)findViewById(R.id.mgt_picture);
        mgt_word = (Button)findViewById(R.id.mgt_word);

        initList = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner,init_array);
        spinnerInit.setAdapter(initList);

        vowelList = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner,vowel_array);
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
