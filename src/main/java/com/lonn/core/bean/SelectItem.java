package com.lonn.core.bean;

public class SelectItem extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private Object object;
	private boolean isSelected = false;
	
	public SelectItem(String id, String title, Object object, boolean isSelected) {
		super();
		this.id = id;
		this.title = title;
		this.object = object;
		this.isSelected = isSelected;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
}
