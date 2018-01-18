/**
 * 
 */
package com.hzdracom.manager.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @title: FileUpload.java
 * @Package: com.hzdracom.common 
 * @Description: TODO
 * @author: 何昕
 * @date: 2015年7月12日 上午2:13:05 
 * @version: V1.0
 */
@Controller
public class FileUpload {
	
	private Logger logger = Logger.getLogger(FileUpload.class);
	
	@Resource
	private ChunkFileService cfs;

	/**
	 * 
	* @Title: uploadExcel 
	* @Description: TODO(上传Excel) 
	* @param @param file
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @date 2016年5月25日 下午8:42:22
	 */
	public String uploadExcel(MultipartFile file){//MultipartFile
		try{
			if (!file.isEmpty()){
				//名称获取  和   换名称+时间后缀
				String originalFilename = file.getOriginalFilename();
				if(originalFilename.indexOf(".")>=0){     
					String frist   =   originalFilename.substring(0,originalFilename.lastIndexOf("."));
					String end   =   originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
					//originalFilename=frist+FileUtil.getDate()+end;
					originalFilename=UUID.randomUUID().toString()+end;
					if(!end.equalsIgnoreCase(".xlsx") && !end.equalsIgnoreCase(".xls")){
						return "";
					}
		    	}else{
		    		return "";
		    	}
				//上传
				FileInfo fileInfo = new FileInfo();
				fileInfo.setName(originalFilename);
				File target = cfs.getReadySpace(fileInfo, Cast.address+Cast.excel+FileUtil.getDateFolderName());	//为上传的文件准备好对应的位置
				file.transferTo(target);
				String fileUrl = Cast.staticFileUrl+Cast.excel+FileUtil.getDateFolderName()+target.getName();
				System.out.println("----文件上传------" + originalFilename +"  "+ fileUrl);
				return fileUrl;
			}else{
				return "";
			}
		}catch (Exception e){
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 
	* @Title: outExcel 
	* @Description: TODO(导出上传使用) 
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @date 2016年5月26日 下午2:44:18
	 */
	public Map<String,String> outExcel(){
		try{
			Map<String,String> aa=new HashMap();
			String address=Cast.address+Cast.excel+FileUtil.getDateFolderName();
			String name="back_"+FileUtil.getDate()+".xls";
			System.out.println(address+name);
			aa.put("address", address+name);
			aa.put("url", Cast.staticFileUrl+Cast.excel+FileUtil.getDateFolderName()+name);
			return aa;
		}catch (Exception e){
			logger.error(e);
			return null;
		}
	}
	
	
}
