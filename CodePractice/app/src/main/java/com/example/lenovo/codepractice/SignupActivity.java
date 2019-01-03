package com.example.lenovo.codepractice;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity" ;
    private Button btnSignUp,btnLinkToLogIn;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private EditText signupInputEmail, signupInputPassword, signupInputUsername;
    private TextInputLayout  signupInputLayoutEmail, signupInputLayoutPassword, signupInputLayoutUsername;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        signupInputLayoutEmail = (TextInputLayout) findViewById(R.id.signup_input_layout_email);
        signupInputLayoutPassword = (TextInputLayout) findViewById(R.id.signup_input_layout_password);
        signupInputLayoutUsername = (TextInputLayout) findViewById(R.id.signup_input_layout_username);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_password);
        signupInputUsername = (EditText) findViewById(R.id.signup_input_username);



        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLinkToLogIn = (Button) findViewById(R.id.btn_link_login);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();

            }
        });

        btnLinkToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Validating form
     */
    private void submitForm() {

        String email = signupInputEmail.getText().toString().trim();
        String password = signupInputPassword.getText().toString().trim();

        if(!checkUsername()){
            return;
        }

        if(!checkEmail()) {
            return;
        }
        if(!checkPassword()) {
            return;
        }
        signupInputLayoutEmail.setErrorEnabled(false);
        signupInputLayoutPassword.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);
        //create user

        if(isNetworkAvailable()) {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"Email Already In Use",Toast.LENGTH_SHORT).show();
                            }
                            else if (!task.isSuccessful()) {
                                Log.d(TAG, "Authentication failed." + task.getException());
                            }else {
                                String name = signupInputUsername.getText().toString();
                                String email = signupInputEmail.getText().toString();
                                createUser(name,email);
                                Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            }
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(),"Network Connectivity Problem",Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    private void createUser(String name, String email) {

        FDatabase fd = new FDatabase();
        String key = fd.getUsersReference().push().getKey();
        ArrayList<String> list = new ArrayList<>();
        list.add("No Problem Solved");
        User user = User.getInstance(name, email, key, list);
        fd.getUsersReference().child(key).setValue(user);
    }

    private boolean checkUsername() {
        String username = signupInputUsername.getText().toString().trim();

        if(username.isEmpty() || !isUsernameValid(username)){
            signupInputLayoutUsername.setErrorEnabled(true);
            signupInputLayoutUsername.setError(getString(R.string.err_msg_username));
            signupInputUsername.setError(getString(R.string.err_msg_required));
            requestFocus(signupInputUsername);
            return false;
        }
        signupInputLayoutUsername.setErrorEnabled(false);
        return true;
    }


    private boolean checkEmail() {
        String email = signupInputEmail.getText().toString().trim();
        if (email.isEmpty() || !isEmailValid(email)) {

            signupInputLayoutEmail.setErrorEnabled(true);
            signupInputLayoutEmail.setError(getString(R.string.err_msg_email));
            signupInputEmail.setError(getString(R.string.err_msg_required));
            requestFocus(signupInputEmail);
            return false;
        }
        signupInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword() {

        String password = signupInputPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)) {

            signupInputLayoutPassword.setError(getString(R.string.err_msg_password));
            signupInputPassword.setError(getString(R.string.err_msg_required));
            requestFocus(signupInputPassword);
            return false;
        }
        signupInputLayoutPassword.setErrorEnabled(false);
        return true;
    }

    private boolean isUsernameValid(String username) {
        return !TextUtils.isEmpty(username) && Pattern.compile(USERNAME_PATTERN).matcher(username).matches();
    }

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isPasswordValid(String password){
        return (password.length() >= 6);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null;
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}