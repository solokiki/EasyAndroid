package com.lonn.core.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.lonn.core.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBannerAdapter<T> extends PagerAdapter {
    private Context mContext;
    private List<T> datas;
    private List<View> mViews;

    public BaseBannerAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.datas = datas;
        initViews(mContext);
    }

    private void initViews(Context context) {
        if (context == null || datas == null) {
            return;
        }

        // 无限循环处理
        if(datas.size() > 1) {
            datas.add(0,datas.get(datas.size()-1));
            datas.add(datas.get(1));
        }

        mViews = new ArrayList<View>();
        View view;
        for (T data : datas) {
            view = getItemView(data, context);
            view.setTag(R.id.lonn_common_tag, data);
            view.setOnClickListener(onClickListener);
            mViews.add(view);
        }
    }

    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mViews != null && mViews.size() > position) {
            container.removeView(mViews.get(position));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    protected abstract View getItemView(T data, Context context);

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mOnItemClickLitener != null){
                mOnItemClickLitener.onItemClick(v, v.getTag(R.id.lonn_common_tag));
            }
        }
    };

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, Object o);
    }

}
