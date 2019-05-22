package com.lonn.core.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

    private static final int PADDING_LARGE = 38;
    private static final int PADDING_MEDIUM = 28;

	private LinearLayout ll_top;
	private ScrollView sv_middle;
	
	private TextView tv_title;
	private TextView tv_msg;
	private Button bt_left;
	private Button bt_right;
	private View iv_line;
	
	private int type, dismiss;
	private Callback<Boolean> callback = null;

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
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;

		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

		Configuration mConfiguration = getContext().getResources().getConfiguration();
		int ori = mConfiguration.orientation; //获取屏幕方向
		if (ori == Configuration.ORIENTATION_LANDSCAPE) {
			//横屏
			p.width = (int) (width * 0.35); // 宽度设置为屏幕的0.35
		} else if (ori == Configuration.ORIENTATION_PORTRAIT) {
			//竖屏
			p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
		}

        dialogWindow.setAttributes(p);
		
	}
	
	private void initView() {
		ll_top = (LinearLayout) findViewById(R.id.lonn_dialog_titlemsg_ll_top);
        ll_top.setPadding(PADDING_MEDIUM, PADDING_LARGE,PADDING_MEDIUM, PADDING_LARGE);
		ll_top.setVisibility(View.GONE);
		sv_middle = (ScrollView) findViewById(R.id.lonn_dialog_titlemsg_sv_middle);
        sv_middle.setPadding(PADDING_MEDIUM, PADDING_LARGE,PADDING_MEDIUM, PADDING_LARGE);
		sv_middle.setVisibility(View.GONE);
		
		tv_title = (TextView) findViewById(R.id.lonn_dialog_titlemsg_tv_title);
		tv_msg = (TextView) findViewById(R.id.lonn_dialog_titlemsg_tv_msg);
		bt_left = (Button) findViewById(R.id.lonn_dialog_titlemsg_bt_left);
		bt_right = (Button) findViewById(R.id.lonn_dialog_titlemsg_bt_right);
		iv_line = findViewById(R.id.lonn_dialog_titlemsg_iv_line);
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

//	public void setTitle(String s_title){
//		if(TextUtils.isEmpty(s_title)){
//			ll_top.setVisibility(View.GONE);
//		}else{
//			tv_title.setText(s_title);
//			ll_top.setVisibility(View.VISIBLE);
//		}
//	}
//
//	public void setTitle(int resid){
//		String s = getContext().getResources().getString(resid);
//		if(TextUtils.isEmpty(s)){
//			ll_top.setVisibility(View.GONE);
//		}else{
//			tv_title.setText(s);
//			ll_top.setVisibility(View.VISIBLE);
//		}
//	}

	public void setTitleTextSize(float size){
        tv_title.setTextSize(size);
	}

	public void setTtitleTextColor(int color){
        tv_title.setTextColor(color);
    }

//	public void setMessage(String s_msg){
//		if(TextUtils.isEmpty(s_msg)){
//			sv_middle.setVisibility(View.GONE);
//		}else{
//			tv_msg.setText(s_msg);
//			sv_middle.setVisibility(View.VISIBLE);
//		}
//	}
//
//	public void setMessage(int resid){
//		String s = getContext().getResources().getString(resid);
//		if(TextUtils.isEmpty(s)){
//			sv_middle.setVisibility(View.GONE);
//		}else{
//			tv_msg.setText(s);
//			sv_middle.setVisibility(View.VISIBLE);
//		}
//	}

	public void setMessage(int msgRes){
		String msg = null;
		try {
			msg = getContext().getResources().getString(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!TextUtils.isEmpty(msg)){
			setText(null, msg);
		}else{
			throw new RuntimeException("title and msg can not both null,please check your resource id.");
		}
	}

    public void setMessage(String msg){
        setText(null, msg);
    }

	public void setText(int titleRes, int msgRes){
        String title = null;
        String msg = null;
	    try {
            title = getContext().getResources().getString(titleRes);
	    } catch (Exception e) {
            e.printStackTrace();
	    }
        try {
            msg = getContext().getResources().getString(msgRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!TextUtils.isEmpty(title) || !TextUtils.isEmpty(msg)){
            setText(title, msg);
        }else{
            throw new RuntimeException("title and msg can not both null,please check your resource id.");
        }
	}

	public void setText(String title, String msg){
		if(TextUtils.isEmpty(title)){
			ll_top.setVisibility(View.GONE);
		}else{
			tv_title.setText(title);
			ll_top.setVisibility(View.VISIBLE);
            sv_middle.setPadding(PADDING_MEDIUM,0,PADDING_MEDIUM, PADDING_MEDIUM);
		}

		if(TextUtils.isEmpty(msg)){
			sv_middle.setVisibility(View.GONE);
		}else{
			tv_msg.setText(msg);
			sv_middle.setVisibility(View.VISIBLE);
		}
	}

	public View getView(int resId){
        return findViewById(resId);
    }

    public void setMessageTextSize(float size){
        tv_msg.setTextSize(size);
    }

    public void setMessageTextColor(int color){
        tv_msg.setTextColor(color);
    }
	
	public void setLeftButtonText(String s_left){
		if(TextUtils.isEmpty(s_left)){
			bt_left.setVisibility(View.GONE);
		}else{
			bt_left.setText(s_left);
			bt_left.setVisibility(View.VISIBLE);
		}
	}
	
	public void setLeftButtonText(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			bt_left.setVisibility(View.GONE);
		}else{
			bt_left.setText(s);
			bt_left.setVisibility(View.VISIBLE);
		}
	}

    public void setLeftButtonTextSize(float size){
        bt_left.setTextSize(size);
    }

    public void setLeftButtonTextColor(int color){
        bt_left.setTextColor(color);
    }
	
	public void setRightButtonText(String s_right){
		if(TextUtils.isEmpty(s_right)){
			bt_right.setVisibility(View.GONE);
		}else{
			bt_right.setText(s_right);
			bt_right.setVisibility(View.VISIBLE);
		}
	}
	
	public void setRightButtonText(int resid){
		String s = getContext().getResources().getString(resid);
		if(TextUtils.isEmpty(s)){
			bt_right.setVisibility(View.GONE);
		}else{
			bt_right.setText(s);
			bt_right.setVisibility(View.VISIBLE);
		}
	}

    public void setRightButtonTextSize(float size){
        bt_right.setTextSize(size);
    }

    public void setRightButtonTextColor(int color){
        bt_right.setTextColor(color);
    }


}
