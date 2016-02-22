package com.example.user.cantoneselearningplatform;

import android.widget.Button;

/**
 * Created by user on 21/2/16.
 */
public class Initobj {
    String initial;
    Boolean clicked;

    public Initobj(String init, Boolean boo){
        this.initial = init;
        this.clicked = boo;
    }

    public String getButtonString(){
        return this.initial;
    }
    public Boolean getBoolean(){
        return this.clicked;
    }
    public void setBoolean(Boolean click){
        this.clicked=click;
    }

}
