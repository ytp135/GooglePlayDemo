package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/16 12:03
 * 描述： TODO
 */
public class LoopView extends RelativeLayout {
    private static final String TAG = "LoopView";

    @BindView(R.id.vp)
    LoopViewPager mVp;
    @BindView(R.id.dots_container)
    LinearLayout mDotsContainer;

    private int mDotSize = 10;
    private int mDotMargin = 5;
    private int mLastPosition = 0;

    private List<String> mImages;

/*
    private int[] mImages = {R.drawable.home01, R.drawable.home02, R.drawable.home03, R.drawable.home04, R.drawable.home05,
            R.drawable.home06, R.drawable.home07, R.drawable.home08};
*/



    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoopView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loop, this);
        ButterKnife.bind(this, this);
//        initDots();
//        initViewPager();
    }

    private void initViewPager() {
        mVp.setAdapter(mPagerAdapter);
        mVp.setOnPageChangeListener(mOnPageChangeListener);
    }

    private void initDots() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mDotSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mDotSize, dm);
        mDotMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mDotMargin, dm);
        for (int i = 0; i < mImages.size(); i++) {
            View dot = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mDotSize, mDotSize);
            if (i != mImages.size() - 1) {
                layoutParams.rightMargin = mDotMargin;
            }
            dot.setLayoutParams(layoutParams);
            dot.setBackgroundResource(i == 0 ? R.drawable.dot_selected : R.drawable.dot_normal);
            mDotsContainer.addView(dot);
        }
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (mImages == null) {
                return 0;
            }
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(Constant.URL_IMAGE + mImages.get(position)).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            View dot = mDotsContainer.getChildAt(position);
            dot.setBackgroundResource(R.drawable.dot_selected);

            View preDot = mDotsContainer.getChildAt(mLastPosition);
            preDot.setBackgroundResource(R.drawable.dot_normal);

            mLastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void setData(List<String> data) {
        mImages = data;
        initDots();
        initViewPager();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(mLooper, 3000);
    }

    private Runnable mLooper = new Runnable() {
        @Override
        public void run() {
            mVp.setCurrentItem(mVp.getCurrentItem() + 1);
            postDelayed(mLooper, 3000);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mLooper);
    }
}
