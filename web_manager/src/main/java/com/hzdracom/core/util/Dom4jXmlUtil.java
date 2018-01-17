package com.hzdracom.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * @Title: Dom4jXmlUtil.java
 * @Package file.ok
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2016年6月1日 上午10:00:17
 */
public class Dom4jXmlUtil
{
	/**
	 * @ClassName: KeyValue
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author 刘章
	 * @date 2016年6月1日 下午12:52:13
	 */
	public static class KeyValue
	        implements
	        Serializable
	{
		
		/**
		 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
		 */
		private static final long       serialVersionUID = -5765071045749149516L;
		
		private String                  name;
		
		private String                  value;
		
		private String                  readme;
		
		private HashMap<String, String> attribute;
		
		public String getName() {
			
			return name;
		}
		
		public void setName(String name) {
			
			this.name = name;
		}
		
		public String getValue() {
			
			return value;
		}
		
		public void setValue(String value) {
			
			this.value = value;
		}
		
		public HashMap<String, String> getAttribute() {
			
			return attribute;
		}
		
		public void setAttribute(HashMap<String, String> attribute) {
			
			this.attribute = attribute;
		}
		
		public String getReadme() {
			
			return readme;
		}
		
		public void setReadme(String readme) {
			
			this.readme = readme;
		}
		
	}
	
	/**
	 * @Title: init
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param file
	 * @param @param rootName
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return Element 返回类型
	 * @throws
	 * @date 2016年6月1日 下午12:34:34
	 */
	public Document init(File file, String rootName) throws IOException {
		SAXReader sax = new SAXReader();//创建一个SAXReader对象  
		if (!file.exists()) file.createNewFile();
		Document document = null;
		try
		{
			document = sax.read(file);//获取document对象,如果文档无节点，则会抛出Exception提前结束  
			//document.getRootElement();
		}
		catch (DocumentException e)
		{
			//创建文档对象
			document = DocumentHelper.createDocument();
			//添加元素，第一个元素为根元素
			document.addElement(rootName);
			saveDocument(document, file);
		}
		return document;
	}
	
	/**
	 * @Title: addNamespace
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param namespace 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:41:35
	 */
	public void addNamespace(Element node, Namespace namespace) {
		//Namespace xs=new Namespace("xs","http://www.w3.org/2001/XMLSchema");
		//Namespace msdata=new Namespace("msdata","urn:schemas-microsoft-com:xml-msdata");
		node.add(namespace);
	}
	
	/**
	 * @Title: findElement
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param elementID
	 * @param @return 设定文件
	 * @return Element 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:50:57
	 */
	public Element findElement(Element node, String elementID) {
		return node.elementByID(elementID);
	}
	
	/**
	 * @Title: addNode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @param content
	 * @param @return 设定文件
	 * @return Element 返回类型
	 * @throws
	 * @date 2016年6月1日 下午12:34:30
	 */
	public Element addNode(Element node, KeyValue keyValue) {
		Element newNode = node.addElement(keyValue.getName());//对指定的节点node新增子节点,名为nodeName  
		//Element newNode = node.addElement("xs:schema");//对指定的节点node新增子节点,名为nodeName  
		if (keyValue.getValue() != null) newNode.setText(keyValue.getValue());//对新增的节点添加文本内容content  
		if (keyValue.getReadme() != null) newNode.addComment(keyValue.getReadme());
		if (keyValue.getAttribute() != null && !keyValue.getAttribute().isEmpty())
		{
			for (String n : keyValue.getAttribute().keySet())
			{
				newNode.addAttribute(n, keyValue.getAttribute().get(n));
			}
		}
		return newNode;
	}
	
	/**
	 * @Title: addXml
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @param element 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:32:06
	 */
	public void addXml(Element node, String nodeName, Element element) {
		Element updElement = node.element(nodeName);
		updElement.appendAttributes(element);
	}
	
	/**
	 * @Title: addXml
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param newTrue
	 * @param @param nodeName
	 * @param @param xml 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:38:40
	 */
	public void addXml(Element node, boolean newTrue, String nodeName, String xml) {
		(newTrue ? node.addElement(nodeName) : node.element(nodeName)).setText(xml);
	}
	
	/**
	 * @Title: updNode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @param content 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午12:36:09
	 */
	public void updNode(Element node, KeyValue keyValue) {
		Element updElement = node.element(keyValue.getName());
		if (keyValue.getValue() != null) updElement.setText(keyValue.getValue());
		if (keyValue.getAttribute() != null && !keyValue.getAttribute().isEmpty())
		{
			for (String n : keyValue.getAttribute().keySet())
			{
				updElement.attribute(n).setText(keyValue.getAttribute().get(n));
			}
		}
	}
	
	/**
	 * @Title: updNode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param value
	 * @param @param attribute 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:56:41
	 */
	public void updNode(Element node, String value, HashMap<String, String> attribute) {
		node.setText(value);
		if (attribute != null && !attribute.isEmpty())
		{
			for (String n : attribute.keySet())
			{
				node.attribute(n).setText(attribute.get(n));
			}
		}
	}
	
	/**
	 * @Title: updAttribute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @param attribute 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:06:52
	 */
	public void updAttribute(Element node, String nodeName, HashMap<String, String> attribute) {
		Element updElement = node.element(nodeName);
		for (String n : attribute.keySet())
		{
			updElement.attribute(n).setText(attribute.get(n));
		}
	}
	
	/**
	 * @Title: updAttribute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param attribute 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:57:02
	 */
	public void updAttribute(Element node, HashMap<String, String> attribute) {
		for (String n : attribute.keySet())
		{
			node.attribute(n).setText(attribute.get(n));
		}
	}
	
	/**
	 * @Title: delNode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param element
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:58:05
	 */
	public boolean delNode(Element node, Element element) {
		return node.remove(element);
	}
	
	/**
	 * @Title: delNode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2016年6月1日 下午12:34:21
	 */
	public boolean delNode(Element node, String nodeName) {
		return delNode(node, node.element(nodeName));
	}
	
	/**
	 * @Title: delAttribute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param nodeName
	 * @param @param attNames
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:04:44
	 */
	public boolean delAttribute(Element node, String nodeName, String... attNames) {
		boolean result = true;
		Element updElement = node.element(nodeName);
		for (String attName : attNames)
		{
			try
			{
				result = updElement.remove(updElement.attribute(attName));
				if (!result) break;
			}
			catch (NullPointerException e)
			{
				
			}
		}
		return result;
	}
	
	/**
	 * @Title: delAttribute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param node
	 * @param @param attNames
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2016年6月1日 下午1:59:20
	 */
	public boolean delAttribute(Element node, String... attNames) {
		boolean result = true;
		for (String attName : attNames)
		{
			try
			{
				result = node.remove(node.attribute(attName));
				if (!result) break;
			}
			catch (NullPointerException e)
			{
				
			}
		}
		return result;
	}
	
	/**
	 * 把改变的domcument对象保存到指定的xml文件中
	 * 
	 * @author chenleixing
	 * @throws IOException
	 */
	public void saveDocument(Document document, File file) throws IOException {
		//创建写XML文档的Writer对象
		Writer osWrite = new OutputStreamWriter(new FileOutputStream(file));//创建输出流  
		OutputFormat format = OutputFormat.createPrettyPrint(); //获取输出的指定格式    
		format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式  
		XMLWriter writer = new XMLWriter(osWrite, format);//XMLWriter 指定输出文件以及格式    
		writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)    
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		Dom4jXmlUtil u = new Dom4jXmlUtil();
		File file = new File("c://ok.xml");
		Document document = u.init(file, "users");
		Element root = document.getRootElement();
		u.addNamespace(root, new Namespace("xs", "http://www.w3.org/2001/XMLSchema"));
		u.addNamespace(root, new Namespace("msdata", "urn:schemas-microsoft-com:xml-msdata"));
		KeyValue keyValue = null;
		HashMap<String, String> attribute = new HashMap<String, String>();
		for (int i = 1; i <= 100; i++)
		{
			attribute.put("ID", String.valueOf(i));
			keyValue = new KeyValue();
			keyValue.setName("user" + i);
			keyValue.setValue(i + " Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。");
			keyValue.setReadme("备注"+i);
			keyValue.setAttribute(attribute);
			u.addNode(root, keyValue);
		}
		keyValue.setValue("被我改了，哈哈");
		u.updNode(root, keyValue);
		u.delNode(root, "user99");
		System.out.println(u.findElement(root, "100").asXML());
		u.saveDocument(document, file);
	}
	
}
