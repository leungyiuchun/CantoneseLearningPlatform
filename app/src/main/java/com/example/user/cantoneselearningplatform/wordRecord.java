package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 24/3/16.
 */
public class wordRecord {
    Integer c_id;
    Integer s_id;
    String chin_word;
    Integer tone;
    public wordRecord(Integer c_id1, String chin_word1,Integer tone1,Integer s_id1) {
        this.c_id = c_id1;
        this.s_id = s_id1;
        this.chin_word = chin_word1;
        this.tone = tone1;
    }
    public String getchin_word(){
        return this.chin_word;
    }
    public void setchin_word(String chin_word1){
        this.chin_word = chin_word1;
    }
    public Integer getTone(){
        return this.tone;
    }
    public void setTone(Integer tone1){
        this.tone = tone1;
    }
    public Integer getC_id(){
        return this.c_id;
    }
    public void setC_id(Integer c_id1){
        this.c_id = c_id1;
    }
    public Integer getS_id(){
        return this.s_id;
    }
    public void setS_id(Integer s_id1){
        this.s_id = s_id1;
    }
}