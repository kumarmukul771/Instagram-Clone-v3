package com.example.instagramclone2.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instagramclone2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import Utils.UniversalImageLoader;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditProfileFragment extends Fragment {

    private ImageView mProfilePhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_editprofile,container,false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);

//        Setting up back arrow for navigating back to ProfileActivity
        ImageView backArrow = (ImageView)view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().beginTransaction().remove(EditProfileFragment.this).commitAllowingStateLoss();
//                AccountSettingsActivity.mRelativeLayout.setVisibility(View.VISIBLE);
                getActivity().finish();
//                Log.i("Get activity",String.valueOf(getActivity().getSupportFragmentManager()));
             }
        });

        setProfileImage();
        return view;
    }

    private void setProfileImage(){
        String imgURL = "www.jakpost.travel/wimages/large/16-162680_disha-patani-disha-patani-hd-4k.jpg";
        UniversalImageLoader.setImage(imgURL,mProfilePhoto,null,"https://");
    }
}
