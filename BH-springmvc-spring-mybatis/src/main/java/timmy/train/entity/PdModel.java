package timmy.train.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PdModel extends BaseDomain{
	
	private Long model_id;
	
	private String md_name;
	
	private Integer pd_price;
	
	private Integer order_sort;
	
	private Date add_date;
	
	private Integer del_mark;
	
	private Date del_date;

	
	}

	