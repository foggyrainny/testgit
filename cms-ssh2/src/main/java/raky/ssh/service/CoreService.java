package raky.ssh.service;

import java.io.Serializable;
import java.util.List;

public interface CoreService<T extends Serializable> {
	
	public Serializable create(T t);
	
	public void modify(T t);
	
	public void remove(T t);
	
	public T getOne(Serializable id);
	
	public List<T> getList(T t);	
	
	public int getCount(T t);
	
	public List<T> getPaginatedList(T t);
}
