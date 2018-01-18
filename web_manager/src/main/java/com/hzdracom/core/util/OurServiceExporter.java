package com.hzdracom.core.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.util.NestedServletException;


/**
 * @Title: OurServiceExporter.java
 * @Package com.hzdracom.core.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2015年9月24日 下午2:05:06
 */
public class OurServiceExporter
        extends
        HessianServiceExporter
{
	private static final Logger log = Logger.getLogger(OurServiceExporter.class);
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断方法，是否是post如果不是则相应错误消息，HessianServiceExporter only supports POST requests
		if (!"POST".equalsIgnoreCase(request.getMethod())) { throw new HttpRequestMethodNotSupportedException(request.getMethod(), new String[] {
			"POST"
		}, "HessianServiceExporter only supports POST requests !"); }
		//IP控制
		String ips = Util.getIpAddr(request);
		log.info("hessian filter ip is " + ips + " !");
		for (String ip : ips.split(","))
		{
			//10.122.64.x 正式环境内网段
			//192.168.x.x 、 10.0.x.x 公司及华数 办公内网
			//122.224.218.57 公司指定刘章外网ip
			if (ip.equals("127.0.0.1") || ip.startsWith("10.122.64.") || ip.startsWith("192.168.") || ip.startsWith("10.0.") || ip.startsWith("122.224.218.57"))
			{
				continue;
			}
			else
			{
				throw new HttpRequestMethodNotSupportedException(request.getMethod(), new String[] {
					"POST"
				}, "HessianServiceExporter your ip must in ip white list !");
			}
		}
		//设置请求类型为application/x-hessian
		response.setContentType(CONTENT_TYPE_HESSIAN);
		try
		{
			//这里主要就是执行调用导出的对象。
			invoke(request.getInputStream(), response.getOutputStream());
		}
		catch (Throwable ex)
		{
			throw new NestedServletException("Hessian skeleton invocation failed", ex);
		}
	}
	
}
