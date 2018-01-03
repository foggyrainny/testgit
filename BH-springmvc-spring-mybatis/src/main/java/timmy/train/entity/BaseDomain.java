package timmy.train.entity;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BaseDomain {
	
	//包装额外的查询条件
	private Map<String,String> map = new HashMap<String,String>();

	private Integer first;
	
	private Integer count;
	
		
}
