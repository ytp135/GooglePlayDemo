package com.itheima.googleplaydemo.ui.activity;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.itheima.googleplaydemo.R;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/18 11:55
 * 描述： TODO
 */
public class AppDetailActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {
        super.init();
        initActionBar();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("应用详情");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
