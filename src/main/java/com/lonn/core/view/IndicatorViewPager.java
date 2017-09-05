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

public class IndicatorViewPager extends RelativeLayout{

	public static final int DEFAULT_ITEM = 0;
	
	private Context mContext;
	
	private AutoScrollViewPager viewPager;
	private PagerAdapter adapter;
	
	private LinearLayout dotLayout;
	private ImageView[] dots;
	
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

	private void init(Context context){
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
	
	public void setAdapter(PagerAdapter adapter){
		this.adapter = adapter;
		if(viewPager != null){
			viewPager.setAdapter(adapter);
			viewPager.setCurrentItem(DEFAULT_ITEM);
		}
		initDots();
	}
	
	/**
	 * @Description: 初始化显示小圆点。当小圆点数量小于2时，不显示小圆点。
	 * @author lonn chen
	 * @date 2016年12月22日 下午6:17:18
	 */
	private void initDots(){
        dotLayout.removeAllViews();

        if(adapter == null){
            return;
        }

		LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		dotParams.leftMargin = 10;
		
		int count = adapter.getCount();
		if(count < 2){
			return;
		}
		
		dots = new ImageView[count];
		for(int i=0; i<dots.length; i++){
			ImageView iv = new ImageView(mContext);
			iv.setImageResource(R.drawable.lonn_dot_gray_normal);
			iv.setLayoutParams(dotParams);
			dots[i] = iv;
			dotLayout.addView(iv);
		}
		
		setDot(DEFAULT_ITEM);
	}
	
	/**
	 * @Description: 设置小圆点区域背景颜色。当小圆点数量小于2时，不显示小圆点，所以此时设置无效。
	 * @param color 十六进制颜色
	 * @author lonn chen
	 * @date 2016年12月22日 下午6:09:49
	 */
	public void setDotsBackgroundColor(String color){
		if(dotLayout == null || adapter == null || TextUtils.isEmpty(color)){
			return;
		}
		
		if(adapter.getCount() < 2){
			return;
		}
		
		dotLayout.setBackgroundColor(Color.parseColor(color));
	}
	
	public void startAutoScroll(long delay){
		if(viewPager != null){
			viewPager.startAutoScroll(delay);
		}
	}
	
	public void startAutoScroll(){
		if(viewPager != null){
			viewPager.startAutoScroll();
		}
	}
	
	public void stopAutoScroll(){
		if(viewPager != null){
			viewPager.stopAutoScroll();
		}
	}
	
	private void setDot(int position){
		if(dots == null || dots.length == 0){
			return;
		}
		resetDots();
		
		dots[position].setImageResource(R.drawable.lonn_dot_gray_current);
		
	}
	
	/**
	 * @Description: 重置所有原点，显示默认未选中状态。
	 * @author lonn chen
	 * @date 2016年12月22日 下午5:47:13
	 */
	private void resetDots(){
		for(ImageView iv : dots){
			iv.setImageResource(R.drawable.lonn_dot_gray_normal);
		}
	}
	
	private void setOnPageChangeListener(OnPageChangeListener onPageChangeListener){
		if(viewPager != null && onPageChangeListener != null){
			viewPager.setOnPageChangeListener(onPageChangeListener);
		}
	}
	
	public void setOnPageSelectListener(OnPageSelectListener onPageSelectListener){
		this.onPagerSelectListener = onPageSelectListener;
	}
	
	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
			setDot(arg0);
			if(onPagerSelectListener != null){
				onPagerSelectListener.onPageSelect(arg0);
			}
			
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public interface OnPageSelectListener{
		void onPageSelect(int position);
	}

}
