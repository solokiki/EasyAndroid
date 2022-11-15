package com.lonn.core.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class EditTextUtil {

    private static final int EditTextUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

    /**
     * 指定最大值、指定最大小数位的EditText输入检查
     * 可在TextWatcher监听对象中调用此方法
     * 此方法尚需要完善，使用时请注意
     * @param editText EditText
     * @param maxValue 最大值
     * @param maxDigit 最大小数位
     */
    public static void formartInput(EditText editText, double maxValue, int maxDigit){
        String s_value = editText.getText().toString().trim();

        if(TextUtils.isEmpty(s_value)){
            return;
        }

        if(".".equals(s_value)){
            editText.setText("0.");
            editText.setSelection(editText.getText().toString().trim().length());
            return;
        }


        double value = Double.parseDouble(s_value);

        if (value > maxValue) {
            editText.setText(maxValue + "");
            editText.setSelection(editText.getText().toString().trim().length());
            return;
        }

        if (s_value.contains(".")) {
            if (s_value.length() - 1 - s_value.indexOf(".") > maxDigit) {
                s_value = s_value.substring(0, s_value.indexOf(".") + maxDigit + 1);
                editText.setText(s_value);
                editText.setSelection(editText.getText().toString().trim().length());
            }
        }
    }

}
