package com.example.user.cantoneselearningplatform;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user on 23/3/16.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
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
                return wordfragment.newInstance(0, "ABC");
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


