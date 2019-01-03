package com.example.lenovo.codepractice;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Lenovo on 25-Mar-18.
 */

public class User {

    public static String KEY_SOLVED = "solvedProblems";


    private static User userObject;
    ArrayList<String> problemSet;
    private String username;
    private String userEmail;
    private String userKey;

    private User(String name, String email, String key, ArrayList<String> prb) {
        this.problemSet = prb;
        this.userEmail = email;
        this.username = name;
        this.userKey = key;
    }

    public static User getInstance(String name, String email,String key, ArrayList<String> prb){
        if(userObject == null)
            userObject = new User(name, email, key, prb);
        return userObject;
    }

    public static User getUserObject() {
        return userObject;
    }

    public static void setUserObject(User userObject) {

        User.userObject = userObject;
    }

    public User() {

    }

    public String getUserKey() {

        return userKey;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
