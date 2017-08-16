package com.lonn.core.app;

import android.app.Activity;

import com.lonn.core.utils.AppUtil;
import com.lonn.core.utils.ClassUtil;

import java.lang.reflect.Method;
import java.util.Stack;


/**
 * 当然活动的Activity栈
 * @author lonn
 * @date 2015-5-25
 */
public class AppActivities {
	private static Activity curActivity = null; // 当前活动 Activity
	private final static Stack<Activity> activityStack = new Stack<Activity>(); // Activity 栈

	/**
	 * 设置当前活动的 Activity
	 * @param activity 当前被激活的 Activity
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static void setCurrentActivity(Activity activity) {
		curActivity = activity;
	}

	/**
	 * 获取栈中 Activity 的数量
	 * @author lonn
	 * @date 2015-5-25
	 */
	public static int getActivityStackSize() {
		int stackSize = 0;

		synchronized (AppActivities.class) {
			stackSize = activityStack.size();
		}

		return stackSize;
	}

	/**
	 * 获取当前活动的 Activity
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static Activity getCurrentActivity() {
		return curActivity;
	}

	/**
	 * 退出栈中所有 Activity
	 * 
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static void finishAllActivities() {
		while (true) {
			if (activityStack.size() < 1) {
				return;
			}

			Activity curActivity = activityStack.lastElement();

			if (curActivity == null) {
				break;
			}

			curActivity.finish();
			activityStack.remove(curActivity);
		}
	}
	
	/**
	 * 移除掉堆栈中所有指定的activity
	 * 
	 * @author lonn
	 * @date 2015-5-25
	 * @param cls
	 */
	public synchronized static void finishTheActivity(Class<?> cls) {
		if (null == cls) {
			return;
		}

		if (activityStack.size() < 1) {
			return;
		}
		
		Activity curActivity = activityStack.lastElement();
		
		if (curActivity == null) {
			return ;
		}
		
		while(true){
			boolean needLoop = false;
			for(int i = 0; i < activityStack.size(); i++){
				if(null != activityStack.get(i) && activityStack.get(i).getClass().equals(cls)){
					needLoop = true;
					activityStack.get(i).finish();
					activityStack.remove(activityStack.get(i));
					break;
				}
			}
			if(!needLoop){
				break;
			}
		}
	}

	/**
	 * @Description: 销毁从栈底到指定Activity之间的所有Activity
	 * @param cls void
	 * @author long chen
	 * @date 2016年6月29日 下午5:40:23
	 */
	public synchronized static void popToActivity(Class<?> cls) {
		if (null == cls) {
			return;
		}

		while (true) {
			if (activityStack.size() < 1) {
				return;
			}

			Activity curActivity = activityStack.lastElement();

			if (curActivity == null) {
				break;
			}

			if (curActivity.getClass().equals(cls)) {
				break;
			}

			curActivity.finish();
			activityStack.remove(curActivity);
		}
	}

	/**
	 * @Description: 销毁从栈底到指定Activity之间的所有Activity
	 * @param activity void
	 * @author long chen
	 * @date 2016年6月29日 下午5:44:36
	 */
	public synchronized static void popToActivity(Activity activity) {
		if (null == activity) {
			return;
		}
		popToActivity(activity.getClass());
	}

	/**
	 * 获取当前 Activity的完整路径
	 * 
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static String getActivityPath() {
		if (activityStack.size() < 1) {
			return "/";
		}

		StringBuffer message = new StringBuffer();

		for (Activity x : activityStack) {
			message.append(String.format("/%s", ClassUtil.getClassName(x)));
		}

		return message.toString();
	}

	/**
	 * Activity入栈
	 * 
	 * @param activity Activity 对象
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static void pushActivity(Activity activity) {
		if (null == activity) {
			return;
		}

//		AppTasks.createActivityTasks(activity);

		if(activityStack.contains(activity)){
			activityStack.remove(activity);
		}

		activityStack.add(activity);
	}

	/**
	 * 把指定 Activity 从 activityStack 中移除
	 * 
	 * @param activity 指定 Activity
	 * @author lonn
	 * @date 2015-5-25
	 */
	public synchronized static void removeActivity(Activity activity) {
		if (activityStack.size() < 1 || null == activity) {
			return;
		}

//		AppTasks.RemoveActivityTasks(activity);

		for (Activity x : activityStack) {
			if (x.equals(activity)) {
				activityStack.remove(x);
				break;
			}
		}
	}

	/**
	 * 通知栈里面所有 activity 调用 method 方法
	 * 
	 * @param method 字符串方法名
	 * @param isLocalMethod true 表示获取本类所有成员方法; false 表示取该类中包括父类所有 public 方法
	 */
	public static void noticeActivity(final String method, final boolean isLocalMethod) {
		noticeActivity(method, isLocalMethod, null, null);
	}

	/**
	 * 通知栈里面所有 activity 调用 method 方法；带参数
	 * 
	 * @param method 字符串方法名
	 * @param isLocalMethod true 表示获取本类所有成员方法; false 表示取该类中包括父类所有 public 方法
	 * @param parType 参数类型数组
	 * @param obj 参数列表
	 */
	public static void noticeActivity(final String method, final boolean isLocalMethod, final Class<?>[] parType, final Object[] obj) {
		new Thread() { // 启用新线程的目的是不影响当前业务的进行
			@Override
			public void run() {
				synchronized (AppActivities.class) {
					for (final Activity activity : activityStack) {
						try {
							if (activity.isFinishing()) {
								continue;
							}

							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									try {
										if (activity.isFinishing()) {
											return;
										}

										Class<? extends Activity> activityClass = activity.getClass();
										Method activityMethod;

										if (!isLocalMethod) {
											// getDeclaredMethod得到所有权限的方法，getMethod只能获取到public类型的方法
											activityMethod = activityClass.getDeclaredMethod(method, parType);
										} else {
											// getMethod可以获取到包括父类中的public方法 替代以前的使用getSuperClass，这样避免不确定性因素
											activityMethod = activityClass.getMethod(method, parType);
										}

										// 设置该方法的访问权限
										activityMethod.setAccessible(true);

										// 调用该方法
										activityMethod.invoke(activity, obj);
									} catch (Exception e) {
										// 如果出错，不需要做处理，也不需要打印
									}
								}
							});
						} catch (Exception e) {
							// 如果出错，不需要做处理，也不需要打印
						}
					}
				}
			}
		}.start();
	}
}
