package com.lonn.core.utils;

import android.app.Activity;
import android.view.View;

public class LayoutUtil {
    public static View find(Activity activity, int layout_id){
    	return activity.findViewById(layout_id);
    }
}
