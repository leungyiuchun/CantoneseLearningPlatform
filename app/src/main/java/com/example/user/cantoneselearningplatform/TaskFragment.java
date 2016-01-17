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
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;


public class TaskFragment extends DialogFragment {
    int taskDecision;
    String task = "  ";
//    public static TaskFragment newInstance() {
//        return new TaskFragment();
//        Bundle args = new Bundle();
//        args.putInt("title",title);
//        task.setArguments(args);
//        return task;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskFragment.this.getDialog().cancel();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView) getActivity().findViewById(R.id.tv2_task);
                        tv.setText(((MyApp) getActivity().getApplication()).getTaskString());

                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_task,null);
        final RadioButton rb1 = (RadioButton)view.findViewById(R.id.task_rb_1);
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(1);
                    setTaskString(rb1.getText().toString());

                }
            }
        });
        final RadioButton rb2 = (RadioButton)view.findViewById(R.id.task_rb_2);
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(2);
                    setTaskString(rb2.getText().toString());
                }
            }
        });
        final RadioButton rb3 = (RadioButton)view.findViewById(R.id.task_rb_3);
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTaskInt(3);
                    setTaskString(rb3.getText().toString());
                }
            }
        });
        switch (((MyApp) getActivity().getApplication()).getTaskInt()){
            case 1: rb1.setChecked(true);
                    break;
            case 2:rb2.setChecked(true);
                    break;
            case 3:rb3.setChecked(true);
                    break;
        }
        builder.setView(view);
        return builder.create();


    }
    public void setTaskInt(Integer int1){((MyApp)getActivity().getApplication()).setTaskInt(int1);}
    public void setTaskString(String string1){((MyApp)getActivity().getApplication()).setTaskString(string1);}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
