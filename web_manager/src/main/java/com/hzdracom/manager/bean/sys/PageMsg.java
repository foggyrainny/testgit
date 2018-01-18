package com.hzdracom.manager.bean.sys;

import java.util.List;


/**
 * @类功能说明：分页用实体类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：qianfuqiang
 * @创建时间：2013-4-15 下午10:10:27
 * @版本：V1.0
 */
public class PageMsg<E>
{
	
	private int     page      = 1; // 当前页数
	private int     pageNum   = 10; // 每页条数
	private int     totalNum  = 1; // 总条数
	private int     totalPage = 1; // 总页数
	private int     pageStart = 0; //开始页
	private List<E> listResult;    //结果集
	private List<E> listResults;   //结果集返回2个结果集
	private List<E> listResultss;  //结果集返回3个结果集
	                                
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getTotalNum() {
		return totalNum;
	}
	
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage() {
		if ((totalNum % pageNum) == 0)
		{
			this.totalPage = totalNum / pageNum;
		}
		else
		{
			this.totalPage = (totalNum / pageNum) + 1;
		}
		
	}
	
	public int getPageStart() {
		return this.pageStart = (page - 1) * pageNum;
	}
	
	public void setPageStart() {
		this.pageStart = (page - 1) * pageNum;
	}
	
	public List<E> getListResult() {
		return listResult;
	}
	
	public void setListResult(List<E> listResult) {
		this.listResult = listResult;
	}
	
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<E> getListResults() {
		return listResults;
	}
	
	public void setListResults(List<E> listResults) {
		this.listResults = listResults;
	}
	
	public List<E> getListResultss() {
		
		return listResultss;
	}
	
	public void setListResultss(List<E> listResultss) {
		
		this.listResultss = listResultss;
	}
	
}
