package com.lonn.core.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lonn.core.R;
import com.lonn.core.utils.Callback;

public class TitleMsgDialog extends Dialog implements View.OnClickListener{

	public static final int TYPE_YES_OR_NO = 1;  // 确定、取消
	public static final int TYPE_CONFIRM = 2;    // 确定
	public static final int DISMISS_NORMAL = 0;  // 点击确定或者取消，对话框必定消失
	public static final int DISMISS_STUBBORN = 1;// 点击确定或者取消，对话框都不会消失
	
	private LinearLayout ll_top;
	private ScrollView sv_middle;
	
	private TextView tv_title;
	private TextView tv_msg;
	private Button bt_left;
	private Button bt_right;
	private ImageView iv_line;
	
	private int type, dismiss;
	private Callback<Boolean> callback = null;
	private int width, height;
	
	public TitleMsgDialog(Context context, int type, Callback<Boolean> cb) {
		super(context, R.style.dialog);
		this.type = type;
		this.callback = cb;
		this.dismiss = DISMISS_NORMAL;
	}
	
	public TitleMsgDialog(Context context, int type, Callback<Boolean> cb, int dismiss) {
		super(context, R.style.dialog);
		this.type = type;
		this.callback = cb;
		this.dismiss = dismiss;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lonn_dialog_titlemsg);
		initView();
		initSize();
		initType();
		addListener();
	}
	
	private void initSize(){
		Window dialogWindow = getWindow();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		height = displayMetrics.heightPixels;
		width = displayMetrics.widthPixels;
		
		
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        p.height = (int) (height * 0.35); // 高度设置为屏幕的0.35
        p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
        dialogWindow.setAttributes(p);
		
	}
	
	private void initView() {
		ll_top = (LinearLayout) findViewById(R.id.lonn_dialog_titlemsg_ll_top);
		ll_top.setVisibility(View.GONE);
		sv_middle = (ScrollView) findViewById(R.id.lonn_dialog_titlemsg_sv_middle);
		sv_middle.setVisibility(View.GONE);
		
		tv_title = (TextView) findViewById(R.id.lonn_dialog_titlemsg_tv_title);
		tv_msg = (TextView) findViewById(R.id.lonn_dialog_titlemsg_tv_msg);
		bt_left = (Button) findViewById(R.id.lonn_dialog_titlemsg_bt_left);
		bt_right = (Button) findViewById(R.id.lonn_dialog_titlemsg_bt_right);
		iv_line = (ImageView) findViewById(R.id.lonn_dialog_titlemsg_iv_line);
	}
	
	private void initType() {
		switch (type) {
		case TYPE_YES_OR_NO:
			bt_left.setVisibility(View.VISIBLE);
			bt_right.setVisibility(View.VISIBLE);
			iv_line.setVisibility(View.VISIBLE);
			break;
		case TYPE_CONFIRM:
			bt_left.setVisibility(View.GONE);
			iv_line.setVisibility(View.GONE);
			bt_right.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	private void addListener() {
		bt_left.setOnClickListener(this);
		bt_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.lonn_dialog_titlemsg_bt_left){
			doCallback(false);
			if(dismiss == DISMISS_NORMAL){
				this.dismiss();
			}
		}else if(v.getId() == R.id.lonn_dialog_titlemsg_bt_right){
			doCallback(true);
			if(dismiss == DISMISS_NORMAL){
				this.dismiss();
			}
		}

	}
	
	private void doCallback(boolean result) {
		if(callback != null){
			callback.onCallback(result);
		}

	}
	
	public void setTitle(String s_title){
		if(TextUtils.isEmpty(s_title)){
			ll_top.setVisibility(View.GONE);
		}else{
			tv_title.setText(s_title);
			ll_top.setVisibility(View.VISIBLE);
		}
	}
	
	public void setTitle(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			ll_top.setVisibility(View.GONE);
		}else{
			tv_title.setText(s);
			ll_top.setVisibility(View.VISIBLE);
		}
	}

	public void setTitleTextSize(float size){
        tv_title.setTextSize(size);
	}

	public void setTtitleTextColor(int color){
        tv_title.setTextColor(color);
    }

	public void setMessage(String s_msg){
		if(TextUtils.isEmpty(s_msg)){
			sv_middle.setVisibility(View.GONE);
		}else{
			tv_msg.setText(s_msg);
			sv_middle.setVisibility(View.VISIBLE);
		}
	}
	
	public void setMessage(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			sv_middle.setVisibility(View.GONE);
		}else{
			tv_msg.setText(s);
			sv_middle.setVisibility(View.VISIBLE);
		}
	}

    public void setMessageTextSize(float size){
        tv_msg.setTextSize(size);
    }

    public void setMessageTextColor(int color){
        tv_msg.setTextColor(color);
    }
	
	public void setLeftText(String s_left){
		if(TextUtils.isEmpty(s_left)){
			bt_left.setVisibility(View.GONE);
		}else{
			bt_left.setText(s_left);
			bt_left.setVisibility(View.VISIBLE);
		}
	}
	
	public void setLeftText(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			bt_left.setVisibility(View.GONE);
		}else{
			bt_left.setText(s);
			bt_left.setVisibility(View.VISIBLE);
		}
	}

    public void setLeftTextSize(float size){
        bt_left.setTextSize(size);
    }

    public void setLeftTextColor(int color){
        bt_left.setTextColor(color);
    }
	
	public void setRightText(String s_right){
		if(TextUtils.isEmpty(s_right)){
			bt_right.setVisibility(View.GONE);
		}else{
			bt_right.setText(s_right);
			bt_right.setVisibility(View.VISIBLE);
		}
	}
	
	public void setRightText(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			bt_right.setVisibility(View.GONE);
		}else{
			bt_right.setText(s);
			bt_right.setVisibility(View.VISIBLE);
		}
	}

    public void setRightTextSize(float size){
        bt_right.setTextSize(size);
    }

    public void setRightTextColor(int color){
        bt_right.setTextColor(color);
    }
	

}
