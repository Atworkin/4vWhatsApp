package com.xea.whatsappxea.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Users extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String password;
    private String photo;

    public Users() {
    }

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Users(String name, String password, String photo) {
        this(name, password);
        this.photo = photo;
    }

    public int getId() {
        return id;
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
