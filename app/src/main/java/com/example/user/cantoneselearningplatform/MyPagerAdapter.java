package com.example.user.cantoneselearningplatform;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by user on 23/3/16.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 2;
    String word_product;
    public MyPagerAdapter(FragmentManager fragmentManager,String word_product1) {
        super(fragmentManager);
        this.word_product = word_product1;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                Log.d("syllable1 on Adapter", "" + word_product);
                return new wordfragment(word_product);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return picturefragment.newInstance(1, "文字");
//            case 2: // Fragment # 0 - This will show FirstFragment different title
//                return picturefragment.newInstance(2, "圖片");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "title";
        if (position==0){
            title = "設定文字";
        }else{
            if(position==1){
                title ="設定圖片";
            }
        }
        CharSequence cs = title;
        return cs;
    }

}


