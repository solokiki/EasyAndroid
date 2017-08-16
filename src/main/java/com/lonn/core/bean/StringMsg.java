package com.lonn.core.bean;

public class StringMsg extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String KEY = "bean_StringMsg";

	// titie image
	private int imageRes;
	// title
	private String title;
	// msg
	private String msg;
	// arrow
	private boolean hasArrow;

	public StringMsg(int imageRes, String title, String msg, boolean hasArrow) {
		this.imageRes = imageRes;
		this.title = title;
		this.msg = msg;
		this.hasArrow = hasArrow;
	}

	public StringMsg(String title, String msg, boolean hasArrow) {
		super();
		this.title = title;
		this.msg = msg;
		this.hasArrow = hasArrow;
	}

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isHasArrow() {
		return hasArrow;
	}
	public void setHasArrow(boolean hasArrow) {
		this.hasArrow = hasArrow;
	}
	
	
}
