package com.hzdracom.manager.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzdracom.manager.bean.sys.Area;
import com.hzdracom.manager.dao.sys.AreaDao;
import com.hzdracom.manager.service.sys.IAreaService;

/**
 * @title: AreaService.java
 * @pacjage: com.hzdracom.manager.service.sys.impl
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月10日 下午2:22:04
 */
@Service
public class AreaService implements IAreaService{
	
	@Resource
	private AreaDao dao;
	
	@Override
	public List<Area> getAreaList(Area area) {
		return dao.getAreaList(area);
	}
	
}
