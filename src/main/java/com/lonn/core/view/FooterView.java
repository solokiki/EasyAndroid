package com.lonn.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lonn.core.R;
import static android.view.animation.Animation.INFINITE;

public class FooterView extends LinearLayout implements View.OnClickListener{

    private ReloadListener reloadListener;

    private LinearLayout ll_loading;
    private ImageView iv_loading;
    private TextView tv_loading;

    private LinearLayout ll_failure;
    private TextView tv_failure;

    public FooterView(Context context) {
        super(context);
        init();
    }

    public FooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.lonn_view_footer, this);
        initView(getRootView());
        addListener();
    }

    private void initView(View view) {
        ll_loading = (LinearLayout) view.findViewById(R.id.lonn_view_footer_ll_loading);
        iv_loading = (ImageView) view.findViewById(R.id.lonn_view_footer_iv_loading);
        tv_loading = (TextView) view.findViewById(R.id.lonn_view_footer_tv_loading);

        ll_failure = (LinearLayout) view.findViewById(R.id.lonn_view_footer_ll_failure);
        tv_failure = (TextView) view.findViewById(R.id.lonn_view_footer_tv_failure);
        ll_failure.setVisibility(View.GONE);

        startRotate();
    }

    private void addListener() {
        ll_failure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.lonn_view_footer_ll_failure){
            onLoading();
            if(reloadListener != null){
                reloadListener.onReload();
            }
        }
    }

    public void setReloadListener(ReloadListener rl){
        this.reloadListener = rl;
    }

    public void onLoading(){
        ll_loading.setVisibility(View.VISIBLE);
        ll_failure.setVisibility(View.GONE);
    }

    public void loadingFailure(){
        ll_loading.setVisibility(View.GONE);
        ll_failure.setVisibility(View.VISIBLE);
    }

    private void startRotate(){
        Animation anim =new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(1000);
        anim.setRepeatCount(INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        iv_loading.startAnimation(anim);
    }

    private void stopRotate(){
        iv_loading.clearAnimation();
    }

    public void setImage(int resId){
        if(iv_loading != null){
            iv_loading.setImageResource(resId);
        }
    }

    public void setMessage(String msg){
        if(tv_loading != null){
            tv_loading.setText(msg);
        }
    }

    public void setMessageTextSize(float size) {
        if(tv_loading != null){
            tv_loading.setTextSize(size);
        }
    }

    public void setMessageTextColor(int color) {
        if(tv_loading != null){
            tv_loading.setTextColor(color);
        }
    }

    public interface ReloadListener{
        void onReload();
    }

}
