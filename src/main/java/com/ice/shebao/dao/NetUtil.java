package com.ice.shebao.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;

public class NetUtil {
	private static String charset = "utf-8";
	/**
	 * 通过url获取路径中的获取
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static InputStream getInputSream(String url) throws IOException {
		
		
		URL myUrl = new URL(url);
		
		
		HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
		InputStream inputStream = null;
		if (connection.getResponseCode() == 200) {
			inputStream = connection.getInputStream();
		}
		return inputStream;
	}
}
