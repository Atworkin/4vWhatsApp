package com.xea.whatsappxea.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xea.whatsappxea.R;
import com.xea.whatsappxea.adapter.RecyclerDataAdapter;
import com.xea.whatsappxea.models.Conversacion;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class AddFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerDataAdapter adapter;
    private List<Conversacion> listData;

    public AddFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewConversaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Conversacion> conversaciones = realm.where(Conversacion.class).findAll();
        List<Conversacion> list = new ArrayList<>(conversaciones);
        listData = list;
        return  view;
    }
}