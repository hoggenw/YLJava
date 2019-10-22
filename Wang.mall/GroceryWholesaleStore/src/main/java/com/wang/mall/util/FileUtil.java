package com.wang.mall.util;

import java.io.File;

public class FileUtil {

	public static void deleteFile(String storePath) {
		File file = new File(PathUtil.getImgBasePath() + storePath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			file.delete();
		}
	}

}
