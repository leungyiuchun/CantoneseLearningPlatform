package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.zip.Inflater;

public class ExerciseActivity extends Activity{

    TextToSpeech t1;
    Integer index;
    String initial;
    String vowel;
    String ans_init;
    String ans_vowel;
    String userAnswer;
    public Integer totalQuestion;
    public Integer currentQuestion =1;
    CustomKeyboard mCustomKeyboard;
    String[] globalInitArray = {" "};
    String[] globalVowelArray ={" "};
    Integer home_clicked;
    public Integer globalTaskInt = 1;
    public Integer globalHintInt = 1;
    public EditText et1;
    public EditText et2;
    public TextView tv_currentQuestion;
    public TextView tv_totalQuestion;
    public TextView tv_mode;
    public ArrayList<Exer> exerciseList = new ArrayList<Exer>();
    public ArrayList<Answer> AnswerList = new ArrayList<Answer>();
    Button soundButton;
    Button confirmButton;
    LinearLayout ll_exercise1;
    LinearLayout ll_exercise2;
    LinearLayout ll_exercise3;
    int[] et1Coord = new int[2];
    int[] et2Coord = new int[2];
    int[] soundbtnCoord = new int[2];
    int[] submitCoord = new int[2];
    TextWatcher textWatcher1;
    TextWatcher textWatcher2;
    ImageView img2;
    Animation animation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img2.setVisibility(View.INVISIBLE);
        index = 0;
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        tv_currentQuestion = (TextView)findViewById(R.id.tv_currentQuestion);
        tv_totalQuestion = (TextView)findViewById(R.id.tv_totalQuestion);
        tv_mode = (TextView)findViewById(R.id.tv_mode);
        globalTaskInt =  ((MyApp)getApplication()).getTaskInt();
        globalHintInt =  ((MyApp)getApplication()).getHintInt();
        Log.d("task", "" + globalTaskInt.toString());
        final ImageView img = (ImageView) findViewById(R.id.imageView);
//        img2.setImageAlpha(1);
        ll_exercise1 = (LinearLayout)findViewById(R.id.ll_exercise1);
        ll_exercise2 = (LinearLayout)findViewById(R.id.ll_exercise2);
        ll_exercise3 = (LinearLayout)findViewById(R.id.ll_exercise3);
        home_clicked = 0;
        final Button home_button = (Button) this.findViewById(R.id.homeButton);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] abc = new int[2];
                v.getLocationInWindow(abc);
                home_clicked += 1;
                if (home_clicked.intValue() == 3) {
                    new AlertDialog.Builder(ExerciseActivity.this)
                            .setTitle("確定離開")
                            .setMessage("確定離開？")
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ExerciseActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                    home_clicked = 0;
                }
            }
        });


        mCustomKeyboard= new CustomKeyboard(ExerciseActivity.this, R.id.keyboardview, R.xml.keyboard );
        mCustomKeyboard.registerEditText(R.id.editText);
        mCustomKeyboard.registerEditText(R.id.editText2);

        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        declareTextListener();
        addTextListener();
        tv_currentQuestion.setText(String.format("%d", currentQuestion));
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("zh", "HK"));
                }
            }
        });

        confirmButton = (Button) findViewById(R.id.confirmButton);
        final Handler handler = new Handler();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial = et1.getText().toString();
                vowel = et2.getText().toString();
                userAnswer = initial + vowel;
                if (((MyApp) getApplication()).getModeInt() == 0) {
                    if (checkAnswer(initial, vowel, ans_init, ans_vowel)) {
                        Answer ans = new Answer(initial, vowel, userAnswer, soundButton.getText().toString());
                        AnswerList.add(ans);
                        if (currentQuestion.intValue() == totalQuestion.intValue()) {
                            DialogFragment remindDialog = new RemindFragment(AnswerList);
                            remindDialog.show(getFragmentManager(), "dialog");
                        } else {
                            executeAnimation(img, getApplicationContext(), getRandomNumber(1, 5));
                            ll_exercise1.setBackgroundResource(R.drawable.borders_blue_and_white_big);
                            ll_exercise2.setBackgroundResource(R.drawable.borders_blue_and_white_big);
                            ll_exercise3.setBackgroundResource(R.drawable.borders_blue_and_white_big);
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    updateExercise();
                                }
                            }, 2000);
                        }
                    }
                } else {
                    Answer ans = new Answer(initial, vowel, userAnswer, soundButton.getText().toString());
                    AnswerList.add(ans);

                    if (currentQuestion.intValue() == totalQuestion.intValue()) {
                        DialogFragment resultDialog = new ResultFragment(AnswerList, exerciseList);
                        resultDialog.show(getFragmentManager(), "dialog");
                    } else {
                        updateExercise();
                    }
                }

            }
        });

        soundButton = (Button) findViewById(R.id.soundButton);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = soundButton.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t1.setSpeechRate(0.3f); //larger it is, faster it would be.
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                et1.setVisibility(View.VISIBLE);
                et2.setVisibility(View.VISIBLE);
                setFocus(et1, et2, globalTaskInt);
                Log.d("img2","before animation "+img2.getVisibility());
                switch (globalTaskInt) {
                    case 0:
                        break;
                    case 1:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et1Coord[0], et1Coord[1]);
                        break;
                    case 2:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et2Coord[0], et2Coord[1]);
                        break;
                    case 3:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et1Coord[0], et1Coord[1]);
                        break;
                }
                Log.d("img2","after animation "+img2.getVisibility());

            }
        });

        exerciseList = ((MyApp)this.getApplication()).getRandomlizeList();
        for(int i =0;i<exerciseList.size();i++){
        Log.d("random_list",""+exerciseList.get(i).getCardProduct());}
        ans_init = exerciseList.get(index).getInit();
        ans_vowel = exerciseList.get(index).getVowel();
        totalQuestion= ((MyApp)this.getApplication()).getQuantityInt();
        tv_totalQuestion.setText(String.format("%d", totalQuestion));
        tv_mode.setText(((MyApp) getApplication()).getModeString());

        setTask(et1, et2, globalTaskInt);
        setHints(et1, et2, globalHintInt);

    }

    @Override
    protected void onResume() {
        Log.d("img2","on resume before "+img2.getVisibility());
        img2.setVisibility(View.GONE);
        Log.d("img2", "on resume after " + img2.getVisibility());

        super.onResume();
    }

    @Override public void onBackPressed() {
//        if( mCustomKeyboard.isCustomKeyboardVisible() ) mCustomKeyboard.hideCustomKeyboard(); else this.finish();
    }

    public boolean checkAnswer(String init, String vowel, String ans_init_check,String ans_vowel_check) {

        if (init.equalsIgnoreCase(ans_init_check)){
            if (vowel.equalsIgnoreCase(ans_vowel_check)){
                et1.setTextColor(Color.BLACK);
                et2.setTextColor(Color.BLACK);
                return true;
            }else {
                et1.setTextColor(Color.BLACK);
                et2.setTextColor(Color.RED);
                return false;
            }
        }else {
            et1.setTextColor(Color.RED);
            et2.setTextColor(Color.BLACK);
            if (vowel.equalsIgnoreCase(ans_vowel_check)){
            }else {
                et2.setTextColor(Color.RED);
            }
            return false;
        }
    }
    public void setTask(EditText et1,EditText et2, Integer decision){
        switch (decision) {
            case 0:
                //do nothing
                break;
            case 1:
                et2.setText(ans_vowel);
                et2.setEnabled(false);
                et2.setKeyListener(null);
                break;
            case 2:
                et1.setText(ans_init);
                et1.setEnabled(false);
                et1.setKeyListener(null);
                break;
            case 3:
                //do nothing as both init and vowel are needed to be filled.
                break;
        }
    }
    public void setFocus(EditText et1,EditText et2, Integer decision){
        switch (decision) {
            case 0:
                break;
            case 1:
                et1.requestFocus();
                break;
            case 2:
                et2.requestFocus();
                break;
            case 3:
                et1.requestFocus();
                break;
        }
    }
    public void setHints(EditText et1,EditText et2, Integer decision) {
        switch (decision) {
            case 0:
                //do nothing
                break;
            case 1:
                et1.setHint(ans_init);
                break;
            case 2:
                et2.setHint(ans_vowel);
                break;
            case 3:
                et2.setHint(ans_vowel);
                et1.setHint(ans_init);
                break;
        }
    }


    public void updateExercise(){
        currentQuestion +=1;
        index += 1;
        tv_currentQuestion.setText(String.format("%d", currentQuestion));
        ans_init = exerciseList.get(index).getInit();
        ans_vowel = exerciseList.get(index).getVowel();
        et1.getText().clear();
        et1.setTextColor(Color.BLACK);
        et2.getText().clear();
        et2.setTextColor(Color.BLACK);
        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        ll_exercise1.setBackgroundResource(R.drawable.borders_black_and_white_big);
        ll_exercise2.setBackgroundResource(R.drawable.borders_black_and_white_big);
        ll_exercise3.setBackgroundResource(R.drawable.borders_black_and_white_big);
        setHints(et1, et2, globalHintInt);
        setFocus(et1, et2, globalTaskInt);
        setTask(et1, et2, globalTaskInt);
        addTextListener();
    }

    public void executeAnimation(ImageView image,Context context1,Integer int1){
        ImageView img = image;
        Context context = context1;
        Integer decision =int1;
        switch (decision){
            case 1:
                Animation animation = AnimationUtils.loadAnimation(context,R.anim.anticlockwise);
                img.startAnimation(animation);
                break;
            case 2:
                Animation animation2 = AnimationUtils.loadAnimation(context,R.anim.clockwise);
                img.startAnimation(animation2);
                break;
            case 3:
                Animation animation3 = AnimationUtils.loadAnimation(context,R.anim.blink);
                img.startAnimation(animation3);
                break;
            case 4:
                Animation animation4 = AnimationUtils.loadAnimation(context,R.anim.zoomin);
                img.startAnimation(animation4);
                break;
            case 5:
                Animation animation5 = AnimationUtils.loadAnimation(context, R.anim.zoomout);
                img.startAnimation(animation5);
                break;
        }
    }
    public Integer getRandomNumber(Integer min, Integer max){
        Random random = new Random();
        Integer randomNumber = random.nextInt((max-min)+1)+min;
        return randomNumber;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        et1.getLocationOnScreen(et1Coord);
        et2.getLocationOnScreen(et2Coord);
        soundButton.getLocationOnScreen(soundbtnCoord);
        confirmButton.getLocationOnScreen(submitCoord);
        super.onWindowFocusChanged(hasFocus);

    }

    public void animation(int x1,int y1,int x2,int y2){
        img2.setVisibility(View.VISIBLE);
        animation = new TranslateAnimation(x1,x2,y1,y2);
        animation.setDuration(1000);
        animation.setRepeatCount(0);
        animation.setRepeatMode(1);
        img2.startAnimation(animation);
        img2.setVisibility(View.GONE);

    }
    public void declareTextListener(){
        textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (globalTaskInt) {
                    case 0:
                        break;
                    case 1:
                        animation(et1Coord[0], et1Coord[1], submitCoord[0], submitCoord[1]);
                        break;
                    case 2:
                        break;
                    case 3:
                        animation(et1Coord[0], et1Coord[1], et2Coord[0], et2Coord[1]);
                        break;
                }
            }
        };
        textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                animation(et2Coord[0], et2Coord[1], submitCoord[0], submitCoord[1]);
            }
        };
    }
    public void addTextListener(){
        et1.addTextChangedListener(textWatcher1);
        et2.addTextChangedListener(textWatcher2);
    }
    public void removeTextListener(){
        et1.removeTextChangedListener(textWatcher1);
        et2.removeTextChangedListener(textWatcher2);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}
