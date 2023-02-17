package com.xea.whatsappxea.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.RecyclerDataAdapter;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}