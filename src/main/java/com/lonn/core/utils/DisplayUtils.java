package com.lonn.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayUtils {

	private static final int DisplayUtils_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

    /**
     * dip2px
     */
    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dip
     */
    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, int spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static int getDeviceFitLevel(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth();
        if (w <= 320)
            return 0;
        else if (w >= 480)
            return 1;
        else
            return 2;
    }

    public static int getDeviceFitSampleLevel(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth();
        if (w <= 320)
            return 256;
        else if (w >= 480)
            return 512;
        else
            return 128;
    }

    /**
     * 计算控件宽度 measureWidth
     */
    public static int measureWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }

    /**
     * 计算控件高度 measureHeight
     */
    public static int measureHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    /**
     * 计算出该TextView中文字的长度(像素) getTextViewLength
     */
    public static float getTextViewWidth(TextView textView, String text) {
        TextPaint paint = textView.getPaint();
        // 得到使用该paint写上text的时候,像素为多少
        float textLength = paint.measureText(text);
        return textLength;
    }

    /**
     * 根据px获取文字所占宽度 getTextViewWidth
     */
    public static float getTextWidthBySize(int size_px, String displayText) {
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(size_px);
        float textWidth = mTextPaint.measureText(displayText);
        return textWidth;
    }

    /**
     * 计算屏幕宽度 measureScreenWidth
     */
    public static int measureScreenWidth(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int width = metric.widthPixels; // 屏幕宽度（像素）px
        return width;
    }

    /**
     * 计算屏幕高度 measureScreenWidth
     */
    public static int measureScreenHeight(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int height = metric.heightPixels; // 屏幕高度（像素）

        return height;
    }

    /**
     * 计算actionbar高度 measureActionBarHeight
     */
    public static float measureActionBarHeight(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            TypedArray actionbarSizeTypedArray = activity.obtainStyledAttributes(new int[]
                    {android.R.attr.actionBarSize});
            float h = actionbarSizeTypedArray.getDimension(0, 0);

            return h;
        }
        return 100;
    }

    /**
     * 计算状态栏高度高度 measureStatusHeight
     */
    public static int measureStatusHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        return statusBarHeight;
    }

    public static int measureContentHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.bottom - frame.top;
        return statusBarHeight;
    }

    /**
     * 计算页面高度 measureActivityHeight
     */
    public static float measureActivityHeight(Activity activity) {
        return measureScreenHeight(activity) - measureStatusHeight(activity) - measureActionBarHeight(activity);
    }

    /**
     * 描述：根据分辨率获得字体大小.
     */
    public static int resizeTextSize(int textSize, Activity activity) {
        if (textSize == 0 || activity == null) {
            return 0;
        }

        float ratio = 1;
        try {

            float ratioWidth = (float) measureScreenWidth(activity) / 480;
            float ratioHeight = (float) measureScreenHeight(activity) / 800;

            ratio = Math.min(ratioWidth, ratioHeight);
        } catch (Exception e) {
        }

        return Math.round(textSize * ratio);
    }

    /**
     * 设置touch效果
     */
    public static void setTouchEffect(View view, int colorRes) {
        if (view == null)
            return;
        view.setOnTouchListener(buildLayTouchListener(colorRes, view.getContext()));
    }

    /**
     * 自定义按钮点击效果
     */
    public static View.OnTouchListener buildLayTouchListener(final int colorRes, final Context context) {
        return new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setSelected(view, true);
                        break;
                    default:
                        break;
                }
                return false;
            }

            private void setSelected(final View view, boolean isSelected) {
                Drawable drawable = view.getBackground();
                if (drawable == null) {
                    if (view instanceof ImageView) {
                        drawable = ((ImageView) view).getDrawable();
                    } else if (view instanceof TextView) {
                        Drawable[] drawables = ((TextView) view).getCompoundDrawables();
                        for (int i = 0; i < drawables.length; i++) {
                            if (drawables[i] != null) {
                                drawable = drawables[i];
                                break;
                            }
                        }
                    }
                }
                if (drawable == null) {
                    return;
                }

                if (isSelected) {
                    drawable.setColorFilter(context.getResources().getColor(colorRes), PorterDuff.Mode.MULTIPLY);
                    view.invalidate();
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setSelected(view, false);
                        }
                    }, 100);
                } else {
                    drawable.clearColorFilter();
                    view.invalidate();
                }
            }
        };
    }

    /**
     * 根据屏幕大小获取适当的尺寸标志 getSuitableSize
     */
    public static String getSuitableSize(Context context) {
        // 480 640 750 1080 1224
        // s m l x xx
        int screenWidth = measureScreenWidth(context);
        if (screenWidth <= 560) {
            return "s";
        } else if (screenWidth <= 695) {
            return "m";
        } else if (screenWidth <= 915) {
            return "m";
        } else if (screenWidth <= 1152) {
            return "l";
        } else {
            return "x";
        }
    }

    /**
     * 获取带边框的drawable
     */
    public static Drawable getBorderDrawable(int strokeWidth, int roundRadius, int strokeColor, int fillColor) {
        GradientDrawable gd = new GradientDrawable();// 创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    public static int parseColor(String colorStr, String defColor) {
        int color = Color.parseColor(defColor);
        try {
            color = Color.parseColor(colorStr);
        } catch (Exception e) {
        }
        return color;
    }

    /**
     * 获取屏幕长宽比
     */
    public static float getScreenRate(Context context) {
        float H = measureScreenHeight(context);
        float W = measureScreenWidth(context);
        return (H / W);
    }
}
