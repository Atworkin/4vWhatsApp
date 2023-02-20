package com.xea.whatsappxea.FirebaseDB;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDB {

    private static FirebaseFirestore instance;

    public static FirebaseFirestore getInstance() {
        if (instance == null) {
            instance = FirebaseFirestore.getInstance();
        }
        return instance;
    }
}