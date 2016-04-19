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
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
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
    TextView studentName;
    TextView studentMode;
    TextView studentTask;
    TextView studentHint;
    TextView time;
    TextView path;
    Button leaveBtn;
    Button saveBtn;
    String currentTime = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent iin= getIntent();
        iin.getExtras();
        this.answerList = (ArrayList<Answer>)iin.getSerializableExtra("Answerlist");
        this.chinList = (ArrayList<ChineseExer>)iin.getSerializableExtra("chineseExerList");

        totalQuestion = ((MyApp) getApplication()).getQuantityInt();
        totalQuantity = (TextView)findViewById(R.id.tv_totalQuantity2);
        totalQuantity.setText(totalQuestion.toString());
        totalCorrect = (TextView)findViewById(R.id.tv_totalCorrect);
        correctPercent = (TextView)findViewById(R.id.tv_correctPercent);
        ll_testList = (LinearLayout)findViewById(R.id.ll_testList);
        studentName = (TextView)findViewById(R.id.result_name);
        studentMode = (TextView)findViewById(R.id.result_mode);
        studentTask = (TextView)findViewById(R.id.result_task);
        studentHint = (TextView)findViewById(R.id.result_hints);
        path = (TextView)findViewById(R.id.path_tv);
        path.setText(Environment.getExternalStorageDirectory().toString());
        time = (TextView)findViewById(R.id.result_date);
        studentName.setText(((MyApp) getApplication()).getStudentName());
        studentMode.setText(((MyApp) getApplication()).getModeString());
        studentTask.setText(((MyApp) getApplication()).getTaskString());
        studentHint.setText(((MyApp) getApplication()).getHintsString());

        leaveBtn = (Button)findViewById(R.id.leave_button);
        saveBtn = (Button)findViewById(R.id.leave_and_save_button);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = sdf.format(new Date());
        time.setText(currentTime);

        insertCheck();
        updateList(ll_testList);
        totalCorrect.setText(String.format("%d", correctIndex));
        percent = (float)correctIndex/totalQuestion;
        percent_present = percent*100 + "%";
        correctPercent.setText(percent_present);
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
                try{genCSV();}catch (Exception e){}
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
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
    public void updateList(LinearLayout ll){
        ListView list = new ListView(getApplicationContext());
        final checkListAdapter customAdapter = new checkListAdapter(getApplicationContext(),R.layout.checklistrow,getDisplayCheckList());
        ll.removeAllViewsInLayout();
        list.setAdapter(customAdapter);
        ll.addView(list);
    }
    public ArrayList<Check> getDisplayCheckList(){
        ArrayList<Check> newCheckList = new ArrayList<Check>();
        Integer intC = 5;
        Check newCheck = new Check("拼音答案","學生答案",intC,"中文答案");
        newCheckList.add(newCheck);
        for (int i =0;i<checkList.size();i++){
            newCheckList.add(checkList.get(i));
        }
        return newCheckList;
    }
    public void capScreen(){
        try{
            String name = Environment.getExternalStorageDirectory().toString() + "/" + "Test_" + studentName.getText() + " " +currentTime +".jpg";
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
            Log.d("Print", "done");

        }catch (Throwable e){
            e.printStackTrace();
            Log.d("Print", "fail");
            Log.d("Fail",""+e.toString());
        }
    }
    public void genCSV()throws IOException{
        String name = Environment.getExternalStorageDirectory().toString() + "/" + "Test_" + studentName.getText() + " " +currentTime +".csv";
        FileWriter fw = new FileWriter(name);
        try{
            fw.append("姓名");
            fw.append(',');
            fw.append("練習模式");
            fw.append(',');
            fw.append("選填");
            fw.append(',');
            fw.append("提示");
            fw.append(',');
            fw.append("日期");
            fw.append(',');
            fw.append('\n');

            fw.append(((MyApp) getApplication()).getStudentName());
            fw.append(',');
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
            fw.append(totalQuestion.toString());
            fw.append("答對題數：");
            fw.append(String.format("%d", correctIndex));
            fw.append("答對率：");
            fw.append(percent_present);
            fw.append('\n');

            fw.append("題數");
            fw.append(',');
            fw.append("拼音答案");
            fw.append(',');
            fw.append("中文答案");
            fw.append(',');
            fw.append("學生答案");
            fw.append(',');
            fw.append("結果");
            fw.append(',');
            fw.append('\n');

            for (int a=1;a<getDisplayCheckList().size();a++){
             Integer index = a;
                fw.append(index.toString());
                fw.append(',');
                fw.append(getDisplayCheckList().get(a).getCheckAnswer());
                fw.append(',');
                fw.append(getDisplayCheckList().get(a).getChinese());
                fw.append(',');
                fw.append(getDisplayCheckList().get(a).getCheckUserAnswer());
                fw.append(',');
                if(getDisplayCheckList().get(a).getCorrectDecision().intValue()==0){
                    fw.append("正確");
                    fw.append(',');
                }else {
                    fw.append("錯誤");
                    fw.append(',');
                }

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
