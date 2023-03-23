package com.example.register_middle_exam.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.register_middle_exam.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();
    @Query("SELECT * FROM user where username= :username")
    List<User> checkUser(String username);
}