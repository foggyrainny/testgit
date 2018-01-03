package timmy.train.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SysOperLog extends BaseDomain{

	private Long id;
	
	private String oper_type;
	
	private Date oper_time;
	
	private Long oper_uid;
	
	private String oper_uname;
	
	private String oper_uip;
	
	private String oper_memo;
	
	private Integer is_del;

	
	
	
}
