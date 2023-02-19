package com.xea.whatsappxea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.xea.whatsappxea.models.User;

public class MainActivity extends AppCompatActivity {


    //FirebaseFirestore db;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userLogged = (String) getIntent().getStringExtra("userLogged");
        //Toast.makeText(MainActivity.this, userLogged.getName(), Toast.LENGTH_SHORT).show();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("SHOW"));
        tabLayout.addTab(tabLayout.newTab().setText("DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("ADD"));
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

        Button myButton = findViewById(R.id.btnContactos);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactosActivity.class);
                intent.putExtra("userLogged",userLogged);
                startActivity(intent);
            }
        });

    }
}