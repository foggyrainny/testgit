package com.hzdracom.manager.util;

import java.io.*;


/**
 * @Title: FileUtil.java
 * @Package com.hzdracom.ant
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014年3月12日 上午10:40:41
 */
public class FileUtil
{
	
	/**
	 * @Title: read
	 * @Description:读文件
	 * @param @param filePath
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2014-3-14 下午3:22:30
	 */
	public static String read(String filePath) {
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();
		try
		{
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(new FileReader(filePath));
			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null)
			{
				buf.append(line);
				buf.append(System.getProperty("line.separator"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭流
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					br = null;
				}
			}
		}
		return buf.toString();
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param filePathAndName
	 *            String 如 c:\\1.txt 绝对路径
	 * @return boolean
	 */
	public static String readFile(String filePath) {
		String fileContent = "";
		try
		{
			File f = new File(filePath);
			if (f.isFile() && f.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null)
				{
					fileContent += line;
				}
				read.close();
			}
		}
		catch (Exception e)
		{
			
		}
		return fileContent;
	}
	
	/**
	 * @Title: write
	 * @Description: 写文件
	 * @param @param filePath
	 * @param @param content 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2014年3月12日 上午11:08:46
	 */
	public static boolean write(String filePath, String content) {
		BufferedWriter bw = null;
		try
		{
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(filePath));
			// 将内容写入文件中
			bw.write(content);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			// 关闭流
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException e)
				{
					bw = null;
				}
				
			}
			
		}
		
	}
	
	/**
	 * 写文件内容
	 * 
	 * @param filePathAndName
	 *            String 如 c:\\1.txt 绝对路径
	 * @return boolean
	 */
	public static boolean writeFile(String filePath, String content) {
		try
		{
			File f = new File(filePath);
			if(!f.getParentFile().exists()) {
	            //如果目标文件所在的目录不存在，则创建父目录  
	            if(!f.getParentFile().mkdirs()) {
	                return false;  
	            }  
	        }
			if (!f.exists())
			{
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	// 复制文件
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try
		{
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1)
			{
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		}
		finally
		{
			// 关闭流
			if (inBuff != null) inBuff.close();
			if (outBuff != null) outBuff.close();
		}
	}
	
	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++)
		{
			if (file[i].isFile())
			{
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory())
			{
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}
	
	//拼接HTML内容
	public static StringBuffer appendHtmlContent(String title, String content){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>" + title + "</title>");
		sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		sb.append("<meta http-equiv=\"pragma\" content=\"no-cache\" />");
		sb.append("<meta http-equiv=\"cache-control\" content=\"no-cache\" />");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0\" />");
		sb.append("<style>img{width:100%;}</style>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(content);
		sb.append("</body>");
		sb.append("</html>");
		return sb;
	}
	
	public static String getDateFolderName(){
		return "/"+DateUtil.now("yyyyMM")+"/";
	}
	
	public static String getDate(){
		return "_"+DateUtil.now("yyyyMMddhhmmss");
	}
	
}
