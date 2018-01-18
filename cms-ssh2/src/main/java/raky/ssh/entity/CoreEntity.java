package raky.ssh.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CoreEntity implements Serializable{

	private static final long serialVersionUID = -6899345357642206832L;

	private Map<String,Object> map = new HashMap<String,Object>();
	
	private Integer firstRow;
	
	private Integer rowCount;
	
	private String queryString;

}
