package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class ModeFragment extends DialogFragment {
    EditText student_name;
    TextView tv_student;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_mode);
                        tv.setText(((MyApp) getActivity().getApplication()).getModeString());
                        ((MyApp) getActivity().getApplication()).setStudentName(student_name.getText().toString());
                        Log.d("StudentName",""+((MyApp) getActivity().getApplication()).getStudentName());

                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_mode,null);
        student_name = (EditText)view.findViewById(R.id.et_student);
        tv_student = (TextView)view.findViewById(R.id.tv_student);
        final RadioButton rb1 = (RadioButton)view.findViewById(R.id.mode_rb_1);
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setmodeInt(0);
                    setmodeString(rb1.getText().toString());
                    student_name.setVisibility(View.INVISIBLE);
                    tv_student.setVisibility(View.INVISIBLE);
                }
            }
        });

        final RadioButton rb2 = (RadioButton)view.findViewById(R.id.mode_rb_2);
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setmodeInt(1);
                    setmodeString(rb2.getText().toString());
                    student_name.setVisibility(View.VISIBLE);
                    tv_student.setVisibility(View.VISIBLE);
//                    student_name.setText(((MyApp) getActivity().getApplication()).getStudentName());
                }
            }
        });

        switch (((MyApp) getActivity().getApplication()).getModeInt()){
            case 0: rb1.setChecked(true);
                student_name.setVisibility(View.INVISIBLE);
                tv_student.setVisibility(View.INVISIBLE);
                break;
            case 1:rb2.setChecked(true);
                student_name.setVisibility(View.VISIBLE);
                tv_student.setVisibility(View.VISIBLE);
                student_name.setText(((MyApp) getActivity().getApplication()).getStudentName());

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
    public void setmodeInt(Integer int1){((MyApp)getActivity().getApplication()).setModeInt(int1);}
    public void setmodeString(String string1){((MyApp)getActivity().getApplication()).setModeString(string1);}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
