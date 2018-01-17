package com.hzdracom.manager.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hzdracom.manager.util.ContextUtils;
import com.hzdracom.manager.util.FtpUtil;

/**
 * 
 * 
 */

@Controller
@RequestMapping("webftp")
public class WebFtpController {

	
	@RequestMapping("login")
	public void login(String serverIp, int port, String userName, String password, HttpServletResponse response) throws IOException {
	
	}

	
	@RequestMapping("index")
	public ModelAndView index(HttpServletResponse response) throws IOException {
		return new ModelAndView("webftp/manager");
	}
	
	
	@RequestMapping("download")
	public void download(String path,String file,HttpServletResponse response) throws IOException {
		if(!path.endsWith("/")) {
			path += "/";
		};
		try {
			 response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file, "UTF-8"));  
			path = new String( (path+file).getBytes(), "iso-8859-1");
			
			FtpUtil.getDefaultFtp().getFile(path, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			SendAnswer(response, 1 , "文件["+file+"]下失败");
		}
		
	}

	
	/**
					 * [分发处理客服端的action]
					 * @param [type] $action     [客服端的请求]
					 * @param [type] &ftp [FTP对象]
	 * @throws IOException 
					 */
	@RequestMapping("manager")
	public void DisposeAction(String action,HttpServletResponse response,String file,String path,String folder,String newname, HttpServletRequest request) throws IOException{
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		
		switch (action){
			case "Login":
				SendAnswer(response,0, "/");
				break;
			case "GetFileList":
				GetFileList(response, file);
				break;
			case "CreateFolder":
				CreateFolder(response, path, folder);
				break;
			case "CreateFile":
			//	CreateFile(ftp, $_POST["path"], $_POST["file"]);
				break;
			case "UploadFile":
				UploadFile(request,response, path); 
				break;
			case "delete":
				DeleteFile(response, path, file);
				break;
			case "rename":
				RenameFile(response, path, file, newname);
				break;
	/*			case "paste":
				$filepath = "upload/".session_id().$_POST["file"];
				DownloadFile(ftp, $_POST["path"], $_POST["file"], $filepath);
				if (isset($_POST["newname"]) && ($_POST["newname"] == true) ){
					DeleteFile(ftp, $_POST["path"], $_POST["file"]);
				}
				break;
*/
			case "download":
				//DownloadFile(response, path, file);
				SendAnswer(response, 0,basePath + "webftp/download.do?path=" + path +"&file="+file);	
				break;
			default:break;
		}
	}

	private void RenameFile(HttpServletResponse response, String path, String file, String newname) {
		try {
			FtpUtil.getDefaultFtp().rename(new String(path.getBytes(), "iso-8859-1"), new String(file.getBytes(), "iso-8859-1"),new String(newname.getBytes(), "iso-8859-1") );
			SendAnswer(response, 0, "重命名成功");
		} catch (IOException e) {
			e.printStackTrace();
			SendAnswer(response, 1, "文件["+file+"]重命名失败");
		}
	}

	private void DeleteFile(HttpServletResponse response, String path, String file) {
		if(!path.endsWith("/")) {
			path += "/";
		};
		try {
			FtpUtil.getDefaultFtp().remove(new String(path.getBytes(), "iso-8859-1") + new String(file.getBytes(), "iso-8859-1"));
			SendAnswer(response, 0, "文件["+file+"]删除成功");
		} catch (IOException e) {
			e.printStackTrace();
			SendAnswer(response, 1 , "文件["+file+"]删除失败");
		}
	}

	private void UploadFile(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		if(request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			Collection<MultipartFile> files = req.getFileMap().values();
			if(files.isEmpty()) {
				SendAnswer(response, 1, "请至少选择一个文件");
				return ;
			}
			for (MultipartFile multipartFile : files) {
				if(!path.endsWith("/")) {
					path += "/";
				};
				try{
					FtpUtil.getDefaultFtp().putFile(new String((path+multipartFile.getOriginalFilename()).getBytes(),"iso-8859-1"), multipartFile.getInputStream());
				} catch (Exception e){
					e.printStackTrace();
					SendAnswer(response, 1, "文件["+multipartFile.getOriginalFilename()+"] 上传失败");
					return;
				}
			}
			SendAnswer(response, 0, "");
		} else {
			SendAnswer(response, 1, "请至少选择一个文件");
		}
	}

	private void CreateFolder(HttpServletResponse response, String path, String folder)  {
		boolean success = false;
		try {
			success = FtpUtil.getDefaultFtp().makeDir(new String(path.getBytes(),"iso-8859-1"),new String(folder.getBytes(),"iso-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
			SendAnswer(response, 1, "创建失败");
			return;
		}
		
		SendAnswer(response, success?0:1, "创建成功");
	}

	private void GetFileList(HttpServletResponse response, String file) throws IOException {
		List list = FtpUtil.getDefaultFtp().listFiles(file+"");
		sendMsg(response, list);
	}

	/**
	 * 封装并以json返回错误执行的信息
	 * 
	 * @param response
	 * @param msg
	 */
	protected void SendAnswer(HttpServletResponse response, Object state, Object msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("state", state);
			map.put("msg", msg);
			response.setContentType("application/json");
			ContextUtils.respString(response, JSON.toJSONString(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sendMsg(HttpServletResponse response, Object msg) {
		try {
			response.setContentType("application/json");
			ContextUtils.respString(response, JSON.toJSONString(msg));
		} catch (Exception e) {
		}
	}

}
