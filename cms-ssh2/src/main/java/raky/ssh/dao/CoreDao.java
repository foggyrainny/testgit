package raky.ssh.dao;

import java.io.Serializable;
import java.util.List;

import cn.goodjobs.cms.utils.Pager;

public interface CoreDao<T extends Serializable> {
	
	public Serializable insert(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public T getOne(Serializable id);
	
	public List<T> getList(T t);	
	
	public int getCount(T t);
	
	public List<T> getPaginatedList(T t);	
	
	////////////////////////////////////////////////////////////////////////
		
	public List<T> getList();
	
	public List<T> getList(String queryString, Object...values);
	
	public int getCount(String queryString, Object...values);
	
	public List<T> getPaginatedList(String queryString, Pager pager, Object...values);
}
