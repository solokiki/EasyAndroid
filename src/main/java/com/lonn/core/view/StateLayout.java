package com.lonn.core.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class StateLayout extends FrameLayout implements View.OnClickListener{

    private static final int V_CODE = 1;  // 标记当前类的版本，高版本兼容低版本

    private LinearLayout coverView;
    private ImageView iv_image;
    private TextView tv_message;
    private TextView tv_tips;

    private View contentView;

    public StateLayout(Context context) {
        super(context);
        init();
    }

    public StateLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StateLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    private void initCoverView(){

        int coverHeight = contentView.getHeight();

        if(coverHeight <= 0){
            coverHeight = LinearLayout.LayoutParams.MATCH_PARENT;
        }

        LinearLayout.LayoutParams lp_cover = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, coverHeight);
        coverView = new LinearLayout(getContext());
        coverView.setLayoutParams(lp_cover);
        coverView.setOrientation(LinearLayout.VERTICAL);
        coverView.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams lp_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_image.bottomMargin = 40;

        iv_image = new ImageView(getContext());
        iv_image.setLayoutParams(lp_image);

        LinearLayout.LayoutParams lp_text = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_image.bottomMargin = 20;

        tv_message = new TextView(getContext());
        tv_message.setTextColor(Color.parseColor("#6C6C6C"));
        tv_message.setTextSize(14);
        tv_message.setLayoutParams(lp_text);

        tv_tips = new TextView(getContext());
        tv_tips.setTextColor(Color.parseColor("#C0C0C0"));
        tv_tips.setTextSize(12);
        tv_tips.setLayoutParams(lp_text);

        coverView.addView(iv_image);
        coverView.addView(tv_message);
        coverView.addView(tv_tips);

        coverView.setOnClickListener(this);
        coverView.setVisibility(View.GONE);

        addView(coverView);

    }

    public void showContentView(){
        if(coverView != null){
            coverView.setVisibility(View.GONE);
        }
        contentView.setVisibility(View.VISIBLE);
    }

    public void showCoverView(int imageResId, String message, String tips){
        if(coverView == null){
            initCoverView();
        }

        coverView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);

        if(imageResId <= 0){
            iv_image.setVisibility(View.GONE);
        }else{
            iv_image.setVisibility(View.VISIBLE);
            iv_image.setImageResource(imageResId);
        }

        if(TextUtils.isEmpty(message)){
            tv_message.setVisibility(View.GONE);
        }else {
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(message);
        }

        if(TextUtils.isEmpty(tips)){
            tv_tips.setVisibility(View.GONE);
        }else {
            tv_tips.setVisibility(View.VISIBLE);
            tv_tips.setText(tips);
        }
    }

    public void showCoverView(int imageResId, String message){
        showCoverView(imageResId, message, "");
    }

    public void showCoverView(int imageResId){
        showCoverView(imageResId, "", "");
    }

    @Override
    public void onClick(View v) {
        if(onCoverLayoutClickListener != null){
            onCoverLayoutClickListener.onCoverLayoutClick(v);
        }
    }

    private OnCoverLayoutClickListener onCoverLayoutClickListener;

    public void setOnCoverLayoutClickListener(OnCoverLayoutClickListener onCoverLayoutClickListener) {
        this.onCoverLayoutClickListener = onCoverLayoutClickListener;
    }

    public interface OnCoverLayoutClickListener {
        void onCoverLayoutClick(View v);
    }

    // --------------------重写addView相关方法，以便获取contentView--------------------
    private void checkIsContentView(View view) {
        if (contentView == null && view != coverView ) {
            contentView = view;
        }
    }

    @Override
    public void addView(View child) {
        checkIsContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        checkIsContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        checkIsContentView(child);
        super.addView(child, width, height);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }
}
