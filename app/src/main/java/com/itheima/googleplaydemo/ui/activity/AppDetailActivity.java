package com.itheima.googleplaydemo.ui.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.itheima.googleplaydemo.R;

import butterknife.BindView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/18 11:55
 * 描述： TODO
 */
public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {
        super.init();
        initActionBar();
        setStatusBarColor();
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
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
