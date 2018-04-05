package com.jerry.yiyachat.main;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jerry.yiyachat.R;
import com.jerry.yiyachat.main.message.MessageFragment;
import com.jerry.yiyachat.main.roster.RosterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.view_pager_main)
    ViewPager viewPageMain;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewPageMain.setAdapter(
                new MainFragmentViewPagerAdapter(getSupportFragmentManager()));
    }
}

class MainFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    MainFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MessageFragment());
        fragments.add(new RosterFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}