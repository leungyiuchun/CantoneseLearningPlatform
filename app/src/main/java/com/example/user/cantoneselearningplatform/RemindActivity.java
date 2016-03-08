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
                capScreen();
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
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
