package com.lonn.core.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AutoScrollViewPager extends ViewPager{
	
	private static long SCROLL_DELAY = 3000;
	private boolean isAutoScroll = false;
	
	public AutoScrollViewPager(Context context) {
		super(context);
		init();
	}

	public AutoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		isAutoScroll = false;
	}
	
	public void startAutoScroll(long delay){
		SCROLL_DELAY = delay;
		startAutoScroll();
	}
	
	public void startAutoScroll(){
		if(getAdapter() == null || getAdapter().getCount() <= 0){
			return;
		}
		
		isAutoScroll = true;

        if(timer != null){
            timer.cancel();
            timer = null;
        }
		
        timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				scrollHandler.sendEmptyMessage(0);
			}
		}, SCROLL_DELAY, SCROLL_DELAY);
	}
	
	public void stopAutoScroll(){
		if(timer != null){
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}
	
	private Timer timer;
	private Handler scrollHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int count = getAdapter().getCount();
			int current = getCurrentItem();
			int next = current + 1;
			if(next >= count){
				next = 0;
			}
			setCurrentItem(next);
		}
	};
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		if(isAutoScroll){
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				stopAutoScroll();
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				startAutoScroll();
				break;
			}
		}
		
		
		return super.onTouchEvent(ev);
	}

}
