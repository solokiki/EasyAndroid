package com.lonn.core.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ResultView extends LinearLayout implements View.OnClickListener{

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILURE = 2;
    public static final int STATUS_EMPTY = 3;

    private Context context;

    private ImageView iv_image;
    private TextView tv_message;

    private int status;

    public ResultView(Context context) {
        super(context);
        init(context);
    }

    public ResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c){
        this.context = c;

        LayoutParams lp_root = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(lp_root);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);

        LayoutParams lp_child = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp_child.bottomMargin = 16;

        iv_image = new ImageView(context);
        iv_image.setLayoutParams(lp_child);

        tv_message = new TextView(context);
        tv_message.setTextColor(Color.parseColor("#9C9C9C"));
        tv_message.setLayoutParams(lp_child);

        addView(iv_image);
        addView(tv_message);

        setOnClickListener(this);
    }

    public void showSuccess(ViewGroup group, int image, String msg){
        status = STATUS_SUCCESS;
        show(group, image, msg);
    }

    public void showFailure(ViewGroup group, int image, String msg){
        status = STATUS_FAILURE;
        show(group, image, msg);
    }

    public void showEmpty(ViewGroup group, int image, String msg){
        status = STATUS_EMPTY;
        show(group, image, msg);
    }

    private void show(ViewGroup group, int image, String msg){
        if(group == null){
            return;
        }
        hide(group);
        setImage(image);
        setMessage(msg);
        measure(0,0);
        group.addView(this);
        group.invalidate();
    }

    public void hide(ViewGroup group){
        if(group != null){
            group.removeView(this);
        }
    }

    public void setImage(int resId){
        if(iv_image != null){
            iv_image.setImageResource(resId);
        }
    }

    public void setMessage(String msg){
        if(tv_message != null){
            tv_message.setText(msg);
        }
    }

    public void setMessageTextSize(float size) {
        if(tv_message != null){
            tv_message.setTextSize(size);
        }
    }

    public void setMessageTextColor(int color) {
        if(tv_message != null){
            tv_message.setTextColor(color);
        }
    }

    public void setMessageLineSpacing(float add, float mult) {
        if(tv_message != null){
            tv_message.setLineSpacing(add, mult);
        }
    }

    @Override
    public void onClick(View v) {
        if(resultViewClickListener == null){
            return;
        }

        if(status == STATUS_EMPTY || status == STATUS_FAILURE){
            resultViewClickListener.onReload();
        }
    }

    private ResultViewClickListener resultViewClickListener;

    public void addOnClickListener(ResultViewClickListener resultViewClickListener) {
        this.resultViewClickListener = resultViewClickListener;
    }

    public interface ResultViewClickListener {
        void onReload();
    }
}
