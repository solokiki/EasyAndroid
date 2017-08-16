package com.lonn.core.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 解决SwipeRefreshLayout配合Viewpager时水平滑动事件冲突
 * @author chenlong
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

	public MySwipeRefreshLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
//	float startX = 0, startY = 0, nowX = 0, nowY = 0;
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			startX = event.getX();
//			startY = event.getY();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			nowX = event.getX();
//			nowY = event.getY();
//			if( Math.abs(startX - nowX) > 80  ){
//				return true;
//			}
//			break;
//		case MotionEvent.ACTION_UP:
//			break;
//
//		}
//		return super.dispatchTouchEvent(event);
//	}
	
	
	float startX = 0, startY = 0, nowX = 0, nowY = 0;
	
	// 容错范围
	private int nX = 20;
	
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
			
			// 如果横向滑动值超过指定值，则屏蔽SwipeRefreshLayout的下拉刷新功能，事件抛给下层
			// 设计初衷为了解决SwipeRefreshLayout中放置Viewpager时水平滑动事件冲突
			if( Math.abs(startX - nowX) > nX ){
				return false;
			}
			
			
		case MotionEvent.ACTION_UP:
			break;
		}
    	return super.onInterceptTouchEvent(ev);
    }
	
	
//	float startX = 0, startY = 0, nowX = 0, nowY = 0;
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			startX = event.getX();
//			startY = event.getY();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			nowX = event.getX();
//			nowY = event.getY();
//			
//			if( Math.abs(startX - nowX) > 80  ){
//				return true;
//			}
//			
//		case MotionEvent.ACTION_UP:
//			break;
//
//		}
//		return super.onTouchEvent(event);
//	}
	
	
//	private boolean canScroll;
//	 
//    private GestureDetector mGestureDetector;
//    View.OnTouchListener mGestureListener;
// 
//    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        mGestureDetector = new GestureDetector(context, new YScrollDetector());
//        canScroll = true;
//    }
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if(ev.getAction() == MotionEvent.ACTION_UP){
//            canScroll = true;
//        }
//        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
//    }
// 
//    class YScrollDetector extends SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            if(canScroll){
//            	if( Math.abs(distanceY) >= Math.abs(distanceX) ){
//            		canScroll = true;
//            	}
//            	else{
//            		canScroll = false;
//            	}
//            }
//            return canScroll;
//        }
//    }
	
}