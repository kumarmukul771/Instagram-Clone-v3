package com.example.instagramclone2.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone2.Home.MainActivity;
import com.example.instagramclone2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //    Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressBar mProgressBar;
    private Context mContext;
    private TextView mPleaseWait;
    private EditText mEmail,mPassword;
    private Button mLoginButton;
    private TextView linkSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void initWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = (Context) LoginActivity.this;
        mLoginButton = (Button) findViewById(R.id.btn_login);
        linkSignUp = (TextView) findViewById(R.id.link_signUp);

        mProgressBar.setVisibility(View.GONE);
        mPleaseWait.setVisibility(View.GONE);
    }

    private boolean isStringNull(String string){
        if(string.equals("")){
            return true;
        }else{
            return false;
        }
    }

    //    ==============================================================     //
    //    =========================  Firebase  =========================     //
    //    ==============================================================     //

    private void init(){
        // initialize the button for logging in

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(!isStringNull(email) && !isStringNull(password)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        try{
                                            if(user.isEmailVerified()){
                                                Intent intent = new Intent(mContext,MainActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(mContext,"Email is not verified\n Check your email inbox.",Toast.LENGTH_SHORT).show();
                                                mPleaseWait.setVisibility(View.GONE);
                                                mProgressBar.setVisibility(View.GONE);
                                            }
                                        }catch (Exception e){
                                            Log.d("Sign in failed",String.valueOf(e));
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Login activity", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(mContext, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                        mProgressBar.setVisibility(View.GONE);
                                        mPleaseWait.setVisibility(View.GONE);
                                    }

                                }
                            });
                }else{
                    Toast.makeText(mContext,"You must fill out all the fields.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RegisterActivity.class);
                startActivity(intent);
            }
        });

        // If user is logged in then navigate to MainActivity and call 'finish()'
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupFirebaseAuth(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    // User is signed in
                    Log.i("User Id",user.getUid());
                }else{
                    // User is signed out
                    Log.i("User Id","Not signed in");
                }
            }
        };
    }

    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
//    ==============================================================     //
//    ==============================================================     //
//    ==============================================================     //
}
