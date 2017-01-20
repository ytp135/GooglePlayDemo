package com.itheima.googleplaydemo.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.widget.stellarmap.StellarMap;

import java.util.ArrayList;
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
public class RecommendFragment extends BaseFragment {

    private List<String> mData = new ArrayList<String>();


    @Override
    protected void startLoadData(){
        Call<List<String>> listCall = HeiMaRetrofit.getInstance().getApi().listRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mData = response.body();
                if (mData.size() == 0) {
                    onDataLoadedEmpty();
                } else {
                    onDataLoadedSuccess();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }

    @Override
    protected View onCreateContentView() {
        StellarMap stellarMap = new StellarMap(getContext());
        stellarMap.setAdapter(new RecommendAdapter(mData));
        stellarMap.setRegularity(15, 20);
        stellarMap.setGroup(0, false);
        return stellarMap;
    }

    private class RecommendAdapter implements StellarMap.Adapter {

        private static final int PAGE_SIZE = 15;

        private List<String> mDataList;

        public RecommendAdapter(List<String> data) {
            mDataList = data;
        }

        @Override
        public int getGroupCount() {//得到一共多少组
            //mDataSet
            int pageCount = mDataList.size() / PAGE_SIZE;//32 15 -->2+1

            if (mDataList.size() % PAGE_SIZE != 0) {//有余数的时候
                pageCount++;
            }

            return pageCount;
        }

        @Override
        public int getCount(int group) {//得到每一个组的条目总数  32 15 15 2
            if (mDataList.size() % PAGE_SIZE != 0) {//有余数
                if (group == getGroupCount() - 1) {//最后一组
                    return mDataList.size() % PAGE_SIZE;
                }
            }
            return PAGE_SIZE;
        }

        @Override
        public View getView(int group, int position, View convertView) {//返回具体的视图

            TextView tv = new TextView(getContext());
            int index = group * PAGE_SIZE + position;
            String data = mDataList.get(index);
            tv.setText(data);

            //随机大小
            Random random = new Random();
            tv.setTextSize(random.nextInt(4) + 14);//14-18

            //随机颜色
            int alpha = 255;
            int red = random.nextInt(190) + 30;//30-220
            int green = random.nextInt(190) + 30;//30-220
            int blue = random.nextInt(190) + 30;//30-220
            int argb = Color.argb(alpha, red, green, blue);
            tv.setTextColor(argb);
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (isZoomIn) {
                return (group + 1) % getGroupCount();
            } else {
                return (group - 1 + getGroupCount()) % getGroupCount();
            }
        }
    }
}
