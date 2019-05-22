package com.lonn.core.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lonn.core.R;
import com.lonn.core.adapter.BaseBannerAdapter;
import com.lonn.core.utils.AppUtil;

public class IndicatorViewPager extends RelativeLayout {

    public static final int DEFAULT_ITEM = 1;

    public static final int INDICATOR_STYLE_NONE = 1;
    public static final int INDICATOR_STYLE_DOT = 2;
    public static final int INDICATOR_STYLE_NUMBER = 3;

    private Context mContext;

    private AutoScrollViewPager viewPager;
    private BaseBannerAdapter adapter;

    private LinearLayout indicatorLayout;
    private ImageView[] dots;
    private TextView indicatorNumber;

    private int indicatorStyle = INDICATOR_STYLE_DOT;

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
        indicatorLayout = new LinearLayout(context);
        indicatorLayout.setGravity(Gravity.CENTER);
        indicatorLayout.setPadding(10,0,10,0);

        LayoutParams viewPagerParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LayoutParams dotLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 80);
        dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dotLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        addView(viewPager, viewPagerParams);
        addView(indicatorLayout, dotLayoutParams);

        // 事件监听
        setOnPageChangeListener(onPageChangeListener);
    }

    public void setAdapter(BaseBannerAdapter adapter) {
        this.adapter = adapter;
        currentIndex = DEFAULT_ITEM;
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(currentIndex);
        }
        initIndicator();
    }

    /**
     * 设置指示器样式
     * @param style
     */
    public void setIndicatorStyle(int style){
        this.indicatorStyle = style;
    }

    /**
     * 设置指示器位置
     * @param gravity
     */
    public void setIndicatorGravity(int gravity){
        indicatorLayout.setGravity(gravity);
    }

    /**
     * @Description: 初始化轮播指示器
     */
    private void initIndicator() {
        indicatorLayout.removeAllViews();

        if (adapter == null) {
            return;
        }

        if(indicatorStyle == INDICATOR_STYLE_NONE){
            indicatorLayout.setVisibility(View.GONE);
        }else if(indicatorStyle == INDICATOR_STYLE_DOT){
            LinearLayout.LayoutParams indicatorParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            indicatorParams.leftMargin = 10;

            // 当数量小于2时，不显示小圆点
            int count = getCount();
            if (count < 2) {
                return;
            }

            dots = new ImageView[count];
            for (int i = 0; i < dots.length; i++) {
                ImageView iv = new ImageView(mContext);
                iv.setImageResource(resNormal);
                iv.setLayoutParams(indicatorParams);
                dots[i] = iv;
                indicatorLayout.addView(iv);
            }
            setDot();
        }else if(indicatorStyle == INDICATOR_STYLE_NUMBER){
            indicatorNumber = new TextView(mContext);
            indicatorNumber.setTextColor(AppUtil.getColor(mContext,R.color.white));
            indicatorNumber.setBackgroundColor(AppUtil.getColor(mContext,R.color.black));
            indicatorLayout.addView(indicatorNumber);
            setNumber();
        }


    }

    /**
     * @param color 十六进制颜色
     * @Description: 设置小圆点区域背景颜色。当小圆点数量小于2时，不显示小圆点，所以此时设置无效。
     */
    public void setIndicatorBackgroundColor(String color) {
        if (indicatorLayout == null || adapter == null || TextUtils.isEmpty(color)) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        indicatorLayout.setBackgroundColor(Color.parseColor(color));
    }

    /**
     * 设置指示器背景
     */
    public void setIndicatorBackgroundResource(int resId) {
        if (indicatorLayout == null || adapter == null) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        indicatorLayout.setBackgroundResource(resId);
    }

    /**
     * 设置指示器背景大小
     */
    public void setIndicatorSize(int width, int height) {
        if (indicatorLayout == null || adapter == null) {
            return;
        }

        if (adapter.getCount() < 2) {
            return;
        }

        indicatorLayout.getLayoutParams().width = width;
        indicatorLayout.getLayoutParams().height = height;

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

    private void setIndicator(){
        switch (indicatorStyle) {
            case INDICATOR_STYLE_DOT:
                setDot();
                break;
            case INDICATOR_STYLE_NUMBER:
                setNumber();
                break;
        }
    }

    private void setDot() {
        if (dots == null || dots.length == 0) {
            return;
        }
        resetDots();

//        int index = 0;
//        if (currentIndex == 0) {
//            index = dots.length - 1;
//        } else if (currentIndex == adapter.getCount() - 1) {
//            index = 0;
//        }else{
//            index = currentIndex - 1;
//        }

        int index = getRealIndex();

        if(index >= 0 && index < dots.length){
            dots[index].setImageResource(resCurrent);
        }
    }

    private void setNumber(){
        if(indicatorNumber != null){
            indicatorNumber.setText((getRealIndex()+1) + "/" + getCount());
        }
    }

    /**
     * 获取当前滑动的页数
     * @return
     */
    private int getRealIndex(){
        int index = 0;
        if (currentIndex == 0) {
            index = getCount() - 1;
        } else if (currentIndex == getCount() + 1) {
            index = 0;
        }else{
            index = currentIndex - 1;
        }
        return index;
    }

    /**
     * 获取总数
     * @return
     */
    private int getCount(){
        return adapter.getCount() - 2;
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
            setIndicator();

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
