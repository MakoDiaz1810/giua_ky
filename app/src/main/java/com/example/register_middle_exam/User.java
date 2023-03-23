package com.example.register_middle_exam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "user")
public class User implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
   private String username;
   private String password;
   private String confimpass;
   public User (String email,String username,String password,String confimpass){
       this.email = email;
       this.username = username;
       this.password = password;
       this.confimpass = confimpass;

   }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfimpass() {
        return confimpass;
    }

    public void setConfimpass(String confimpass) {
        this.confimpass = confimpass;
    }
}
