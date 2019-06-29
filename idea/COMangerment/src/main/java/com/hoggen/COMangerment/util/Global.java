package com.hoggen.COMangerment.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	private static final Logger logger = LoggerFactory.getLogger(Global.class);


	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}



	public void getInfo() {

		Properties prop = new Properties();
		try {

			logger.info("当前目录:" + new File(getProjectPath() + "/configuration.properties").getAbsolutePath());
			prop.load(new FileInputStream(new File(getProjectPath() + "/configuration.properties")));
			return;
		} catch (Exception e) {
			logger.info("当前目录获取configuration.properties失败");
			e.printStackTrace();
		}

		// 2. 试着从运行目录下找文件
		try {

			logger.info("Running directory:"
					+ new File(System.getProperty("user.dir") + "/configuration.properties").getAbsolutePath());
			prop.load(new FileInputStream((System.getProperty("user.dir") + "/configuration.properties")));
			return;
		} catch (Exception e) {
			logger.info("Running directory:get configuration.properties failed");
			e.printStackTrace();
		}

		// 3. 当前目录 和 运行目录下 都没有，再试着读取jar打包内部的文件
		try {
			logger.info("war directory:get");
			prop.load(getClass().getResourceAsStream("/configuration.properties"));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取项目所在路径(包括war)
	 */
	public String getProjectPath() {
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".war"))
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		return new File(filePath).getAbsolutePath();
	}

}
