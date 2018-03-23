package com.lonn.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lonn.core.R;

@SuppressLint("NewApi")
public class TitleView extends LinearLayout implements View.OnClickListener {
    public static final int THEME_LEFT = 1;
    public static final int THEME_MIDDLE = 2;
    public static final int THEME_RIGHT = 3;
    public static final int THEME_LEFT_MIDDLE = 4;
    public static final int THEME_LEFT_MIDDLE_RIGHT = 5;
    public static final int THEME_LEFT_MIDDLE_RIGHT2 = 6;
    public static final int THEME_MIDDLE_RIGHT = 7;
    public static final int THEME_MIDDLE_RIGHT2 = 8;

    public static final boolean THEME_LEFT_TITLE = false;

    // 左右按钮类型
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_BUTTON = 1;
    public static final int TYPE_TEXT = 2;

    private LinearLayout leftLayout1;
    private LinearLayout leftLayout2;
    private LinearLayout middleLayout;
    private LinearLayout rightLayout1;
    private LinearLayout rightLayout2;

    private View left1;
    private View left2;
    private TextView tv_title;
    private View right1;
    private View right2;

    // 自定义属性
    private int theme;

    private boolean leftTitle;

    private int left1_type;
    private int left2_type;
    private int right1_type;
    private int right2_type;

    private int left1_bg;
    private int left2_bg;
    private int right1_bg;
    private int right2_bg;

    public TitleView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        theme = array.getInt(R.styleable.TitleView_lonnTVTheme, THEME_MIDDLE);
        leftTitle = array.getBoolean(R.styleable.TitleView_lonnTVLeftTitle, THEME_LEFT_TITLE);
        left1_type = array.getInt(R.styleable.TitleView_lonnTVLeft1Type, TYPE_IMAGE);
        left2_type = array.getInt(R.styleable.TitleView_lonnTVLeft2Type, TYPE_IMAGE);
        right1_type = array.getInt(R.styleable.TitleView_lonnTVRight1Type, TYPE_IMAGE);
        right2_type = array.getInt(R.styleable.TitleView_lonnTVRight2Type, TYPE_IMAGE);
        left1_bg = array.getResourceId(R.styleable.TitleView_lonnTVLeft1Background, 0);
        left2_bg = array.getResourceId(R.styleable.TitleView_lonnTVLeft2Background, 0);
        right1_bg = array.getResourceId(R.styleable.TitleView_lonnTVRight1Background, 0);
        right2_bg = array.getResourceId(R.styleable.TitleView_lonnTVRight2Background, 0);

        init();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.lonn_view_title, this);
        initView(getRootView());
        addListener();
    }


    private void initView(View view) {

        leftLayout1 = (LinearLayout) view.findViewById(R.id.lonn_view_title_layout_left1);
        leftLayout2 = (LinearLayout) view.findViewById(R.id.lonn_view_title_layout_left2);
        middleLayout = (LinearLayout) view.findViewById(R.id.lonn_view_title_layout_middle);
        rightLayout1 = (LinearLayout) view.findViewById(R.id.lonn_view_title_layout_right1);
        rightLayout2 = (LinearLayout) view.findViewById(R.id.lonn_view_title_layout_right2);
        tv_title = (TextView) view.findViewById(R.id.lonn_view_title_tv_title);

        initType();
        initTheme(theme);
    }

    private void initTheme(int t) {
        switch (t) {
            case THEME_LEFT:
                leftLayout1.setVisibility(View.VISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.INVISIBLE);
                rightLayout1.setVisibility(View.INVISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_MIDDLE:
                leftLayout1.setVisibility(View.INVISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.INVISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_RIGHT:
                leftLayout1.setVisibility(View.INVISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.INVISIBLE);
                rightLayout1.setVisibility(View.VISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_LEFT_MIDDLE:
                leftLayout1.setVisibility(View.VISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.INVISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_LEFT_MIDDLE_RIGHT:
                leftLayout1.setVisibility(View.VISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.VISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_LEFT_MIDDLE_RIGHT2:
                leftLayout1.setVisibility(View.VISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.VISIBLE);
                rightLayout2.setVisibility(View.VISIBLE);
                break;
            case THEME_MIDDLE_RIGHT:
                leftLayout1.setVisibility(View.INVISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.VISIBLE);
                rightLayout2.setVisibility(View.INVISIBLE);
                break;
            case THEME_MIDDLE_RIGHT2:
                leftLayout1.setVisibility(View.INVISIBLE);
                leftLayout2.setVisibility(View.INVISIBLE);
                middleLayout.setVisibility(View.VISIBLE);
                rightLayout1.setVisibility(View.VISIBLE);
                rightLayout2.setVisibility(View.VISIBLE);
                break;
        }

        // 标题文字靠左的相关设置
        if(leftTitle){
            middleLayout.setGravity(Gravity.CENTER_VERTICAL);
            if(leftLayout1.getVisibility() == View.INVISIBLE){
                leftLayout1.setVisibility(View.GONE);
            }
            if(leftLayout2.getVisibility() == View.INVISIBLE){
                leftLayout2.setVisibility(View.GONE);
            }
            if(leftLayout1.getVisibility() == View.GONE && leftLayout2.getVisibility() == View.GONE){
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) middleLayout.getLayoutParams();
                lp.setMargins(24,0,0,0);
            }
        }
    }

    private void initType() {

        left1 = createView(left1_type, getContext());
        left2 = createView(left2_type, getContext());
        right1 = createView(right1_type, getContext());
        right2 = createView(right2_type, getContext());

        if (left1 != null) {
            left1.setId(R.id.lonn_titleView_left1_id);
            leftLayout1.addView(left1);
            if (left1_bg != 0) {
                left1.setBackgroundResource(left1_bg);
            }
        }

        if (left2 != null) {
            left2.setId(R.id.lonn_titleView_left2_id);
            leftLayout2.addView(left2);
            if (left2_bg != 0) {
                left2.setBackgroundResource(left2_bg);
            }
        }

        if (right1 != null) {
            right1.setId(R.id.lonn_titleView_right1_id);
            rightLayout1.addView(right1);
            if (right1_bg != 0) {
                right1.setBackgroundResource(right1_bg);
            }
        }

        if (right2 != null) {
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            right2.setId(R.id.lonn_titleView_right2_id);
            rightLayout2.addView(right2, lp);
            if (right2_bg != 0) {
                right2.setBackgroundResource(right2_bg);
            }
        }

    }

    private View createView(int type, Context context) {
        View view = null;
        switch (type) {
            case TYPE_IMAGE:
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                view = imageView;
                break;
            case TYPE_BUTTON:
                view = new Button(context);
                break;
            case TYPE_TEXT:
                view = new TextView(context);
                ((TextView) view).setTextSize(14);
                ((TextView) view).setTextColor(Color.BLACK);
                ((TextView) view).setGravity(Gravity.CENTER);
                break;
        }
        return view;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 设置标题
     *
     * @param resid
     */
    public void setTitle(int resid) {
        tv_title.setText(resid);
    }

    /**
     * 设置标题字体大小
     *
     * @param size
     */
    public void setTitleTextSize(float size) {
        tv_title.setTextSize(size);
    }

    /**
     * 设置标题字体颜色
     *
     * @param color
     */
    public void setTitleTextColor(int color) {
        tv_title.setTextColor(color);
    }

    /**
     * 设置整体背景
     */
    public void setBackgroundResource(int resid) {
        getRootView().setBackgroundResource(resid);
    }

    /**
     * 设置整体背景
     */
    public void setBackgroundColor(int color) {
        getRootView().setBackgroundColor(color);
    }

    /**
     * 设置左侧显示或者隐藏
     *
     * @param hide
     */
    public void hideLeft1Layout(boolean hide) {
        if (hide) {
            leftLayout1.setVisibility(View.INVISIBLE);
        } else {
            leftLayout1.setVisibility(View.VISIBLE);
        }
    }

    public void hideLeft2Layout(boolean hide) {
        if (hide) {
            leftLayout2.setVisibility(View.INVISIBLE);
        } else {
            leftLayout2.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置右侧显示或者隐藏
     *
     * @param hide
     */
    public void hideRight1Layout(boolean hide) {
        if (hide) {
            rightLayout1.setVisibility(View.INVISIBLE);
        } else {
            rightLayout1.setVisibility(View.VISIBLE);
        }
    }

    public void hideRight2Layout(boolean hide) {
        if (hide) {
            rightLayout2.setVisibility(View.INVISIBLE);
        } else {
            rightLayout2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置左侧按钮显示文本
     *
     * @param text
     */
    public void setLeft1Text(CharSequence text) {
        if (left1 instanceof TextView) {
            ((TextView) left1).setText(text);
        }
    }

    public void setLeft2Text(CharSequence text) {
        if (left2 instanceof TextView) {
            ((TextView) left2).setText(text);
        }
    }

    public void setLeft1TextColor(int color) {
        if (left1 instanceof TextView) {
            ((TextView) left1).setTextColor(color);
        }
    }

    public void setLeft2TextColor(int color) {
        if (left2 instanceof TextView) {
            ((TextView) left2).setTextColor(color);
        }
    }

    public void setLeft1TextSize(float size) {
        if (left1 instanceof TextView) {
            ((TextView) left1).setTextSize(size);
        }
    }

    public void setLeft2TextSize(float size) {
        if (left2 instanceof TextView) {
            ((TextView) left2).setTextSize(size);
        }
    }

    /**
     * 设置左侧按钮背景
     */
    public void setLeft1BackgroundResource(int resId) {
        left1.setBackgroundResource(resId);
    }

    public void setLeft2BackgroundResource(int resId) {
        left2.setBackgroundResource(resId);
    }

    /**
     * 设置右边第一个按钮显示文本
     *
     * @param text
     */
    public void setRight1Text(CharSequence text) {
        if (right1 instanceof TextView) {
            ((TextView) right1).setText(text);
        }
    }

    public void setRight2Text(CharSequence text) {
        if (right2 instanceof TextView) {
            ((TextView) right2).setText(text);
        }
    }

    /**
     * 设置右边第一个按钮背景
     *
     * @param resId
     */
    public void setRight1BackgroundResource(int resId) {
        right1.setBackgroundResource(resId);
    }

    public void setRight2BackgroundResource(int resId) {
        right2.setBackgroundResource(resId);
    }

    /**
     * 设置右边第一个按钮字体大小
     *
     * @param size
     */
    public void setRight1TextSize(float size) {
        if (right1 instanceof TextView) {
            ((TextView) right1).setTextSize(size);
        }
    }

    public void setRight2TextSize(float size) {
        if (right2 instanceof TextView) {
            ((TextView) right2).setTextSize(size);
        }
    }

    /**
     * 设置右边第一个按钮字体颜色
     *
     * @param color
     */
    public void setRight1TextColor(int color) {
        if (right1 instanceof TextView) {
            ((TextView) right1).setTextColor(color);
        }
    }

    public void setRight2TextColor(int color) {
        if (right2 instanceof TextView) {
            ((TextView) right2).setTextColor(color);
        }
    }

    private void addListener() {

        if (left1 != null) {
            left1.setOnClickListener(this);
        }

        if (left2 != null) {
            left2.setOnClickListener(this);
        }

        if (right1 != null) {
            right1.setOnClickListener(this);
        }

        if (right2 != null) {
            right2.setOnClickListener(this);
        }

        if (leftLayout1 != null) {
            leftLayout1.setOnClickListener(this);
        }

        if (leftLayout2 != null) {
            leftLayout2.setOnClickListener(this);
        }

        if (rightLayout1 != null) {
            rightLayout1.setOnClickListener(this);
        }

        if (rightLayout2 != null) {
            rightLayout2.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        if (titleViewClickListener == null) {
            return;
        }

        if(v.getId() == R.id.lonn_view_title_layout_left1){

            titleViewClickListener.onLeft1Click();

        }else if(v.getId() == R.id.lonn_view_title_layout_left2){

            titleViewClickListener.onLeft2Click();

        }else if(v.getId() == R.id.lonn_view_title_layout_right1){

            titleViewClickListener.onRight1Click();

        }else if(v.getId() == R.id.lonn_view_title_layout_right2){

            titleViewClickListener.onRight2Click();

        }else if(v.getId() == R.id.lonn_titleView_left1_id){

            titleViewClickListener.onLeft1Click();

        }else if(v.getId() == R.id.lonn_titleView_left2_id){

            titleViewClickListener.onLeft2Click();

        }else if(v.getId() == R.id.lonn_titleView_right1_id){

            titleViewClickListener.onRight1Click();

        }else if(v.getId() == R.id.lonn_titleView_right2_id){

            titleViewClickListener.onRight2Click();

        }

    }

    private TitleViewClickListener titleViewClickListener;

    public void addOnClickListener(TitleViewClickListener titleViewClickListener) {
        this.titleViewClickListener = titleViewClickListener;
    }

    public interface TitleViewClickListener {
        void onLeft1Click();

        void onLeft2Click();

        void onRight1Click();

        void onRight2Click();
    }

}
