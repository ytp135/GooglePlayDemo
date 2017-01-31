package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.widget.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * Created by Leon on 2017/1/31.
 */

public class RecommendAdapter implements StellarMap.Adapter {

    private static final int PAGE_SIZE = 15;

    private List<String> mDataList;
    private Context mContext;

    public RecommendAdapter(Context context, List<String> data) {
        mDataList = data;
        mContext = context;
    }

    /**
     * 返回组(页面)的个数
     */
    @Override
    public int getGroupCount() {
        int pageCount = mDataList.size() / PAGE_SIZE;

        if (mDataList.size() % PAGE_SIZE != 0) {//有余数的时候
            pageCount++;
        }

        return pageCount;
    }

    /**
     * 返回对应组(页面)条目的个数
     */
    @Override
    public int getCount(int group) {
        if (mDataList.size() % PAGE_SIZE != 0) {//有余数
            if (group == getGroupCount() - 1) {//最后一组
                return mDataList.size() % PAGE_SIZE;
            }
        }
        return PAGE_SIZE;
    }

    /**
     * 返回对应组中对应位置的view
     *
     * @param convertView 回收的view
     */
    @Override
    public View getView(int group, int position, View convertView) {//返回具体的视图
        TextView tv;
        if (convertView == null) {
            tv = new TextView(mContext);
        } else {
            tv = (TextView) convertView;
        }
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

    /**
     * 返回放大或者缩小下一组的下标
     *
     * @param group 当前组的下标
     * @param isZoomIn true表示放大，false表示缩小
     */
    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if (isZoomIn) {
            return (group + 1) % getGroupCount();
        } else {
            return (group - 1 + getGroupCount()) % getGroupCount();
        }
    }
}