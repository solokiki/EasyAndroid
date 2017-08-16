package com.lonn.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lonn.core.R;
import com.lonn.core.bean.SelectItem;
import java.util.HashMap;
import java.util.List;

public class SelectAdapter extends BaseAdapter {
	private List<SelectItem> list;
	private LayoutInflater inflater;
	private Context context;
	
	public static HashMap<Integer, Boolean> isSelected;
	
	public SelectAdapter(Context context) {
		this.context=context;
	}
	
	private void init() {
		isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < list.size(); i++) {
		    isSelected.put(i, list.get(i).isSelected());
		 }
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

	/**
	 * 获得GridView中每个Item的View对象；
	 * 
	 * */
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.lonn_adapter_select, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.adapter_select_text);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.adapter_select_checkBox);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		SelectItem item = list.get(position);
		if(item != null){
			holder.name.setText(item.getTitle());
		}else{
			holder.name.setText("");
		}
		holder.checkBox.setChecked( isSelected.get(position) );
		
		return convertView;
	}
	
	public void setList(List<SelectItem> list){
		this.list=list;
		init();
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	public static class ViewHolder{
		public TextView name;
		public CheckBox checkBox;
	}

}
