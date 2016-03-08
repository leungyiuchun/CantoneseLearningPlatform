package com.example.user.cantoneselearningplatform;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user on 23/2/16.
 */
public class ChineseExer implements Serializable{
    private static final long ParcelableVersionUID = -5053412967314724078L;

    Exer exers;
    String chinese;
    public ChineseExer(Exer exer,String word){
        this.exers=exer;
        this.chinese = word;
    }
    public String getChineseExerInit(){
        return exers.getInit();
    }
    public String getChineseExerVowel(){
        return exers.getVowel();
    }
    public String getChineseExerCardProduct(){
        return exers.getCardProduct();
    }
    public String getChineseExerWord(){
        return this.chinese;
    }

}
