package com.hzdracom.core.util;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;


/**
 * @Title: InitServlet.java
 * @Package com.wasu.douban.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2016年4月20日 下午7:26:06
 */
public class InitServlet
        extends
        HttpServlet
{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long  serialVersionUID = 1838572068435481385L;
	
	public static final Logger logger           = Logger.getLogger(InitServlet.class);
	
	public static final String CATALINA_PORT    = "CATALINA_PORT";
	
	private static int         serverPort       = 0;
	
	/**
	 * 加载tomcat读取配置文件地址
	 */
	public void init(ServletConfig config) throws ServletException {
		try
		{
			String serverName = config.getServletContext().getInitParameter("webAppRootKey").replace(".root", "");
			String path = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
			path = path.replace("webapps/" + serverName + "/WEB-INF/classes", "conf");
			serverPort = getTomcatPortFromConfigXml(new File(path + "server.xml"));
			if (serverPort > 0) System.setProperty(CATALINA_PORT, String.valueOf(serverPort));
			System.out.println("=================" + serverName + "=============容器端口:" + serverPort + "================================");
		}
		catch (Exception ex)
		{
			logger.error("缓存启动错误", ex);
		}
	}
	
	public static int getTomcatPortFromConfigXml(Class<?> c) {
		int port;
		try
		{
			String classPath = c.getProtectionDomain().getCodeSource().getLocation().toString();
			String serverXml = classPath.substring(0, classPath.indexOf("webapps")) + "conf/server.xml";
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(serverXml);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("/Server/Service[@name='Catalina']/Connector[count(@scheme)=0]/@port[1]");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			port = result != null && result.length() > 0 ? Integer.valueOf(result) : null;
		}
		catch (Exception e)
		{
			port = -1;
		}
		return port;
	}
	
	/**
	 * @Title: getTomcatPortFromConfigXml
	 * @Description: 解析tomcat配置文件
	 * @param @param serverXml
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @date 2016年4月20日 下午8:07:13
	 */
	private static int getTomcatPortFromConfigXml(File serverXml) {
		int port;
		try
		{
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(serverXml);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("/Server/Service[@name='Catalina']/Connector[count(@scheme)=0]/@port[1]");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			port = result != null && result.length() > 0 ? Integer.valueOf(result) : null;
		}
		catch (Exception e)
		{
			port = -1;
		}
		return port;
	}
	
	/**
	 * @Title: getServerPort
	 * @Description: 获取容器服务端口
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @date 2016年4月20日 下午8:07:30
	 */
	public static int getServerPort() {
		return serverPort;
	}
	
	/**
	 * @Title: getServerPort
	 * @Description: 获取容器服务端口
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @date 2016年4月20日 下午8:07:30
	 */
	public static int getServerPort(int defPort) {
		return serverPort > 0 ? serverPort : defPort;
	}
}
