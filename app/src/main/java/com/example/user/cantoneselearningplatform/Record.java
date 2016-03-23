package com.example.user.cantoneselearningplatform;

import java.sql.Blob;

/**
 * Created by user on 20/3/16.
 */
public class Record {
    String combination;
    Integer id;
    String chin_word;
    Integer tone;
    Blob picture;

    public Record(String combination1,Integer id1,String chin_word1,Integer tone1,Blob picture1) {
        this.combination = combination1;
        this.id = id1;
        this.chin_word = chin_word1;
        this.tone = tone1;
        this.picture = picture1;
    }
    public String getCombination(){
        return this.combination;
    }
    public void setCombination(String combination1){
        this.combination = combination1;
    }
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id1){
        this.id = id1;
    }
    public String getChin_word(){
        return this.chin_word;
    }
    public void setChin_word(String chin_word1){
        this.chin_word = chin_word1;
    }
    public Integer getTone(){
        return this.tone;
    }
    public void setTone(Integer tone1){
        this.tone = tone1;
    }
    public Blob getPicture(){
        return this.picture;
    }
    public void setPicture(Blob picture1){
        this.picture = picture1;
    }
}
