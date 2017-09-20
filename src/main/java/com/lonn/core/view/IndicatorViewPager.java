package com.lonn.core.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lonn.core.R;

public class IndicatorViewPager extends RelativeLayout {

    public static final int DEFAULT_ITEM = 1;

    private Context mContext;

    private AutoScrollViewPager viewPager;
    private PagerAdapter adapter;

    private LinearLayout dotLayout;
    private ImageView[] dots;

    private int resNormal = R.drawable.lonn_dot_gray_normal;
    private int resCurrent = R.drawable.lonn_dot_gray_current;
    private int currentIndex = DEFAULT_ITEM;

    private OnPageSelectListener onPagerSelectListener;

    public IndicatorViewPager(Context context) {
        super(context);
        init(context);
    }

    public IndicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndicatorViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        viewPager = new AutoScrollViewPager(context);
        dotLayout = new LinearLayout(context);
        dotLayout.setGravity(Gravity.CENTER);

        LayoutParams viewPagerParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LayoutParams dotLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 80);
        dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dotLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        addView(viewPager, viewPagerParams);
        addView(dotLayout, dotLayoutParams);

        // 事件监听
        setOnPageChangeListener(onPageChangeListener);
    }

    public void setAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
        currentIndex = DEFAULT_ITEM;
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(currentIndex);
        }
        initDots();
    }

    /**
     * @Description: 初始化显示小圆点。当小圆点数量小于2时，不显示小圆点。
     */
    private void initDots() {
        dotLayout.removeAllViews();

        if (adapter == null) {
            return;
        }

        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        dotParams.leftMargin = 10;

        int count = adapter.getCount() - 2;
        if (count < 2) {
            return;
        }

        dots = new ImageView[count];
        for (int i = 0; i < dots.length; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(resNormal);
            iv.setLayoutParams(dotParams);
            dots[i] = iv;
            dotLayout.addView(iv);
        }

        setDot();
    }

    /**
     * @param color 十六进制颜色
     * @Description: 设置小圆点区域背景颜色。当小圆点数量小于2时，不显示小圆点，所以此时设置无效。
     */
    public void setDotLayoutBackgroundColor(String color) {
        if (dotLayout == null || adapter == null || TextUtils.isEmpty(color)) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        dotLayout.setBackgroundColor(Color.parseColor(color));
    }

    /**
     * 设置小圆点区域背景
     */
    public void setDotLayoutBackgroundResource(int resId) {
        if (dotLayout == null || adapter == null) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        dotLayout.setBackgroundResource(resId);
    }

    /**
     * 设置小圆点区域尺寸
     */
    public void setDotLayoutSize(int width, int height) {
        if (dotLayout == null || adapter == null) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        dotLayout.getLayoutParams().width = width;
        dotLayout.getLayoutParams().height = height;

    }

    /**
     * 设置圆点图片
     */
    public void setDotImage(int resNormal, int resCurrent) {
        this.resNormal = resNormal;
        this.resCurrent = resCurrent;
        setDot();
    }

    public void startAutoScroll(long delay) {
        if (viewPager != null) {
            viewPager.startAutoScroll(delay);
        }
    }

    public void startAutoScroll() {
        if (viewPager != null) {
            viewPager.startAutoScroll();
        }
    }

    public void stopAutoScroll() {
        if (viewPager != null) {
            viewPager.stopAutoScroll();
        }
    }

    private void setDot() {
        if (dots == null || dots.length == 0) {
            return;
        }
        resetDots();

        int index = 0;
        if (currentIndex == 0) {
            index = dots.length - 1;
        } else if (currentIndex == adapter.getCount() - 1) {
            index = 0;
        }else{
            index = currentIndex - 1;
        }

        if(index >= 0 && index < dots.length){
            dots[index].setImageResource(resCurrent);
        }


    }

    /**
     * @Description: 重置所有原点，显示默认未选中状态。
     */
    private void resetDots() {
        for (ImageView iv : dots) {
            iv.setImageResource(resNormal);
        }
    }

    private void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (viewPager != null && onPageChangeListener != null) {
            viewPager.setOnPageChangeListener(onPageChangeListener);
        }
    }

    public void setOnPageSelectListener(OnPageSelectListener onPageSelectListener) {
        this.onPagerSelectListener = onPageSelectListener;
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            currentIndex = arg0;
            setDot();

            if (onPagerSelectListener != null) {
                onPagerSelectListener.onPageSelect(arg0);
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
//        若viewpager滑动未停止，直接返回
//            if (state != ViewPager.SCROLL_STATE_IDLE) return;
//        若当前为第一张，设置页面为倒数第二张
            if (currentIndex == 0) {
                viewPager.setCurrentItem(adapter.getCount() - 2,false);
            } else if (currentIndex == adapter.getCount() - 1) {
//        若当前为倒数第一张，设置页面为第二张
                viewPager.setCurrentItem(1, false);
            }

        }
    };

    public interface OnPageSelectListener {
        void onPageSelect(int position);
    }

    public AutoScrollViewPager getViewPager() {
        return viewPager;
    }

}
