package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 22/2/16.
 */
public class Vowelobj {
    int id;
    Boolean clicked;
    public Vowelobj(int vowelId,Boolean click){
        this.id = vowelId;
        this.clicked = click;
    }
    public int getId(){return this.id;}
    public Boolean getBoolean(){return this.clicked;}
    public void setBoolean(Boolean click){this.clicked = click;}
}
