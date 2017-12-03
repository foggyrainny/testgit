package timmy.daomain;

import java.util.Date;

public class Users {
	
	private Long id;
	
	private String name;
	
	private String password;
	
	private int sex;
	
	private Date add_time;
	
	private String mobile;
	
	private String address;
	
	private String meto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMeto() {
		return meto;
	}

	public void setMeto(String meto) {
		this.meto = meto;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", add_time="
				+ add_time + ", mobile=" + mobile + ", address=" + address + ", meto=" + meto + "]";
	}
	
	
	
	

}
