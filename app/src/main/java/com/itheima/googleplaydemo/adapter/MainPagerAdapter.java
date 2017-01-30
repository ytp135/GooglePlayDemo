package com.itheima.googleplaydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.googleplaydemo.ui.fragment.AppFragment;
import com.itheima.googleplaydemo.ui.fragment.CategoryFragment;
import com.itheima.googleplaydemo.ui.fragment.GameFragment;
import com.itheima.googleplaydemo.ui.fragment.HomeFragment;
import com.itheima.googleplaydemo.ui.fragment.HotFragment;
import com.itheima.googleplaydemo.ui.fragment.RecommendFragment;
import com.itheima.googleplaydemo.ui.fragment.SubjectFragment;

/**
 * Created by Leon on 2017/1/28.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_APP = 1;
    private static final int FRAGMENT_GAME = 2;
    private static final int FRAGMENT_SUBJECT = 3;
    private static final int FRAGMENT_RECOMMEND = 4;
    private static final int FRAGMENT_CATEGORY = 5;
    private static final int FRAGMENT_LEADER_BOARD = 6;


    public MainPagerAdapter(String[] titles, FragmentManager fragmentManager) {
        super(fragmentManager);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_HOME:
                return new HomeFragment();
            case FRAGMENT_APP:
                return new AppFragment();
            case FRAGMENT_GAME:
                return new GameFragment();
            case FRAGMENT_SUBJECT:
                return new SubjectFragment();
            case FRAGMENT_RECOMMEND:
                return new RecommendFragment();
            case FRAGMENT_CATEGORY:
                return new CategoryFragment();
            case FRAGMENT_LEADER_BOARD:
                return new HotFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
