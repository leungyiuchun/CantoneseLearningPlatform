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


public class addPictureFragment extends DialogFragment {
    static Integer c_id;
    static String syllable;
    TextView tv_syllable;
    TextView tv_word;
    TextView tv_tone;
    Button addPic_btn;
    ImageView ivImage;
    private DialogInterface.OnDismissListener onDismissListener;

    Bitmap output;
    static dataAdapter dataAdapter1;
    public static addPictureFragment newInstance() {
        addPictureFragment f = new addPictureFragment(c_id,syllable,dataAdapter1);
        return f;
    }

    public addPictureFragment(Integer c_id1,String syllable1,dataAdapter dataAdapter2) {
        c_id = c_id1;
        syllable = syllable1;
        dataAdapter1 = dataAdapter2;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        Log.d("setOnDismissListener","");
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addPictureFragment.this.getDialog().cancel();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (output!=null && dataAdapter1.insertPicture(output,c_id)){
                            addPic_btn.setText("新增成功");
                            AlertDialog dialog2 = new AlertDialog.Builder(getActivity())
                                    .setTitle("新增成功")
                                    .setMessage("新增成功")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })

                                    .show();
                        }else{
                            AlertDialog dialog3 = new AlertDialog.Builder(getActivity())
                                    .setTitle("新增失敗")
                                    .setMessage("新增失敗")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })

                                    .show();
                        }
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_picture, null);
        tv_syllable = (TextView) view.findViewById(R.id.addPicture_tv1);
        tv_word = (TextView) view.findViewById(R.id.addPicture_tv2);
        tv_tone = (TextView)view.findViewById(R.id.addPicture_tv3);
        addPic_btn = (Button) view.findViewById(R.id.btn_add_pic);
        ivImage = (ImageView) view.findViewById(R.id.addImageView);
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


        addPic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
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

    private void selectImage() {
        final CharSequence[] items = {"拍攝照片", "從媒體櫃選擇", "取消"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Log.d("Context", "" + getActivity());
        builder.setTitle("加入圖片!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("拍攝照片")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else if (items[item].equals("從媒體櫃選擇")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), 1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 0) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                output = thumbnail;
                ivImage.setImageBitmap(thumbnail);
            } else if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(getActivity(), selectedImageUri, projection, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                output = bm;
                ivImage.setImageBitmap(bm);
            }
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("onDismissListener",""+onDismissListener.toString());
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }



}
