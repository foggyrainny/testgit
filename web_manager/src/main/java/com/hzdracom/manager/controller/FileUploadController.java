/**
 * 
 */
package com.hzdracom.manager.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hzdracom.core.util.Util;

/** 
 *  Title: com.hzdracom.manager.controller
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月17日 
 */
@Controller
@RequestMapping("upload")
public class FileUploadController extends  BaseController{

	private  Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("image")
	public void imageUpload(HttpServletResponse response,HttpServletRequest request){
		//respSuccessMsg(response, "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D800%2C450/sign=1ae19fcd4390f60304e5944f09229f2f/7e3e6709c93d70cfaba4b9f1f1dcd100bba12beb.jpg", "");
		MultipartFile file = null;
		if(request instanceof  MultipartHttpServletRequest){
			Collection<MultipartFile> files = ((MultipartHttpServletRequest)request).getFileMap().values();
			if(files!=null && !files.isEmpty()) {
				file = new ArrayList<MultipartFile>(files).get(0);
			} else {
				respErrorMsg(response, "请至少选择一个文件");
				return;
			}
		} else {
			respErrorMsg(response, "请至少选择一个文件");
			return;
		}
		
		try {
			if (file.isEmpty()) {
				respErrorMsg(response, "请至少选择一个文件");
				return;
			} else {
				/*IFileService is = null;
				String fileAbsolutePath = null;
				try
				{
					String tempFileName = file.getOriginalFilename();
					String fileExtName = tempFileName.substring(tempFileName.lastIndexOf(".") + 1).toLowerCase();
					is = FdfsUtil.getFileService();
					fileAbsolutePath = is.uploadFile(file.getBytes(),fileExtName);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if(Util.isEmpty(fileAbsolutePath)) {
					respErrorMsg(response, "文件上传失败");
				} else {
					respSuccessMsg(response,FdfsUtil.getIp() + fileAbsolutePath, "上传成功");
				}*/
				respSuccessMsg(response,"http://", "上传成功");
			}
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			respErrorMsg(response, "请至少选择一个文件");
			return;
		}
	}
	
	/**
	 *  空接口
	 */
	@RequestMapping("void")
	public void voidInterface(HttpServletResponse response){
		respSuccessMsg(response, "", "");
	}
	
	
}
