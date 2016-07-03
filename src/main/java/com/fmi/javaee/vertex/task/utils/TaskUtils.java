package com.fmi.javaee.vertex.task.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskUtils {

	public static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
	
	public static String modifyDate(String pattern, long timeInMills) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMills);
		return dateFormat.format(calendar.getTime());
	}
}
