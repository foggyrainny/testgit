package com.hzdracom.core.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;

/**
 * @Title: HttpDownFileUtil.java
 * @Package com.hzdracom.core.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2017年2月22日 上午9:17:36
 */
public class HttpDownFileUtil
{
	public static void downFile(String httpUrl) {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		try
		{
			URLConnection conn = new URL(httpUrl).openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("c:/cafeac130ac61631597ec018605569d4(1).jpg");
			byte[] buffer = new byte[1204];
			int length;
			while ((byteread = inStream.read(buffer)) != -1)
			{
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		//downFile("http://nim.nos.netease.com/NDA5MjQ5Mg==/bmltYV8yNzc4OTYxOTJfMTQ4NzY3MTQ5ODQwMl9lZDVkNWY0ZC03NWQwLTQwMjUtOGJiNi03Mjk2ZmRjN2U0MWM=");
		//System.out.println(new String(Md5Utils.computeMD5Hash(FileUtil.readFile(new File("c:/cafeac130ac61631597ec018605569d4(1).jpg"))),"UTF-8"));
	}
	
}
