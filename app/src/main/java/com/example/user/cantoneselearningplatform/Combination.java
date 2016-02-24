package com.example.user.cantoneselearningplatform;

/**
 * Created by user on 27/1/16.
 */
public class Combination {
    Exer exer;
    Integer number;
    Boolean clicked;

    public Combination(Exer exer1,Integer int1,Boolean click){
        exer = exer1;
        number = int1;
        clicked = click;
    }
    public Integer getInt1(){
        return number;
    }

    public String getInit(){
        return exer.getInit();
    }

    public String getVowel(){
        return exer.getVowel();
    }
    public String getCartProduct(){
        return exer.getCardProduct();
    }
    public Boolean getClicked(){return this.clicked;}
    public void setClicked(Boolean click){this.clicked = click;}
}
