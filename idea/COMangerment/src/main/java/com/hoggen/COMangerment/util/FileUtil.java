package com.hoggen.COMangerment.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {

	private static final SimpleDateFormat sdatwFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
	private static final Random r = new Random();

//	public static void deleteFile(String storePath) {
//		File file = new File(PathUtil.getImgBasePath() + storePath);
//		if (file.exists()) {
//			if (file.isDirectory()) {
//				File files[] = file.listFiles();
//				for (int i = 0; i < files.length; i++) {
//					files[i].delete();
//				}
//			}
//			file.delete();
//		}
//	}

//	public static File saveFileOrPicture(CommonsMultipartFile dicom, String targetAddr) {
//		String realFileName = getRandomFileName();
//		String extension = ".jpg";// getFileExtension(dicom);
//		// String extension = "";
//		makeDirPath(targetAddr);
//		String relativeAddr = targetAddr + realFileName + extension;
//		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
//
////		try {
////			Thumbnails.of(dicom.getInputStream()).toFile(dest);
////		} catch (Exception e) {
////			e.printStackTrace();
////			// TODO: handle exception
////		}
//		// return PathUtil.getImgBasePath() + relativeAddr;
//		return dest;
//	}

	/**
	 * @注释 创建目标路径上所涉及到目录
	 */
//	private static void makeDirPath(String targetAddr) {
//		// TODO Auto-generated method stub
//		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
//		File firPath = new File(realFileParentPath);
//		if (!firPath.exists()) {
//			firPath.mkdirs();
//		}
//	}

	private static String getRandomFileName() {

		// 获取随机五位
		int rannumber = r.nextInt(89999) + 10000;
		String nowTimeStr = sdatwFormat.format(new Date());

		return nowTimeStr + rannumber;
	}

	/**
	 * @注释 获取输入文件流的扩展名
	 */
	private static String getFileExtension(CommonsMultipartFile thumbnail) {
		String originalFileName = thumbnail.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	// base64字符串转化成图片
	public static InputStream getImageStringToInputStream(String imgStr) {

		Base64.Decoder decoder = Base64.getDecoder();
		try {
			// Base64解码
			byte[] b = decoder.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			InputStream inputStream = new ByteArrayInputStream(b);

			return inputStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
