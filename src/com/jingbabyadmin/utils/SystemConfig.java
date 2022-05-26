package com.jingbabyadmin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfig {
	
	private static Properties p = new Properties();
	
	private static SystemConfig SystemConfig = null;
	
	private SystemConfig() {}
	
	public static final SystemConfig getInstance() {
		if (SystemConfig==null) {
			InputStream inputStream = SystemConfig.class.getClassLoader().getResourceAsStream("system.properties");
			try {
				p.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SystemConfig = new SystemConfig();
		}
		return SystemConfig;
	}

	/**
	 * 获取配置文件中的value
	 * @param key
	 * @return
	 */
	public final String get(String key) {
		return p.getProperty(key);
	}
	
	/**
	 * 获取文件上传路径
	 * @return
	 */
	public final String getUploadDir() {
		return get("upload_dir");
	}
}
