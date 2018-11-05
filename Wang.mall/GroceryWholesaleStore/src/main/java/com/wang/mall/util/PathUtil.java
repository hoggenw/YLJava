package com.wang.mall.util;

public class PathUtil {

	private static String separator = System.getProperty("file.separator");

	public static String getImgBasePath() {
		String oString = System.getProperty("os.name");
		String basePath = "";
		if (oString.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/wangliugen/Desktop/image/";
		}
		basePath = basePath.replace("/", separator);
		return basePath;
	}

	public static String getShopImagePath(Long shopId) {
		String imagePath = "upload/item/shop/" + shopId + "/";
		imagePath = imagePath.replace("/", separator);
		return imagePath;
	}

}
