package com.xea.whatsappxea.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.MyViewPagerAdapter;
import com.xea.whatsappxea.models.Hobby;
import com.google.android.material.tabs.TabLayout;
import com.xea.whatsappxea.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("SHOW"));
        tabLayout.addTab(tabLayout.newTab().setText("DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("ADD"));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       

    }
}