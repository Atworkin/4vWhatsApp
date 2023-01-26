package com.xea.whatsappxea.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User  {

    private String name;
    private String password;
    private String photo;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String photo) {
        this(name, password);
        this.photo = photo;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
