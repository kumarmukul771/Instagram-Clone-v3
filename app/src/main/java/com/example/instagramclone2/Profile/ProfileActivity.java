package com.example.instagramclone2.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.instagramclone2.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import Utils.BottomNavigationViewHelper;
import Utils.GridImageAdapter;
import Utils.UniversalImageLoader;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 4;
    private Context mContext= ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;
    private GridView gridView;
    private final int NUM_OF_COLUMNS = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupActivityWidget();
        setupBottomNavigationView();
        setupToolBar();
        setProfileImage();
        tempGridSetup();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.orissapost.com/wp-content/uploads/2020/02/Disha-Patani-looks-SMOKING-HOT-as-she-poses-in-Calvin-Klein-UNDERWEAR.jpg");
        imgURLs.add("https://theenglishpost.com/wp-content/uploads/2019/11/Disha-Patani-Insta_600x442.jpg");
        imgURLs.add("https://4.bp.blogspot.com/--0xlpZIXt8A/XBJAuwGQJvI/AAAAAAAAhZw/2RXV-UU1_CA07PFd_fvmoAHOStE8WMi-wCLcBGAs/s1600/disha-patani-latest-pics%2B%25281%2529.jpg");
        imgURLs.add("https://wallpaperaccess.com/full/1108628.jpg");
        imgURLs.add("https://wallpapers.oneindia.com/ph-1024x768/2016/11/disha-patani_147918739000.jpg");
        imgURLs.add("https://www.hdwallpapers.in/download/disha_patani-HD.jpg");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSKxWzQFtpe6f7MPx3V-pWULAvoHVnIq_TauQ&usqp=CAU");
        imgURLs.add("https://lh3.googleusercontent.com/proxy/ScZI0FpklmWvbZejCd3KQqucRSJE5GzQGuOrmyvIT2dTDOyso9ESJ-tZvAHO9RXmuWw6XFAQrkUiYSbMtxpekJ4vZ4KPWx_3vI7cfHp9xFriE4v2MewIhHIoRlIVlVEHRqZ-Jc7Nf9AFz0GqOg");
        imgURLs.add("https://i.pinimg.com/564x/58/96/ef/5896ef86c593eebac1089382d50d29e6.jpg");
        imgURLs.add("https://i1.wp.com/www.filmfare.com/media/content/2020/jun/dishapatani31591106773.jpg?resize=616%2C794&ssl=1");

        setImageGrid(imgURLs);
    }

    private void setImageGrid(ArrayList<String> imgURLs){
        GridImageAdapter gridImageAdapter = new GridImageAdapter(mContext,R.layout.layout_grid_imageview,"",imgURLs);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_OF_COLUMNS;

        gridView.setColumnWidth(imageWidth);

        gridView.setAdapter(gridImageAdapter);
    }

    private void setProfileImage(){
        String imgURL = "wi.wallpapertip.com/wsimgs/151-1512616_disha-patani-hd-4k-mobile-disha-patani-wallpaper.jpg";

        UniversalImageLoader.setImage(imgURL,profilePhoto,mProgressBar,"https://");
    }

    private void setupActivityWidget(){
        mProgressBar = (ProgressBar)findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);

        profilePhoto = (ImageView)findViewById(R.id.profile_image);
        gridView = (GridView)findViewById(R.id.gridView);
    }

    private void setupToolBar(){

        Toolbar toolbar = (Toolbar)findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView)findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
