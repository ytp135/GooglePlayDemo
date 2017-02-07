package com.itheima.googleplaydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.googleplaydemo.factory.FragmentFactory;

/**
 * Created by Leon on 2017/1/28.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public MainPagerAdapter(String[] titles, FragmentManager fragmentManager) {
        super(fragmentManager);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance().getFragment(position);
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
