package com.hzdracom.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;


/**
 * @Title: HttpClientUtil.java
 * @Package com.hzdracom.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2015-5-18 下午2:57:50
 */
public class HttpClientUtil
{
	
	private static HttpClient   httpClient;
	private static HttpParams   httpParams               = new BasicHttpParams();
	private static final int    DEFAULT_SOCKET_TIMEOUT   = 30 * 1000;
	private static final int    DEFAULT_HOST_CONNECTIONS = 30 * 1000;
	private static final int    DEFAULT_MAX_CONNECTIONS  = 1000;
	private static final String charSet                  = "UTF-8";
	private static final String contentType              = "application/json";
	
	/**
	 * @Title: sendData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param method
	 * @param @param url
	 * @param @param header_map
	 * @param @param parameter_map
	 * @param @param file
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2015-5-18 下午3:13:51
	 */
	public static String sendData(Method method, String url, Map<String, String> header_map, String data, Map<String, String> parameter_map, File[] files) throws Exception {
		HttpGet getMethod = null;
		HttpPost postMethod = null;
		//请求响应
		HttpResponse response;
		try
		{
			if (method == Method.GET)
			{
				StringBuffer urlParm = new StringBuffer();
				Iterator<Entry<String, String>> itr = parameter_map.entrySet().iterator();
				while (itr.hasNext())
				{
					Entry<String, String> param = itr.next();
					urlParm.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue().toString(), charSet));
					if (itr.hasNext()) urlParm.append("&");
				}
				getMethod = new HttpGet(URI.create(url + "?" + urlParm.toString()));
				//getMethod.addHeader(new BasicHeader("Content-Type", contentType));
				//头信息
				if (header_map != null)
				{
					Iterator<Entry<String, String>> it = header_map.entrySet().iterator();
					while (it.hasNext())
					{
						Entry<String, String> param = it.next();
						getMethod.addHeader(new BasicHeader(param.getKey(), param.getValue()));
					}
				}
				response = getHttpClient().execute(getMethod);
				return entityToString(response.getEntity());
			}
			else
			{
				postMethod = new HttpPost(URI.create(url));
				//postMethod.addHeader(new BasicHeader("Content-Type", contentType));
				if (data != null)
				{
					byte[] requestData = data.getBytes(charSet);
					InputStream instream = new ByteArrayInputStream(requestData);
					InputStreamEntity inputStreamEntity = new InputStreamEntity(instream, requestData.length);
					postMethod.setEntity(inputStreamEntity);
				}
				else
				{
					if (files == null)
					{
						List<NameValuePair> paramList = new ArrayList<NameValuePair>();
						Iterator<Entry<String, String>> itr = parameter_map.entrySet().iterator();
						while (itr.hasNext())
						{
							Entry<String, String> param = itr.next();
							paramList.add(new BasicNameValuePair(param.getKey(), URLEncoder.encode(param.getValue().toString(),charSet)));
						}
						postMethod.setEntity(new UrlEncodedFormEntity(paramList, charSet));
					}
					else
					{
						MultipartEntity entity = new MultipartEntity(); //文件传输
						Iterator<Entry<String, String>> itr = parameter_map.entrySet().iterator();
						while (itr.hasNext())
						{
							Entry<String, String> param = itr.next();
							entity.addPart(param.getKey(), new StringBody(param.getValue().toString()));
						}
						int f_index = 1;
						for (File file : files)
						{
							entity.addPart("file" + f_index, new FileBody(file));
							f_index += 1;
						}
						postMethod.setEntity(entity);
					}
				}
				//头信息
				if (header_map != null)
				{
					Iterator<Entry<String, String>> it = parameter_map.entrySet().iterator();
					while (it.hasNext())
					{
						Entry<String, String> param = it.next();
						postMethod.addHeader(new BasicHeader(param.getKey(), param.getValue()));
					}
				}
				response = getHttpClient().execute(postMethod);
				return entityToString(response.getEntity());
			}
		}
		finally
		{
			if (getMethod != null)
			{
				getMethod.abort();
				getMethod = null;
			}
			if (postMethod != null)
			{
				postMethod.abort();
				postMethod = null;
			}
			if (header_map != null)
			{
				header_map.clear();
				header_map = null;
			}
			if (parameter_map != null)
			{
				parameter_map.clear();
				parameter_map = null;
			}
		}
		
	}
	
	/**
	 * @Title: entityToString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param entity
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2015年4月23日 下午9:01:59
	 */
	protected static String entityToString(HttpEntity entity) throws IOException {
		return new String(EntityUtils.toByteArray(entity), charSet);
	}
	
	/**
	 * @Title: getHttpClient
	 * @Description: 获取http请求的 HttpClient
	 * @param @return 设定文件
	 * @return HttpClient 返回类型
	 * @throws
	 * @date 2015年4月23日 下午9:00:17
	 */
	protected synchronized static HttpClient getHttpClient() {
		// 代理设置
		// httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("", 0));
		// httpParams.removeParameter(ConnRoutePNames.DEFAULT_PROXY);
		if (httpClient == null)
		{
			// timeout: get connections from connection pool
			ConnManagerParams.setTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
			// timeout: connect to the server
			HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
			// timeout: transfer data from server
			HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
			
			// set max connections per host
			ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(DEFAULT_HOST_CONNECTIONS));
			// set max total connections
			ConnManagerParams.setMaxTotalConnections(httpParams, DEFAULT_MAX_CONNECTIONS);
			
			// use expect-continue handshake
			HttpProtocolParams.setUseExpectContinue(httpParams, true);
			
			// disable stale check
			//HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
			
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, charSet);
			// disable Nagle algorithm
			HttpConnectionParams.setTcpNoDelay(httpParams, true);
			
			// scheme: http and https
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			
			ClientConnectionManager manager = new ThreadSafeClientConnManager(httpParams, schemeRegistry);
			httpClient = new DefaultHttpClient(manager, httpParams);
		}
		
		return httpClient;
	}
	
	/**
	 * @ClassName: Method
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author 刘章
	 * @date 2015年4月23日 下午9:02:16
	 */
	public static enum Method {
		POST, GET;
	}
}
