package com.example.user.cantoneselearningplatform;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 3/2/16.
 */
public class ListAdapter extends ArrayAdapter<Combination> {

    public ListAdapter(Context context,int resource, List<Combination> combinations){
        super(context, resource, combinations);

    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        View v = convertView;

        if(v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }
        Combination p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.cartesianProduct);
            TextView tt2 = (TextView) v.findViewById(R.id.cp_quantity);

            if (tt1 != null) {
                tt1.setText(p.getCartProduct());

            }

            if (tt2 != null) {
                tt2.setText(p.getInt1().toString());

            }

        }
        return v;

    }

}


