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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class TaskFragment extends DialogFragment {
    int taskDecision;
    String task = "  ";
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskFragment.this.getDialog().cancel();
                    }
                })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_task);
                        tv.setText(((MyApp) getActivity().getApplication()).getTaskString());

                        TextView tv2 = (TextView) getActivity().findViewById(R.id.tv2_hints);

                        if(((MyApp)getActivity().getApplication()).getHintInt()==0){
                            tv2.setText("沒有提示");
                        }else {
                            if(((MyApp)getActivity().getApplication()).getHintsString().isEmpty()){
                            }else {
                                tv2.setText(((MyApp) getActivity().getApplication()).getHintsString());
                            }
                        }



                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_task,null);
        final RadioButton task_rb1 = (RadioButton)view.findViewById(R.id.task_rb_1);
        rb1 = (RadioButton)view.findViewById(R.id.hints_rb_1);
        rb2 = (RadioButton)view.findViewById(R.id.hints_rb_2);
        rb3 = (RadioButton)view.findViewById(R.id.hints_rb_3);
        task_rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(1);
                    setTaskString(task_rb1.getText().toString());
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.INVISIBLE);
                    rb3.setVisibility(View.INVISIBLE);
                }
            }
        });
        final RadioButton task_rb2 = (RadioButton)view.findViewById(R.id.task_rb_2);
        task_rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(2);
                    setTaskString(task_rb2.getText().toString());
                    rb1.setVisibility(View.INVISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.INVISIBLE);
                }
            }
        });
        final RadioButton task_rb3 = (RadioButton)view.findViewById(R.id.task_rb_3);
        task_rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(3);
                    setTaskString(task_rb3.getText().toString());
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);

                }
            }
        });
        switch (((MyApp) getActivity().getApplication()).getTaskInt()){
            case 1: task_rb1.setChecked(true);
                    break;
            case 2:task_rb2.setChecked(true);
                    break;
            case 3:task_rb3.setChecked(true);
                    break;
        }

        final RadioGroup rg = (RadioGroup)view.findViewById(R.id.hints_rg_1);

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb1.setTextColor(Color.GRAY);
        rb2.setTextColor(Color.GRAY);
        rb3.setTextColor(Color.GRAY);
        final Switch switch1 = (Switch)view.findViewById(R.id.hints_switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rg.setEnabled(true);
                    rb1.setEnabled(true);
                    rb2.setEnabled(true );
                    rb3.setEnabled(true);
                    rb1.setTextColor(Color.BLACK);
                    rb2.setTextColor(Color.BLACK);
                    rb3.setTextColor(Color.BLACK);
                    setHintsInt(4);
                    setHintsString("請選擇提示");

                } else {
                    rg.setEnabled(false);
                    rb1.setEnabled(false);
                    rb2.setEnabled(false);
                    rb3.setEnabled(false);
                    rb1.setTextColor(Color.GRAY);
                    rb2.setTextColor(Color.GRAY);
                    rb3.setTextColor(Color.GRAY);
                    setHintsInt(0);
                    setHintsString("沒有提示");
                }
            }

        });
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setHintsInt(1);
                    setHintsString(rb1.getText().toString());
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setHintsInt(2);
                    setHintsString(rb2.getText().toString());
                }

            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setHintsInt(3);
                    setHintsString(rb3.getText().toString());
                }
            }
        });

        switch (((MyApp) getActivity().getApplication()).getHintInt()){
            case 0: switch1.setChecked(false);
                break;
            case 1: switch1.setChecked(true);
                rb1.setChecked(true);
                break;
            case 2: switch1.setChecked(true);
                rb2.setChecked(true);
                break;
            case 3: switch1.setChecked(true);
                rb3.setChecked(true);
                break;
            case 4: switch1.setChecked(true);
                break;
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
    public void setTaskInt(Integer int1){((MyApp)getActivity().getApplication()).setTaskInt(int1);}
    public void setTaskString(String string1){((MyApp)getActivity().getApplication()).setTaskString(string1);}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public void setHintsInt(Integer int1){((MyApp)getActivity().getApplication()).setHintInt(int1);}
    public void setHintsString(String string1){((MyApp)getActivity().getApplication()).setHintsString(string1);}
}
