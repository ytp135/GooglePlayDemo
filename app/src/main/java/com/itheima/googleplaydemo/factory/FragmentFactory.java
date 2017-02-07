package com.itheima.googleplaydemo.factory;

import android.support.v4.app.Fragment;

import com.itheima.googleplaydemo.ui.fragment.AppFragment;
import com.itheima.googleplaydemo.ui.fragment.CategoryFragment;
import com.itheima.googleplaydemo.ui.fragment.GameFragment;
import com.itheima.googleplaydemo.ui.fragment.HomeFragment;
import com.itheima.googleplaydemo.ui.fragment.HotFragment;
import com.itheima.googleplaydemo.ui.fragment.RecommendFragment;
import com.itheima.googleplaydemo.ui.fragment.SubjectFragment;

/**
 * Created by Leon on 2017/2/7.
 */

public class FragmentFactory {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_APP = 1;
    private static final int FRAGMENT_GAME = 2;
    private static final int FRAGMENT_SUBJECT = 3;
    private static final int FRAGMENT_RECOMMEND = 4;
    private static final int FRAGMENT_CATEGORY = 5;
    private static final int FRAGMENT_HOT = 6;


    private static FragmentFactory sFragmentFactory;

    private FragmentFactory() {}

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public Fragment getFragment(int pos) {
        switch (pos) {
            case FRAGMENT_HOME:
                return new HomeFragment();
            case FRAGMENT_APP:
                return new AppFragment();
            case FRAGMENT_GAME:
                return new GameFragment();
            case FRAGMENT_SUBJECT:
                return new SubjectFragment();
            case FRAGMENT_CATEGORY:
                return new CategoryFragment();
            case FRAGMENT_RECOMMEND:
                return new RecommendFragment();
            case FRAGMENT_HOT:
                return new HotFragment();
        }
        return null;
    }
}
