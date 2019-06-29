package com.hoggen.COMangerment.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static int getStringLen(String str) {
		int reLn = 0;
		if (str == null || str.equals("")) {
			return reLn;
		}
		try {
			byte[] t_utf8 = str.getBytes("UTF-8");
			return t_utf8.length;
		} catch (Exception e) {
			return reLn;
			// TODO: handle exception
		}

//		String teString = "asdf";
//		String teString1 = "中文中文";
//		String teString2 = "1234";
//		String teString3 = "q4啊";
//		System.out.println("teString:" + StringUtil.getStringLen(teString) + "teString1:"
//				+ StringUtil.getStringLen(teString1) + "teString2:" + StringUtil.getStringLen(teString2) + "teString3:"
//				+ StringUtil.getStringLen(teString3));
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		if (dateDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLongForFileName(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrYMD(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrYM(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
}
