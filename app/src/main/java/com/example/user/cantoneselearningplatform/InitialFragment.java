package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Handler;


public class InitialFragment extends DialogFragment {
    public int array_index =0;
    public String[] display = new String[19];
    //    public Boolean init_1_clicked=false;
    public ArrayList<Initobj> initArray = new ArrayList<Initobj>();
    public ArrayList<String> tmpInitArray = new ArrayList<String>();
    public Boolean found = false;
    public Integer foundIndex =0;
    public String[] passArray;
    TableLayout tl_init;
    ArrayList<String> init= new ArrayList<String>(Arrays.asList("b","p","m","f","d","t","n","l","g","k","ng","h","gw","kw","w","z","c","s","j"));
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("TestDialog","OnCreateDialog");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_initial, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InitialFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MyApp) getActivity().getApplication()).addInitReopenFlag();
                        ((MyApp) getActivity().getApplication()).setInitList(tmpInitArray);
                        ((MyApp) getActivity().getApplication()).setInitFlag(1);
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(0);
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_initial);
                        if (((MyApp) getActivity().getApplication()).getInitList().size() == 0) {
                            tv.setText("請選擇聲母");
                        } else {
                            tv.setText(((MyApp) getActivity().getApplication()).getInitList().toString());
                        }


                    }
                });
        tl_init = (TableLayout)view.findViewById(R.id.tl_init);
        tl_init.setOrientation(LinearLayout.VERTICAL);
        if(((MyApp) getActivity().getApplication()).getInitflag() -1 == 0) {
            initArray = ((MyApp) getActivity().getApplication()).getglobalInitobj();
            tmpInitArray = ((MyApp)getActivity().getApplication()).getInitList();
        }else {
            createInitProduct();
        }
        addView();


        builder.setView(view);
        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alert.getButton(Dialog.BUTTON_POSITIVE);
                btnPositive.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                Button btnNegative = alert.getButton(Dialog.BUTTON_NEGATIVE);
                btnNegative.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            }
        });
        return alert;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TestDialog","OncreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d("TestDialog","OnAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("TestDialog","OnCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        Log.d("TestDialog","OnDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d("TestDialog","OnDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("TestDialog","OnDestroy");
        super.onDestroy();
    }

    public void sendArray(String[] array){
    }
    
//    public Boolean change(Boolean clicked, String msg,Button button){
//        Boolean click_status = clicked;
//
//        if (click_status){
//            button.setBackgroundColor(Color.parseColor("#F7BCBC"));
//            button.setTextColor(Color.parseColor("#5E3434"));
//            ((MyApp)getActivity().getApplication()).delInit(msg);
//            click_status = false;
//
//            return click_status;
//        }else{
//            button.setBackgroundColor(Color.parseColor("#7D5050"));
//            button.setTextColor(Color.parseColor("#FFFFFF"));
//            click_status = true;
//            ((MyApp)getActivity().getApplication()).addInit(msg);
//            return click_status;
//        }
//    }

//    public void show(Boolean clicked,String msg,Button button){
//        btnArray.add(button);
//        if (clicked){
//            button.setBackgroundColor(Color.parseColor("#7D5050"));
//            button.setTextColor(Color.parseColor("#FFFFFF"));
//
//        }else{
//            button.setBackgroundColor(Color.parseColor("#F7BCBC"));
//            button.setTextColor(Color.parseColor("#5E3434"));
//
//        }
//    }

    public void addView(){
        Integer looping =0;
        TableRow row1 = new TableRow(getActivity().getApplicationContext());
        TableRow row2 = new TableRow(getActivity().getApplicationContext());
        TableRow row3 = new TableRow(getActivity().getApplicationContext());
        TableRow row4 = new TableRow(getActivity().getApplicationContext());
        TableRow row5 = new TableRow(getActivity().getApplicationContext());

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row1.setOrientation(LinearLayout.VERTICAL);
        row2.setOrientation(LinearLayout.VERTICAL);
        row3.setOrientation(LinearLayout.VERTICAL);
        row4.setOrientation(LinearLayout.VERTICAL);
        row5.setOrientation(LinearLayout.VERTICAL);
        row1.setHorizontalGravity(1);
        row1.setLayoutParams(lp);
        row2.setLayoutParams(lp);
        row3.setLayoutParams(lp);
        row4.setLayoutParams(lp);
        row5.setLayoutParams(lp);

        View view = new View(getActivity().getApplicationContext());
        view.setBackgroundColor(Color.parseColor("#BCF7F7"));
        view.setMinimumWidth(5);
        for(int i=0;i<4;i++ ){
            final Integer index = i;
            final Button init1 = new Button(getActivity().getApplicationContext());
            show(initArray.get(index), init1);
            init1.setText(initArray.get(i).getButtonString());
            init1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            init1.setAllCaps(false);
            looping +=1;
            init1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(initArray.get(index),init1);
                }
            });
            row1.addView(init1);
        }

        for(int i=4;i<8;i++ ){
            final Integer index = i;
            final Button init1 = new Button(getActivity().getApplicationContext());
            show(initArray.get(index), init1);
            init1.setText(initArray.get(i).getButtonString());
            init1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            init1.setAllCaps(false);
            looping +=1;
            init1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(initArray.get(index),init1);
                }
            });
            row2.addView(init1);
        }

        for(int i=8;i<12;i++ ){
            final Integer index = i;
            final Button init1 = new Button(getActivity().getApplicationContext());
            show(initArray.get(index), init1);
            init1.setText(initArray.get(i).getButtonString());
            init1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            init1.setAllCaps(false);
            looping +=1;
            init1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(initArray.get(index),init1);
                }
            });
            row3.addView(init1);
        }

        for(int i=12;i<15;i++ ){
            final Integer index = i;
            final Button init1 = new Button(getActivity().getApplicationContext());
            show(initArray.get(index), init1);
            init1.setText(initArray.get(i).getButtonString());
            init1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            init1.setAllCaps(false);
            looping +=1;
            init1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(initArray.get(index),init1);
                }
            });
            row4.addView(init1);
        }
        for(int i=15;i<19;i++ ){
            final Integer index = i;
            final Button init1 = new Button(getActivity().getApplicationContext());
            show(initArray.get(index), init1);
            init1.setText(initArray.get(i).getButtonString());
            init1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            init1.setAllCaps(false);
            looping +=1;
            init1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(initArray.get(index),init1);
                }
            });
            row5.addView(init1);
        }

        tl_init.addView(row1);
        tl_init.addView(row2);
        tl_init.addView(row3);
        tl_init.addView(row4);
        tl_init.addView(row5);
    }

    public void add(Initobj initobj,Button btn){
        if(initobj.getBoolean()){
            btn.setBackgroundResource(R.drawable.init_button);
            btn.setTextColor(Color.parseColor("#5E3434"));
            delInit(btn.getText().toString());
            initobj.setBoolean(false);

        }else{
            btn.setBackgroundResource(R.drawable.init_button2);
            btn.setTextColor(Color.parseColor("#ffffff"));
            addInit(btn.getText().toString());
            initobj.setBoolean(true);

        }
    }
    public void show(Initobj initobj,Button btn){
        if(initobj.getBoolean()){
            btn.setBackgroundResource(R.drawable.init_button2);
            btn.setTextColor(Color.parseColor("#ffffff"));

        }else {
            btn.setBackgroundResource(R.drawable.init_button);
            btn.setTextColor(Color.parseColor("#5E3434"));
        }
    }
    public void addInit(String init){
        tmpInitArray.add(init);
    }
    public void delInit(String init){
        tmpInitArray.remove(init);
    }

    public void createInitProduct(){
        for(int i=0;i<init.size();i++){
            Initobj initobj = new Initobj(init.get(i),false);
            initArray.add(initobj);
        }
        ((MyApp)getActivity().getApplication()).setglobalInitobj(initArray);
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}
