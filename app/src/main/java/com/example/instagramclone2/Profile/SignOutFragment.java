package com.example.instagramclone2.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone2.Home.MainActivity;
import com.example.instagramclone2.Login.LoginActivity;
import com.example.instagramclone2.Login.RegisterActivity;
import com.example.instagramclone2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignOutFragment extends Fragment {

    // firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressBar mProgressBar;
    private TextView tvSignOut,tvSigningOut;
    private Button confirmSignOutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout,container,false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tvSignOut = (TextView) view.findViewById(R.id.tvConfirmSignOut);
        tvSigningOut = (TextView) view.findViewById(R.id.tvSigningOut);
        confirmSignOutButton = (Button) view.findViewById(R.id.btnConfirmSignOut);

        mProgressBar.setVisibility(View.GONE);
        tvSigningOut.setVisibility(View.GONE);

        setupFirebaseAuth();

        confirmSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                tvSigningOut.setVisibility(View.VISIBLE);

                mAuth.signOut();
                getActivity().finish();
            }
        });


        return view;
    }


    //    ==============================================================     //
    //    =========================  Firebase  =========================     //
    //    ==============================================================     //

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
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    // For not getting into app by back pressing after signing out
                    // If we don't write below line and just to startActivity then user will be logged out
                    // but if user presses bac button it will go back to Profile activity and
                    // then if he goes to Home i.e, Main Activity he will be logged out
                    // due to checkCurrentUser fn in main activity and then even he presses back button he won't get
                    // back into the app.
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
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
