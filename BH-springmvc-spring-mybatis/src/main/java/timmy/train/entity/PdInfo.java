package timmy.train.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PdInfo extends BaseDomain{
	
	private BigInteger pd_id;
	
	private String uuid;
	
	private String pd_name;
	
	private BigInteger entp_id;
	
	private BigInteger pd_type;
	
	private BigInteger model_id;
	
	private String main_image;
	
	private BigDecimal price_ref;
	
	private Integer pd_num;
	
	private Integer view_count;
	
	private String pd_desc;
	
	private Integer order_value;
	
	private Integer is_del;
	
	private Date del_time;
	
	private BigInteger del_user_id;
	
	private Integer audit_state;
	
	private Integer audit_user_id;
	
	private Date audit_time;
	
	private String audit_desc;

	public BigInteger getPd_id() {
		return pd_id;
	}

	public void setPd_id(BigInteger pd_id) {
		this.pd_id = pd_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public BigInteger getEntp_id() {
		return entp_id;
	}

	public void setEntp_id(BigInteger entp_id) {
		this.entp_id = entp_id;
	}

	public BigInteger getPd_type() {
		return pd_type;
	}

	public void setPd_type(BigInteger pd_type) {
		this.pd_type = pd_type;
	}

	public BigInteger getModel_id() {
		return model_id;
	}

	public void setModel_id(BigInteger model_id) {
		this.model_id = model_id;
	}

	public String getMain_image() {
		return main_image;
	}

	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}

	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	public void setPrice_ref(BigDecimal price_ref) {
		this.price_ref = price_ref;
	}

	public Integer getPd_num() {
		return pd_num;
	}

	public void setPd_num(Integer pd_num) {
		this.pd_num = pd_num;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public String getPd_desc() {
		return pd_desc;
	}

	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getDel_time() {
		return del_time;
	}

	public void setDel_time(Date del_time) {
		this.del_time = del_time;
	}

	public BigInteger getDel_user_id() {
		return del_user_id;
	}

	public void setDel_user_id(BigInteger del_user_id) {
		this.del_user_id = del_user_id;
	}

	public Integer getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}

	public Integer getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public Date getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}

	public String getAudit_desc() {
		return audit_desc;
	}

	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}

	
	
}
