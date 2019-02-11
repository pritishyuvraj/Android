package com.example.fragmentsampledaysofweek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentManagement extends FragmentPagerAdapter {
    public FragmentManagement(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new Monday();
            case 1: return new Tuesday();
            case 2: return new Wednesday();
            default: return new Monday();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
