package com.example.user.cantoneselearningplatform;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class SettingActivity extends AppCompatActivity {

    String ssssss[];
    TextView init_tv;
    TextView vowel_tv;
    TextView task_tv;
    TextView hints_tv;
    TextView quantity_tv;
    TextView mode_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init_tv = (TextView)findViewById(R.id.tv2_initial);
        vowel_tv = (TextView)findViewById(R.id.tv2_vowel);
        task_tv = (TextView)findViewById(R.id.tv2_task);
        hints_tv = (TextView)findViewById(R.id.tv2_hints);
        quantity_tv = (TextView)findViewById(R.id.tv2_quantity);
        mode_tv = (TextView)findViewById(R.id.tv2_mode);
        if(((MyApp)getApplication()).getInitList().size()==0){
            init_tv.setText("請選擇聲母");
        }

        if(((MyApp)getApplication()).getVowelList().size()==0){
            vowel_tv.setText("請選擇韻母");
        }
        if(((MyApp)getApplication()).getTaskInt()==0){
            task_tv.setText("請選擇任務");
        }
        if(((MyApp)getApplication()).getHintInt()==0){
            hints_tv.setText("請選擇提示");
        }
        Button pass_button = (Button)this.findViewById(R.id.setting_button);
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final Button init_button = (Button)this.findViewById(R.id.tv_initial);
        init_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment InitialDialog = new InitialFragment();
                InitialDialog.show(getFragmentManager(), "dialog");
//                startActivity(intent);
            }
        });

        final Button vowel_button = (Button)this.findViewById(R.id.tv_vowel);
        vowel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment VowelDialog = new VowelFragment();
                VowelDialog.show(getFragmentManager(), "dialog");
            }
        });

        final Button task_button = (Button)this.findViewById(R.id.tv_task);
        task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Task", "clicked");
                DialogFragment taskDialog = new TaskFragment();
                taskDialog.show(getFragmentManager(), "dialog");
            }
        });

        final Button hints_button = (Button)this.findViewById(R.id.tv_hints);
        hints_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment hintsDialog = new HintsFragment();
                hintsDialog.show(getFragmentManager(), "dialog");

            }
        });
        final Button quantity_button = (Button)this.findViewById(R.id.tv_quantity);
        quantity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment quantityDialog= new QuantityFragment();
                quantityDialog.show(getFragmentManager(), "dialog");
            }
        });
        final Button mode_button = (Button)this.findViewById(R.id.tv_mode);
        mode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment modeDialog= new ModeFragment();
                modeDialog.show(getFragmentManager(), "dialog");
            }
        });
    }

    @Override
    protected void onResume() {
        if(((MyApp)getApplication()).getInitList().size()==0){
            init_tv.setText("請選擇聲母");
        }else {
            init_tv.setText(((MyApp)getApplication()).getInitList().toString());
        }

        if(((MyApp)getApplication()).getVowelList().size()==0){
            vowel_tv.setText("請選擇韻母");
        }else {
            vowel_tv.setText(((MyApp)getApplication()).getVowelList().toString());
        }
//        if(((MyApp)getApplication()).getVowelList().size()==0){
//            task_tv.setText("請選擇任務");
//        }else {
            task_tv.setText(((MyApp)getApplication()).getTaskString());
//        }
//        if(((MyApp)getApplication()).getVowelList().size()==0){
//            hints_tv.setText("請選擇韻母");
//        }else {
            hints_tv.setText(((MyApp)getApplication()).getHintsString());
            quantity_tv.setText(((MyApp) getApplication()).getQuantityInt());
            mode_tv.setText(((MyApp)getApplication()).getModeString());

//        }
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onStop() {
        Log.d("SettingActivity", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("SettingActivity","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("SettingActivity","onRestart");
        super.onRestart();
    }


}
