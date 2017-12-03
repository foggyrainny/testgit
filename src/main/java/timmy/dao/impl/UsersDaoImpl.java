package timmy.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import timmy.dao.UsersDao;
import timmy.daomain.Users;


public class UsersDaoImpl extends BaseDao<Users> implements UsersDao{
	
	
	@Override
	public void update(Users users) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("update users set ");
		sb.append("password= '").append(users.getPassword()).append("' , ");
		sb.append("sex= ").append(users.getSex()).append(" , ");
//		sb.append("add_time= '").append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(users.getAdd_time())).append("' , ");
		sb.append("mobile= '").append(users.getMobile()).append("' , ");
		sb.append("address= '").append(users.getAddress()).append("' , ");
		sb.append("meto= '").append(users.getMeto()).append("' ");
		sb.append("where id= ").append(users.getId());
		
		System.out.println(sb.toString());
		
		this.update(sb.toString());
	}

	@Override
	public void add(Users users) throws Exception {
		String sql="insert into users (name,password,sex,add_time,mobile,address,meto) values ('"
				+users.getName()+"', '"+users.getPassword()+"',"+users.getSex()+", '"+
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(users.getAdd_time())+"', '"+
				users.getMobile()
				+"', '"+users.getAddress()+"', '"+users.getMeto()+"')";
		this.update(sql);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql="delete from users  where  id="+id; 
		this.update(sql);
	}

	@Override
	public Users getUsersById(Long id) throws Exception {
		
		return this.findObject("select * from users where id="+id, Users.class);
	}

	@Override
	public List<Users> getList(Users users) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select * from users where 1=1");
		if(users.getName()!=null&&users.getName().length()>0){
			sb.append(" and name like '%").append(users.getName()).append("%' ");
		}
		if(users.getSex()>0){
			sb.append(" and sex like ").append(users.getSex()).append(" ");
		}
		if(users.getMobile()!=null&&users.getMobile().length()>0){
			sb.append(" and mobile like '%").append(users.getMobile()).append("%' ");}
		if(users.getName()!=null&&users.getName().length()>0){
			sb.append(" and name = '").append(users.getName()).append("' ");}
		return this.findList(sb.toString(), Users.class);
	} 
	
		public Users getUsersByName(String  name) throws Exception {
		
		return this.findObject("select * from users where name="+"'"+name+"'", Users.class);
	}

	
 }
