package com.lonn.core.utils;

import android.os.Handler;

public class MyHandler extends Handler{

	public static final String KEY_RESULT = "result";
	
    public static final int TYPE_A = 1;
    public static final int TYPE_B = 2;
    public static final int TYPE_C = 3;
    public static final int TYPE_D = 4;
    public static final int TYPE_E = 5;
    
    public enum MyResult{
    	RESULT_SUCCESS(0, "成功", "操作成功！"),
    	RESULT_NO_CONN(1, "连接失败", "连接不上服务器，请稍后再试！"),
    	RESULT_ERROR(2, "数据异常", "服务器维护中，请稍后再试！"),
    	RESULT_NULL(3, "空数据", "没有相关数据！"),
    	RESULT_NO_AUTHOR(5, "验证失效", "验证码过期，请重新登录！");
    	
    	// 编号
    	private int id;
    	// 标题
    	private String title;
    	// 详细信息
    	private String msg;
    	
		private MyResult(int id, String title, String msg) {
			this.id = id;
			this.title = title;
			this.msg = msg;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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
    	
    	
    	
    	
    }
    
}

