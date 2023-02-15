package com.xea.whatsappxea.models;

import com.xea.whatsappxea.R;

import java.io.Serializable;
import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable {

    @PrimaryKey
    private String telNumber;

    private String name;
    private String password;
    private int photo;

    public User() {
    }

    public User(String name, String password,String telNumber) {
        this.telNumber = telNumber;
        this.name = name;
        this.password = password;
        this.photo = R.drawable.defaultuser;
    }

    public User(String name, String password,String telNumber, int photo) {
        this(name, password,telNumber);
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

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return telNumber.equals(user.telNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telNumber);
    }
}
