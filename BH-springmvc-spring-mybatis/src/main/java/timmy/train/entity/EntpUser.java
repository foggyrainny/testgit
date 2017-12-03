package timmy.train.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EntpUser extends BaseDomain{

	private Long user_id;
	
	private String user_name;
	
	private String pass_word;
	
	private Integer user_type;
	
	private Long entp_id;
	
	private String realname;
	
	private Integer user_state;
	
	private Date last_login_time;
	
	private String last_login_ip;
	
	
	
	
}
