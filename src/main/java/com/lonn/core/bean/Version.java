package com.lonn.core.bean;

public class Version extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String KEY = "bean_version";
	
	// 最新版本名
	private String versionName;
	// 最新版本号
	private int versionCode;
	// 下载链接
	private String url;
	// 更新日志
	private String logMsg;
	// 是否需要强制更新
	private int mustUpdate;
	// 最低支持的版本号
	private int minVersion;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public int getMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(int mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	public int getMinVersion() {
		return minVersion;
	}

	public void setMinVersion(int minVersion) {
		this.minVersion = minVersion;
	}
}
