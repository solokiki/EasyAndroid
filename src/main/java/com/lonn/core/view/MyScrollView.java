package com.lonn.core.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

	private Context mContext;
	private Activity activity;

	public MyScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		activity = scanForActivity(mContext);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (activity == null) {
			return;
		}
		
		try {
			Window dialogWindow = activity.getWindow();
			DisplayMetrics displayMetrics = new DisplayMetrics();
			dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			int height = displayMetrics.heightPixels;
			int width = displayMetrics.widthPixels;
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(height / 2, MeasureSpec.AT_MOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private Activity scanForActivity(Context context) {
		if (context == null)
			return null;
		else if (context instanceof Activity)
			return (Activity) context;
		else if (context instanceof ContextWrapper)
			return scanForActivity(((ContextWrapper) context).getBaseContext());

		return null;
	}

}
