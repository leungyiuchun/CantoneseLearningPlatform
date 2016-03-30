package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class picturefragment extends Fragment implements DialogInterface.OnDismissListener{
    public static Integer globalc_id;
    private String title;
    private int page;
    static String syllable;
    TextView tv_add_picture;
    public static TableLayout tl_picture;
    public static GridView gv_picture;
    Context context1;
    public static dataAdapter dataAdapter1;
    ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();
    ArrayList<wordRecord> wordRecordArrayList = new ArrayList<wordRecord>();
    public ArrayList<pictureRecord> pictureRecordArrayList = new ArrayList<pictureRecord>();
    GridViewAdapter customGridAdapter;
    List<Map<String, Object>> items;
    // newInstance constructor for creating fragment with arguments
    public static picturefragment newInstance(int page, String title,String syllable1) {
        picturefragment fragmentFirst = new picturefragment(syllable1);
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public picturefragment(String syllable1) {
        syllable = syllable1;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
        dataAdapter1 = new dataAdapter(this.getContext());
        try{
            dataAdapter1.createDatabase();
            dataAdapter1.open();
        }catch (SQLException e){
            Log.d("sql error", "" + e);
        }
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picturefragment, container, false);
        tv_add_picture = (TextView)view.findViewById(R.id.tv_add_picture);
        tl_picture = (TableLayout)view.findViewById(R.id.pic_word_tl);

        gv_picture = (GridView)view.findViewById(R.id.gv_pic);
        gv_picture.setNumColumns(2);
        tv_add_picture.setText(syllable);
        setWordList();


        return view;
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
//        Log.d("",""+this.getContext().toString());
        Log.d("addPictureFragment", "receive");
//        Log.d("onDismiss",""+getGlobalc_id().toString());
//        Log.d("onDismiss",""+dataAdapter1.toString());
//        ArrayList<Bitmap> bitmapArrayList3 = new ArrayList<Bitmap>();
//        Cursor cursor2 = dataAdapter1.getPicture(getGlobalc_id());
//        Integer sum = cursor2.getCount();
//        Log.d("Sum", "" + sum.toString());
//        if (sum != 0) {
//            cursor2.moveToFirst();
//            for (int i = 0; i < sum; i++) {
//                Integer p_id = cursor2.getInt(0);
//                Integer c_id = cursor2.getInt(1);
//                byte[] picture = cursor2.getBlob(2);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//                bitmapArrayList3.add(bitmap);
//                Timestamp ts = Timestamp.valueOf(cursor2.getString(3));
//                pictureRecord newRecord = new pictureRecord(p_id, c_id, picture, ts);
//                pictureRecordArrayList.add(newRecord);
//                cursor2.moveToNext();
//            }
//        }

        Log.d("context",""+getActivity().toString());
    }

    public void setWordList(){
        Log.d("setWordList","called");
        Cursor cursor = dataAdapter1.getWord_Tone(syllable);
        Integer sum = cursor.getCount();
        wordRecordArrayList.clear();
        tl_picture.removeAllViews();
        tl_picture.requestFocus();
        TableRow row = new TableRow(getActivity());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TextView tone3= new TextView(getActivity().getApplicationContext());
        tone3.setText("文字");
        tone3.setTextSize(40);
        tone3.setPadding(20, 0, 30, 0);
        tone3.setTextColor(Color.BLACK);

        TextView tone2 = new TextView(getActivity());
        tone2.setText("聲調");
        tone2.setTextSize(40);
        tone2.setPadding(100, 0, 30, 0);
        tone2.setTextColor(Color.BLACK);


        row.addView(tone3);
        row.addView(tone2);
        tl_picture.addView(row);
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
        Log.d("setTable","called");

        final Integer index =i;

        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        final TextView cp = new TextView(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(wordRecordArrayList.get(i).getchin_word());
//        cp.setText(i.toString());
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(40);
        cp.setGravity(Gravity.CENTER);
        row.addView(cp);


        TextView tone= new TextView(getActivity().getApplicationContext());
        tone.setText(wordRecordArrayList.get(i).getTone().toString());
        tone.setTextSize(30);
        tone.setTextColor(Color.BLACK);
        row.addView(tone);
        tone.setPadding(100, 0, 30, 0);

        TextView addPic = new Button(getActivity().getApplicationContext());
        addPic.setBackgroundResource(R.color.myPurple);
        addPic.setText("新增圖片");
        addPic.setTextSize(30);
        addPic.setVisibility(View.INVISIBLE);
        row.addView(addPic);
        setListener(row, cp, wordRecordArrayList.get(i).getC_id(), addPic);
        tl_picture.addView(row);
    }
    public void setListener(TableRow row1, TextView cp1, final Integer c_id1,TextView addPic1){
        final TableRow row = row1;
        final TextView cp = cp1;
        final Integer c_id = c_id1;
        final TextView addPic = addPic1;
        row.setFocusableInTouchMode(true);
        row.setFocusable(true);
        row.setClickable(true);
        row.requestFocus();

        row.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pictureRecordArrayList.clear();
                bitmapArrayList.clear();
                globalc_id = c_id1;
                setGlobalc_id(c_id1);
                Log.d("", "" + globalc_id.toString());
                Cursor cursor1 = dataAdapter1.getPicture(c_id1);
                Integer sum = cursor1.getCount();
                Log.d("Sum", "" + sum.toString());
                if (sum != 0) {
                    cursor1.moveToFirst();
                    for (int i = 0; i < sum; i++) {
                        Integer p_id = cursor1.getInt(0);
                        Integer c_id = cursor1.getInt(1);
                        byte[] picture = cursor1.getBlob(2);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                        bitmapArrayList.add(bitmap);
                        Timestamp ts = Timestamp.valueOf(cursor1.getString(3));
                        pictureRecord newRecord = new pictureRecord(p_id, c_id, picture, ts);
                        pictureRecordArrayList.add(newRecord);
                        cursor1.moveToNext();
                    }
                }
                customGridAdapter = new GridViewAdapter(getContext(), R.layout.grid_item, bitmapArrayList);
                setContext1(getContext());
                gv_picture.setAdapter(customGridAdapter);
                gv_picture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog dialog = new AlertDialog.Builder(getContext())
                                .setTitle("刪除記錄？")
                                .setMessage("刪除記錄？")
                                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ArrayList<Bitmap> bitmapArrayList2 = new ArrayList<Bitmap>();
                                        dataAdapter1.delPicture(pictureRecordArrayList.get(position).getP_id());
                                        Cursor cursor1 = dataAdapter1.getPicture(c_id1);
                                        setGlobalc_id(c_id);
                                        Integer sum = cursor1.getCount();
                                        Log.d("Sum", "" + sum.toString());
                                        if (sum != 0) {
                                            cursor1.moveToFirst();
                                            for (int i = 0; i < sum; i++) {
                                                Integer p_id = cursor1.getInt(0);
                                                Integer c_id = cursor1.getInt(1);
                                                byte[] picture = cursor1.getBlob(2);
                                                Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                                                bitmapArrayList2.add(bitmap);
                                                Timestamp ts = Timestamp.valueOf(cursor1.getString(3));
                                                pictureRecord newRecord = new pictureRecord(p_id, c_id, picture, ts);
                                                pictureRecordArrayList.add(newRecord);
                                                cursor1.moveToNext();
                                            }
                                        }
                                        gv_picture.setAdapter(null);
                                        GridViewAdapter customGridAdapter2 = new GridViewAdapter(getContext(), R.layout.grid_item, bitmapArrayList2);
                                        gv_picture.setAdapter(customGridAdapter2);
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }
                });
                cp.setBackgroundResource(R.drawable.borders_black_and_darkblue);
                addPic.setVisibility(View.VISIBLE);
                addPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        globalc_id = c_id;
                        Log.d("globalc_id", "" + globalc_id.toString());
                        addPictureFragment addPictureFragment1 = new addPictureFragment(c_id, syllable, dataAdapter1);
                        addPictureFragment1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                ArrayList<Bitmap> bitmapArrayList3 = new ArrayList<Bitmap>();
                                Cursor cursor2 = dataAdapter1.getPicture(getGlobalc_id());
                                Integer sum = cursor2.getCount();
                                Log.d("Sum", "" + sum.toString());
                                if (sum != 0) {
                                    cursor2.moveToFirst();
                                    for (int i = 0; i < sum; i++) {
                                        Integer p_id = cursor2.getInt(0);
                                        Integer c_id = cursor2.getInt(1);
                                        byte[] picture = cursor2.getBlob(2);
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                                        bitmapArrayList3.add(bitmap);
                                        Timestamp ts = Timestamp.valueOf(cursor2.getString(3));
                                        pictureRecord newRecord = new pictureRecord(p_id, c_id, picture, ts);
                                        pictureRecordArrayList.add(newRecord);
                                        cursor2.moveToNext();
                                    }
                                }
                                gv_picture.setAdapter(null);
                                GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),R.layout.grid_item,bitmapArrayList3);
                                gv_picture.setAdapter(gridViewAdapter);
                            }

                        });
                        FragmentManager fm = getActivity().getFragmentManager();
                        addPictureFragment1.show(fm, "Dialog");

                    }
                });
                row.requestFocus();
                return false;
            }
        });
        row.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                v.requestFocus();
                addPic.setVisibility(View.INVISIBLE);
                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
            }
        });

    }



    public void setGlobalc_id(Integer c_id1){
        this.globalc_id = c_id1;
        Log.d("setClass", "" + globalc_id.toString());

    }
    public Integer getGlobalc_id(){
        Log.d("getClass",""+globalc_id.toString());
        return this.globalc_id;
    }
    public void setContext1(Context context){
        context1 = context;
    }
    public Context getContext1(){
        return context1;
    }

    @Override
    public void onStart() {
        Log.d("onstart","wuhoo");
        super.onStart();
    }

    private Callbacks mCallbacks;


    public interface Callbacks {
        //Callback for when button clicked.
        public void onButtonClicked();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Activities containing this fragment must implement its callbacks
        mCallbacks = (Callbacks) activity;

    }

}
