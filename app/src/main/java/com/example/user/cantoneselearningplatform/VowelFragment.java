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
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class VowelFragment extends DialogFragment {
    public Boolean found = false;
    public String[] passArray;
    public int array_index =0;
    public String[] display = new String[61];


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                VowelFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_vowel);

                        if(((MyApp)getActivity().getApplication()).getVowelList().size()==0){
                            tv.setText("請選擇韻母");
                        }else {
                            tv.setText(((MyApp)getActivity().getApplication()).getVowelList().toString());
                        }
                        ((MyApp) getActivity().getApplication()).setQuantityFlag(0);

                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_vowel, null);

        final TextView vowel_1_tv = (TextView)view.findViewById(R.id.vowel_1_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_1(),vowel_1_tv.getText().toString(), vowel_1_tv);
        vowel_1_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_1(change(((MyApp) getActivity().getApplication()).get_vowel_1(), vowel_1_tv.getText().toString(), vowel_1_tv));
            }
        });

        final TextView vowel_3_tv = (TextView)view.findViewById(R.id.vowel_3_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_3(),vowel_3_tv.getText().toString(), vowel_3_tv);
        vowel_3_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_3(change(((MyApp) getActivity().getApplication()).get_vowel_3(), vowel_3_tv.getText().toString(), vowel_3_tv));
            }
        });

        final TextView vowel_4_tv = (TextView)view.findViewById(R.id.vowel_4_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_4(),vowel_4_tv.getText().toString(), vowel_4_tv);
        vowel_4_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_4(change(((MyApp) getActivity().getApplication()).get_vowel_4(), vowel_4_tv.getText().toString(), vowel_4_tv));
            }
        });
        final TextView vowel_5_tv = (TextView)view.findViewById(R.id.vowel_5_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_5(),vowel_5_tv.getText().toString(), vowel_5_tv);
        vowel_5_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_5(change(((MyApp) getActivity().getApplication()).get_vowel_5(), vowel_5_tv.getText().toString(), vowel_5_tv));
            }
        });
        final TextView vowel_6_tv = (TextView)view.findViewById(R.id.vowel_6_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_6(),vowel_6_tv.getText().toString(), vowel_6_tv);
        vowel_6_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_6(change(((MyApp) getActivity().getApplication()).get_vowel_6(), vowel_6_tv.getText().toString(), vowel_6_tv));
            }
        });

        final TextView vowel_8_tv = (TextView)view.findViewById(R.id.vowel_8_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_8(),vowel_8_tv.getText().toString(), vowel_8_tv);
        vowel_8_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_8(change(((MyApp) getActivity().getApplication()).get_vowel_8(), vowel_8_tv.getText().toString(), vowel_8_tv));
            }
        });
        final TextView vowel_9_tv = (TextView)view.findViewById(R.id.vowel_9_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_9(),vowel_9_tv.getText().toString(), vowel_9_tv);
        vowel_9_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_9(change(((MyApp) getActivity().getApplication()).get_vowel_9(), vowel_9_tv.getText().toString(), vowel_9_tv));
            }
        });
        final TextView vowel_10_tv = (TextView)view.findViewById(R.id.vowel_10_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_10(),vowel_10_tv.getText().toString(), vowel_10_tv);
        vowel_10_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_10(change(((MyApp) getActivity().getApplication()).get_vowel_10(), vowel_10_tv.getText().toString(), vowel_10_tv));
            }
        });
        final TextView vowel_11_tv = (TextView)view.findViewById(R.id.vowel_11_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_11(),vowel_11_tv.getText().toString(), vowel_11_tv);
        vowel_11_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_11(change(((MyApp) getActivity().getApplication()).get_vowel_11(), vowel_11_tv.getText().toString(), vowel_11_tv));
            }
        });
        final TextView vowel_12_tv = (TextView)view.findViewById(R.id.vowel_12_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_12(),vowel_12_tv.getText().toString(), vowel_12_tv);
        vowel_12_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_12(change(((MyApp) getActivity().getApplication()).get_vowel_12(), vowel_12_tv.getText().toString(), vowel_12_tv));
            }
        });
        final TextView vowel_13_tv = (TextView)view.findViewById(R.id.vowel_13_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_13(),vowel_13_tv.getText().toString(), vowel_13_tv);
        vowel_13_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_13(change(((MyApp) getActivity().getApplication()).get_vowel_13(), vowel_13_tv.getText().toString(), vowel_13_tv));
            }
        });
        final TextView vowel_14_tv = (TextView)view.findViewById(R.id.vowel_14_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_14(),vowel_14_tv.getText().toString(), vowel_14_tv);
        vowel_14_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_14(change(((MyApp) getActivity().getApplication()).get_vowel_14(), vowel_14_tv.getText().toString(), vowel_14_tv));
            }
        });
        final TextView vowel_15_tv = (TextView)view.findViewById(R.id.vowel_15_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_15(),vowel_15_tv.getText().toString(), vowel_15_tv);
        vowel_15_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_15(change(((MyApp) getActivity().getApplication()).get_vowel_15(), vowel_15_tv.getText().toString(), vowel_15_tv));}
        });

        final TextView vowel_17_tv = (TextView)view.findViewById(R.id.vowel_17_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_17(),vowel_17_tv.getText().toString(), vowel_17_tv);
        vowel_17_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_17(change(((MyApp) getActivity().getApplication()).get_vowel_17(), vowel_17_tv.getText().toString(), vowel_17_tv));
            }
        });
        final TextView vowel_18_tv = (TextView)view.findViewById(R.id.vowel_18_TextView);
        show(((MyApp) getActivity().getApplication()).get_vowel_18(), vowel_18_tv.getText().toString(), vowel_18_tv);
        vowel_18_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_18(change(((MyApp) getActivity().getApplication()).get_vowel_18(), vowel_18_tv.getText().toString(), vowel_18_tv));
            }
        });
        final TextView vowel_19_tv = (TextView)view.findViewById(R.id.vowel_19_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_19(),vowel_19_tv.getText().toString(), vowel_19_tv);
        vowel_19_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_19(change(((MyApp) getActivity().getApplication()).get_vowel_19(), vowel_19_tv.getText().toString(), vowel_19_tv));
            }
        });
        final TextView vowel_20_tv = (TextView)view.findViewById(R.id.vowel_20_TextView);
        vowel_20_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_20(change(((MyApp) getActivity().getApplication()).get_vowel_20(), vowel_20_tv.getText().toString(), vowel_20_tv));
            }
        });
        final TextView vowel_22_tv = (TextView)view.findViewById(R.id.vowel_22_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_22(),vowel_22_tv.getText().toString(), vowel_22_tv);
        vowel_22_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_22(change(((MyApp) getActivity().getApplication()).get_vowel_22(), vowel_22_tv.getText().toString(), vowel_22_tv));
            }
        });
        final TextView vowel_23_tv = (TextView)view.findViewById(R.id.vowel_23_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_23(),vowel_23_tv.getText().toString(), vowel_23_tv);
        vowel_23_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_23(change(((MyApp) getActivity().getApplication()).get_vowel_23(), vowel_23_tv.getText().toString(), vowel_23_tv));
            }
        });
        final TextView vowel_24_tv = (TextView)view.findViewById(R.id.vowel_24_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_24(),vowel_24_tv.getText().toString(), vowel_24_tv);
        vowel_24_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_24(change(((MyApp) getActivity().getApplication()).get_vowel_24(), vowel_24_tv.getText().toString(), vowel_24_tv));
            }
        });
        final TextView vowel_26_tv = (TextView)view.findViewById(R.id.vowel_26_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_26(),vowel_26_tv.getText().toString(), vowel_26_tv);
        vowel_26_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_26(change(((MyApp) getActivity().getApplication()).get_vowel_26(), vowel_26_tv.getText().toString(), vowel_26_tv));
            }
        });
        final TextView vowel_27_tv = (TextView)view.findViewById(R.id.vowel_27_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_27(),vowel_27_tv.getText().toString(), vowel_27_tv);
        vowel_27_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_27(change(((MyApp) getActivity().getApplication()).get_vowel_27(), vowel_27_tv.getText().toString(), vowel_27_tv));
            }
        });
        final TextView vowel_28_tv = (TextView)view.findViewById(R.id.vowel_28_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_28(),vowel_28_tv.getText().toString(), vowel_28_tv);
        vowel_28_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_28(change(((MyApp) getActivity().getApplication()).get_vowel_28(), vowel_28_tv.getText().toString(), vowel_28_tv));
            }
        });
        final TextView vowel_29_tv = (TextView)view.findViewById(R.id.vowel_29_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_29(),vowel_29_tv.getText().toString(), vowel_29_tv);
        vowel_29_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_29(change(((MyApp) getActivity().getApplication()).get_vowel_29(), vowel_29_tv.getText().toString(), vowel_29_tv));
            }
        });
        final TextView vowel_30_tv = (TextView)view.findViewById(R.id.vowel_30_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_30(),vowel_30_tv.getText().toString(), vowel_30_tv);
        vowel_30_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_30(change(((MyApp) getActivity().getApplication()).get_vowel_30(), vowel_30_tv.getText().toString(), vowel_30_tv));
            }
        });
        final TextView vowel_31_tv = (TextView)view.findViewById(R.id.vowel_31_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_31(),vowel_31_tv.getText().toString(), vowel_31_tv);
        vowel_31_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp) getActivity().getApplication()).set_vowel_31(change(((MyApp) getActivity().getApplication()).get_vowel_31(), vowel_31_tv.getText().toString(), vowel_31_tv));
            }
        });
        final TextView vowel_32_tv = (TextView)view.findViewById(R.id.vowel_32_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_32(),vowel_32_tv.getText().toString(), vowel_32_tv);
        vowel_32_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_32(change(((MyApp) getActivity().getApplication()).get_vowel_32(), vowel_32_tv.getText().toString(), vowel_32_tv));
            }
        });

        final TextView vowel_34_tv = (TextView)view.findViewById(R.id.vowel_34_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_34(),vowel_34_tv.getText().toString(), vowel_34_tv);
        vowel_34_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_34(change(((MyApp) getActivity().getApplication()).get_vowel_34(), vowel_34_tv.getText().toString(), vowel_34_tv));
            }
        });
        final TextView vowel_35_tv = (TextView)view.findViewById(R.id.vowel_35_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_35(),vowel_35_tv.getText().toString(), vowel_35_tv);
        vowel_35_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_35(change(((MyApp) getActivity().getApplication()).get_vowel_35(), vowel_35_tv.getText().toString(), vowel_35_tv));
            }
        });
        final TextView vowel_36_tv = (TextView)view.findViewById(R.id.vowel_36_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_36(),vowel_36_tv.getText().toString(), vowel_36_tv);
        vowel_36_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_36(change(((MyApp) getActivity().getApplication()).get_vowel_36(), vowel_36_tv.getText().toString(), vowel_36_tv));
            }
        });
        final TextView vowel_37_tv = (TextView)view.findViewById(R.id.vowel_37_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_37(),vowel_37_tv.getText().toString(), vowel_37_tv);
        vowel_37_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_37(change(((MyApp) getActivity().getApplication()).get_vowel_37(), vowel_37_tv.getText().toString(), vowel_37_tv));
            }
        });
        final TextView vowel_38_tv = (TextView)view.findViewById(R.id.vowel_38_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_38(),vowel_38_tv.getText().toString(), vowel_38_tv);
        vowel_38_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_38(change(((MyApp) getActivity().getApplication()).get_vowel_38(), vowel_38_tv.getText().toString(), vowel_38_tv));
            }
        });
        final TextView vowel_39_tv = (TextView)view.findViewById(R.id.vowel_39_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_39(),vowel_39_tv.getText().toString(), vowel_39_tv);
        vowel_39_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_39(change(((MyApp) getActivity().getApplication()).get_vowel_39(), vowel_39_tv.getText().toString(), vowel_39_tv));
            }
        });
        final TextView vowel_40_tv = (TextView)view.findViewById(R.id.vowel_40_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_40(),vowel_40_tv.getText().toString(), vowel_40_tv);
        vowel_40_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_40(change(((MyApp) getActivity().getApplication()).get_vowel_40(), vowel_40_tv.getText().toString(), vowel_40_tv));
            }
        });

        final TextView vowel_42_tv = (TextView)view.findViewById(R.id.vowel_42_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_42(),vowel_42_tv.getText().toString(), vowel_42_tv);
        vowel_42_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_42(change(((MyApp) getActivity().getApplication()).get_vowel_42(), vowel_42_tv.getText().toString(), vowel_42_tv));
            }
        });
        final TextView vowel_43_tv = (TextView)view.findViewById(R.id.vowel_43_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_43(),vowel_43_tv.getText().toString(), vowel_43_tv);
        vowel_43_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_43(change(((MyApp) getActivity().getApplication()).get_vowel_43(), vowel_43_tv.getText().toString(), vowel_43_tv));
            }
        });
        final TextView vowel_44_tv = (TextView)view.findViewById(R.id.vowel_44_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_44(),vowel_44_tv.getText().toString(), vowel_44_tv);
        vowel_44_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_44(change(((MyApp) getActivity().getApplication()).get_vowel_44(), vowel_44_tv.getText().toString(), vowel_44_tv));
            }
        });

        final TextView vowel_46_tv = (TextView)view.findViewById(R.id.vowel_46_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_46(),vowel_46_tv.getText().toString(), vowel_46_tv);
        vowel_46_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_46(change(((MyApp) getActivity().getApplication()).get_vowel_46(), vowel_46_tv.getText().toString(), vowel_46_tv));
            }
        });
        final TextView vowel_47_tv = (TextView)view.findViewById(R.id.vowel_47_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_47(),vowel_47_tv.getText().toString(), vowel_47_tv);
        vowel_47_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_47(change(((MyApp) getActivity().getApplication()).get_vowel_47(), vowel_47_tv.getText().toString(), vowel_47_tv));
            }
        });
        final TextView vowel_48_tv = (TextView)view.findViewById(R.id.vowel_48_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_48(),vowel_48_tv.getText().toString(), vowel_48_tv);
        vowel_48_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_48(change(((MyApp) getActivity().getApplication()).get_vowel_48(), vowel_48_tv.getText().toString(), vowel_48_tv));
            }
        });
        final TextView vowel_49_tv = (TextView)view.findViewById(R.id.vowel_49_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_49(),vowel_49_tv.getText().toString(), vowel_49_tv);
        vowel_49_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_49(change(((MyApp) getActivity().getApplication()).get_vowel_49(), vowel_49_tv.getText().toString(), vowel_49_tv));
            }
        });
        final TextView vowel_50_tv = (TextView)view.findViewById(R.id.vowel_50_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_50(),vowel_50_tv.getText().toString(), vowel_50_tv);
        vowel_50_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_50(change(((MyApp) getActivity().getApplication()).get_vowel_50(), vowel_50_tv.getText().toString(), vowel_50_tv));
            }
        });
        final TextView vowel_51_tv = (TextView)view.findViewById(R.id.vowel_51_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_51(),vowel_51_tv.getText().toString(), vowel_51_tv);
        vowel_51_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_51(change(((MyApp) getActivity().getApplication()).get_vowel_51(), vowel_51_tv.getText().toString(), vowel_51_tv));
            }
        });
        final TextView vowel_52_tv = (TextView)view.findViewById(R.id.vowel_52_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_52(),vowel_52_tv.getText().toString(), vowel_52_tv);
        vowel_52_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_52(change(((MyApp) getActivity().getApplication()).get_vowel_52(), vowel_52_tv.getText().toString(), vowel_52_tv));
            }
        });

        final TextView vowel_54_tv = (TextView)view.findViewById(R.id.vowel_54_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_54(),vowel_54_tv.getText().toString(), vowel_54_tv);
        vowel_54_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_54(change(((MyApp) getActivity().getApplication()).get_vowel_54(), vowel_54_tv.getText().toString(), vowel_54_tv));
            }
        });
        final TextView vowel_55_tv = (TextView)view.findViewById(R.id.vowel_55_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_55(),vowel_55_tv.getText().toString(), vowel_55_tv);
        vowel_55_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_55(change(((MyApp) getActivity().getApplication()).get_vowel_55(), vowel_55_tv.getText().toString(), vowel_55_tv));
            }
        });
        final TextView vowel_56_tv = (TextView)view.findViewById(R.id.vowel_56_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_56(),vowel_56_tv.getText().toString(), vowel_56_tv);
        vowel_56_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_56(change(((MyApp) getActivity().getApplication()).get_vowel_56(), vowel_56_tv.getText().toString(), vowel_56_tv));
            }
        });
        final TextView vowel_57_tv = (TextView)view.findViewById(R.id.vowel_57_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_57(),vowel_57_tv.getText().toString(), vowel_57_tv);
        vowel_57_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_57(change(((MyApp) getActivity().getApplication()).get_vowel_57(), vowel_57_tv.getText().toString(), vowel_57_tv));
            }
        });
        final TextView vowel_58_tv = (TextView)view.findViewById(R.id.vowel_58_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_58(),vowel_58_tv.getText().toString(), vowel_58_tv);
        vowel_58_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_58(change(((MyApp) getActivity().getApplication()).get_vowel_58(), vowel_58_tv.getText().toString(), vowel_58_tv));
            }
        });
        final TextView vowel_59_tv = (TextView)view.findViewById(R.id.vowel_59_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_59(),vowel_59_tv.getText().toString(), vowel_59_tv);
        vowel_59_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_59(change(((MyApp) getActivity().getApplication()).get_vowel_59(), vowel_59_tv.getText().toString(), vowel_59_tv));
            }
        });
        final TextView vowel_60_tv = (TextView)view.findViewById(R.id.vowel_60_TextView);
        show(((MyApp)getActivity().getApplication()).get_vowel_60(),vowel_60_tv.getText().toString(), vowel_60_tv);
        vowel_60_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApp)getActivity().getApplication()).set_vowel_60(change(((MyApp) getActivity().getApplication()).get_vowel_60(), vowel_60_tv.getText().toString(), vowel_60_tv));
            }
        });

        builder.setView(view);

        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public void show(Boolean clicked,String msg,TextView tv){
        if (clicked){
            tv.setBackgroundResource(R.drawable.borders_black_and_darkblue);
            tv.setTextColor(Color.parseColor("#FFFFFF"));

        }else{
            tv.setBackgroundResource(R.drawable.borders_black_and_blue);
            tv.setTextColor(Color.parseColor("#49345E"));


        }
    }

    public Boolean change(Boolean clicked,String msg,TextView tv){

        if (clicked){
            tv.setBackgroundResource(R.drawable.borders_black_and_blue);
            tv.setTextColor(Color.parseColor("#49345E"));
            clicked = false;
            ((MyApp)getActivity().getApplication()).delVowel(msg);

            return clicked;
        }else{
            tv.setBackgroundResource(R.drawable.borders_black_and_darkblue);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            clicked = true;
            ((MyApp)getActivity().getApplication()).addVowel(msg);
            return clicked;
        }
    }

}
