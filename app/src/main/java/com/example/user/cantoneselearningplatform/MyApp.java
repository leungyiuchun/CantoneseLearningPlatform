package com.example.user.cantoneselearningplatform;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 7/1/16.
 */
public class MyApp extends Application {


    public ArrayList<String> init_List = new ArrayList<String>();

    public ArrayList<String> vowel_List = new ArrayList<String>();

    String globalTaskString = "請選擇任務";
    String globalHintsString = "請選擇提示";
    String globalModeString = "請選擇模式";
    String globalQuantityString = "請設定已各組合數量";
    Integer globalTaskInt = 1;
    Integer globalHintInt = 0;
    Integer globalModeInt=0;
    Integer quantityInt = 0;
    public ArrayList<Combination> combination_list = new ArrayList<Combination>();
    public ArrayList<Exer> exercise_list = new ArrayList<Exer>();




    public ArrayList getInitList(){return this.init_List;}
    public void setInitList(ArrayList<String> initList){this.init_List = initList;}

    public ArrayList getVowelList(){return this.vowel_List;}
    public void setVowelList(ArrayList<String> vowelList){this.vowel_List = vowelList;}

    public String getTaskString(){return this.globalTaskString;}
    public void setTaskString(String taskString){this.globalTaskString =taskString;}

    public String getHintsString(){return this.globalHintsString;}
    public void setHintsString(String hintsString){this.globalHintsString =hintsString;}

    public String getModeString(){return this.globalModeString;}
    public void setModeString(String modeString){this.globalModeString =modeString;}
    public String getQuantityString(){return  this.globalQuantityString;}
    public Integer getTaskInt(){return this.globalTaskInt;}
    public void setTaskInt(Integer taskInt){this.globalTaskInt = taskInt;}

    public Integer getHintInt(){return this.globalHintInt;}
    public void setHintInt(Integer taskInt){this.globalHintInt = taskInt;}
    public Integer getModeInt(){return this.globalModeInt;}
    public void setModeInt(Integer modeInt){this.globalModeInt = modeInt;}

    public Integer getQuantityInt(){return this.quantityInt;}
    public void setQuantityInt(Integer qInt){this.quantityInt = qInt;}

    public ArrayList<Combination> getCombinationList(){return this.combination_list;}
    public void setCombinationList (ArrayList<Combination> combinationList){
//        this.combination_list.clear();
        this.combination_list = combinationList;}
    public ArrayList<Exer> getRandomlizeList(){
        ArrayList<Exer> randomList = new ArrayList<Exer>();
        getExerList();
        randomList = this.exercise_list;
        Collections.shuffle(randomList);
        Log.d("RandomSize",""+randomList.size());
        return  randomList;
    }
    public void getExerList(){
        exercise_list.clear();
        for(Integer i=0;i<combination_list.size();i++){
            for(Integer j=0;j<combination_list.get(i).getInt1();j++){
                Exer exer = new Exer(combination_list.get(i).getInit(),combination_list.get(i).getVowel(),combination_list.get(i).getCartProduct());
                exercise_list.add(exer);
            }
        }
    }

    Integer quantity_flag=0;
    public void setQuantityFlag(Integer flag){
        this.quantity_flag =flag;
    }
    public Integer getQuantityFlag(){
         return this.quantity_flag;
    }

    Integer init_flag=0;
    public void setInitFlag(Integer flag){
        this.init_flag = flag;
    }
    public Integer getInitflag(){
        return this.init_flag;
    }

    Integer vowel_flag = 0;
    public void setVowelFlag(Integer flag){
        this.vowel_flag = flag;
    }
    public Integer getVowel_flag(){
        return this.vowel_flag;
    }

    Integer reopen_init_flag=0;
    public void setInitReopenFlag(Integer flag){this.reopen_init_flag = flag;}
    public Integer getInitReopenFlag(){return this.reopen_init_flag;}
    public void addInitReopenFlag(){this.reopen_init_flag+=1;}

    Integer reopen_vowel_flag=0;
    public void setVowelReopenFlag(Integer flag){this.reopen_vowel_flag = flag;}
    public Integer getVowelReopenFlag(){return this.reopen_vowel_flag;}
    public void addVowelReopenFlag(){this.reopen_vowel_flag+=1;}

    ArrayList<Initobj> globalInitobj =new ArrayList<Initobj>();
    public void setglobalInitobj(ArrayList<Initobj> initobjs){
        this.globalInitobj = initobjs;
    }
    public ArrayList<Initobj> getglobalInitobj(){
        return globalInitobj;
    }

    ArrayList<Vowelobj>globalVowelobj = new ArrayList<Vowelobj>();
    public void setGlobalVowelobj(ArrayList<Vowelobj> vowelobj){this.globalVowelobj = vowelobj;}
    public ArrayList<Vowelobj> getGlobalVowelobj(){ return globalVowelobj;}

    String globalStudentName= " ";
    public void setStudentName(String name){
        this.globalStudentName = name;
    }
    public String getStudentName(){
        return globalStudentName;
    }

    Integer color_index = 0;
    public void setColorIndex(Integer index){this.color_index = index;}
    public Integer getColorIndex(){return this.color_index;}

    Boolean animBoolean =false;
    public void  setAnimBoolean(Boolean animBool){this.animBoolean = animBool;}
    public Boolean getAnimBoolean(){return this.animBoolean;}

    Float speakSpeed = 0.3f;
    public void setSpeakSpeed(Float speed){this.speakSpeed = speed;}
    public Float getSpeakSpeed(){return this.speakSpeed;}
}
