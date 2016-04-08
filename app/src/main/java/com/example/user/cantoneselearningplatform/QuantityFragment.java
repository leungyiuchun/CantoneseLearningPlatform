package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;



public class QuantityFragment extends DialogFragment{
    Integer init_number;
    Integer vowel_number;
    Integer total_number;
    Integer looping;
    ArrayList<Integer> number_array = new ArrayList<Integer>();
    ArrayList<String> not_available_array = new ArrayList<String>();
    //    Spinner mSpinner;
    Integer rowLooping=0;
    Integer loop=0;
    public ArrayList<Combination> final_List = new ArrayList<Combination>();
    public ArrayList<Exer> ExersList = new ArrayList<Exer>();
    TableLayout quantity_tl;
    TextView total_quantity_tv;
    TextView total_combination_tv;
    Integer total_combination;
//    Button button_confirm;
    String init;
    String vowel;
    Integer status =0;
    Switch switch1;
    LinearLayout ll_nilRecord1;
    LinearLayout ll_nilRecord2;
    AlertDialog.Builder builder;
    dataAdapter dataAdapter1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_quantity, null);
        quantity_tl = (TableLayout)view.findViewById(R.id.quantity_tl);
//        button_confirm = (Button)view.findViewById(R.id.number_confirm);
        total_quantity_tv = (TextView)view.findViewById(R.id.tv_totalQuantity);
        total_combination_tv = (TextView)view.findViewById(R.id.tv_totalCombination);
        ll_nilRecord1 = (LinearLayout)view.findViewById(R.id.ll_nilRecord);
        ll_nilRecord2 = (LinearLayout)view.findViewById(R.id.ll_nilRecord2);
        switch1 = (Switch)view.findViewById(R.id.quantity_switch1);
        final TextView tv = (TextView) getActivity().findViewById(R.id.tv2_quantity);
        init_number = ((MyApp)getActivity().getApplication()).getInitList().size();
        vowel_number = ((MyApp)getActivity().getApplication()).getVowelList().size();
        dataAdapter1 = new dataAdapter(this.getActivity());
        try{
            dataAdapter1.createDatabase();
            dataAdapter1.open();
        }catch (SQLException e){
            Log.d("sql error",""+e);
        }
        if(((MyApp) getActivity().getApplication()).getQuantityFlag() -1 == 0) {
            final_List = ((MyApp) getActivity().getApplication()).getCombinationList();
            not_available_array = ((MyApp)getActivity().getApplication()).getNilRecordList();
        }else {
            createCartesianProduct(init_number, vowel_number);
        }
        if(not_available_array.size()!=0){
            setNilRecord();
        }else {
            ll_nilRecord1.setVisibility(View.GONE);
        }
        total_number = init_number*vowel_number;

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuantityFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MyApp) getActivity().getApplication()).setQuantityInt(getTotalQuantity());
                        Log.d("Total Quantity", "" + getTotalQuantity().toString());
                        ((MyApp) getActivity().getApplication()).setCombinationList(final_List);
                        ((MyApp) getActivity().getApplication()).setInitReopenFlag(1);
                        ((MyApp) getActivity().getApplication()).setVowelReopenFlag(1);
                        ((MyApp) getActivity().getApplication()).setNilRecordList(not_available_array);
                        tv.setText((getTotalQuantity().toString()));
                        for (Integer i = 0; i < final_List.size(); i++) {
                            Log.d("final_list_loop", "after " + final_List.get(i).getCartProduct());
                        }
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(1);
                    }
                });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                quantity_tl.removeAllViews();
                if (isChecked) {
//                    button_confirm.setVisibility(View.VISIBLE);
                    for(Integer j=0;j<final_List.size();j++){
                        addRowItem(j);
                    }
                } else {
//                    button_confirm.setVisibility(View.INVISIBLE);
                    for(Integer j=0;j<final_List.size();j++){
                        addRowItem2(j);
                    }
                }
            }
        });

        total_quantity_tv.setText(getTotalQuantity().toString());
        total_combination_tv.setText(getTotalCombination().toString());
//        button_confirm.setVisibility(View.INVISIBLE);
        for(Integer j=0;j<final_List.size();j++){
            addRowItem2(j);
        }
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public ArrayList<Integer> getTotalNumberArray(Integer totalNumber){
        for (int i =0; i<totalNumber;i++ ) {
            number_array.add(i+1);
        }

        return number_array;
    }

    public void createCartesianProduct(int init_number,int vowel_number){
        String tmp;
        for (int i=0;i<init_number ;i++ ) {
            for (int j=0;j<vowel_number ;j++ ) {
                tmp = ((MyApp)getActivity().getApplication()).getInitList().get(i).toString()+ ((MyApp)getActivity().getApplication()).getVowelList().get(j).toString();
                Cursor cursor = dataAdapter1.getWordList(tmp);
                Integer sum = cursor.getCount();
                if (sum!=0){
                    ExersList.add(new Exer(((MyApp)getActivity().getApplication()).getInitList().get(i).toString(), ((MyApp)getActivity().getApplication()).getVowelList().get(j).toString(),tmp));
                }else {
                    not_available_array.add(tmp);
                }
            }
        }
        for(int i=0;i<ExersList.size();i++){
            final_List.add(new Combination(ExersList.get(i),3,false));
        }
    }


    public void addRowItem(Integer i){
        final Integer index =i;
        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        row.setFocusable(true);
        row.setFocusableInTouchMode(true);
        final Button cp = new Button(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(final_List.get(i).getCartProduct());
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(40);
        cp.setGravity(Gravity.CENTER);
        row.addView(cp);

        TextView init= new TextView(getActivity().getApplicationContext());

        init.setText(R.string.setting_initial1);
        init.setTextSize(30);
        init.setPadding(10, 0, 10, 0);
        init.setTextColor(Color.BLACK);
        row.addView(init);

        final TextView init2 = new TextView(getActivity().getApplicationContext());
        init2.setText(final_List.get(i).getInit());
        init2.setTextSize(30);
        init2.setPadding(10, 0, 10, 0);
        init2.setTextColor(Color.BLACK);
        row.addView(init2);

        TextView vowel = new TextView(getActivity().getApplicationContext());
        vowel.setText(R.string.setting_vowel1);
        vowel.setTextSize(30);
        vowel.setPadding(10, 0, 10, 0);
        vowel.setTextColor(Color.BLACK);
        row.addView(vowel);

        TextView vowel2 = new TextView(getActivity().getApplicationContext());
        vowel2.setText(final_List.get(i).getVowel());
        vowel2.setTextSize(30);
        vowel2.setPadding(10, 0, 10, 0);
        vowel2.setTextColor(Color.BLACK);
        row.addView(vowel2);

        final Button decreaseBtn = new Button(getActivity().getApplicationContext());
        decreaseBtn.setBackgroundResource(R.drawable.calculator22);
        decreaseBtn.setLayoutParams(new TableRow.LayoutParams(70, 70));
        decreaseBtn.setVisibility(View.INVISIBLE);
        row.addView(decreaseBtn);

        final TextView quantity_tv1 = new TextView(getActivity().getApplicationContext());
        quantity_tv1.setTextSize(30);
        quantity_tv1.setText(final_List.get(index).getInt1().toString());
        quantity_tv1.setTextColor(Color.BLACK);
        quantity_tv1.setPadding(10, 0, 10, 0);
        row.addView(quantity_tv1);

        final Button increaseBtn = new Button(getActivity().getApplicationContext());
        increaseBtn.setBackgroundResource(R.drawable.add139);
        increaseBtn.setLayoutParams(new TableRow.LayoutParams(70, 70));
        increaseBtn.setVisibility(View.INVISIBLE);
        row.addView(increaseBtn);

        setRowListner(row, cp, quantity_tv1, increaseBtn, decreaseBtn, index);


        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer innumber = Integer.parseInt(quantity_tv1.getText().toString());
                innumber = innumber + 1;
                if (innumber > 10) {
                    innumber = 10;
                }
                quantity_tv1.setText(innumber.toString());
                final_List.set(index, new Combination(new Exer(final_List.get(index).getInit(), final_List.get(index).getVowel(), final_List.get(index).getCartProduct()), innumber, true));
                total_quantity_tv.setText(getTotalQuantity().toString());

            }
        });
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer denumber = Integer.parseInt(quantity_tv1.getText().toString());
                denumber = denumber - 1;
                if (denumber < 1) {
                    denumber = 1;
                }
                quantity_tv1.setText(denumber.toString());
                final_List.set(index, new Combination(new Exer(final_List.get(index).getInit(), final_List.get(index).getVowel(), final_List.get(index).getCartProduct()), denumber, true));
                total_quantity_tv.setText(getTotalQuantity().toString());

            }
        });

        quantity_tl.addView(row);
    }
//    public void setButtonFunction(final Button confirm,final Integer index,Button tv_cp,Button decreaseBtn, final Button increaseBtn, final TextView quantity){
//        final Button btn_decrease = decreaseBtn;
//        final Button btn_increase = increaseBtn;
//        final Button cp = tv_cp;
//        final String setInit = final_List.get(index).getInit();
//        final String setVowel = final_List.get(index).getVowel();
//        final String CardProduct = final_List.get(index).getCartProduct();
//        final TextView tv_int = quantity;
//        if(final_List.get(index).getClicked()){
//            confirm.setText(R.string.action_set);
//        }else{
//            confirm.setText(R.string.action_settings);
//        }
//
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Integer number = Integer.parseInt(quantity.getText().toString());
//                final_List.set(index, new Combination(new Exer(setInit, setVowel, CardProduct), number, true));
//                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
//                btn_decrease.setVisibility(View.INVISIBLE);
//                btn_increase.setVisibility(View.INVISIBLE);
//                confirm.setText(R.string.action_set);
//                tv_int.setText(final_List.get(index).getInt1().toString());
////                quantity_tl.requestFocus();
//            }
//        });
//    }

    public Integer getTotalQuantity(){
        Integer total=0;
        for(int i=0;i<final_List.size();i++){
            total +=final_List.get(i).getInt1();

        }
        return total;
    }
    public Integer getTotalCombination(){
        Integer combination=0;
        for(int i=0;i<final_List.size();i++){
            combination +=1;
        }
        return combination;
    }
    public void setRowListner(TableRow row1,Button tv,TextView quantity_tv,Button increaseB,Button decreaseB,Integer i){
        final TableRow row = row1;
        final Button cp = tv;
        final TextView quantity_tv1 = quantity_tv;
        final Button increaseBtn = increaseB;
        final Button decreaseBtn = decreaseB;
        final Integer index = i;
//        Integer status = 0;
        row.setFocusableInTouchMode(true);
        row.setFocusable(true);
        row.setClickable(true);
        row.requestFocus();
        quantity_tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row.requestFocus();
            }
        });
//        row.requestFocus();
//        button_confirm.setText(R.string.action_settings);

        row.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                cp.setBackgroundResource(R.drawable.borders_black_and_darkblue);
                quantity_tv1.setText(final_List.get(index).getInt1().toString());
                increaseBtn.setVisibility(View.VISIBLE);
                decreaseBtn.setVisibility(View.VISIBLE);
//                setButtonFunction(button_confirm, index, cp, decreaseBtn, increaseBtn, quantity_tv1);
                row.requestFocus();
                return false;
            }
        });
        row.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                v.requestFocus();
                Log.d("Row","onFocus "+index.toString());
                increaseBtn.setVisibility(View.INVISIBLE);
                decreaseBtn.setVisibility(View.INVISIBLE);
                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
                quantity_tv1.setText(final_List.get(index).getInt1().toString());
//                row.requestFocus();
            }
        });

    }
    public void addRowItem2(Integer i){
        final Integer index =i;
        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        row.setFocusable(true);
        row.setFocusableInTouchMode(true);
        final Button cp = new Button(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(final_List.get(i).getCartProduct());
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(40);
        cp.setGravity(Gravity.CENTER);
        row.addView(cp);

        TextView init1 = new TextView(getActivity().getApplicationContext());
        init1.setText(R.string.setting_initial1);
        init1.setTextSize(30);
        init1.setPadding(10, 0, 10, 0);
        init1.setTextColor(Color.BLACK);
        row.addView(init1);

        final TextView init2 = new TextView(getActivity().getApplicationContext());
        init2.setText(final_List.get(i).getInit());
        init2.setTextSize(30);
        init2.setPadding(10, 0, 10, 0);
        init2.setTextColor(Color.BLACK);
        row.addView(init2);

        TextView vowel = new TextView(getActivity().getApplicationContext());
        vowel.setText(R.string.setting_vowel1);
        vowel.setTextSize(30);
        vowel.setPadding(10, 0, 10, 0);
        vowel.setTextColor(Color.BLACK);
        row.addView(vowel);

        TextView vowel2 = new TextView(getActivity().getApplicationContext());
        vowel2.setText(final_List.get(i).getVowel());
        vowel2.setTextSize(30);
        vowel2.setPadding(10, 0, 10, 0);
        vowel2.setTextColor(Color.BLACK);
        row.addView(vowel2);

        final Button decreaseBtn = new Button(getActivity().getApplicationContext());
        decreaseBtn.setBackgroundResource(R.drawable.calculator22);
        decreaseBtn.setLayoutParams(new TableRow.LayoutParams(70, 70));
        decreaseBtn.setVisibility(View.INVISIBLE);
        row.addView(decreaseBtn);

        final TextView quantity_tv1 = new TextView(getActivity().getApplicationContext());
        quantity_tv1.setTextSize(30);
        quantity_tv1.setText(final_List.get(index).getInt1().toString());
        quantity_tv1.setTextColor(Color.BLACK);
        quantity_tv1.setPadding(10, 0, 10, 0);
        row.addView(quantity_tv1);

        final Button increaseBtn = new Button(getActivity().getApplicationContext());
        increaseBtn.setBackgroundResource(R.drawable.add139);
        increaseBtn.setLayoutParams(new TableRow.LayoutParams(70, 70));
        increaseBtn.setVisibility(View.INVISIBLE);
        row.addView(increaseBtn);

        quantity_tl.addView(row);

    }
    public void setNilRecord(){
        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        Integer nil_size = not_available_array.size();
        for (int i = 0;i<nil_size;i++){
            final TextView cp = new Button(getActivity().getApplicationContext());
            cp.setAllCaps(false);
            cp.setText(not_available_array.get(i).toString());
            cp.setTextSize(20);
            cp.setGravity(Gravity.CENTER);
            cp.setPadding(20, 0, 30, 0);

            row.addView(cp);
        }
        ll_nilRecord2.addView(row);
    }
}