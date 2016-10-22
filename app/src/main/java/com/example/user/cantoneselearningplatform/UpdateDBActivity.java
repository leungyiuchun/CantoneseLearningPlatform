package com.example.user.cantoneselearningplatform;

import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UpdateDBActivity extends AppCompatActivity {
    Button updateBtn;
    dataAdapter dataAdapter1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_db);
        updateBtn = (Button)findViewById(R.id.update_db_btn);
        tv = (TextView)findViewById(R.id.tv_uri);
        dataAdapter1 = new dataAdapter(this);

        if(((MyApp)getApplication()).getDbUri() !=null){
            File file = new File(((MyApp)getApplication()).getDbUri().getPath());
            tv.setText(file.getName());
        }else{
            tv.setText("未選擇特定資料庫");
        }
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocument();
            }
        });
    }

    private void selectDocument() {
        final CharSequence[] items = { "從裝置中選擇", "取消"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更新資料庫!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("從裝置中選擇")) {
                    Intent intent = new Intent(
                            Intent.ACTION_OPEN_DOCUMENT
                            );
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("*/*");
                    String[] mime = {"*/*"};
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mime);
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), 1);
                } else if (items[item].equals("取消")) {
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
            if (requestCode == 1) {
                Uri selectedDBUri = data.getData();
                String uriString = selectedDBUri.toString();
                File myFile = new File(uriString);
                String path = myFile.getAbsolutePath();
                String displayName = null;

                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = getContentResolver().query(selectedDBUri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    displayName = myFile.getName();
                }
                System.out.println("displayName: " +displayName);
                System.out.println("displayName path " + path );
                Log.d("uri", selectedDBUri.getPath().toString());
                ((MyApp)getApplication()).setDbUri(selectedDBUri);
                File file = new File(((MyApp)getApplication()).getDbUri().getPath());
                tv.setText(file.getName());
            }
        }
    }
}
