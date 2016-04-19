package com.example.user.cantoneselearningplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


public class delPictureFragment extends DialogFragment {


    private DialogInterface.OnDismissListener onDismissListener;
    static Integer p_id;
    static Integer c_id;
    static String syllable;
    Bitmap output;
    TextView tv_syllable;
    TextView tv_word;
    TextView tv_tone;
    ImageView ivImage;
    static dataAdapter dataAdapter1;

    public static delPictureFragment newInstance() {
        delPictureFragment f = new delPictureFragment(p_id,c_id,syllable);
        return f;
    }

    public delPictureFragment(Integer p_id1,Integer c_id1,String syllable1) {
        this.p_id = p_id1;
        this.c_id = c_id1;
        this.syllable = syllable1;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        Log.d("setOnDismissListener","");
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataAdapter1 = new dataAdapter(this.getActivity());
        try{
            dataAdapter1.createDatabase();
            dataAdapter1.open();
        }catch (SQLException e){
            Log.d("sql error", "" + e);
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delPictureFragment.this.getDialog().cancel();
            }
        })
                .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataAdapter1.delPicture(p_id);
                    }
                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delPictureFragment.this.getDialog().cancel();

                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_del_picture, null);
        tv_syllable = (TextView) view.findViewById(R.id.delPicture_tv1);
        tv_word = (TextView) view.findViewById(R.id.delPicture_tv2);
        tv_tone = (TextView)view.findViewById(R.id.delPicture_tv3);
        ivImage = (ImageView) view.findViewById(R.id.delImageView);

        tv_syllable.setText(syllable);
        Cursor cursor = dataAdapter1.getWordByC_id(c_id);
        Integer sum = cursor.getCount();

        if(sum != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < sum; i++) {
                tv_word.setText(cursor.getString(0));
                tv_tone.setText(String.valueOf(cursor.getInt(1)));
                cursor.moveToNext();
            }
        }
        Cursor cursor1 = dataAdapter1.getPictureByPId(p_id);
        Integer sum2 = cursor1.getCount();

        Log.d("sum2"," "+ sum2);
        Bitmap bitmap;
        try{
            if(sum2 !=0){
                cursor1.moveToFirst();
                for(int j=0;j<sum2;j++){
                    byte[] picture = cursor1.getBlob(0);
                    bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                    ivImage.setImageBitmap(bitmap);

                    cursor1.moveToNext();
                }
            }
        }catch (Exception e){Log.d("sql error"," "+e);}

        builder.setView(view);
        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alert.getButton(Dialog.BUTTON_POSITIVE);
                btnPositive.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                Button btnNeutral = alert.getButton(Dialog.BUTTON_NEUTRAL);
                btnNeutral.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                Button btnNegative = alert.getButton(Dialog.BUTTON_NEGATIVE);
                btnNegative.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            }
        });
        return alert;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }



}
