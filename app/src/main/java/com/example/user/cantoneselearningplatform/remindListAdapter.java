package com.example.user.cantoneselearningplatform;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 10/2/16.
 */
public class remindListAdapter extends ArrayAdapter<Answer>{
    public remindListAdapter(Context context, int resource, List<Answer> reminds) {
        super(context, resource, reminds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.remindlistrow, null);
        }
        Answer p = getItem(position);
        Integer position1 = position;
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.remindPosition);
            TextView tt2 = (TextView) v.findViewById(R.id.remindAnswer);
            TextView tt3 = (TextView) v.findViewById(R.id.remindChineseWord);
            if (tt1 != null) {
                if (position1.intValue()+1==1){
                    tt1.setText("題數");
                }else{
                    tt1.setText(String.format("%d", position));
                }
            }

            if (tt2 != null) {
                tt2.setText(p.getUserAnswer());
            }
            if(tt3 !=null){
                tt3.setText(p.getChin());
            }
        }
        return v;
    }
}
