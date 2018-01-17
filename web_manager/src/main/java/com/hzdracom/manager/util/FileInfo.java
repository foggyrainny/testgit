/**
* @Title: FileInfo.java
* @Package com.hzdracom.common
* @Description: TODO
* @author hexin
* @date 2016年3月24日 下午3:25:08
* @version V1.0
*/

package com.hzdracom.manager.util;

/**  
 * @Title: FileInfo.java
 * @Package com.hzdracom.common
 * @Description: TODO
 * @author hexin
 * @date 2016年3月24日 下午3:25:08
 * @version V1.0  
 */
public class FileInfo {
	private String md5;
    private Integer chunkIndex;
    private String size;
    private String name;
    private String userId;
    private String id;
    private Integer chunks;
    private Integer chunk;
    private String lastModifiedDate;
    private String type;
    private String ext;
    
    public FileInfo(){}

    public FileInfo(String name, String size, Integer chunkIndex){
        this.name = name;
        this.size = size;
        this.chunkIndex = chunkIndex;
    }

    public FileInfo(String userId, String id){
        this.userId = userId;
        this.id = id;
    }

    public FileInfo(String md5){
        this.md5 = md5;
    }
    
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public Integer getChunkIndex() {
		return chunkIndex;
	}
	public void setChunkIndex(Integer chunkIndex) {
		this.chunkIndex = chunkIndex;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getChunks() {
		return chunks;
	}
	public void setChunks(Integer chunks) {
		this.chunks = chunks;
	}
	public Integer getChunk() {
		return chunk;
	}
	public void setChunk(Integer chunk) {
		this.chunk = chunk;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
}
