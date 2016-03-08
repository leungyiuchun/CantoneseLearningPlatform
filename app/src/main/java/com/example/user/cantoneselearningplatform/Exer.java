package com.example.user.cantoneselearningplatform;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user on 25/1/16.
 */
public class Exer implements Serializable{
    private static final long serialVersionUID = -5053412967314724078L;
    String init1;
    String vowel1;
    String cartProduct1;

    public Exer(String init, String vowel, String cartProduct){
        init1 = init;
        vowel1 = vowel;
        cartProduct1 = cartProduct;
    }

    public String getInit(){
        return init1;
    }
    public String getVowel(){
        return vowel1;
    }
    public String getCardProduct(){
        return cartProduct1;
    }

}
