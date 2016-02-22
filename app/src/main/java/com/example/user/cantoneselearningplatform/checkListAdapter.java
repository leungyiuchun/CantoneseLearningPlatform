package com.example.user.cantoneselearningplatform;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
        Integer position1 = position + 1;
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.checkPosition);
            TextView tt2 = (TextView) v.findViewById(R.id.checkAnswer);
            TextView tt3 = (TextView) v.findViewById(R.id.checkUserAnswer);
            TextView tt4 = (TextView) v.findViewById(R.id.checkCorrectness);
            TextView tt5 = (TextView) v.findViewById(R.id.checkChineseWord);
            if (tt1 != null) {
                tt1.setText(String.format("%d", position1));

            }

            if (tt2 != null) {
                tt2.setText(p.getCheckAnswer());

            }
            if (tt3 != null) {
                tt3.setText(p.getCheckUserAnswer());


            }
            if (tt4 != null) {

                tt4.setText(p.getCorrectDecision().toString());
                if(p.getCorrectDecision().intValue()==0){
                    tt4.setTextColor(Color.GREEN);
                    tt4.setText("正確");
                }else{
                    tt4.setTextColor(Color.RED);
                    tt4.setText("錯誤");
                }
            }
            if (tt5 !=null){
                tt5.setText(p.getChinese());
            }
        }
        return v;
    }

}
