package com.itheima.googleplaydemo.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.loader.RecommendDataLoader;
import com.itheima.googleplaydemo.widget.stellarmap.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class RecommendFragment extends BaseFragment {
    private static final String TAG = "RecommendFragment";

    @Override
    protected void startLoadData() {
        RecommendDataLoader.getInstance().loadData(this);
    }

    @Override
    protected View onCreateContentView() {
/*        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return textView;*/
        StellarMap stellarMap = new StellarMap(getContext());
        stellarMap.setAdapter(new RecommendAdapter(RecommendDataLoader.getInstance().getData()));
        stellarMap.setRegularity(15, 20);
        stellarMap.setGroup(0, false);
        return stellarMap;
    }

/*    private List<String> mockDataList() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 28; i++) {
            data.add(i + "黑马程序员");
        }
        return data;
    }*/

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
