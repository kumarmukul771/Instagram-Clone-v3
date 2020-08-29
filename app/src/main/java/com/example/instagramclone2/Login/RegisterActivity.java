package com.example.instagramclone2.Login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Utils.FireBaseMethods;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    //    Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FireBaseMethods fireBaseMethods;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    private ProgressBar mProgressBar;
    private Context mContext;
    private TextView mPleaseWait;
    private EditText mEmail,mPassword,mUsername;
    private Button mRegisterButton;
    private String email,username,password,append="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initWidgets();

        fireBaseMethods = new FireBaseMethods(mContext);
        setupFirebaseAuth();
        init();
    }

    private void initWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mUsername = (EditText) findViewById(R.id.input_name);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = (Context) RegisterActivity.this;
        mRegisterButton = (Button) findViewById(R.id.btn_register);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);

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

    private void init(){
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(!isStringNull(email) && !isStringNull(username) && !isStringNull(password)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);

                    fireBaseMethods.registerNewEmail(email,password,username);
                }else{
                    Toast.makeText(mContext,"You must fill out all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //    ==============================================================     //
    //    =========================  Firebase  =========================     //
    //    ==============================================================     //

    private void setupFirebaseAuth(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    // User is signed in
                    Log.i("User Id",user.getUid());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Make sure username is not already in use
                            if(fireBaseMethods.checkIfUsernameExists(username,snapshot)){
                                append = myRef.push().getKey().substring(3,10);
                            }

                            username = username + append;

                            // add new user & user_account_settings to the database
                            fireBaseMethods.addNewUser(email,username,"","https://www.youtube.com","");

                            Toast.makeText(mContext,"Sign up success.Sending verification email",Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    finish();

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
