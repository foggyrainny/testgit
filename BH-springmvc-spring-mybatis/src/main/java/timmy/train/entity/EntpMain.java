package timmy.train.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EntpMain extends BaseDomain{
	
	private Long entp_id;
	
	private String entp_name;
	
	private String entp_sname;
	
	private String entp_ename;
	
	private Integer entp_type;
	
	private String main_pd;
	
	private Integer entp_kind;
	
	private String entp_size;
	
	private String entp_licence;
	
	private String corporation;
	
	private String addr;
	
	private String  postcode;
	
	private String linkman;
	
	private String tel;
	
	private String fax;
	
	private String email;
	
	private String www;
	
	private Date add_date;
	
	private Integer info_state;
	
	private String sq_serial;
	
	private String del_man;
	
	private Date del_date;
}
