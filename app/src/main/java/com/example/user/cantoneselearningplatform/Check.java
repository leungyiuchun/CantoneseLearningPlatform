package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 7/2/16.
 */
public class Check {
    String answer;
    String userAnswer;
    Integer correctDecision;
    String chin;
    String correct;
    public Check(String o_ans, String u_ans, Integer correctness,String chinese){
        answer = o_ans;
        userAnswer = u_ans;
        correctDecision = correctness;
        chin = chinese;
    }

    public String getCheckAnswer(){
        return answer;
    }
    public String getCheckUserAnswer(){
        return userAnswer;
    }
    public Integer getCorrectDecision(){
        return correctDecision;
    }
    public String getChinese(){return this.chin;}
}
