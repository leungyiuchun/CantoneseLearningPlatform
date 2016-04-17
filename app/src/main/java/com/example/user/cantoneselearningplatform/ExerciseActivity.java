package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

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
    public TextView tv1;
    public TextView tv2;
    public TextView tv_currentQuestion;
    public TextView tv_totalQuestion;
    public TextView tv_mode;
    public float speakSpeed;
    public ArrayList<Exer> exerciseList = new ArrayList<Exer>();
    public ArrayList<Answer> AnswerList = new ArrayList<Answer>();
    public ArrayList<ChineseExer> chineseExerList = new ArrayList<ChineseExer>();
    public ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();
    public ArrayList<Bitmap> tmpBitmapArrayList = new ArrayList<Bitmap>();
    ImageView img;
    Button soundButton;
    Button confirmButton;
    LinearLayout ll_exercise1;

    RelativeLayout ll_exercise2;
    RelativeLayout ll_exercise3;
    int[] et1Coord = new int[2];
    int[] et2Coord = new int[2];
    int[] soundbtnCoord = new int[2];
    int[] submitCoord = new int[2];
    TextWatcher textWatcher1;
    TextWatcher textWatcher2;
    ImageView img2;
    Animation animation;
    Animation animation2;
    dataAdapter dataAdapter1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);
        dataAdapter1 = new dataAdapter(this.getApplicationContext());
        try{
            dataAdapter1.createDatabase();
            dataAdapter1.open();
        }catch (SQLException e){
            Log.d("sql error",""+e);
        }

        img2 = (ImageView)findViewById(R.id.imageView2);
        img2.setVisibility(View.INVISIBLE);
        index = 0;
        speakSpeed = ((MyApp)getApplication()).getSpeakSpeed();
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv_currentQuestion = (TextView)findViewById(R.id.tv_currentQuestion);
        tv_totalQuestion = (TextView)findViewById(R.id.tv_totalQuestion);
        tv_mode = (TextView)findViewById(R.id.tv_mode);
        globalTaskInt =  ((MyApp)getApplication()).getTaskInt();
        globalHintInt =  ((MyApp)getApplication()).getHintInt();
        img = (ImageView) findViewById(R.id.imageView);
        ll_exercise1 = (LinearLayout)findViewById(R.id.ll_exercise1);
        ll_exercise2 = (RelativeLayout)findViewById(R.id.ll_exercise2);
        ll_exercise3 = (RelativeLayout)findViewById(R.id.ll_exercise3);

        home_clicked = 0;
        final Button home_button = (Button) this.findViewById(R.id.homeButton);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] abc = new int[2];
                v.getLocationInWindow(abc);
                home_clicked += 1;
                if (home_clicked.intValue() == 3) {
                    AlertDialog dialog = new AlertDialog.Builder(ExerciseActivity.this)
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
                    TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    home_clicked = 0;
                }
            }
        });

        mCustomKeyboard= new CustomKeyboard(ExerciseActivity.this, R.id.keyboardview, R.xml.keyboard );
        mCustomKeyboard.registerEditText(R.id.editText);
        mCustomKeyboard.registerEditText(R.id.editText2);



        tv1.setEnabled(false);
        tv2.setEnabled(false);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
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
                            Intent intent2 = new Intent(ExerciseActivity.this, RemindActivity.class);
                            intent2.putExtra("Arraylist", AnswerList);
                            Log.d("Arraylist",String.valueOf(AnswerList.size()));
                            
                            startActivity(intent2);

                        } else {
                            executeAnimation(img, getApplicationContext(), getRandomNumber(1, 5));
                            ll_exercise1.setBackgroundResource(R.drawable.borders_blue_and_white_big);
                            ll_exercise2.setBackgroundResource(R.drawable.et1_border2);
                            ll_exercise3.setBackgroundResource(R.drawable.et2_border2);
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

                        Intent intent3 = new Intent(ExerciseActivity.this, ResultActivity.class);
                        intent3.putExtra("Answerlist", AnswerList);
                        intent3.putExtra("chineseExerList",chineseExerList);
                        startActivity(intent3);
                    } else {
                        updateExercise();
                    }
                }

            }
        });

        exerciseList = ((MyApp)this.getApplication()).getRandomlizeList();
        translate(exerciseList);
        ans_init = chineseExerList.get(index).getChineseExerInit();
        ans_vowel = chineseExerList.get(index).getChineseExerVowel();
        totalQuestion= ((MyApp)this.getApplication()).getQuantityInt();
        tv_totalQuestion.setText(String.format("%d", totalQuestion));
        tv_mode.setText(((MyApp) getApplication()).getModeString());
        if(((MyApp) getApplication()).getModeInt() == 0){
            et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_init.length())});
            et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_vowel.length())});
        }

        declareTextListener();
        addTextListener();

        soundButton = (Button) findViewById(R.id.soundButton);
        soundButton.setText(chineseExerList.get(index).getChineseExerWord());
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = soundButton.getText().toString();
//                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t1.setSpeechRate(speakSpeed); //larger it is, faster it would be.
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                et1.setVisibility(View.VISIBLE);
                et2.setVisibility(View.VISIBLE);
                setFocus(et1, et2, globalTaskInt);
                Log.d("coord2", "" + soundbtnCoord[0] + soundbtnCoord[1]);
                switch (globalTaskInt) {
                    case 0:
                        break;
                    case 1:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et1Coord[0], et1Coord[1]);
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                initAnimation(et1Coord[0], et1Coord[1], 0, 0);
                            }
                        }, 4000);
                        break;
                    case 2:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et2Coord[0], et2Coord[1]);
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                initAnimation(et2Coord[0], et2Coord[1], 0, 0);
                            }
                        }, 4000);
                        break;
                    case 3:
                        animation(soundbtnCoord[0], soundbtnCoord[1], et1Coord[0], et1Coord[1]);
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                initAnimation(et1Coord[0], et1Coord[1], 0, 0);
                            }
                        }, 4000);
                        break;
                }
                Log.d("img2", "after animation " + img2.getVisibility());

            }
        });


        setTask(et1, et2, globalTaskInt);
        setHints(et1, et2, globalHintInt);
        if(bitmapArrayList.get(currentQuestion)==null){
            img.setImageResource(R.drawable.flower_icon);
            Log.d("bitmapArrayList first","null");
        }else {
            img.setImageBitmap(bitmapArrayList.get(index));
            Log.d("bitmapArrayList first", "not null");

        }
//        ll_exercise2.setBackgroundResource(R.drawable.borders_blue_and_white_big);
//        ll_exercise3.setBackgroundResource(R.drawable.borders_blue_and_white_big);
        Log.d("GetMode",""+((MyApp)getApplication()).getModeString());
        Log.d("GetTask", "" + ((MyApp) getApplication()).getTaskString());
        Log.d("GetHint",""+(((MyApp) getApplication()).getHintsString()));
    }

    @Override
    protected void onResume() {
        Log.d("img2", "on resume before " + img2.getVisibility());
        img2.setVisibility(View.INVISIBLE);
        Log.d("img2", "on resume after " + img2.getVisibility());

        super.onResume();
    }

    @Override public void onBackPressed() {
        home_clicked += 1;
        if (home_clicked.intValue() == 3) {
            AlertDialog dialog = new AlertDialog.Builder(ExerciseActivity.this)
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
            TextView textView = (TextView) dialog.findViewById(android.R.id.message);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            home_clicked = 0;
        }    }

    public boolean checkAnswer(String init, String vowel, String ans_init_check,String ans_vowel_check) {
        Integer textColor_resource=0;
        Integer frameColor_resource=0;
        Integer decision = ((MyApp)getApplication()).getColorIndex();

        switch (decision){
            case 0:
                textColor_resource = getResources().getColor(R.color.myRed);
                frameColor_resource = R.drawable.borders_red_and_white_big;
                break;
            case 1:
                textColor_resource = getResources().getColor(R.color.myGreen);
                frameColor_resource = R.drawable.borders_green_and_white_big;
                break;
            case 2:
                textColor_resource = getResources().getColor(R.color.myOrange);
                frameColor_resource = R.drawable.borders_orange_and_white_big;
                break;
            case 3:
                textColor_resource = getResources().getColor(R.color.myPurple);
                frameColor_resource = R.drawable.borders_purple_and_white_big;
                break;

        }
        if (init.equalsIgnoreCase(ans_init_check)){
            if (vowel.equalsIgnoreCase(ans_vowel_check)){
                et1.setTextColor(Color.BLACK);
                et2.setTextColor(Color.BLACK);
                return true;
            }else {
                et1.setTextColor(Color.BLACK);
                ll_exercise2.setBackgroundResource(R.drawable.et1_border);
                et2.setTextColor(textColor_resource);
                ll_exercise3.setBackgroundResource(frameColor_resource);
                return false;
            }
        }else {
            et2.setTextColor(Color.BLACK);
            ll_exercise3.setBackgroundResource(R.drawable.et2_border);

            et1.setTextColor(textColor_resource);
            ll_exercise2.setBackgroundResource(frameColor_resource);

            if (vowel.equalsIgnoreCase(ans_vowel_check)){
            }else {
                et2.setTextColor(textColor_resource);
                ll_exercise3.setBackgroundResource(frameColor_resource);

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
        Log.d("globalHint"," "+decision.toString());
        switch (decision) {
            case 0:
                tv2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                tv1.setText(ans_init);
                tv2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                tv1.setVisibility(View.INVISIBLE);
                tv2.setText(ans_vowel);
                break;
            case 3:
                tv2.setText(ans_vowel);
                tv1.setText(ans_init);
                break;
        }
    }


    public void updateExercise(){
        currentQuestion +=1;
        index += 1;
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                String toSpeak = soundButton.getText().toString();
////                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
//                t1.setSpeechRate(speakSpeed); //larger it is, faster it would be.
//                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        }, 2000);
        tv_currentQuestion.setText(String.format("%d", currentQuestion));
        ans_init = chineseExerList.get(index).getChineseExerInit();
        ans_vowel = chineseExerList.get(index).getChineseExerVowel();
        soundButton.setText(chineseExerList.get(index).getChineseExerWord());
        removeTextListener();
        et1.getText().clear();
        et1.setTextColor(Color.BLACK);
        et2.getText().clear();
        et2.setTextColor(Color.BLACK);
        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        if(((MyApp) getApplication()).getModeInt() == 0){
            et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_init.length())});
            et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_vowel.length())});
        }
        et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_init.length())});
        et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ans_vowel.length())});

        ll_exercise1.setBackgroundResource(R.drawable.borders_black_and_white_big);
        ll_exercise2.setBackgroundResource(R.drawable.et1_border);
        ll_exercise3.setBackgroundResource(R.drawable.et2_border);

        setHints(et1, et2, globalHintInt);
        setFocus(et1, et2, globalTaskInt);
        setTask(et1, et2, globalTaskInt);
        if(bitmapArrayList.get(index)==null){
            img.setImageResource(R.drawable.flower_icon);
            Log.d("bitmapArraylist", "null");
        }else {
            img.setImageBitmap(bitmapArrayList.get(index));
            Log.d("bitmapArraylist", "not null");


        }

        addTextListener();
        animation(soundbtnCoord[0], soundbtnCoord[1], soundbtnCoord[0], soundbtnCoord[1]);
        initAnimation(soundbtnCoord[0], soundbtnCoord[1], submitCoord[0], submitCoord[1]);

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
        Log.d("coord1", "" + soundbtnCoord[0] + soundbtnCoord[1]);
        animation(soundbtnCoord[0], soundbtnCoord[1], soundbtnCoord[0], soundbtnCoord[1]);
        initAnimation(soundbtnCoord[0], soundbtnCoord[1], submitCoord[0], submitCoord[1]);

        super.onWindowFocusChanged(hasFocus);

    }

    public void animation(int x1,int y1,int x2, int y2) {
        img2.setVisibility(View.VISIBLE);
        animation = new TranslateAnimation(x1,x2, y1, y2);
        animation.setDuration(4000);
        animation.setRepeatCount(0);
        animation.setRepeatMode(1);
        animation2 = new AlphaAnimation(1, 0);
        animation2.setDuration(1000);
        animation2.setRepeatCount(5);
        animation2.setRepeatMode(1);
        animation2.setStartOffset(1000);
        AnimationSet am = new AnimationSet(false);
        am.addAnimation(animation2);
        am.addAnimation(animation);
        img2.setAnimation(am);
        am.startNow();
        img2.startAnimation(animation);
        img2.setVisibility(View.INVISIBLE);

    }
//    public void animation2(){
//        animation2 = new AlphaAnimation(1, 0);
//        animation2.setDuration(1000);
//        animation2.setRepeatCount(5);
//        animation2.setRepeatMode(1);
//        animation2.setStartOffset(4000);
//        img2.startAnimation(animation2);
//        img2.setVisibility(View.INVISIBLE);
//    }
    public void initAnimation(int x1,int y1,int x2, int y2){
        img2.setVisibility(View.VISIBLE);
        Log.d("InitAnimation", "");
        animation = new TranslateAnimation(x1,x1,y1,y1);
        animation.setDuration(1000);
        animation2 = new AlphaAnimation(1, 0);
        animation2.setDuration(1000);
        animation2.setRepeatCount(5);
        animation2.setRepeatMode(1);
        AnimationSet am = new AnimationSet(false);
        am.addAnimation(animation);
        am.addAnimation(animation2);
        img2.setAnimation(am);
        am.startNow();
        img2.setVisibility(View.INVISIBLE);
    }
    public void declareTextListener(){
        final Handler handler = new Handler();
        textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et1.setTextColor(Color.BLACK);
                Log.d("et1 string ", " " + et1.getText().length());
                if(et1.getText().length() == ans_init.length()){
//                    et2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

                Log.d("globalHintInt",""+ globalHintInt.toString());
                    if (globalHintInt.intValue() == 0 ){
                    }else {
                        tv1.setText(floatingHint(et1.getText().length(), et1, ans_init));
                    }
                if(((MyApp)getApplication()).getAnimBoolean()) {
                    switch (globalTaskInt) {
                        case 0:
                            break;
                        case 1:
                            animation(et1Coord[0], et1Coord[1], submitCoord[0], submitCoord[1]);
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    initAnimation(submitCoord[0], submitCoord[1], 0, 0);
                                }
                            }, 4000);
                            break;
                        case 2:
                            break;
                        case 3:
                            animation(et1Coord[0], et1Coord[1], et2Coord[0], et2Coord[1]);
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    initAnimation(et2Coord[0], et2Coord[1], 0, 0);
                                }
                            }, 4000);
                            break;
                    }
                }
            }
        };
        textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et2.setTextColor(Color.BLACK);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (globalHintInt.intValue() !=0){
                    tv2.setText(floatingHint(et2.getText().length(), et2, ans_vowel));


                }
                if(((MyApp)getApplication()).getAnimBoolean()) {

                    animation(et2Coord[0], et2Coord[1], submitCoord[0], submitCoord[1]);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            initAnimation(submitCoord[0], submitCoord[1], 0, 0);
                        }
                    }, 4000);
                }
            }
        };
        Log.d("","textListener set 1");

    }
    public void addTextListener(){
        et1.addTextChangedListener(textWatcher1);
        et2.addTextChangedListener(textWatcher2);
        Log.d("","textListener set");
    }
    public void removeTextListener(){
        et1.removeTextChangedListener(textWatcher1);
        et2.removeTextChangedListener(textWatcher2);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
    public void translate(ArrayList<Exer> exerciseArrayList){
        chineseExerList.clear();
        for (int i =0;i<exerciseArrayList.size();i++){
            Log.d("exerList Size"," "+exerciseArrayList.size());
            ChineseExer chineseExer = new ChineseExer(exerciseArrayList.get(i), tmpAddChinese(exerciseArrayList.get(i).getCardProduct()));
            chineseExerList.add(chineseExer);
            addPicture(chineseExerList.get(i).getChineseExerWord());
        }
    }
    public String tmpAddChinese(String cardProduct){
        String product = cardProduct;
        String chinese;
        Cursor cursor = dataAdapter1.getWordList(product);
        Integer sum = cursor.getCount();

        String [] data = new String[sum];
        if(sum != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < sum; i++) {
                String strCr = cursor.getString(0);
                data[i] = strCr;
                cursor.moveToNext();
            }
        }
        Integer random1;
        if(sum.intValue() ==1){
            random1 = 0;
        }else {
            random1 = getRandomNumber(0,sum-1);
        }
        chinese = data[random1];

//        switch (product){
//            case "baa":
//                chinese = "爸";
//                break;
//            case "maa":
//                chinese="媽";
//                break;
//            case "saa":
//                chinese="沙";
//                break;
//            case "haa":
//                chinese="哈";
//                break;
//            default:
//                chinese="花";
//        }
        return chinese;
    }
    public String floatingHint(Integer textLength,EditText et1Hint,String ansString) {
        String returnString;
        String text = et1Hint.getText().toString();
        String edit = ansString.substring(textLength,ansString.length());
        returnString = text + edit;
        Log.d("returnString ",""+returnString);
        return returnString;
    }
    public void addKey(){

    }
    public void addPicture(String chin_word1){
        tmpBitmapArrayList.clear();
        String chin_word = chin_word1;
        Cursor cursor1 = dataAdapter1.getPicutrByChinWord(chin_word);
        Integer sum = cursor1.getCount();
        Log.d("Add pic Sum", "" + sum.toString());
        if (sum != 0) {
            cursor1.moveToFirst();
            for (int i = 0; i < sum; i++) {
                byte[] picture = cursor1.getBlob(0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                tmpBitmapArrayList.add(bitmap);
                cursor1.moveToNext();
            }
        }
        Integer random = getRandomNumber(0,sum);
        Log.d("Random",""+random);
        if (sum>0){
            if(sum==1){
                bitmapArrayList.add(tmpBitmapArrayList.get(0));
            } else
            if(sum==random) {
                bitmapArrayList.add(tmpBitmapArrayList.get(random-1));
            }else{
                bitmapArrayList.add(tmpBitmapArrayList.get(random));
            }
        }else {
            bitmapArrayList.add(null);
        }
    }
}
