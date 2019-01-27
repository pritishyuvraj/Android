package com.example.pritish.trytabview;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayouts extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentNumber = new ArrayList<>();
    private final List<String> mFragmentTitle = new ArrayList<>();

    public TabLayouts(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return mFragmentTitle.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentNumber.get(i);
    }

    public void addFragment(Fragment fragment, String fragmentTitle){
        mFragmentTitle.add(fragmentTitle);
        mFragmentNumber.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }


}
