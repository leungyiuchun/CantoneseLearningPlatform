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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultFragment extends DialogFragment {
    TextView totalQuantity;
    TextView totalCorrect;
    TextView correctPercent;
    Integer correctness;
    Integer correctIndex =0;
    Float percent;
    String percent_present;
    LinearLayout ll_testList;
    Integer totalQuestion;
    ArrayList<Answer> answerList;
    ArrayList<ChineseExer> chinList;
    ArrayList<Check> checkList = new ArrayList<Check>();
    public ResultFragment(ArrayList<Answer> answerlist, ArrayList<ChineseExer> chineseExerList){
        this.answerList = answerlist;
        this.chinList = chineseExerList;
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
        View view = inflater.inflate(R.layout.fragment_result,null);
        totalQuantity = (TextView)view.findViewById(R.id.tv_totalQuantity2);
        totalQuantity.setText(totalQuestion.toString());
        totalCorrect = (TextView)view.findViewById(R.id.tv_totalCorrect);
        correctPercent = (TextView)view.findViewById(R.id.tv_correctPercent);
        ll_testList = (LinearLayout)view.findViewById(R.id.ll_testList);
        insertCheck();
        updateList(builder, ll_testList);
        totalCorrect.setText(String.format("%d", correctIndex));
        percent = (float)correctIndex/totalQuestion;
        percent_present = percent*100 + "%";
        correctPercent.setText(percent_present);
        builder.setView(view);
        return builder.create();

    }
    public void insertCheck(){
        for(Integer i=0;i<totalQuestion;i++){
            if(answerList.get(i).getUserAnswer().equalsIgnoreCase(chinList.get(i).getChineseExerCardProduct())){
                correctness = 0;
                correctIndex +=1;
            }else {correctness = 1;}
            Check check = new Check(chinList.get(i).getChineseExerCardProduct(),answerList.get(i).getUserAnswer(),correctness,chinList.get(i).getChineseExerWord());
            checkList.add(check);
        }
    }
    public void updateList(AlertDialog.Builder builder, LinearLayout ll){
        ListView list = new ListView(builder.getContext());
        final checkListAdapter customAdapter = new checkListAdapter(builder.getContext(),R.layout.checklistrow,checkList);
        ll.removeAllViewsInLayout();
        list.setAdapter(customAdapter);
        ll.addView(list);
    }
}
