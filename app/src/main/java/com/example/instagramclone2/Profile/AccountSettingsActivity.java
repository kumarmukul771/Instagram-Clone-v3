package com.example.instagramclone2.Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.instagramclone2.Home.MainActivity;
import com.example.instagramclone2.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import Utils.BottomNavigationViewHelper;
import Utils.SectionPagerAdapter;
import Utils.SectionsStatePagerAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class AccountSettingsActivity extends AppCompatActivity {
    private Context mContext;
    private static final int ACTIVITY_NUM = 4;

    private SectionsStatePagerAdapter sectionPagerAdapter;
    private ViewPager viewPager;
    public static RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = AccountSettingsActivity.this;
        setContentView(R.layout.activity_accountsettings);

        viewPager=(ViewPager)findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout)findViewById(R.id.relLayout1);

        setupBottomNavigationView();
        setupSettingsList();
        setupFragment();

//        setup the back arrow for navigating back to profile activity
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setupFragment(){
        sectionPagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        sectionPagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.edit_profile_fragment));  //fragment 0
        sectionPagerAdapter.addFragment(new SignOutFragment(),getString(R.string.sign_out_fragment));  //fragment 1
    }

    private void setupViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        viewPager.setAdapter(sectionPagerAdapter);
        viewPager.setCurrentItem(fragmentNumber);
    }

    public void setupSettingsList(){
        ListView listView = (ListView)findViewById(R.id.lvAccountSettings);
        ArrayList<String> options = new ArrayList<>();

        options.add(getString(R.string.edit_profile_fragment)); // fragment 0
        options.add(getString(R.string.sign_out_fragment));  //fragment 1

        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setupViewPager(position);
            }
        });
    }

    //    Bottom navigation setup
    public void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
