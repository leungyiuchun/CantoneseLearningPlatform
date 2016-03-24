package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 24/3/16.
 */
public class wordRecord {
    String syllable;
    Integer tone;
    public wordRecord(String syllable1,Integer tone1) {
        this.syllable = syllable1;
        this.tone = tone1;
    }
    public String getSyllable(){
        return this.syllable;
    }
    public void setSyllable(String syllable1){
        this.syllable = syllable1;
    }
    public Integer getTone(){
        return this.tone;
    }
    public void setTone(Integer tone1){
        this.tone = tone1;
    }
}
