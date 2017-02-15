package com.itheima.googleplaydemo.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.widget.FlowLayout;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HotFragment extends BaseFragment {

    private List<String> mDataList;

    @Override
    protected void startLoadData() {
        Call<List<String>> listCall = HeiMaRetrofit.getInstance().getApi().listHot();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }


    @Override
    protected View onCreateContentView() {
        ScrollView scrollView = new ScrollView(getContext());
        //流式布局
        FlowLayout fl = new FlowLayout(getContext());
        int padding = getResources().getDimensionPixelOffset(R.dimen.padding);
        fl.setPadding(padding, padding, padding, padding);
        //给fl添加应有的孩子
        for (int i = 0; i < mDataList.size(); i++) {
            final String data = mDataList.get(i);
            TextView tv = getTextView(padding, data);
            //创建selector
            StateListDrawable selectorBg = getStateListDrawable();
            tv.setBackgroundDrawable(selectorBg);
            //设置tv可以点击
            tv.setClickable(true);
            //给textView设置点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                }
            });
            fl.addView(tv);
        }
        scrollView.addView(fl);
        return scrollView;
    }

    @NonNull
    private StateListDrawable getStateListDrawable() {
        //创建normal圆角shape
        GradientDrawable normalBg = new GradientDrawable();
        //设置圆角
        normalBg.setCornerRadius(10);
        //设置颜色
        int argb = getRandomColor();
        normalBg.setColor(argb);

        //创建pressed圆角shape
        GradientDrawable pressedBg = new GradientDrawable();
        pressedBg.setColor(Color.DKGRAY);
        pressedBg.setCornerRadius(10);

        //创建selector
        StateListDrawable selectorBg = new StateListDrawable();
        //按下去的状态
        selectorBg.addState(new int[]{android.R.attr.state_pressed}, pressedBg);
        //默认状态
        selectorBg.addState(new int[]{}, normalBg);
        return selectorBg;
    }

    private int getRandomColor() {
        Random random = new Random();
        int alpha = 255;
        int red = random.nextInt(190) + 30;//30-220
        int green = random.nextInt(190) + 30;//30-220
        int blue = random.nextInt(190) + 30;//30-220
        return Color.argb(alpha, red, green, blue);
    }

    @NonNull
    private TextView getTextView(int padding, String data) {
        TextView tv = new TextView(getContext());
        tv.setText(data);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(padding, padding, padding, padding);
        return tv;
    }
}
