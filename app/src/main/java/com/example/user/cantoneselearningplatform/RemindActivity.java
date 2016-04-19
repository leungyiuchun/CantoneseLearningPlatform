package com.example.user.cantoneselearningplatform;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RemindActivity extends AppCompatActivity {
    ArrayList<Answer> answerList;
    public TextView totalQuantity;
    Integer totalQuestion;
    LinearLayout ll_remindList;
    TextView studentMode;
    TextView studentTask;
    TextView studentHint;
    TextView time;
    TextView path;
    String currentTime = " ";
    Button leaveBtn;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        Intent iin= getIntent();
        iin.getExtras();
        this.answerList = (ArrayList<Answer>)iin.getSerializableExtra("Arraylist");

        totalQuestion = ((MyApp)getApplication()).getQuantityInt();
        ll_remindList = (LinearLayout)findViewById(R.id.ll_remindList);
        totalQuantity = (TextView)findViewById(R.id.tv_totalQuantity2);
        totalQuantity.setText(String.format("%d",totalQuestion));
        studentMode = (TextView) findViewById(R.id.remind_mode);
        studentTask = (TextView)findViewById(R.id.remind_task);
        studentHint = (TextView)findViewById(R.id.remind_hints);
        path = (TextView)findViewById(R.id.path_tv);
        path.setText(Environment.getExternalStorageDirectory().toString());
        time = (TextView)findViewById(R.id.remind_date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = sdf.format(new Date());
        time.setText(currentTime);

        studentMode.setText(((MyApp) getApplication()).getModeString());
        studentTask.setText(((MyApp) getApplication()).getTaskString());
        studentHint.setText(((MyApp) getApplication()).getHintsString());
        updateList(ll_remindList);

        leaveBtn = (Button)findViewById(R.id.leave_button);
        saveBtn = (Button)findViewById(R.id.leave_and_save_button);

        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{ genCSV();
                }
                catch (Exception e){
                    Log.d("","genCSV");
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
    public void updateList( LinearLayout ll){
        ListView list = new ListView(this.getApplicationContext());
        final remindListAdapter customAdapter = new remindListAdapter(this.getApplicationContext(),R.layout.remindlistrow,getDisplayAnswerList());
        ll.removeAllViewsInLayout();
        list.setAdapter(customAdapter);
        ll.addView(list);
    }
    public ArrayList<Answer> getDisplayAnswerList(){
        ArrayList<Answer> displayAnswer = new ArrayList<Answer>();
        Answer answer = new Answer(null,null,"拼音答案","中文答案");
        displayAnswer.add(answer);
        for(int i = 0;i<answerList.size();i++){
            displayAnswer.add(answerList.get(i));
        }
        return displayAnswer;
    }
    public void capScreen(){
        try{
            String name = Environment.getExternalStorageDirectory().toString() + "/" + "Exercise_" +currentTime +".jpg";
            View v1 = getWindow().getDecorView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(name);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
            Log.d("Print","done");
        }catch (Throwable e){
            e.printStackTrace();
            Log.d("Print", "fail");

        }
    }
    public void genCSV() throws IOException{
        String name = Environment.getExternalStorageDirectory().toString() + "/" + "Exercise_" +currentTime +".csv";
        FileWriter fw = new FileWriter(name);

        try{
            fw.append("練習模式");
            fw.append(',');
            fw.append("選填");
            fw.append(',');
            fw.append("提示");
            fw.append(',');
            fw.append("日期");
            fw.append(',');
            fw.append('\n');

            fw.append(((MyApp) getApplication()).getModeString());
            fw.append(',');
            fw.append(((MyApp) getApplication()).getTaskString());
            fw.append(',');
            fw.append(((MyApp) getApplication()).getHintsString());
            fw.append(',');
            fw.append(currentTime);
            fw.append(',');

            fw.append('\n');
            fw.append("總題數：");
            fw.append(String.format("%d",totalQuestion));
            fw.append('\n');

            fw.append("題數");
            fw.append(',');
            fw.append("拼音答案");
            fw.append(',');
            fw.append("中文答案");
            fw.append(',');
            fw.append('\n');

            for (int i =1;i<getDisplayAnswerList().size();i++){
                Integer index = i;
                fw.append(index.toString());
                fw.append(',');
                fw.append(getDisplayAnswerList().get(i).getUserAnswer());
                fw.append(',');
                fw.append(getDisplayAnswerList().get(i).getChin());
                fw.append(',');
                fw.append('\n');
            }
            fw.close();
            Toast.makeText(this.getApplicationContext(), "已儲存於： "+name, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this.getApplicationContext(), "儲存失敗", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onBackPressed() {

    }
}
