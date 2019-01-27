package com.example.pritish.trytabview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] tabIcons = {
                R.drawable.home_foreground,
                R.drawable.home_foreground,
                R.drawable.home_foreground
        };

        ViewPager viewPager = (ViewPager) findViewById(R.id.pagerMode);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabMode);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabMode1);

        TabLayouts tabLayouts = new TabLayouts(getSupportFragmentManager());
        tabLayouts.addFragment(new tab1(), "Tab 1");
        tabLayouts.addFragment(new tab2(), "Tab 2");
        tabLayouts.addFragment(new tab3(), "Tab 3");



        viewPager.setAdapter(tabLayouts);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout2.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}
