package com.lonn.core.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.lonn.core.R;


public class WaitingDialog extends ProgressDialog{
	
	private TextView tv_loading;
	private ImageView iv_loading;
	
	public WaitingDialog(Context context) {
		super(context, R.style.dialog);
		// TODO Auto-generated constructor stub
	}

	public WaitingDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lonn_dialog_waiting);
		initView();
		doNext();
	}
	
	private void initView() {
		tv_loading = (TextView) findViewById(R.id.lonn_dialog_waiting_tv_loading);
		iv_loading = (ImageView) findViewById(R.id.lonn_dialog_waiting_iv_loading);

	}
	
	private void doNext() {
		startAnim();
		setCancelable(false);
	}
	
	private void startAnim(){
		AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getBackground();
		animationDrawable.start();
	}
	
	public void setMessage(CharSequence msg){
		tv_loading.setText(msg);
	}
	
	

}
