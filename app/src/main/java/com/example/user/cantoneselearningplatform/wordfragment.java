package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link wordfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link wordfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wordfragment extends Fragment {
    private String title;
    private int page;
    static String syllable;
    dataAdapter dataAdapter1;
    TableLayout tl_word;
    ArrayList<wordRecord> wordRecordArrayList = new ArrayList<wordRecord>();
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
        Log.d("syllable1 on wordFragment", "" + syllable1);

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
        setWordList();

        return view;
    }
    public void setWordList(){
        Cursor cursor = dataAdapter1.getWord_Tone(syllable);
        Integer sum = cursor.getCount();
        wordRecordArrayList.clear();
        tl_word.removeAllViews();

        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TextView tone3= new TextView(getActivity().getApplicationContext());
        tone3.setText("文字");
        tone3.setTextSize(40);
        tone3.setPadding(20, 0, 30, 0);
        tone3.setTextColor(Color.BLACK);

        TextView tone2 = new TextView(getActivity().getApplicationContext());
        tone2.setText("聲調");
        tone2.setTextSize(40);
        tone2.setPadding(100, 0, 30, 0);
        tone2.setTextColor(Color.BLACK);

        row.addView(tone3);
        row.addView(tone2);
        tl_word.addView(row);
        if(sum != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < sum; i++) {
                String strCr = cursor.getString(0);
                Integer tone = cursor.getInt(1);
                wordRecord newRecord = new wordRecord(strCr,tone);
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

        final Button cp = new Button(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(wordRecordArrayList.get(i).getSyllable());
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(40);
        cp.setGravity(Gravity.CENTER);
        row.addView(cp);

        TextView tone= new TextView(getActivity().getApplicationContext());
        tone.setText(wordRecordArrayList.get(i).getTone().toString());
        tone.setTextSize(30);
        tone.setPadding(100, 0, 30, 0);
        tone.setTextColor(Color.BLACK);
        row.addView(tone);
        tl_word.addView(row);
    }

    @Override
    public void onResume() {
        setWordList();
        super.onResume();
    }
}
