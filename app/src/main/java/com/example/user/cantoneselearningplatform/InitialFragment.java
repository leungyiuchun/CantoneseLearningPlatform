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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Console;
import java.util.Arrays;




public class InitialFragment extends DialogFragment {
    public int array_index =0;
    public String[] display = new String[19];
    //    public Boolean init_1_clicked=false;

    public Boolean found = false;
    public Integer foundIndex =0;
    public String[] passArray;


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
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_initial);
                        Log.d("InitSize", " " + ((MyApp)getActivity().getApplication()).getInitList().size());
                        if(((MyApp)getActivity().getApplication()).getInitList().size()==0){
                            tv.setText("請選擇聲母");
                        }else {
                            tv.setText(((MyApp)getActivity().getApplication()).getInitList().toString());
                        }
                        sendArray(passArray);
                        Log.d("Presented", "" + Arrays.toString(passArray));
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(0);

                    }
                });
        final Button init_1_button = (Button)view.findViewById(R.id.init_1_button);
        show(((MyApp)getActivity().getApplication()).get_init_1(), init_1_button.getText().toString(), init_1_button);
        init_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_1(change(((MyApp) getActivity().getApplication()).get_init_1(), init_1_button.getText().toString(), init_1_button));

            }
        });
        final Button init_2_button = (Button)view.findViewById(R.id.init_2_button);
        show(((MyApp)getActivity().getApplication()).get_init_2(), init_2_button.getText().toString(), init_2_button);
        init_2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_2(change(((MyApp) getActivity().getApplication()).get_init_2(), init_2_button.getText().toString(), init_2_button));


            }
        });
        final Button init_3_button = (Button)view.findViewById(R.id.init_3_button);
        show(((MyApp)getActivity().getApplication()).get_init_3(), init_3_button.getText().toString(), init_3_button);
        init_3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_3(change(((MyApp) getActivity().getApplication()).get_init_3(), init_3_button.getText().toString(), init_3_button));

            }
        });
        final Button init_4_button = (Button)view.findViewById(R.id.init_4_button);
        show(((MyApp)getActivity().getApplication()).get_init_4(), init_4_button.getText().toString(), init_4_button);
        init_4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_4(change(((MyApp) getActivity().getApplication()).get_init_4(), init_4_button.getText().toString(), init_4_button));

            }
        });
        final Button init_5_button = (Button)view.findViewById(R.id.init_5_button);
        show(((MyApp)getActivity().getApplication()).get_init_5(), init_5_button.getText().toString(), init_5_button);
        init_5_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_5(change(((MyApp) getActivity().getApplication()).get_init_5(), init_5_button.getText().toString(), init_5_button));

            }
        });
        final Button init_6_button = (Button)view.findViewById(R.id.init_6_button);
        show(((MyApp)getActivity().getApplication()).get_init_6(), init_6_button.getText().toString(), init_6_button);
        init_6_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_6(change(((MyApp) getActivity().getApplication()).get_init_6(), init_6_button.getText().toString(), init_6_button));

            }
        });
        final Button init_7_button = (Button)view.findViewById(R.id.init_7_button);
        show(((MyApp)getActivity().getApplication()).get_init_7(), init_7_button.getText().toString(), init_7_button);
        init_7_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_7(change(((MyApp) getActivity().getApplication()).get_init_7(), init_7_button.getText().toString(), init_7_button));

            }
        });
        final Button init_8_button = (Button)view.findViewById(R.id.init_8_button);
        show(((MyApp)getActivity().getApplication()).get_init_8(), init_8_button.getText().toString(), init_8_button);
        init_8_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_8(change(((MyApp) getActivity().getApplication()).get_init_8(), init_8_button.getText().toString(), init_8_button));

            }
        });
        final Button init_9_button = (Button)view.findViewById(R.id.init_9_button);
        show(((MyApp)getActivity().getApplication()).get_init_9(), init_9_button.getText().toString(), init_9_button);
        init_9_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_9(change(((MyApp) getActivity().getApplication()).get_init_9(), init_9_button.getText().toString(), init_9_button));

            }
        });
        final Button init_10_button = (Button)view.findViewById(R.id.init_10_button);
        show(((MyApp)getActivity().getApplication()).get_init_10(), init_10_button.getText().toString(), init_10_button);
        init_10_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_10(change(((MyApp) getActivity().getApplication()).get_init_10(), init_10_button.getText().toString(), init_10_button));

            }
        });
        final Button init_11_button = (Button)view.findViewById(R.id.init_11_button);
        show(((MyApp)getActivity().getApplication()).get_init_11(), init_11_button.getText().toString(), init_11_button);
        init_11_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_11(change(((MyApp) getActivity().getApplication()).get_init_11(), init_11_button.getText().toString(), init_11_button));

            }
        });
        final Button init_12_button = (Button)view.findViewById(R.id.init_12_button);
        show(((MyApp)getActivity().getApplication()).get_init_12(), init_12_button.getText().toString(), init_12_button);
        init_12_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_12(change(((MyApp) getActivity().getApplication()).get_init_12(), init_12_button.getText().toString(), init_12_button));

            }
        });
        final Button init_13_button = (Button)view.findViewById(R.id.init_13_button);
        show(((MyApp)getActivity().getApplication()).get_init_13(), init_13_button.getText().toString(), init_13_button);
        init_13_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_13(change(((MyApp) getActivity().getApplication()).get_init_13(), init_13_button.getText().toString(), init_13_button));

            }
        });
        final Button init_14_button = (Button)view.findViewById(R.id.init_14_button);
        show(((MyApp)getActivity().getApplication()).get_init_14(), init_14_button.getText().toString(), init_14_button);
        init_14_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_14(change(((MyApp) getActivity().getApplication()).get_init_14(), init_14_button.getText().toString(), init_14_button));

            }
        });
        final Button init_15_button = (Button)view.findViewById(R.id.init_15_button);
        show(((MyApp)getActivity().getApplication()).get_init_15(), init_15_button.getText().toString(), init_15_button);
        init_15_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_15(change(((MyApp) getActivity().getApplication()).get_init_15(), init_15_button.getText().toString(), init_15_button));

            }
        });
        final Button init_16_button = (Button)view.findViewById(R.id.init_16_button);
        show(((MyApp)getActivity().getApplication()).get_init_16(), init_16_button.getText().toString(), init_16_button);
        init_16_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_16(change(((MyApp) getActivity().getApplication()).get_init_16(), init_16_button.getText().toString(), init_16_button));

            }
        });
        final Button init_17_button = (Button)view.findViewById(R.id.init_17_button);
        show(((MyApp)getActivity().getApplication()).get_init_17(), init_17_button.getText().toString(), init_17_button);
        init_17_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_17(change(((MyApp) getActivity().getApplication()).get_init_17(), init_17_button.getText().toString(), init_17_button));

            }
        });
        final Button init_18_button = (Button)view.findViewById(R.id.init_18_button);
        show(((MyApp)getActivity().getApplication()).get_init_18(), init_18_button.getText().toString(), init_18_button);
        init_18_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_18(change(((MyApp) getActivity().getApplication()).get_init_18(), init_18_button.getText().toString(), init_18_button));

            }
        });
        final Button init_19_button = (Button)view.findViewById(R.id.init_19_button);
        show(((MyApp)getActivity().getApplication()).get_init_19(), init_19_button.getText().toString(), init_19_button);
        init_19_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_init_19(change(((MyApp) getActivity().getApplication()).get_init_19(), init_19_button.getText().toString(), init_19_button));

            }
        });

        builder.setView(view);
        return builder.create();
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
    
    public Boolean change(Boolean clicked, String msg,Button button){
        Boolean click_status = clicked;

        if (click_status){
            button.setBackgroundColor(Color.parseColor("#F7BCBC"));
            button.setTextColor(Color.parseColor("#5E3434"));
            ((MyApp)getActivity().getApplication()).delInit(msg);
            click_status = false;

            return click_status;
        }else{
            button.setBackgroundColor(Color.parseColor("#7D5050"));
            button.setTextColor(Color.parseColor("#FFFFFF"));
            click_status = true;
            ((MyApp)getActivity().getApplication()).addInit(msg);
            return click_status;
        }
    }

    public void show(Boolean clicked,String msg,Button button){
        if (clicked){
            button.setBackgroundColor(Color.parseColor("#7D5050"));
            button.setTextColor(Color.parseColor("#FFFFFF"));

        }else{
            button.setBackgroundColor(Color.parseColor("#F7BCBC"));
            button.setTextColor(Color.parseColor("#5E3434"));

        }
    }
//    public void addArray(String add) {
//
//        display[array_index] = add;
//        array_index++;
//
//        if (Integer.compare(array_index,20) == 0)
//        {
//            new AlertDialog.Builder(InitialFragment.this.getContext())
//                    .setTitle("已選聲母已到限制數量")
//                    .setMessage("請重按某聲母以刪除 ")
//                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // continue with delete
//                        }
//                    })
//
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//        }
//    }
//
//    public void deleteArray(String del) {
//        int delIndex;
//        int i;
//
//        for (i = 0; i < array_index; i++) {
//            if (display[i] == del) {
//                found = true;
//                break;
//            } else {
//                found = false;
//
//            }
//        }
//        delIndex = i;
//        if (found) {
//            for (int k = delIndex; k < array_index; k++) {
//                display[k] = display[k+1];
//                display[k+1]=null;
//                array_index--;
//            }
//        }
//    }
//    public String[] ArrayDisplay(String[] DisplayArray){
//
//        String[] displayArray = new String[array_index];
//        for (int i=0;i<array_index;i++){
//            displayArray[i] = DisplayArray[i];
//        }
//
//        return displayArray;
//    }
}
