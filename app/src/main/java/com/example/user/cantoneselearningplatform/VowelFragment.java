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
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class VowelFragment extends DialogFragment {
    public Boolean found = false;
    public ArrayList<String> tmpVowel =new ArrayList<String>();
    public ArrayList<Vowelobj> vowelArray = new ArrayList<Vowelobj>();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                VowelFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_vowel);
                        ((MyApp) getActivity().getApplication()).addVowelReopenFlag();
                        ((MyApp) getActivity().getApplication()).setVowelList(tmpVowel);
                        ((MyApp) getActivity().getApplication()).setVowelFlag(1);
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(0);
                        if(((MyApp)getActivity().getApplication()).getVowelList().size()==0){
                            tv.setText("請選擇韻母");
                        }else {
                            tv.setText(((MyApp) getActivity().getApplication()).getVowelList().toString());
                        }


                        for(int i=0;i<tmpVowel.size();i++){
                            Log.d("Vowel List",""+((MyApp) getActivity().getApplication()).getVowelList().get(i));
                        }

                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_vowel, null);

        if(((MyApp) getActivity().getApplication()).getVowel_flag() -1 == 0) {
            vowelArray = ((MyApp) getActivity().getApplication()).getGlobalVowelobj();
            tmpVowel= ((MyApp)getActivity().getApplication()).getVowelList();
        }else {
            createProduct();
        }
        addView(view);

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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }



    public void createProduct(){
        Vowelobj vowelobj1 = new Vowelobj(R.id.vowel_1_TextView,false);
        vowelArray.add(vowelobj1);
        Vowelobj vowelobj3 = new Vowelobj(R.id.vowel_3_TextView,false);
        vowelArray.add(vowelobj3);
        Vowelobj vowelobj4 = new Vowelobj(R.id.vowel_4_TextView,false);
        vowelArray.add(vowelobj4);
        Vowelobj vowelobj5 = new Vowelobj(R.id.vowel_5_TextView,false);
        vowelArray.add(vowelobj5);
        Vowelobj vowelobj6 = new Vowelobj(R.id.vowel_6_TextView,false);
        vowelArray.add(vowelobj6);
        Vowelobj vowelobj8 = new Vowelobj(R.id.vowel_8_TextView,false);
        vowelArray.add(vowelobj8);
        Vowelobj vowelobj9 = new Vowelobj(R.id.vowel_9_TextView,false);
        vowelArray.add(vowelobj9);
        Vowelobj vowelobj10 = new Vowelobj(R.id.vowel_10_TextView,false);
        vowelArray.add(vowelobj10);
        Vowelobj vowelobj11 = new Vowelobj(R.id.vowel_11_TextView,false);
        vowelArray.add(vowelobj11);
        Vowelobj vowelobj12 = new Vowelobj(R.id.vowel_12_TextView,false);
        vowelArray.add(vowelobj12);
        Vowelobj vowelobj13 = new Vowelobj(R.id.vowel_13_TextView,false);
        vowelArray.add(vowelobj13);
        Vowelobj vowelobj14 = new Vowelobj(R.id.vowel_14_TextView,false);
        vowelArray.add(vowelobj14);
        Vowelobj vowelobj15 = new Vowelobj(R.id.vowel_15_TextView,false);
        vowelArray.add(vowelobj15);
        Vowelobj vowelobj17 = new Vowelobj(R.id.vowel_17_TextView,false);
        vowelArray.add(vowelobj17);
        Vowelobj vowelobj18 = new Vowelobj(R.id.vowel_18_TextView,false);
        vowelArray.add(vowelobj18);
        Vowelobj vowelobj19 = new Vowelobj(R.id.vowel_19_TextView,false);
        vowelArray.add(vowelobj19);
        Vowelobj vowelobj20 = new Vowelobj(R.id.vowel_20_TextView,false);
        vowelArray.add(vowelobj20);
        Vowelobj vowelobj22 = new Vowelobj(R.id.vowel_22_TextView,false);
        vowelArray.add(vowelobj22);
        Vowelobj vowelobj23 = new Vowelobj(R.id.vowel_23_TextView,false);
        vowelArray.add(vowelobj23);
        Vowelobj vowelobj24 = new Vowelobj(R.id.vowel_24_TextView,false);
        vowelArray.add(vowelobj24);
        Vowelobj vowelobj26 = new Vowelobj(R.id.vowel_26_TextView,false);
        vowelArray.add(vowelobj26);
        Vowelobj vowelobj27 = new Vowelobj(R.id.vowel_27_TextView,false);
        vowelArray.add(vowelobj27);
        Vowelobj vowelobj28 = new Vowelobj(R.id.vowel_28_TextView,false);
        vowelArray.add(vowelobj28);
        Vowelobj vowelobj29 = new Vowelobj(R.id.vowel_29_TextView,false);
        vowelArray.add(vowelobj29);
        Vowelobj vowelobj30 = new Vowelobj(R.id.vowel_30_TextView,false);
        vowelArray.add(vowelobj30);
        Vowelobj vowelobj31 = new Vowelobj(R.id.vowel_31_TextView,false);
        vowelArray.add(vowelobj31);
        Vowelobj vowelobj32 = new Vowelobj(R.id.vowel_32_TextView,false);
        vowelArray.add(vowelobj32);
        Vowelobj vowelobj34 = new Vowelobj(R.id.vowel_34_TextView,false);
        vowelArray.add(vowelobj34);
        Vowelobj vowelobj35 = new Vowelobj(R.id.vowel_35_TextView,false);
        vowelArray.add(vowelobj35);
        Vowelobj vowelobj36 = new Vowelobj(R.id.vowel_36_TextView,false);
        vowelArray.add(vowelobj36);
        Vowelobj vowelobj37 = new Vowelobj(R.id.vowel_37_TextView,false);
        vowelArray.add(vowelobj37);
        Vowelobj vowelobj38 = new Vowelobj(R.id.vowel_38_TextView,false);
        vowelArray.add(vowelobj38);
        Vowelobj vowelobj39 = new Vowelobj(R.id.vowel_39_TextView,false);
        vowelArray.add(vowelobj39);
        Vowelobj vowelobj40 = new Vowelobj(R.id.vowel_40_TextView,false);
        vowelArray.add(vowelobj40);
        Vowelobj vowelobj42 = new Vowelobj(R.id.vowel_42_TextView,false);
        vowelArray.add(vowelobj42);
        Vowelobj vowelobj43 = new Vowelobj(R.id.vowel_43_TextView,false);
        vowelArray.add(vowelobj43);
        Vowelobj vowelobj44 = new Vowelobj(R.id.vowel_44_TextView,false);
        vowelArray.add(vowelobj44);
        Vowelobj vowelobj46 = new Vowelobj(R.id.vowel_46_TextView,false);
        vowelArray.add(vowelobj46);
        Vowelobj vowelobj47 = new Vowelobj(R.id.vowel_47_TextView,false);
        vowelArray.add(vowelobj47);
        Vowelobj vowelobj48 = new Vowelobj(R.id.vowel_48_TextView,false);
        vowelArray.add(vowelobj48);
        Vowelobj vowelobj49 = new Vowelobj(R.id.vowel_49_TextView,false);
        vowelArray.add(vowelobj49);
        Vowelobj vowelobj50 = new Vowelobj(R.id.vowel_50_TextView,false);
        vowelArray.add(vowelobj50);
        Vowelobj vowelobj51 = new Vowelobj(R.id.vowel_51_TextView,false);
        vowelArray.add(vowelobj51);
        Vowelobj vowelobj52 = new Vowelobj(R.id.vowel_52_TextView,false);
        vowelArray.add(vowelobj52);
        Vowelobj vowelobj54 = new Vowelobj(R.id.vowel_54_TextView,false);
        vowelArray.add(vowelobj54);
        Vowelobj vowelobj55 = new Vowelobj(R.id.vowel_55_TextView,false);
        vowelArray.add(vowelobj55);
        Vowelobj vowelobj56 = new Vowelobj(R.id.vowel_56_TextView,false);
        vowelArray.add(vowelobj56);
        Vowelobj vowelobj57 = new Vowelobj(R.id.vowel_57_TextView,false);
        vowelArray.add(vowelobj57);
        Vowelobj vowelobj58 = new Vowelobj(R.id.vowel_58_TextView,false);
        vowelArray.add(vowelobj58);
        Vowelobj vowelobj59 = new Vowelobj(R.id.vowel_59_TextView,false);
        vowelArray.add(vowelobj59);
        Vowelobj vowelobj60 = new Vowelobj(R.id.vowel_60_TextView,false);
        vowelArray.add(vowelobj60);
        Vowelobj vowelobj62 = new Vowelobj(R.id.vowel_62_TextView,false);
        vowelArray.add(vowelobj62);
        Vowelobj vowelobj63 = new Vowelobj(R.id.vowel_63_TextView,false);
        vowelArray.add(vowelobj63);
        Vowelobj vowelobj64 = new Vowelobj(R.id.vowel_64_TextView,false);
        vowelArray.add(vowelobj64);

        ((MyApp)getActivity().getApplication()).setGlobalVowelobj(vowelArray);

    }
    public void addVowel(String vowel){
        tmpVowel.add(vowel);
    }
    public void delVowel(String vowel){
        tmpVowel.remove(vowel);
    }
    public void add(Vowelobj vowelobj,TextView btn){
        if(vowelobj.getBoolean()){
            btn.setBackgroundResource(R.drawable.borders_black_and_blue);
            btn.setTextColor(Color.parseColor("#49345E"));
            delVowel(btn.getText().toString());
            vowelobj.setBoolean(false);

        }else{
            btn.setBackgroundResource(R.drawable.borders_black_and_darkblue);
            btn.setTextColor(Color.parseColor("#ffffff"));
            addVowel(btn.getText().toString());
            vowelobj.setBoolean(true);

        }
    }
    public void show(Vowelobj vowelobj,TextView btn){
        if(vowelobj.getBoolean()){
            btn.setBackgroundResource(R.drawable.borders_black_and_darkblue);
            btn.setTextColor(Color.parseColor("#ffffff"));

        }else {
            btn.setBackgroundResource(R.drawable.borders_black_and_blue);
            btn.setTextColor(Color.parseColor("#49345E"));
        }
    }
    public void addView(View view){
        for(Integer i = 0;i<vowelArray.size();i++){
            final Integer index = i;
            final TextView vowel = (TextView)view.findViewById(vowelArray.get(i).getId());
            show(vowelArray.get(i), vowel);
            vowel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(vowelArray.get(index), vowel);
                    Log.d("aa","a");
                }
            });
        }
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
