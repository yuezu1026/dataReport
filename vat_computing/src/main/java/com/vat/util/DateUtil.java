package com.vat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getCurrentDate() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	System.out.println("当前时间：" + sdf.format(date));
	return sdf.format(date);
    }

    public static String getDateNo() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	System.out.println("当前时间：" + sdf.format(date));
	return sdf.format(date);
    }

    public static String getYyyyMMddDate() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	System.out.println("当前时间：" + sdf.format(date));
	return sdf.format(date);
    }

    public static String getYyyy_MM_ddDate() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	System.out.println("当前时间：" + sdf.format(date));
	return sdf.format(date);
    }

    public static String getNextYearDate() {
	Date date = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);// 设置起时间
	cal.add(Calendar.YEAR, 1);// 增加一年
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format(cal.getTime());
    }
}
