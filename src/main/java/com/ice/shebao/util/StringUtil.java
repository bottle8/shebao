package com.ice.shebao.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {

	/**
	 * 强InputStream转化成字符串
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String convertString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int length = 0;
		while ((length = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, length);// 写入输出流
		}
		inputStream.close();// 读取完毕，关闭输入流

		return new String(bos.toByteArray(), "UTF-8");

	}
}
