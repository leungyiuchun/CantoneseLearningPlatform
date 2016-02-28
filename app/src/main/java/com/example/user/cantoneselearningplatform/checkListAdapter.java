package com.example.user.cantoneselearningplatform;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 8/2/16.
 */
public class checkListAdapter extends ArrayAdapter<Check>{

    public checkListAdapter(Context context,int resource, List<Check> checks){
        super(context, resource, checks);

    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        View v = convertView;
        if(v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.checklistrow, null);
        }
        Check p = getItem(position);
        Integer position1 = position;
        Log.d("Check position",""+position1.toString());
        Log.d("Check position"," "+position1.equals(1));
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.checkPosition);
            TextView tt2 = (TextView) v.findViewById(R.id.checkAnswer);
            TextView tt3 = (TextView) v.findViewById(R.id.checkUserAnswer);
            TextView tt4 = (TextView) v.findViewById(R.id.checkCorrectness);
            TextView tt5 = (TextView) v.findViewById(R.id.checkChineseWord);
            if (tt1 != null) {
                if (position1.toString().equals("0")){
                    tt1.setText("題數");
                    tt1.setGravity(Gravity.RIGHT);
                }else{
                    tt1.setText(String.format("%d", position));
                    tt1.setGravity(Gravity.RIGHT);
                }

            }

            if (tt2 != null) {

                tt2.setText(p.getCheckAnswer());
                tt2.setGravity(Gravity.RIGHT);

            }
            if (tt3 != null) {
                if(p.getCorrectDecision().toString().equals("0")){
                    tt3.setText(p.getCheckUserAnswer());
                    tt3.setTextColor(Color.BLACK);
                    tt3.setGravity(Gravity.RIGHT);
                }else{
                    if (p.getCorrectDecision().intValue() == 1) {
                        tt3.setTextColor(Color.RED);
                        tt3.setGravity(Gravity.RIGHT);
                    }else{
                        if (p.getCorrectDecision().intValue() == 0){
                            tt3.setTextColor(Color.GREEN);
                            tt3.setGravity(Gravity.RIGHT);
                        }
                    }
                    tt3.setText(p.getCheckUserAnswer());
                    tt3.setGravity(Gravity.RIGHT);
                }
            }
            if (tt4 != null) {

                if (position1.toString().equals("0")){
                    tt4.setText("結果");
                    tt4.setTextColor(Color.BLACK);
                    tt4.setGravity(Gravity.RIGHT);

                }else{
                    tt4.setText(p.getCorrectDecision().toString());
                    if(p.getCorrectDecision().intValue()==0){
                        tt4.setGravity(Gravity.RIGHT);
                        tt4.setTextColor(Color.GREEN);
                        tt4.setText("正確");
                    }else{
                        tt4.setGravity(Gravity.RIGHT);
                        tt4.setTextColor(Color.RED);
                        tt4.setText("錯誤");
                    }
                }


            }
            if (tt5 !=null){
                tt5.setText(p.getChinese());
                tt5.setGravity(Gravity.RIGHT);
            }
        }
        return v;
    }

}
