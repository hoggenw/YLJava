package com.wang.mall.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sdatwFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
	private static final Random r = new Random();

	public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(400, 300)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "好评-2.png")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return PathUtil.getImgBasePath() + relativeAddr;
	}

	public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
		int count = 0;
		List<String> relativeAddrList = new ArrayList<String>();
		if (imgs != null && imgs.size() > 0) {
			makeDirPath(targetAddr);
			for (CommonsMultipartFile img : imgs) {
				String realFileName = getRandomFileName();
				String extension = getFileExtension(img);
				String relativeAddr = targetAddr + realFileName + count + extension;
				File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
				count++;
				try {
					Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
				} catch (IOException e) {
					throw new RuntimeException("创建图片失败：" + e.toString());
				}
				relativeAddrList.add(relativeAddr);
			}
		}
		return relativeAddrList;
	}

	/**
	 * @注释 storePath是文件的路径还是目录的路劲 如果是文件；路径，删除该文件 如果是目录路径这删除该目录下所有文件
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (File file : files) {
					file.delete();
				}
			}
			fileOrPath.delete();
		}

	}

	/**
	 * @注释 创建目标路径上所涉及到目录
	 */
	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File firPath = new File(realFileParentPath);
		if (!firPath.exists()) {
			firPath.mkdirs();
		}
	}

	/**
	 * @注释 获取输入文件流的扩展名
	 */
	private static String getFileExtension(CommonsMultipartFile thumbnail) {
		String originalFileName = thumbnail.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	private static String getRandomFileName() {

		// 获取随机五位
		int rannumber = r.nextInt(89999) + 10000;
		String nowTimeStr = sdatwFormat.format(new Date());

		return nowTimeStr + rannumber;
	}

	public static void main(String[] args) {

		// try {
		// Thumbnails.of(new File("/Users/wangliugen/Desktop/image/美女.jpg")).size(400,
		// 300)
		// .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath +
		// "/好评-2.png")), 0.25f)
		// .outputQuality(0.8f).toFile("/Users/wangliugen/Desktop/image/美女6.jpg");
		// } catch (Exception e) {
		// e.printStackTrace();
		// // TODO: handle exception
		// }

	}

}
