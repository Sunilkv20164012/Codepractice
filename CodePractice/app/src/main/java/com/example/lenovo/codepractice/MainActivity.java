package com.example.lenovo.codepractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class MainActivity extends AppCompatActivity {


    FDatabase myDatabase;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;

    RecyclerAdapter obj_1 = RecyclerAdapter.getInstance_1();
    RecyclerAdapter obj_2 = RecyclerAdapter.getInstance_2();

    Problems problems = Problems.getInstance();
    User muser = User.getUserObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Authenticate User
        authenticateUser();

        setContentView(R.layout.activity_main_layout);

        //Setting up the scroll view
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        PagerAdapter pagerAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        myDatabase = new FDatabase();

        //checkAuth();

        retrieveData();

        //retrieveUserData();

    }

    private void authenticateUser() {
        auth = FirebaseAuth.getInstance();
        //Log.e("Authenticating","User");
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // if user is null launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,user.getEmail(),Toast.LENGTH_LONG).show();
                }
            }
        };
        //Log.e("User","Authenticated");
    }


    private void retrieveData() {

        DatabaseReference prbReference = myDatabase.getProblemsReference();

        prbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Problem problem = dataSnapshot.getValue(Problem.class);
                problems.problemsList.add(problem);
                problems.unsolvedProblemsList.add(problem);
                try {
                    //muser.problemSet.get(User.KEY_UNSOLVED).add(problem.getProblemKey());
                }catch (NullPointerException e){
                    //Log.e("User ","Not Created");
                }
                obj_1.notifyDataSetChanged();
                obj_2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                /*Problem problem = dataSnapshot.getValue(Problem.class);
                for(Problem i: problems.problemsList){
                    if(problem.getTitle().equals(i.getTitle())){
                        problems.problemsList.remove(i);
                        try {
                            //muser.unsolvedProblems.remove(i);
                            //muser.solvedProblems.remove(i);
                        }catch (NullPointerException e){
                            //Log.e("User ","Not Created");
                        }
                        break;
                    }
                }
                obj_1.notifyDataSetChanged();
                obj_2.notifyDataSetChanged();*/
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

;           @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e("Couldn't Retrieve","Data");
            }
        });
    }


    /*private void retrieveUserData() {
        DatabaseReference userReference = myDatabase.getUsersReference();
        //DatabaseReference userSolvedProblemsReference = userReference.child("Solved Problems");

        userSolvedProblemsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Problem problem = dataSnapshot.getValue(Problem.class);
                try {
                    //muser.solvedProblems.add(problem);
                    //muser.unsolvedProblems.remove(problem);
                }catch (NullPointerException e){
                    //Log.e("User ","Not Created");
                }
                obj_1.notifyDataSetChanged();
                obj_2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

}
