package com.qj.weather.adapter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTools {
	/**
	 * ���߷���
	 * @param is ������
	 * @return �ı��ַ���
	 * @throws Exception
	 */
	public static String readStream(InputStream is) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = is.read(buffer))!=-1){
			baos.write(buffer, 0, len);
		}
		is.close();
		String temp=baos.toString();
		return  temp;

	}
}
