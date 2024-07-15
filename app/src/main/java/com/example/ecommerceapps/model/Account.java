package com.example.ecommerceapps.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
