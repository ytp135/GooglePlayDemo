package com.itheima.googleplaydemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.ui.fragment.AppFragment;
import com.itheima.googleplaydemo.ui.fragment.CategoryFragment;
import com.itheima.googleplaydemo.ui.fragment.GameFragment;
import com.itheima.googleplaydemo.ui.fragment.HomeFragment;
import com.itheima.googleplaydemo.ui.fragment.LeaderBoardFragment;
import com.itheima.googleplaydemo.ui.fragment.RecommendFragment;
import com.itheima.googleplaydemo.ui.fragment.SubjectFragment;
import com.itheima.googleplaydemo.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_content)
    LinearLayout mMainContent;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.pager_sliding_tab_strip)
    PagerSlidingTabStrip mPagerSlidingTabStrip;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initActionBar();
        initEvent();
    }

    private void initEvent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mNavigationView.setCheckedItem(item.getItemId());
                return false;
            }
        });
    }

    private void initView() {
        mVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mVp);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("GooglePlay");//设置标题
//        actionBar.setSubtitle("黑马程序员");//设置子标题
//        actionBar.setDisplayShowTitleEnabled(false);// 设置菜单 标题是否可见
//        actionBar.setIcon(R.mipmap.ic_launcher);//设置icon
//        actionBar.setLogo(R.mipmap.ic_action_delete);//设置logo
//        actionBar.setDisplayUseLogoEnabled(true);//设置icon/logo的优先级，true为logo优先，false为icon优先，默认false.
//        actionBar.setDisplayShowHomeEnabled(true);//设置可以显示icon/log
        actionBar.setDisplayHomeAsUpEnabled(true);//设置back按钮是否可见
        initActionBarDrawerLayoutToggle();
    }

    private void initActionBarDrawerLayoutToggle() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.syncState();
//        mDrawerLayout.addDrawerListener(mDrawerListener);
        mDrawerLayout.addDrawerListener(mToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*    private DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };*/


/*    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid",
                "Top New Free", "Trending" };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            LogUtils.d(TAG, "getItem: " + position);
            return SimpleFragment.newInstance(position);
        }
    }*/

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        private static final int FRAGMENT_HOME = 0;
        private static final int FRAGMENT_APP = 1;
        private static final int FRAGMENT_GAME = 2;
        private static final int FRAGMENT_SUBJECT = 3;
        private static final int FRAGMENT_RECOMMEND = 4;
        private static final int FRAGMENT_CATEGORY = 5;
        private static final int FRAGMENT_LEADER_BOARD = 6;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] TITLES = getResources().getStringArray(R.array.main_titles);

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            LogUtils.d(TAG, "getItem: " + position);
//            return SimpleFragment.newInstance(position);
//            return BaseFragment.newInstance();
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
                    return new LeaderBoardFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}