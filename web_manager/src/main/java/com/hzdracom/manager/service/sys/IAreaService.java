package com.hzdracom.manager.service.sys;

import java.util.List;

import com.hzdracom.manager.bean.sys.Area;

/**
 * @title: IAreaService.java
 * @pacjage: com.hzdracom.manager.service.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月10日 上午10:23:01
 */
public interface IAreaService {
	
	public List<Area> getAreaList(Area area);
	
}
