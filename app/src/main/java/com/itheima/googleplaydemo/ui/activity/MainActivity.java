package com.itheima.googleplaydemo.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.ui.fragment.AppFragment;
import com.itheima.googleplaydemo.ui.fragment.CategoryFragment;
import com.itheima.googleplaydemo.ui.fragment.GameFragment;
import com.itheima.googleplaydemo.ui.fragment.HomeFragment;
import com.itheima.googleplaydemo.ui.fragment.LeaderBoardFragment;
import com.itheima.googleplaydemo.ui.fragment.RecommendFragment;
import com.itheima.googleplaydemo.ui.fragment.SubjectFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_content)
    LinearLayout mMainContent;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;


    private ActionBarDrawerToggle mToggle;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        initView();
        initActionBar();
        initEvent();
        checkStoragePermission();
    }

    private void checkStoragePermission() {
        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
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
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置back按钮是否可见
        initActionBarDrawerLayoutToggle();
    }

    private void initActionBarDrawerLayoutToggle() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mToggle.syncState();
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

    private class MyPagerAdapter extends FragmentPagerAdapter {

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
