package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;


public class wordfragment extends Fragment {
    private String title;
    private int page;
    static String syllable;
    dataAdapter dataAdapter1;
    TableLayout tl_word;
    ArrayList<wordRecord> wordRecordArrayList = new ArrayList<wordRecord>();
    TextView tv_syllable;
    Button btn_pronunce;
    Button btn_submit_syllable;
    EditText add_et1;
    EditText add_et2;
    TextToSpeech t1;
    // newInstance constructor for creating fragment with arguments
    public static wordfragment newInstance(int page, String title,String syllable1) {
        wordfragment fragmentFirst = new wordfragment(syllable1);
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public wordfragment(String syllable1) {
        syllable = syllable1;

    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
//        Log.d("wordFragemnt","Oncreate");
        dataAdapter1 = new dataAdapter(this.getContext());
        try{
            dataAdapter1.createDatabase();
            dataAdapter1.open();
        }catch (SQLException e){
            Log.d("sql error",""+e);
        }

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wordfragment, container, false);
        tl_word = (TableLayout)view.findViewById(R.id.word_tl);

        btn_pronunce = (Button)view.findViewById(R.id.add_syllable_pronunce);
        btn_submit_syllable = (Button)view.findViewById(R.id.btn_add_syllable);
        add_et1 = (EditText)view.findViewById(R.id.add_et1_syllable);
        add_et2 = (EditText)view.findViewById(R.id.add_et2_syllable);
        tv_syllable = (TextView)view.findViewById(R.id.tv_add_syllable);
        t1 = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("zh", "HK"));
                }
            }
        });
        tv_syllable.setText(syllable);
        btn_pronunce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = add_et1.getText().toString();

                t1.setSpeechRate(0.3f); //larger it is, faster it would be.
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        btn_submit_syllable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_et1.getText().toString().trim().length() > 0 && add_et2.getText().toString().trim().length() > 0) {
                    if (dataAdapter1.insertWord(add_et1.getText().toString(), Integer.parseInt(add_et2.getText().toString()), wordRecordArrayList.get(0).getS_id())) {
                        AlertDialog dialog = new AlertDialog.Builder(getContext())
                                .setTitle("成功新增記錄！")
                                .setMessage("成功新增記錄！")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                        add_et1.setText("");
                        add_et2.setText("");
                        setWordList();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(getContext())
                                .setTitle("新增失敗！")
                                .setMessage("新增失敗！")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                    }
                } else {
                    if (add_et1.getText().toString().trim().length() == 0) {
                        add_et1.setHint("必填");
                    }
                    if (add_et2.getText().toString().trim().length() == 0) {
                        add_et2.setHint("必填");
                    }
                }
            }
        });
        setWordList();

        return view;
    }
    public void setWordList(){
        Cursor cursor = dataAdapter1.getWord_Tone(syllable);
        Integer sum = cursor.getCount();
        wordRecordArrayList.clear();
        tl_word.removeAllViews();

//        TableRow row = new TableRow(getActivity().getApplicationContext());
//        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//        TextView tone3= new TextView(getActivity().getApplicationContext());
//        tone3.setText("  ");
//        tone3.setTextSize(40);
//        tone3.setPadding(20, 0, 30, 0);
//        tone3.setTextColor(Color.BLACK);
//
//        TextView tone2 = new TextView(getActivity().getApplicationContext());
//        tone2.setText("  ");
//        tone2.setTextSize(40);
//        tone2.setPadding(100, 0, 30, 0);
//        tone2.setTextColor(Color.BLACK);
//
//        row.addView(tone3);
//        row.addView(tone2);
//        tl_word.addView(row);
        if(sum != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < sum; i++) {
                Integer c_id = cursor.getInt(0);
                String chin_word = cursor.getString(1);
                Integer tone = cursor.getInt(2);
                Integer s_id = cursor.getInt(3);
                wordRecord newRecord = new wordRecord(c_id,chin_word,tone,s_id);
                wordRecordArrayList.add(newRecord);

                setTable(i);
                cursor.moveToNext();
            }
        }
    }
    public void setTable(Integer i){
        final Integer index =i;

        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        final TextView cp = new Button(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(wordRecordArrayList.get(i).getchin_word());
//        cp.setText(i.toString());
        cp.setPadding(20, 0, 30, 0);
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(40);
        cp.setGravity(Gravity.CENTER);
        row.addView(cp);
        Log.d("cp text", "" + cp.getText().toString());


        TextView tone= new TextView(getActivity().getApplicationContext());
        tone.setText(wordRecordArrayList.get(i).getTone().toString());
        tone.setTextSize(30);
        tone.setPadding(100, 0, 30, 0);
        tone.setTextColor(Color.BLACK);
        Log.d("tone", "" + wordRecordArrayList.get(i).getTone().toString());
        row.addView(tone);
        delRecord(i,row,cp);
        tl_word.addView(row);
    }

    @Override
    public void onResume() {
        setWordList();
        super.onResume();
    }

    public void delRecord(Integer index,TableRow row1,TextView cp1){
        final TextView cp = cp1;
        final Integer i = index;
        final TableRow row = row1;
        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cp.setBackgroundResource(R.drawable.borders_black_and_darkblue);
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("刪除記錄？")
                        .setMessage("刪除記錄？")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dataAdapter1.delWord(wordRecordArrayList.get(i).getC_id());
                                setWordList();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cp.setBackgroundResource(R.drawable.borders_black_and_blue);

                            }
                        })
                        .show();
                return false;
            }
        });

    }

    @Override
    public void onDestroy() {
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }
}
