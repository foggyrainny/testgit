package raky.ssh.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import raky.ssh.dao.CoreDao;
import raky.ssh.service.CoreService;

@Service
public abstract class CoreServiceImpl<T extends Serializable> implements CoreService<T> {
	
	@Autowired
	private CoreDao<T> coreDao;

	@Override
	public Serializable create(T t) {
		return this.coreDao.insert(t);
	}

	@Override
	public void modify(T t) {
		this.coreDao.update(t);
	}

	@Override
	public void remove(T t) {
		this.coreDao.delete(t);
	}

	@Override
	public T getOne(Serializable id) {
		return this.coreDao.getOne(id);
	}

}
