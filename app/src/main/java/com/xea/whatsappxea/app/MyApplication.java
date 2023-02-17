package com.xea.whatsappxea.app;

import android.app.Application;

import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.Mensaje;
import com.xea.whatsappxea.models.User;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
/**
    //public static AtomicInteger userID = new AtomicInteger();
    public static AtomicInteger conversacionID = new AtomicInteger();
    public static AtomicInteger mensajeID = new AtomicInteger();

    @Override
    public void onCreate(){
        super.onCreate();

        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        //userID = getIdByTable(realm, User.class);
        conversacionID = getIdByTable(realm, Conversacion.class);
        mensajeID = getIdByTable(realm, Mensaje.class);
        realm.close();
    }

    private void  setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();


        if (results.size()>0){
            return new AtomicInteger(results.max("id").intValue()+1);
        }else{
            return new AtomicInteger(0);
        }

    }**/
}
