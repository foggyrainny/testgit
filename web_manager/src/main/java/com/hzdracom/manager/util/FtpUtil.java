package com.hzdracom.manager.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.alibaba.fastjson.JSON;

public class FtpUtil {
    public static final String ANONYMOUS_LOGIN = "anonymous";
    private FTPClient ftp;
    
    private static FtpUtil  client = null;
    
    private static String host = ResourceBundle.getBundle("sys").getString("ftp.server.host");
    
    private static Integer  port =  Integer.parseInt(ResourceBundle.getBundle("sys").getString("ftp.server.port"));
    
    private static String userName  =  ResourceBundle.getBundle("sys").getString("ftp.server.username");
    
    private static String password =  ResourceBundle.getBundle("sys").getString("ftp.server.password");
    
    
    

	public  static FtpUtil  getDefaultFtp(){
		if(client == null) {
			client = new FtpUtil();
		}
		
		try {
			client.getFtp().list();
		} catch (Exception e) {
			
			e.printStackTrace();
			try {
				client.disconnect();
				client.connect();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return client;
		
	}
	
	
	/*try {  
		  int reply;  
		  ftp.connect("ftp.foobar.com");  
		  System.out.println("Connected to " + server + ".");  
		  System.out.print(ftp.getReplyString());  
		  
		  // After connection attempt, you should check the reply code to verify  
		  // success.  
		  reply = ftp.getReplyCode();  
		  
		  if(!FTPReply.isPositiveCompletion(reply)) {  
		    ftp.disconnect();  
		    System.err.println("FTP server refused connection.");  
		    System.exit(1);  
		  }  
		  ... // transfer files  
		  ftp.logout();  
		} catch(IOException e) {  
		  error = true;  
		  e.printStackTrace();  
		} finally {  
		  if(ftp.isConnected()) {  
		    try {  
		      ftp.disconnect();  
		    } catch(IOException ioe) {  
		      // do nothing  
		    }  
		  }  
		  System.exit(error ? 1 : 0);  
		}  */
	

    

    public FtpUtil() {
        ftp = new FTPClient();
        ftp.setCharset(Charset.defaultCharset());
    }
    
    public FtpUtil(int defaultTimeoutSecond, int connectTimeoutSecond, int dataTimeoutSecond){
        ftp = new FTPClient();
        
        ftp.setDefaultTimeout(defaultTimeoutSecond * 1000);
        ftp.setConnectTimeout(connectTimeoutSecond * 1000);
        ftp.setDataTimeout(dataTimeoutSecond * 1000);
    }

    
    public void connect() throws IOException{
    	connect(host, port, userName, password, false);
    }
    
    /**
     * Connects to FTP server.
     * 
     * @param host
     *            FTP server address or name
     * @param port
     *            FTP server port
     * @param user
     *            user name
     * @param password
     *            user password
     * @param isTextMode
     *            text / binary mode switch
     * @throws IOException
     *             on I/O errors
     */
    public void connect(String host, int port, String user, String password, boolean isTextMode) throws IOException {
        // Connect to server.
        try {
            ftp.connect(host, port);
        } catch (UnknownHostException ex) {
            throw new IOException("Can't find FTP server '" + host + "'");
        }

        // Check rsponse after connection attempt.
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();
            throw new IOException("Can't connect to server '" + host + "'");
        }

        if (user == "") {
            user = ANONYMOUS_LOGIN;
        }

        // Login.
        if (!ftp.login(user, password)) {
            disconnect();
            throw new IOException("Can't login to server '" + host + "'");
        } else {
        	
        }

        // Set data transfer mode.
        if (isTextMode) {
            ftp.setFileType(FTP.ASCII_FILE_TYPE);
        } else {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
        }
    }

    /**
     * Uploads the file to the FTP server.
     * 
     * @param ftpFileName
     *            server file name (with absolute path)
     * @param localFile
     *            local file to upload
     * @throws IOException
     *             on I/O errors
     */
    public void upload(String ftpFileName, File localFile) throws IOException {
        // File check.
        if (!localFile.exists()) {
            throw new IOException("Can't upload '" + localFile.getAbsolutePath() + "'. This file doesn't exist.");
        }

        // Upload.
        InputStream in = null;
        try {

            // Use passive mode to pass firewalls.
            ftp.enterLocalPassiveMode();

            in = new BufferedInputStream(new FileInputStream(localFile));
            if (!ftp.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }

        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Downloads the file from the FTP server.
     * 
     * @param ftpFileName
     *            server file name (with absolute path)
     * @param localFile
     *            local file to download into
     * @throws IOException
     *             on I/O errors
     */
    public void download(String ftpFileName, File localFile) throws IOException {
        // Download.
        OutputStream out = null;
        try {
            // Use passive mode to pass firewalls.
            ftp.enterLocalPassiveMode();

            // Get file info.
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
            if (fileInfoArray == null) {
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File " + ftpFileName + " is too large.");
            }

            // Download file.
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            if (!ftp.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file " + ftpFileName + " from FTP server. Check FTP permissions and path.");
            }

            out.flush();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * Removes the file from the FTP server.
     * 
     * @param ftpFileName
     *            server file name (with absolute path)
     * @throws IOException
     *             on I/O errors
     */
    public void remove(String ftpFileName) throws IOException {
        if (!ftp.deleteFile(ftpFileName)) {
            throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
        }
    }

    /**
     * Lists the files in the given FTP directory.
     * 
     * @param filePath
     *            absolute path on the server
     * @return files relative names list
     * @throws IOException
     *             on I/O errors
     */
    public List<String> list(String filePath) throws IOException {
        List<String> fileList = new ArrayList<String>();
        
        // Use passive mode to pass firewalls.
        ftp.enterLocalPassiveMode();
        
        FTPFile[] ftpFiles = ftp.listFiles(filePath);
        int size = (ftpFiles == null) ? 0 : ftpFiles.length;
        for (int i = 0; i < size; i++) {
            FTPFile ftpFile = ftpFiles[i];
            /*if (ftpFile.isFile()) {
                fileList.add(ftpFile.getName());
            }*/
            fileList.add(ftpFile.getName());
        }
        
        return fileList;
    }

    /**
     * Sends an FTP Server site specific command
     * 
     * @param args
     *            site command arguments
     * @throws IOException
     *             on I/O errors
     */
    public void sendSiteCommand(String args) throws IOException {
        if (ftp.isConnected()) {
            try {
                ftp.sendSiteCommand(args);
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Disconnects from the FTP server
     * 
     * @throws IOException
     *             on I/O errors
     */
    public void disconnect() throws IOException {

        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Makes the full name of the file on the FTP server by joining its path and
     * the local file name.
     * 
     * @param ftpPath
     *            file path on the server
     * @param localFile
     *            local file
     * @return full name of the file on the FTP server
     */
    public String makeFTPFileName(String ftpPath, File localFile) {
        if (ftpPath == "") {
            return localFile.getName();
        } else {
            String path = ftpPath.trim();
            if (path.charAt(path.length() - 1) != '/') {
                path = path + "/";
            }

            return path + localFile.getName();
        }
    }
    
    public boolean makeDir(String ftpPath, String dir) throws IOException {

    	if (dir == "") {
            return false;
        } else {
            String path = ftpPath.trim();
            if (path.charAt(path.length() - 1) != '/') {
                path = path + "/"+ dir;
            }else{
            	path += dir;
            }
           return ftp.makeDirectory(path);
        }
    }

  

    /**
     * Get current directory on ftp server
     * 
     * @return current directory
     */
    public String getWorkingDirectory() {

        try {
            return ftp.printWorkingDirectory();
        } catch (IOException e) {
        }

        return "";
    }

    /**
     * Set working directory on ftp server
     * 
     * @param dir
     *            new working directory
     * @return true, if working directory changed
     */
    public boolean setWorkingDirectory(String dir) {
       
        try {
            return ftp.changeWorkingDirectory(dir);
        } catch (IOException e) {
        }

        return false;
    }

    /**
     * Change working directory on ftp server to parent directory
     * 
     * @return true, if working directory changed
     */
    public boolean setParentDirectory() {

        try {
            return ftp.changeToParentDirectory();
        } catch (IOException e) {
        }

        return false;
    }

    /**
     * Get parent directory name on ftp server
     * 
     * @return parent directory
     */
    public String getParentDirectory() {
      
        String w = getWorkingDirectory();
        setParentDirectory();
        String p = getWorkingDirectory();
        setWorkingDirectory(w);

        return p;
    }

    /**
     * Get directory contents on ftp server
     * 
     * @param filePath
     *            directory
     * @return list of FTPFileInfo structures
     * @throws IOException
     */
    public List<FTPFile> listFiles(String filePath) throws IOException {
        
        // Use passive mode to pass firewalls.
        ftp.enterLocalPassiveMode();
        FTPFile[] ftpFiles = ftp.listFiles(new String(filePath.getBytes(),"iso-8859-1"));
       /* int size = (ftpFiles == null) ? 0 : ftpFiles.length;
        for (int i = 0; i < size; i++) {
            FTPFile ftpFile = ftpFiles[i];
            FfpFileInfo fi = new FfpFileInfo();
            fi.setName(ftpFile.getName());
            fi.setSize(ftpFile.getSize());
            fi.setTimestamp(ftpFile.getTimestamp());
            fi.setType(ftpFile.isDirectory());
            fileList.add(fi);
        }*/
        for (FTPFile ftpFile : ftpFiles) {
        	ftpFile.setName(new String(ftpFile.getName().getBytes("iso-8859-1"),"utf-8") );
		}
        System.out.println(JSON.toJSONString(ftpFiles));
        return  Arrays.asList(ftpFiles);
    }


    /**
     * Get file from ftp server into given output stream
     * 
     * @param ftpFileName
     *            file name on ftp server
     * @param out
     *            OutputStream
     * @throws IOException
     */
    public void getFile(String ftpFileName, OutputStream out) throws IOException {
        try {
            // Use passive mode to pass firewalls.
            ftp.enterLocalPassiveMode();
            
            // Get file info.
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
            if (fileInfoArray == null) {
                throw new FileNotFoundException("File '" + ftpFileName + "' was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File '" + ftpFileName + "' is too large.");
            }

            // Download file.
            if (!ftp.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file '" + ftpFileName + "' from FTP server. Check FTP permissions and path.");
            }

            out.flush();

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                	ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Put file on ftp server from given input stream
     * 
     * @param ftpFileName
     *            file name on ftp server
     * @param in
     *            InputStream
     * @throws IOException
     */
    public void putFile(String ftpFileName, InputStream in) throws IOException {
        try {
            // Use passive mode to pass firewalls.
            ftp.enterLocalPassiveMode();
            
            if (!ftp.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Put file on ftp server from given input stream
     * 
     * @param ftpFileName
     *            file name on ftp server
     * @param in
     *            InputStream
     * @throws IOException
     */
    public void rename(String path,String oldName,String newName) throws IOException {
        	if(!path.endsWith("/")){
        		path +="/";
        	}
            ftp.rename(path + oldName, path + newName);
    }
    
    public static void main(String[] args) throws IOException {
		
    	String  host = ResourceBundle.getBundle("sys").getString("ftp.server.host");
    	System.out.println(host);
    	/*
    	FtpUtil ftp = new  FtpUtil();
		ftp.connect("127.0.0.1", 21, "pk", "", false);
		
		System.out.println(ftp.list(""));;*/
		
		
	}

	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FTPClient getFtp() {
		return ftp;
	}

	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}
}