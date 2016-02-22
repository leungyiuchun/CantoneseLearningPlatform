package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;



public class QuantityFragment extends DialogFragment{
    Integer init_number;
    Integer vowel_number;
    Integer total_number;
    Integer looping;
    ArrayList<Integer> number_array = new ArrayList<Integer>();
//    Spinner mSpinner;
    Integer rowLooping=0;
    Integer loop=0;
    public ArrayList<Combination> final_List = new ArrayList<Combination>();
    public ArrayList<Exer> ExersList = new ArrayList<Exer>();
    TableLayout quantity_tl;
    TextView total_quantity_tv;
    TextView total_combination_tv;
    Integer total_combination;
    Button button_confirm;
    String init;
    String vowel;
    AlertDialog.Builder builder;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_quantity, null);
        quantity_tl = (TableLayout)view.findViewById(R.id.quantity_tl);
        button_confirm = (Button)view.findViewById(R.id.number_confirm);
        total_quantity_tv = (TextView)view.findViewById(R.id.tv_totalQuantity);
        total_combination_tv = (TextView)view.findViewById(R.id.tv_totalCombination);
        final TextView tv = (TextView) getActivity().findViewById(R.id.tv2_quantity);
        init_number = ((MyApp)getActivity().getApplication()).getInitList().size();
        vowel_number = ((MyApp)getActivity().getApplication()).getVowelList().size();
        if(((MyApp) getActivity().getApplication()).getQuantityFlag() -1 == 0) {
            final_List = ((MyApp) getActivity().getApplication()).getCombinationList();
        }else {
            createCartesianProduct(init_number, vowel_number);
        }

        total_number = init_number*vowel_number;

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuantityFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MyApp) getActivity().getApplication()).setQuantityInt(getTotalQuantity());
                        Log.d("QaF", "after1 " + final_List.size());
                        Log.d("globalQaF", "after1 " + ((MyApp) getActivity().getApplication()).getCombinationList().size());
                        for (Integer i = 0; i < final_List.size(); i++) {
                            Log.d("final_list_loop", "before " + final_List.get(i).getCartProduct());
                        }

                        ((MyApp) getActivity().getApplication()).setCombinationList(final_List);

                        Log.d("globalQaF", "after2 " + ((MyApp) getActivity().getApplication()).getCombinationList().size());
                        Log.d("QaF","after2 "+ final_List.size());
                        tv.setText((getTotalQuantity().toString()));
                        for (Integer i = 0; i < final_List.size(); i++) {
                            Log.d("final_list_loop", "after " + final_List.get(i).getCartProduct());
                        }
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(1);
                    }
                });
//        ((ViewGroup)quantity_tl.getParent()).removeView(quantity_tl);
//        quantity_tl.removeAllViewsInLayout();
//        quantity_tl.removeAllViews();

        for(Integer j=0;j<final_List.size();j++){
            addRowItem(j);
        }
        total_quantity_tv.setText(getTotalQuantity().toString());
        total_combination_tv.setText(getTotalCombination().toString());
//        ListView list = new ListView(builder.getContext());
//        final ListAdapter customAdapter = new ListAdapter(builder.getContext(),R.layout.itemlistrow,final_List);
//        list.setAdapter(customAdapter);
//        ll_list.addView(list);
        builder.setView(view);
        return builder.create();
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
                ExersList.add(new Exer(((MyApp)getActivity().getApplication()).getInitList().get(i).toString(), ((MyApp)getActivity().getApplication()).getVowelList().get(j).toString(),tmp));
            }
        }
        for(int i=0;i<ExersList.size();i++){
            final_List.add(new Combination(ExersList.get(i),3));
        }
    }


    public void addRowItem(Integer i){
        final Integer index =i;
//        quantity_tl.removeAllViewsInLayout();
        TableRow row = new TableRow(getActivity().getApplicationContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        row.setFocusable(true);
        row.setFocusableInTouchMode(true);
        final Button cp = new Button(getActivity().getApplicationContext());
        cp.setAllCaps(false);
        cp.setText(final_List.get(i).getCartProduct());
        cp.setBackgroundResource(R.drawable.borders_black_and_blue);
        cp.setTextSize(30);
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
        decreaseBtn.setLayoutParams(new TableRow.LayoutParams(80, 80));
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
        increaseBtn.setLayoutParams(new TableRow.LayoutParams(80, 80));
        increaseBtn.setVisibility(View.INVISIBLE);
        row.addView(increaseBtn);
        Log.d("addView", "" + row.hasFocus());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Onclick", "before" + v.hasFocus());
                v.requestFocus();
                Log.d("Onclick", "after" + v.hasFocus());
                cp.setBackgroundResource(R.drawable.borders_black_and_darkblue);
                quantity_tv1.setText(final_List.get(index).getInt1().toString());
                increaseBtn.setVisibility(View.VISIBLE);
                decreaseBtn.setVisibility(View.VISIBLE);
            }
        });
        row.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                increaseBtn.setVisibility(View.INVISIBLE);
                decreaseBtn.setVisibility(View.INVISIBLE);
                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
                quantity_tv1.setText(final_List.get(index).getInt1().toString());
                setButtonFunction(button_confirm,index,cp,decreaseBtn,increaseBtn,quantity_tv1);
            }
        });

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer innumber = Integer.parseInt(quantity_tv1.getText().toString());
                innumber = innumber + 1;
                if (innumber > 10) {
                    innumber = 10;
                }
                quantity_tv1.setText(innumber.toString());
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
            }
        });

        quantity_tl.addView(row);

    }
    public void setButtonFunction(final Button confirm,final Integer index,TextView tv_cp,Button decreaseBtn, final Button increaseBtn, final TextView quantity){
        final Button btn_decrease = decreaseBtn;
        final Button btn_increase = increaseBtn;
        final TextView cp = tv_cp;
        final String setInit = final_List.get(index).getInit();
        final String setVowel = final_List.get(index).getVowel();
        final String CardProduct = final_List.get(index).getCartProduct();
        final TextView tv_int = quantity;
//        confirm.setText(R.string.action_settings);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.parseInt(quantity.getText().toString());
                final_List.set(index, new Combination(new Exer(setInit, setVowel, CardProduct), number));
                cp.setBackgroundResource(R.drawable.borders_black_and_blue);
                btn_decrease.setVisibility(View.INVISIBLE);
                btn_increase.setVisibility(View.INVISIBLE);
//                confirm.setText(R.string.action_set);
//                updateList(builder, ll);
                total_quantity_tv.setText(getTotalQuantity().toString());
                tv_int.setText(final_List.get(index).getInt1().toString());

            }
        });
    }

    public Integer getTotalQuantity(){
        Integer total=0;
        for(int i=0;i<final_List.size();i++){
            total +=final_List.get(i).getInt1();
            Log.d("getTotalQuantity"," "+ total.toString());

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
}
