package com.example.user.cantoneselearningplatform;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class AdvFragment extends DialogFragment {
    TextView example_tv;
    Button red_btn;
    Button green_btn;
    Button orange_btn;
    Button purple_btn;
    Integer color_index;
    Float speakSpeed;
    Switch animSwitch;
    SeekBar seekBar;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AdvFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Button advBtn = (Button) getActivity().findViewById(R.id.btn_adv);
                        switch (color_index){
                            case 0:
                                advBtn.setBackgroundColor(getResources().getColor(R.color.myRed));
                                break;
                            case 1:
                                advBtn.setBackgroundColor(getResources().getColor(R.color.myGreen));
                                break;
                            case 2:
                                advBtn.setBackgroundColor(getResources().getColor(R.color.myOrange));
                                break;
                            case 3:
                                advBtn.setBackgroundColor(getResources().getColor(R.color.myPurple));
                                break;
                        }
                        ((MyApp) getActivity().getApplication()).setColorIndex(color_index);
                        ((MyApp) getActivity().getApplication()).setSpeakSpeed(speakSpeed);

                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adv, null);
        color_index = ((MyApp)getActivity().getApplication()).getColorIndex();
        red_btn = (Button)view.findViewById(R.id.red_button);
        green_btn = (Button)view.findViewById(R.id.green_button);
        orange_btn = (Button)view.findViewById(R.id.orange_button);
        purple_btn = (Button)view.findViewById(R.id.purple_button);
        example_tv = (TextView)view.findViewById(R.id.color_example_tv);
        seekBar = (SeekBar)view.findViewById(R.id.seekBar1);
        animSwitch = (Switch)view.findViewById(R.id.anim_switch1);
        if (((MyApp)getActivity().getApplication()).getAnimBoolean()){
            animSwitch.setChecked(true);
        }else{
            animSwitch.setChecked(false);
        }
        animSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((MyApp) getActivity().getApplication()).setAnimBoolean(true);
                } else {
                    ((MyApp) getActivity().getApplication()).setAnimBoolean(false);
                }
            }
        });
        switch (color_index){
            case 0:
                example_tv.setTextColor(getResources().getColor(R.color.myRed));
                break;
            case 1:
                example_tv.setTextColor(getResources().getColor(R.color.myGreen));
                break;
            case 2:
                example_tv.setTextColor(getResources().getColor(R.color.myOrange));
                break;
            case 3:
                example_tv.setTextColor(getResources().getColor(R.color.myPurple));
                break;
        }
        red_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example_tv.setTextColor(getResources().getColor(R.color.myRed));
                color_index = 0;
            }
        });
        green_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example_tv.setTextColor(getResources().getColor(R.color.myGreen));
                color_index = 1;

            }
        });
        orange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example_tv.setTextColor(getResources().getColor(R.color.myOrange));
                color_index = 2;
            }
        });
        purple_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example_tv.setTextColor(getResources().getColor(R.color.myPurple));
                color_index = 3;
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Seek",""+progress);
                switch (progress){
                    case 0:
                        speakSpeed = 0.1f;
                        break;
                    case 1:
                        speakSpeed = 0.1f;
                        break;
                    case 2:
                        speakSpeed = 0.2f;
                        break;
                    case 3:
                        speakSpeed = 0.3f;
                        break;
                    case 4:
                        speakSpeed = 0.4f;
                        break;
                    case 5:
                        speakSpeed = 0.5f;
                        break;
                    case 6:
                        speakSpeed = 0.6f;
                        break;
                    case 7:
                        speakSpeed = 0.7f;
                        break;
                    case 8:
                        speakSpeed = 0.8f;
                        break;
                    case 9:
                        speakSpeed = 0.9f;
                        break;
                    case 10:
                        speakSpeed = 1f;
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

}
