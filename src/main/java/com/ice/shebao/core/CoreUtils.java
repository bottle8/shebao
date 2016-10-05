package com.ice.shebao.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class CoreUtils extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(CoreUtils.class);
	
	private static Properties config;				//配置文件
	public static Map<String, String> configMap;	//配置文件的内容，键值对形式
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.error("服务启动与：" + sdf.format(System.currentTimeMillis()));
		getPro();
		getConfigList();
	}
	
	/**
	 * 获取配置文件，将配置文件的信息放入Properties config
	 */
	private void getPro(){
		config = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream in = loader.getResourceAsStream("config.properties");
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从配置文件中读取信息，并放入Map中
	 */
	private void getConfigList() {
		Iterator<String> iterator = config.stringPropertyNames().iterator();
		configMap = new HashMap<String, String>();
		while(iterator.hasNext()){
			String key = iterator.next();
			configMap.put(key, config.getProperty(key));
		}
	}
	
//	public static void main(String[] args) {
//		CoreUtils coreUtils = new CoreUtils();
//		coreUtils.getPro();
//		coreUtils.getConfigList();
//		System.out.println(configMap.toString());
//	}
}
