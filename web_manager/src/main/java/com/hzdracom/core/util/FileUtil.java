package com.hzdracom.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Title: FileUtil.java
 * @Package com.ld.ewxserver.common.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2015年9月4日 下午8:09:35
 */
public class FileUtil
{
	/**
	 * @Title: readFileToInputStream
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param file
	 * @param @return
	 * @param @throws FileNotFoundException 设定文件
	 * @return InputStream 返回类型
	 * @throws
	 * @date 2016年5月24日 上午11:26:28
	 */
	public static InputStream readFileToInputStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}
	
	/**
	 * 写文件内容
	 * 
	 * @param filePathAndName
	 *            String 如 c:\\1.txt 绝对路径
	 * @return boolean
	 * @throws IOException
	 */
	public static void writeFile(File f, String content) throws IOException {
		if (f.exists())
		{
			f.delete();
		}
		f.createNewFile();
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(content);
		writer.close();
	}
	
	/**
	 * 
	* @Title: readFile 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param f
	* @param @param content
	* @param @throws IOException 设定文件 
	* @return void 返回类型 
	* @throws 
	* @date 2017年5月5日 上午10:54:35
	 */
	public static List<String> readFile(File f) throws IOException {
		List<String> list = new ArrayList<String>();
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
		BufferedReader br = new BufferedReader(read);
		String line = "";
		while((line = br.readLine()) != null){
		    if(Util.isNotEmpty(line))list.add(line);
		}
		br.close();
		return list;
	}
	
	/**
	 * 
	* @Title: forJava 
	* @Description: 文件复制
	* @param @param f1
	* @param @param f2
	* @param @return
	* @param @throws Exception 设定文件 
	* @return long 返回类型 
	* @throws 
	* @date 2017年5月31日 下午2:30:58
	 */
	public static long forJava(File f1, File f2) throws Exception {
		long time = new java.util.Date().getTime();
		int length = 2097152;
		FileInputStream in = new FileInputStream(f1);
		FileOutputStream out = new FileOutputStream(f2);
		byte[] buffer = new byte[length];
		while (true)
		{
			int ins = in.read(buffer);
			if (ins == -1)
			{
				in.close();
				out.flush();
				out.close();
				return new Date().getTime() - time;
			}
			else out.write(buffer, 0, ins);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//System.out.println(readFile("hhtml/10000/edb45f49ed8240a9a96fc91d81e3ee8d.html"));
		//System.out.println(readFile(new File("\\data\\data\\temp\\TCP_080D_2017050510\\1493952674203_876aafadd7fb41afa8e9bd73d9d0636d.log")).size());
	}
	
}
