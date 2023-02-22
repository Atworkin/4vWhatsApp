package com.xea.whatsappxea.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.activities.ChatActivity;
import com.xea.whatsappxea.activities.ContactosActivity;
import com.xea.whatsappxea.activities.MainActivity;
import com.xea.whatsappxea.adapter.RecyclerContactos;
import com.xea.whatsappxea.adapter.RecyclerConversaciones;
import com.xea.whatsappxea.adapter.RecyclerDataAdapter;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class AddFragment extends Fragment {



    public AddFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);



        return  view;
    }
}