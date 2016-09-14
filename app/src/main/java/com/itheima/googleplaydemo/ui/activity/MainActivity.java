package com.itheima.googleplaydemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.ui.fragment.SimpleFragment;
import com.itheima.googleplaydemo.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_content)
    LinearLayout mMainContent;
    @BindView(R.id.main_left_menu)
    FrameLayout mMainLeftMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.pager_sliding_tab_strip)
    PagerSlidingTabStrip mPagerSlidingTabStrip;
    @BindView(R.id.vp)
    ViewPager mVp;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initActionBar();
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

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] TITLES = { "Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid",
                "Top New Free", "Trending" };

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            LogUtils.d(TAG, "getItem: " + position);
            return SimpleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
