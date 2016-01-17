package com.example.user.cantoneselearningplatform;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 7/1/16.
 */
public class MyApp extends Application {

    public Boolean init_1_clicked=false;
    public Boolean init_2_clicked=false;
    public Boolean init_3_clicked=false;
    public Boolean init_4_clicked=false;
    public Boolean init_5_clicked=false;
    public Boolean init_6_clicked=false;
    public Boolean init_7_clicked=false;
    public Boolean init_8_clicked=false;
    public Boolean init_9_clicked=false;
    public Boolean init_10_clicked=false;
    public Boolean init_11_clicked=false;
    public Boolean init_12_clicked=false;
    public Boolean init_13_clicked=false;
    public Boolean init_14_clicked=false;
    public Boolean init_15_clicked=false;
    public Boolean init_16_clicked=false;
    public Boolean init_17_clicked=false;
    public Boolean init_18_clicked=false;
    public Boolean init_19_clicked=false;
    public ArrayList<String> init_List = new ArrayList<String>();

    public Boolean vowel_1_clicked=false;
    public Boolean vowel_3_clicked=false;
    public Boolean vowel_4_clicked=false;
    public Boolean vowel_5_clicked=false;
    public Boolean vowel_6_clicked=false;
    public Boolean vowel_8_clicked=false;
    public Boolean vowel_9_clicked=false;
    public Boolean vowel_10_clicked=false;
    public Boolean vowel_11_clicked=false;
    public Boolean vowel_12_clicked=false;
    public Boolean vowel_13_clicked=false;
    public Boolean vowel_14_clicked=false;
    public Boolean vowel_15_clicked=false;
    public Boolean vowel_17_clicked=false;
    public Boolean vowel_18_clicked=false;
    public Boolean vowel_19_clicked=false;
    public Boolean vowel_20_clicked=false;
    public Boolean vowel_22_clicked=false;
    public Boolean vowel_23_clicked=false;
    public Boolean vowel_24_clicked=false;
    public Boolean vowel_26_clicked=false;
    public Boolean vowel_27_clicked=false;
    public Boolean vowel_28_clicked=false;
    public Boolean vowel_29_clicked=false;
    public Boolean vowel_30_clicked=false;
    public Boolean vowel_31_clicked=false;
    public Boolean vowel_32_clicked=false;
    public Boolean vowel_34_clicked=false;
    public Boolean vowel_35_clicked=false;
    public Boolean vowel_36_clicked=false;
    public Boolean vowel_37_clicked=false;
    public Boolean vowel_38_clicked=false;
    public Boolean vowel_39_clicked=false;
    public Boolean vowel_40_clicked=false;
    public Boolean vowel_42_clicked=false;
    public Boolean vowel_43_clicked=false;
    public Boolean vowel_44_clicked=false;
    public Boolean vowel_46_clicked=false;
    public Boolean vowel_47_clicked=false;
    public Boolean vowel_48_clicked=false;
    public Boolean vowel_49_clicked=false;
    public Boolean vowel_50_clicked=false;
    public Boolean vowel_51_clicked=false;
    public Boolean vowel_52_clicked=false;
    public Boolean vowel_54_clicked=false;
    public Boolean vowel_55_clicked=false;
    public Boolean vowel_56_clicked=false;
    public Boolean vowel_57_clicked=false;
    public Boolean vowel_58_clicked=false;
    public Boolean vowel_59_clicked=false;
    public Boolean vowel_60_clicked=false;
    public ArrayList<String> vowel_List = new ArrayList<String>();

    String[] globalVowelArray = {""};
    String globalTaskString = "請選擇任務";
    String globalHintsString = "請選擇提示";
    Integer globalTaskInt = 0;
    Integer globalHintInt = 0;

    public int global_init_index=0;
    public int del_init=0;
    public Boolean get_init_1(){return  this.init_1_clicked;}
    public void set_init_1(Boolean status){init_1_clicked = status;}
    public Boolean get_init_2(){return  this.init_2_clicked;}
    public void set_init_2(Boolean status){init_2_clicked = status;}
    public Boolean get_init_3(){return  this.init_3_clicked;}
    public void set_init_3(Boolean status){init_3_clicked = status;}
    public Boolean get_init_4(){return  this.init_4_clicked;}
    public void set_init_4(Boolean status){init_4_clicked = status;}
    public Boolean get_init_5(){return  this.init_5_clicked;}
    public void set_init_5(Boolean status){init_5_clicked = status;}
    public Boolean get_init_6(){return  this.init_6_clicked;}
    public void set_init_6(Boolean status){init_6_clicked = status;}
    public Boolean get_init_7(){return  this.init_7_clicked;}
    public void set_init_7(Boolean status){init_7_clicked = status;}
    public Boolean get_init_8(){return  this.init_8_clicked;}
    public void set_init_8(Boolean status){init_8_clicked = status;}
    public Boolean get_init_9(){return  this.init_9_clicked;}
    public void set_init_9(Boolean status){init_9_clicked = status;}
    public Boolean get_init_10(){return  this.init_10_clicked;}
    public void set_init_10(Boolean status){init_10_clicked = status;}
    public Boolean get_init_11(){return  this.init_11_clicked;}
    public void set_init_11(Boolean status){init_11_clicked = status;}
    public Boolean get_init_12(){return  this.init_12_clicked;}
    public void set_init_12(Boolean status){init_12_clicked = status;}
    public Boolean get_init_13(){return  this.init_13_clicked;}
    public void set_init_13(Boolean status){init_13_clicked = status;}
    public Boolean get_init_14(){return  this.init_14_clicked;}
    public void set_init_14(Boolean status){init_14_clicked = status;}
    public Boolean get_init_15(){return  this.init_15_clicked;}
    public void set_init_15(Boolean status){init_15_clicked = status;}
    public Boolean get_init_16(){return  this.init_16_clicked;}
    public void set_init_16(Boolean status){init_16_clicked = status;}
    public Boolean get_init_17(){return  this.init_17_clicked;}
    public void set_init_17(Boolean status){init_17_clicked = status;}
    public Boolean get_init_18(){return  this.init_18_clicked;}
    public void set_init_18(Boolean status){init_18_clicked = status;}
    public Boolean get_init_19(){return  this.init_19_clicked;}
    public void set_init_19(Boolean status){init_19_clicked = status;}

    public Boolean get_vowel_1(){return this.vowel_1_clicked;}
    public void set_vowel_1(Boolean status){vowel_1_clicked=status;}
    public Boolean get_vowel_3(){return this.vowel_3_clicked;}
    public void set_vowel_3(Boolean status){vowel_3_clicked=status;}
    public Boolean get_vowel_4(){return this.vowel_4_clicked;}
    public void set_vowel_4(Boolean status){vowel_4_clicked=status;}
    public Boolean get_vowel_5(){return this.vowel_5_clicked;}
    public void set_vowel_5(Boolean status){vowel_5_clicked=status;}
    public Boolean get_vowel_6(){return this.vowel_6_clicked;}
    public void set_vowel_6(Boolean status){vowel_6_clicked=status;}
    public Boolean get_vowel_8(){return this.vowel_8_clicked;}
    public void set_vowel_8(Boolean status){vowel_8_clicked=status;}
    public Boolean get_vowel_9(){return this.vowel_9_clicked;}
    public void set_vowel_9(Boolean status){vowel_9_clicked=status;}
    public Boolean get_vowel_10(){return this.vowel_10_clicked;}
    public void set_vowel_10(Boolean status){vowel_10_clicked=status;}
    public Boolean get_vowel_11(){return this.vowel_11_clicked;}
    public void set_vowel_11(Boolean status){vowel_11_clicked=status;}
    public Boolean get_vowel_12(){return this.vowel_12_clicked;}
    public void set_vowel_12(Boolean status){vowel_12_clicked=status;}
    public Boolean get_vowel_13(){return this.vowel_13_clicked;}
    public void set_vowel_13(Boolean status){vowel_13_clicked=status;}
    public Boolean get_vowel_14(){return this.vowel_14_clicked;}
    public void set_vowel_14(Boolean status){vowel_14_clicked=status;}
    public Boolean get_vowel_15(){return this.vowel_15_clicked;}
    public void set_vowel_15(Boolean status){vowel_15_clicked=status;}
    public Boolean get_vowel_17(){return this.vowel_17_clicked;}
    public void set_vowel_17(Boolean status){vowel_17_clicked=status;}
    public Boolean get_vowel_18(){return this.vowel_18_clicked;}
    public void set_vowel_18(Boolean status){vowel_18_clicked=status;}
    public Boolean get_vowel_19(){return this.vowel_19_clicked;}
    public void set_vowel_19(Boolean status){vowel_19_clicked=status;}
    public Boolean get_vowel_20(){return this.vowel_20_clicked;}
    public void set_vowel_20(Boolean status){vowel_20_clicked=status;}
    public Boolean get_vowel_22(){return this.vowel_22_clicked;}
    public void set_vowel_22(Boolean status){vowel_22_clicked=status;}
    public Boolean get_vowel_23(){return this.vowel_23_clicked;}
    public void set_vowel_23(Boolean status){vowel_23_clicked=status;}
    public Boolean get_vowel_24(){return this.vowel_24_clicked;}
    public void set_vowel_24(Boolean status){vowel_24_clicked=status;}
    public Boolean get_vowel_26(){return this.vowel_26_clicked;}
    public void set_vowel_26(Boolean status){vowel_26_clicked=status;}
    public Boolean get_vowel_27(){return this.vowel_27_clicked;}
    public void set_vowel_27(Boolean status){vowel_27_clicked=status;}
    public Boolean get_vowel_28(){return this.vowel_28_clicked;}
    public void set_vowel_28(Boolean status){vowel_28_clicked=status;}
    public Boolean get_vowel_29(){return this.vowel_29_clicked;}
    public void set_vowel_29(Boolean status){vowel_29_clicked=status;}
    public Boolean get_vowel_30(){return this.vowel_30_clicked;}
    public void set_vowel_30(Boolean status){vowel_30_clicked=status;}
    public Boolean get_vowel_31(){return this.vowel_31_clicked;}
    public void set_vowel_31(Boolean status){vowel_31_clicked=status;}
    public Boolean get_vowel_32(){return this.vowel_32_clicked;}
    public void set_vowel_32(Boolean status){vowel_32_clicked=status;}
    public Boolean get_vowel_34(){return this.vowel_34_clicked;}
    public void set_vowel_34(Boolean status){vowel_34_clicked=status;}
    public Boolean get_vowel_35(){return this.vowel_35_clicked;}
    public void set_vowel_35(Boolean status){vowel_35_clicked=status;}
    public Boolean get_vowel_36(){return this.vowel_36_clicked;}
    public void set_vowel_36(Boolean status){vowel_36_clicked=status;}
    public Boolean get_vowel_37(){return this.vowel_37_clicked;}
    public void set_vowel_37(Boolean status){vowel_37_clicked=status;}
    public Boolean get_vowel_38(){return this.vowel_38_clicked;}
    public void set_vowel_38(Boolean status){vowel_38_clicked=status;}
    public Boolean get_vowel_39(){return this.vowel_39_clicked;}
    public void set_vowel_39(Boolean status){vowel_39_clicked=status;}
    public Boolean get_vowel_40(){return this.vowel_40_clicked;}
    public void set_vowel_40(Boolean status){vowel_40_clicked=status;}
    public Boolean get_vowel_42(){return this.vowel_42_clicked;}
    public void set_vowel_42(Boolean status){vowel_42_clicked=status;}
    public Boolean get_vowel_43(){return this.vowel_43_clicked;}
    public void set_vowel_43(Boolean status){vowel_43_clicked=status;}
    public Boolean get_vowel_44(){return this.vowel_44_clicked;}
    public void set_vowel_44(Boolean status){vowel_44_clicked=status;}
    public Boolean get_vowel_46(){return this.vowel_46_clicked;}
    public void set_vowel_46(Boolean status){vowel_46_clicked=status;}
    public Boolean get_vowel_47(){return this.vowel_47_clicked;}
    public void set_vowel_47(Boolean status){vowel_47_clicked=status;}
    public Boolean get_vowel_48(){return this.vowel_48_clicked;}
    public void set_vowel_48(Boolean status){vowel_48_clicked=status;}
    public Boolean get_vowel_49(){return this.vowel_49_clicked;}
    public void set_vowel_49(Boolean status){vowel_49_clicked=status;}
    public Boolean get_vowel_50(){return this.vowel_50_clicked;}
    public void set_vowel_50(Boolean status){vowel_50_clicked=status;}
    public Boolean get_vowel_51(){return this.vowel_51_clicked;}
    public void set_vowel_51(Boolean status){vowel_51_clicked=status;}
    public Boolean get_vowel_52(){return this.vowel_52_clicked;}
    public void set_vowel_52(Boolean status){vowel_52_clicked=status;}
    public Boolean get_vowel_54(){return this.vowel_54_clicked;}
    public void set_vowel_54(Boolean status){vowel_54_clicked=status;}
    public Boolean get_vowel_55(){return this.vowel_55_clicked;}
    public void set_vowel_55(Boolean status){vowel_55_clicked=status;}
    public Boolean get_vowel_56(){return this.vowel_56_clicked;}
    public void set_vowel_56(Boolean status){vowel_56_clicked=status;}
    public Boolean get_vowel_57(){return this.vowel_57_clicked;}
    public void set_vowel_57(Boolean status){vowel_57_clicked=status;}
    public Boolean get_vowel_58(){return this.vowel_58_clicked;}
    public void set_vowel_58(Boolean status){vowel_58_clicked=status;}
    public Boolean get_vowel_59(){return this.vowel_59_clicked;}
    public void set_vowel_59(Boolean status){vowel_59_clicked=status;}
    public Boolean get_vowel_60(){return this.vowel_60_clicked;}
    public void set_vowel_60(Boolean status){vowel_60_clicked=status;}

    public void addInit(String text){
        init_List.add(text);
    }
    public void delInit(String text){
        init_List.remove(text);
    }

    public void addVowel(String text){
        vowel_List.add(text);
    }
    public void delVowel(String text){
        vowel_List.remove(text);
    }

    public List getInitList(){return this.init_List;}
    public List getVowelList(){return this.vowel_List;}

    public String[] getVowelArray(){return this.globalVowelArray;}
    public void setVowelArray(String[] vowelArray){this.globalVowelArray = vowelArray;}

    public String getTaskString(){return this.globalTaskString;}
    public void setTaskString(String taskString){this.globalTaskString =taskString;}

    public String getHintsString(){return this.globalHintsString;}
    public void setHintsString(String hintsString){this.globalHintsString =hintsString;}

    public Integer getTaskInt(){return this.globalTaskInt;}
    public void setTaskInt(Integer taskInt){this.globalTaskInt = taskInt;}

    public Integer getHintInt(){return this.globalHintInt;}
    public void setHintInt(Integer taskInt){this.globalHintInt = taskInt;}
}
