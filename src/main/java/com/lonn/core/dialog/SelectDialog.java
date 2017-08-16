package com.lonn.core.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lonn.core.R;
import com.lonn.core.adapter.SelectAdapter;
import com.lonn.core.bean.SelectItem;
import com.lonn.core.utils.Callback;

import java.util.List;

public class SelectDialog extends Dialog implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Context context;
    private String title;
    private List<SelectItem> list = null;
    private Callback callback;

    private TextView tv_title;
    private Button bt_cancel;
    private ListView lv_list;

    private int width, height;

    private SelectAdapter adapter = null;

    public SelectDialog(Context context, String title, List<SelectItem> list, Callback c) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.list = list;
        this.callback = c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lonn_dialog_select);
        initView();
        initSize();
        addListener();
        doNext();
    }

    private void initSize() {
        Window dialogWindow = getWindow();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        dialogWindow.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;


        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.height = (int) (height * 0.5);
        p.width = (int) (width * 0.9);
        dialogWindow.setAttributes(p);

    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.lonn_dialog_select_tv_title);
        bt_cancel = (Button) findViewById(R.id.lonn_dialog_select_bt_cancel);
        lv_list = (ListView) findViewById(R.id.lonn_dialog_select_lv_list);
    }

    private void addListener() {
        bt_cancel.setOnClickListener(this);
        lv_list.setOnItemClickListener(this);
    }

    private void doNext() {
        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
            tv_title.setText("");
        } else {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }

        adapter = new SelectAdapter(context);
        adapter.setList(list);
        lv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.lonn_dialog_select_bt_cancel){
            dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (callback != null) {
            SelectItem item = (SelectItem) parent.getAdapter().getItem(position);
            callback.onCallback(item.getObject());
            dismiss();
        }
    }
}
