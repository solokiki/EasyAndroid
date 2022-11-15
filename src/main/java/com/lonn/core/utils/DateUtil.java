package com.lonn.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author long chen
 *
 * 2014-1-2
 */
public class DateUtil {

	private static final int DateUtil_V_CODE = 2;  // 标记当前类的版本，高版本兼容低版本

	/**
	 * 获取SimpleDateFormat
	 * @param parttern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 获取日期中的某数值。如获取日期的年份、月份、天数等
	 * @param date 日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateType);
	}
	
	/**
	 * 增加日期中某类型的数值。如增加日期的年份、月份、天数等
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}
	
	/**
	 * 将字符串日期转换为Date,失败返回null。
	 * @param date 日期字符串
	 * @param parttern 日期格式
	 * @return 日期
	 */
	public static Date StringToDate(String date, String parttern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}
	
	/**
	 * 将字符串日期转换为Date,失败返回null。
	 * @param date
	 * @param dateStyle
	 * @return Date
	 */
	public static Date StringToDate(String date, DateStyle dateStyle) {
		Date myDate = null;
		
		if (dateStyle != null) {
			myDate = StringToDate(date, dateStyle.getValue());
		}
		
		return myDate;
	}
	
	/**
	 * 将Date转换为字符串日期,失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(parttern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	/**
	 * 将Date转换为字符串日期。失败返回null。
	 * @param date 日期
	 * @param dateStyle 日期风格
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (dateStyle != null) {
			dateString = DateToString(date, dateStyle.getValue());
		}
		return dateString;
	}

	public static String StringToString(String s_date, String oldPattern, String newPattern) {
		if (s_date == null || oldPattern == null || newPattern == null){
			return null;
		}
		SimpleDateFormat oldFormat = new SimpleDateFormat(oldPattern);
		SimpleDateFormat newFormat = new SimpleDateFormat(newPattern);
		Date date = null;
		try {
			date = oldFormat.parse(s_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFormat.format(date);
	}
	
	public static String StringToString(String s_date, DateStyle oldDateStyle, DateStyle newDateStyle) {
		if (s_date == null || oldDateStyle == null || oldDateStyle == null){
			return null;
		}
		
		return StringToString(s_date, oldDateStyle.getValue(), newDateStyle.getValue());
	}
	
	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	
	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param monthAmount 增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}
	
	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}
	
	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH);
	}

	/**
	 * 获取中文月份
	 * @param date
	 * @return
	 */
	public static String getMonth_CN(long date) {
		int m = getInteger(LongToDate(date), Calendar.MONTH);
		String month = null;
		switch (m) {
		case 0:
			month = Month.JANUARY.getChineseName();
			break;
		case 1:
			month = Month.FEBRUARY.getChineseName();
			break;
		case 2:
			month = Month.MARCH.getChineseName();
			break;
		case 3:
			month = Month.APRIL.getChineseName();
			break;
		case 4:
			month = Month.MAY.getChineseName();
			break;
		case 5:
			month = Month.JUNE.getChineseName();
			break;
		case 6:
			month = Month.JULY.getChineseName();
			break;
		case 7:
			month = Month.AUGUST.getChineseName();
			break;
		case 8:
			month = Month.SEPTEMBER.getChineseName();
			break;
		case 9:
			month = Month.OCTOBER.getChineseName();
			break;
		case 10:
			month = Month.NOVEMBER.getChineseName();
			break;
		case 11:
			month = Month.DECEMBER.getChineseName();
			break;

		}
		
		return month;
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期字符串
	 * @return 天
	 */
	public static int getDay(long date) {
		return getDay((LongToDate(date)));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}
	
	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}
	
	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 */
	public static String getTime(Date date) {
		return DateToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差天数
	 * @author long chen
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		long time = Math.abs( date.getTime() - otherDate.getTime() );
		int day = (int) (time/(24 * 60 * 60 * 1000));
		return day;
	}
	
	/**
	 * 判断指定的时间是否在某个时间区间内
	 * @param currentDate
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @author long chen
	 */
	public static boolean isInDate(Date currentDate, Date beginDate, Date endDate){
		boolean result = true;
		if(currentDate != null && beginDate != null && endDate != null){
			result = beginDate.before(currentDate) && endDate.after(currentDate);
		}
		return result;
	}
	
	/**
	 * 判断当前时间是否在某个时间区间内
	 * @param s_beginDate
	 * @param s_endDate
	 * @param dateStyle
	 * @return
	 * @author long chen
	 */
	public static boolean isInDate(String s_beginDate, String s_endDate, DateStyle dateStyle){
		Date beginDate = null;
		Date endDate = null;
		Date currentDate = null;
		
		try {
			beginDate = StringToDate(s_beginDate, dateStyle);
			endDate = StringToDate(s_endDate, dateStyle);
			currentDate = getDateFormat(dateStyle.getValue()).parse( getDateFormat(dateStyle.getValue()).format(new Date()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isInDate(currentDate, beginDate, endDate);
	}
	
	/**
	 * 获取日期。默认mm-dd-cn。失败返回null
	 * @param milliseconds
	 * @return 日期
	 * @author long chen
	 */
	public static String getDate_CN(long milliseconds) {
		long l_today = System.currentTimeMillis();
		long l_yesterday = l_today - 24 * 60 * 60 * 1000;
		Date today = new Date(l_today);
		Date yesterday = new Date(l_yesterday);
		String s_today = DateToString(today, DateStyle.YYYY_MM_DD_CN);
		String s_yesterday = DateToString(yesterday, DateStyle.YYYY_MM_DD_CN);
		
		Date date = new Date(milliseconds);
		String s_date = DateToString(date, DateStyle.YYYY_MM_DD_CN);
		
		if(s_today.equals(s_date)){
			return "今天";
		}else if(s_yesterday.equals(s_date)){
			return "昨天";
		}else{
			return s_date;
		}
	}
	
	/**
	 * 获取日期。默认yyyy-mm-dd。失败返回null
	 * @param milliseconds
	 * @return 日期
	 * @author long chen
	 */
	public static String getDate_EN(long milliseconds) {
		Date date = new Date(milliseconds);
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}
	
	/**
	 * 获取日期。默认mm-dd-cn。失败返回null
	 * @param milliseconds
	 * @return 日期
	 * @author long chen
	 */
	public static String getDate(long milliseconds, DateStyle dateStyle) {
		Date date = new Date(milliseconds);
		return DateToString(date, dateStyle);
	}
	
	/**
	 * 将long型数值转化为日期
	 * @param date
	 * @return 日期
	 * @author long chen
	 */
	public static Date LongToDate(long date) {
		return new Date(date);
	}
	
	/**
	 * 获取两个日期相差时间
	 * @param date
	 * @param otherDate
	 * @return 不足1年，返回月数；
	 *         不足1月（30天计），返回天数；
	 *         不足1天，返回小时；
	 *         不足1小时，返回分钟；
	 *         不足1分钟，返回秒数；
	 * @author long chen
	 * 注：只计算间隔时间，不考虑实际日期。
	 */
	public static String getIntervalTime(long date, long otherDate) {
		
		long time = Math.abs( date - otherDate );
		
		long year = time/(365 * 24 * 60 * 60 * 1000L);
		if(year > 0){
			return year + "年前";
		}
		
		long month = time/(30 * 24 * 60 * 60 * 1000L);
		if(month > 0){
			return month + "个月前";
		}
		
		long day = time/(24 * 60 * 60 * 1000L);
		if(day > 0){
			return day + "天前";
		}
		
		long hours = time/(60 * 60 * 1000L);
		if(hours > 0){
			return hours + "小时前";
		}
		
		long minutes = time/(60 * 1000L);
		if(minutes > 0){
			return minutes + "分钟前";
		}
		
		long second = time/(1000L);
		if(second > 0){
			return second + "秒前";
		}
		
		return "未知";
	}
	
	public static String getIntervalTime(Date date, Date otherDate) {
		return getIntervalTime(date.getTime(), otherDate.getTime());
	}
	
	/** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * @return String 返回值为：xx天xx小时xx分xx秒
     * @author long chen
     */  
    public static String getDistanceTime(long time1, long time2) {
    	long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;
        long diff = Math.abs(time1 - time2);
        
        day = diff / (24 * 60 * 60 * 1000);  
        hour = (diff / (60 * 60 * 1000) - day * 24);  
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }
	
	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 * @author long chen
	 */
	public static String getTime(long date) {
		return DateToString(LongToDate(date), DateStyle.HH_MM_SS);
	}
	
	/**
	 * 获取指定日期当天0点开始时间
	 * @Description: TODO
	 * @return long
	 * @author long chen
	 * @date 2016年8月11日 下午4:41:29
	 */
	public static long getStartTimeByDay(Date date){
		Calendar cal = Calendar.getInstance(); 
		if(date != null){
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return cal.getTimeInMillis(); 
	}
	
	/**
	 * 获取指定日期当天24点结束时间
	 * @Description: TODO
	 * @return long
	 * @author long chen
	 * @date 2016年8月11日 下午4:42:04
	 */
	public static long getEndTimeByDay(Date date){
		Calendar cal = Calendar.getInstance(); 
		if(date != null){
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return cal.getTimeInMillis(); 
	}
	
	/**
	 * 获取星座
	 * @param month
	 * @param day
	 * @return
	 * @author long chen
	 */
	public static Constellation getConstellation(int month, int day){
		if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
			return Constellation.ARIES;
	    } else if ((month == 4 && day >= 21) || (month == 5 && day <= 20)) {
	    	return Constellation.TAURUS;
	    } else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
	    	return Constellation.GEMINI;
	    } else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
	    	return Constellation.CANCER;
	    } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
	    	return Constellation.LEO;
	    } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
	    	return Constellation.VIRGO;
	    } else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
	    	return Constellation.LIBRA;
	    } else if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) {
	    	return Constellation.SCORPIO;
	    } else if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) {
	    	return Constellation.SAGITTARIUS;
	    } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
	    	return Constellation.CAPRICORN;
	    } else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
	        return Constellation.AQUARIUS;
	    } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
	        return Constellation.PISCES;
	    } else {
	    	return null;
	    }
	}
	
	public enum DateStyle {  
	    
	    MM_DD("MM-dd"),  
	    YYYY_MM("yyyy-MM"),  
	    YYYY_MM_DD("yyyy-MM-dd"),  
	    MM_DD_HH_MM("MM-dd HH:mm"),  
	    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),  
	    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),  
	    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

	    MM_DD_EN("MM/dd"),
	    YYYY_MM_EN("yyyy/MM"),  
	    YYYY_MM_DD_EN("yyyy/MM/dd"),  
	    MM_DD_HH_MM_EN("MM/dd HH:mm"),  
	    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),  
	    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),  
	    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),  
	      
	    MM_DD_CN("MM月dd日"),  
	    YYYY_MM_CN("yyyy年MM月"),  
	    YYYY_MM_DD_CN("yyyy年MM月dd日"),  
	    MM_DD_HH_MM_CN("MM月dd日 HH:mm"),  
	    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),  
	    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),  
	    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),  
	      
	    HH_MM("HH:mm"),  
	    HH_MM_SS("HH:mm:ss"),
	    
	    
	    YYMMDDHH("yyMMddHH");  
	    
	    
	    
	    private String value;  
	      
	    DateStyle(String value) {  
	        this.value = value;  
	    }  
	      
	    public String getValue() {  
	        return value;  
	    }  
	}  

	public enum Week {  
		  
	    MONDAY("星期一", "Monday", "Mon.", 1),  
	    TUESDAY("星期二", "Tuesday", "Tues.", 2),  
	    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),  
	    THURSDAY("星期四", "Thursday", "Thur.", 4),  
	    FRIDAY("星期五", "Friday", "Fri.", 5),  
	    SATURDAY("星期六", "Saturday", "Sat.", 6),  
	    SUNDAY("星期日", "Sunday", "Sun.", 7);  
	      
	    String name_cn;  
	    String name_en;  
	    String name_enShort;  
	    int number;  
	      
	    Week(String name_cn, String name_en, String name_enShort, int number) {  
	        this.name_cn = name_cn;  
	        this.name_en = name_en;  
	        this.name_enShort = name_enShort;  
	        this.number = number;  
	    }  
	      
	    public String getChineseName() {  
	        return name_cn;  
	    }  
	  
	    public String getName() {  
	        return name_en;  
	    }  
	  
	    public String getShortName() {  
	        return name_enShort;  
	    }  
	  
	    public int getNumber() {  
	        return number;  
	    }
	    
	} 
	
	public enum Month{
		JANUARY("一月", "January", "Jan", 0),
		FEBRUARY("二月", "February", "Feb", 1),
		MARCH("三月", "March", "Mar", 2),
		APRIL("四月", "April", "Apr", 3),
		MAY("五月", "May", "May", 4),
		JUNE("六月", "June", "Jun", 5),
		JULY("七月", "July", "Jul", 6),
		AUGUST("八月", "August", "Aug", 7),
		SEPTEMBER("九月", "September", "Sep", 8),
		OCTOBER("十月", "October", "Oct", 9),
		NOVEMBER("十一月", "November", "Nov", 10),
		DECEMBER("十二月", "December", "Dec", 11);
		
		String name_cn;  
	    String name_en;  
	    String name_enShort;  
	    int number;  
	      
	    Month(String name_cn, String name_en, String name_enShort, int number) {  
	        this.name_cn = name_cn;  
	        this.name_en = name_en;  
	        this.name_enShort = name_enShort;  
	        this.number = number;  
	    }
	    
	    public String getChineseName() {  
	        return name_cn;  
	    }  
	  
	    public String getName() {  
	        return name_en;  
	    }  
	  
	    public String getShortName() {  
	        return name_enShort;  
	    }  
	  
	    public int getNumber() {  
	        return number;  
	    }
		
	}
	
	public enum Constellation{
		ARIES("白羊座", "Aries", "Ari", 0),
		TAURUS("金牛座", "Taurus", "Tau", 1),
		GEMINI("双子座", "Gemini", "Gem", 2),
		CANCER("巨蟹座", "Cancer", "Can", 3),
		LEO("狮子座", "Leo", "Leo", 4),
		VIRGO("处女座", "Virgo", "Vir", 5),
		LIBRA("天秤座", "Libra", "Lib", 6),
		SCORPIO("天蝎座", "Scorpio", "Sco", 7),
		SAGITTARIUS("射手座", "Sagittarius", "Sag", 8),
		CAPRICORN("摩羯座", "Capricorn", "Cap", 9),
		AQUARIUS("水瓶座", "Aquarius", "Aqu", 10),
		PISCES("双鱼座", "Pisces", "Pis", 11);
		
		String name_cn;  
	    String name_en;  
	    String name_enShort;  
	    int number;  
	      
	    Constellation(String name_cn, String name_en, String name_enShort, int number) {  
	        this.name_cn = name_cn;  
	        this.name_en = name_en;  
	        this.name_enShort = name_enShort;  
	        this.number = number;  
	    }
	    
	    public String getChineseName() {  
	        return name_cn;  
	    }  
	  
	    public String getName() {  
	        return name_en;  
	    }  
	  
	    public String getShortName() {  
	        return name_enShort;  
	    }  
	  
	    public int getNumber() {  
	        return number;  
	    }
		
	}
	
}

