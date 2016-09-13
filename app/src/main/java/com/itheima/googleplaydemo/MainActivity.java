package com.itheima.googleplaydemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionBar();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("GooglePlay");//设置标题
//        actionBar.setSubtitle("黑马程序员");//设置子标题
//        actionBar.setDisplayShowTitleEnabled(false);// 设置菜单 标题是否可见
        actionBar.setIcon(R.mipmap.ic_launcher);//设置icon
//        actionBar.setLogo(R.mipmap.ic_action_delete);//设置logo
//        actionBar.setDisplayUseLogoEnabled(true);//设置icon/logo的优先级，true为logo优先，false为icon优先，默认false.
        actionBar.setDisplayShowHomeEnabled(true);//设置可以显示icon/log
//        actionBar.setDisplayHomeAsUpEnabled(true);//设置back按钮是否可见
    }
}
