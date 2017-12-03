package timmy.train.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PdType extends BaseDomain{
	
	private Long pd_type;
	
	private String pd_name;
	
	private Integer order_sort;
	
	private Date add_date;
	
	private Integer del_mark;
	
	private Date del_date;

	

}
