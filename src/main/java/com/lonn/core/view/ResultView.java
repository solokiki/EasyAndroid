package com.lonn.core.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultView extends LinearLayout {

    private Context context;

    private ImageView iv_image;
    private TextView tv_message;

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
        tv_message.setLayoutParams(lp_child);

        addView(iv_image);
        addView(tv_message);

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
}
