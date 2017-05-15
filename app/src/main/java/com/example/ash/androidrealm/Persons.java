package com.example.ash.androidrealm;

import io.realm.RealmObject;

/**
 * Created by ash on 5/16/2017.
 */

public class Persons extends RealmObject {

    String name;
    String email;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
