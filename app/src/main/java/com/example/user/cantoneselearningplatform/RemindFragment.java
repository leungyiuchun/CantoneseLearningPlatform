package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RemindFragment extends DialogFragment {
    ArrayList<Answer> answerList;
    public TextView totalQuantity;
    Integer totalQuestion;
    LinearLayout ll_remindList;
    public RemindFragment(ArrayList<Answer> answers) {
        this.answerList = answers;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        totalQuestion = ((MyApp) getActivity().getApplication()).getQuantityInt();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_remind,null);
        ll_remindList = (LinearLayout)view.findViewById(R.id.ll_remindList);
        totalQuantity = (TextView)view.findViewById(R.id.tv_totalQuantity2);
        totalQuantity.setText(String.format("%d",totalQuestion));
        updateList(builder, ll_remindList);
        builder.setView(view);
        return builder.create();
    }
    public void updateList(AlertDialog.Builder builder, LinearLayout ll){
        ListView list = new ListView(builder.getContext());
        final remindListAdapter customAdapter = new remindListAdapter(builder.getContext(),R.layout.remindlistrow,answerList);
        ll.removeAllViewsInLayout();
        list.setAdapter(customAdapter);
        ll.addView(list);
    }
}
