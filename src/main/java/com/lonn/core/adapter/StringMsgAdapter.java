package com.lonn.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lonn.core.R;
import com.lonn.core.bean.StringMsg;
import java.util.List;

public class StringMsgAdapter extends BaseAdapter {

	private List<StringMsg> list;
	private Context context;

	public StringMsgAdapter(Context context, List<StringMsg> list) {
		this.context = context;
		this.list = list;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.lonn_adapter_stringmsg, null);
			holder = new ViewHolder();
			holder.iv_icon = convertView.findViewById(R.id.lonn_adapter_stringmsg_iv_icon);
			holder.tv_title = convertView.findViewById(R.id.lonn_adapter_stringmsg_tv_title);
			holder.tv_msg = convertView.findViewById(R.id.lonn_adapter_stringmsg_tv_msg);
			holder.iv_arrow = convertView.findViewById(R.id.lonn_adapter_stringmsg_iv_arrow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		StringMsg sm = list.get(position);
		if(sm != null){
			if(sm.getImageRes() > 0){
				holder.iv_icon.setVisibility(View.VISIBLE);
				holder.iv_icon.setImageResource(sm.getImageRes());
			}else{
				holder.iv_icon.setVisibility(View.GONE);
			}

			holder.tv_title.setText(sm.getTitle());
			holder.tv_msg.setText(sm.getMsg());
			if(sm.isHasArrow()){
				holder.iv_arrow.setVisibility(View.VISIBLE);
			}else{
				holder.iv_arrow.setVisibility(View.INVISIBLE);
			}
			
		}else{
			holder.tv_title.setText("");
			holder.tv_msg.setText("");
			holder.iv_arrow.setVisibility(View.INVISIBLE);
		}
		
		

		return convertView;
	}
	

	public static class ViewHolder {
		public ImageView iv_icon;
		public TextView tv_title;
		public TextView tv_msg;
		public ImageView iv_arrow;
	}

}
