package com.jingbabyadmin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 返回当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateTimeString() {
		return format.format(new Date());
	}
	
	/**
	 * 返回当前时间字符串，格式自定义
	 * @param format
	 * @return
	 */
	public static String getDateTimeString(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
}
