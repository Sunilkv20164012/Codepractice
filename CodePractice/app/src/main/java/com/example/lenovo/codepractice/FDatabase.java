package com.example.lenovo.codepractice;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class FDatabase {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference problemsReference;
    DatabaseReference usersReference;

    public FDatabase() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        if(problemsReference == null){
            problemsReference = firebaseDatabase.getReference().child("Problem");
            problemsReference.child("-L8qnZnMaMGKqjZopqL0").setValue(new Problem("Life Universe and Everything","-L8qnZnMaMGKqjZopqL0"));
        }
        if(usersReference == null){
            usersReference = firebaseDatabase.getReference().child("Users");
        }
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getProblemsReference() {
        return problemsReference;
    }

    public DatabaseReference getUsersReference() {
        return usersReference;
    }
}
