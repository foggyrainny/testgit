package raky.train.domain;

import java.util.HashMap;
import java.util.Map;

public class BaseDomain {
	
	//包装额外的查询条件
	private Map<String,String> map = new HashMap<String,String>();
	
	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	private Integer first;
	
	private Integer count;
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	

}
