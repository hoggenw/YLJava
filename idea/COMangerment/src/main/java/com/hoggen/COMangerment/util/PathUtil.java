package com.hoggen.COMangerment.util;

import java.util.Date;

public class PathUtil {

	private static String separator = System.getProperty("file.separator");

//	public static String getImgBasePath() {
//		String oString = System.getProperty("os.name");
//		String basePath = "";
//		if (oString.toLowerCase().startsWith("win")) {
//			basePath = "D:/projectdev/file/";
//		} else {
//			basePath = "/Users/wangliugen/Desktop/File/";
//		}
//		basePath = basePath.replace("/", separator);
//		return basePath;
//	}

	public static String getUserNameFilePath(String userName) {

		String imagePath = "upload/item/" + StringUtil.dateToStrLongForFileName(new Date()) + "/" + userName + "/";
		imagePath = imagePath.replace("/", separator);
		return imagePath;
	}
}
