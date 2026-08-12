/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
