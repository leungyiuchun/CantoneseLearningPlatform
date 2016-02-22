package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 6/2/16.
 */
public class Answer {
    String initAnswer;
    String vowelAnswer;
    String userAnswer;
    String chin;
    public Answer(String init,String vowel,String answer,String chineseWord){
        initAnswer = init;
        vowelAnswer = vowel;
        userAnswer = answer;
        chin = chineseWord;
    }
    public String getInitAnswer(){
        return this.initAnswer;
    }
    public String getVowelAnswer(){
        return this.vowelAnswer;
    }
    public String getUserAnswer(){
        return  this.userAnswer;
    }
    public String getChin(){return  this.chin;}
}
