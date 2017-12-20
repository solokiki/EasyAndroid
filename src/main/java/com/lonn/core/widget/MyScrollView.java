package com.lonn.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 解决ScrollView配合SwipeRefreshLayout、ViewPager时事件冲突
 * @author chenlong
 */
public class MyScrollView extends ScrollView {

	private ScrollViewListener scrollViewListener = null;

	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	 
	float startX = 0, startY = 0, nowX = 0, nowY = 0;
	int nY = 50;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	
    	switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = ev.getX();
			startY = ev.getY();
			
			break;
		case MotionEvent.ACTION_MOVE:
			nowX = ev.getX();
			nowY = ev.getY();
			
			// 当此ScrollView已经在顶部时，屏蔽ScrollView本身的下拉操作，事件抛给下层
			// 设计初衷为了解决ScrollView配合SwipeRefreshLayout、ViewPager时事件冲突
			if(getScrollY() == 0 && nowY > startY){
				return false;
			}
			
			// 
//			if( Math.abs(startY - nowY) > ( Math.abs(startX - nowX) + nY ) ){
//				return true;
//			}
			
		case MotionEvent.ACTION_UP:
			break;
		}
    	
    	return super.onInterceptTouchEvent(ev);
    }

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}

	public interface ScrollViewListener {
		void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);

	}
	
}
