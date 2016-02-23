package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 23/2/16.
 */
public class ChineseExer {
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
