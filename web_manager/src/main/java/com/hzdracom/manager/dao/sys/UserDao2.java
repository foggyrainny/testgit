package com.hzdracom.manager.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzdracom.manager.bean.form.UserForm;
import com.hzdracom.manager.bean.form.UserSettiingForm;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.bean.sys.User;

/**
 * @title: UserDao2.java
 * @pacjage: com.hzdracom.manager.dao.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月7日 下午3:04:56
 */
public interface UserDao2 {
	
	public List<User> getUserList(UserForm user) throws Exception;
	
	public int getUserListTotal(UserForm user) throws Exception;
	
	public int addUser(User user) throws Exception;
	
	public int addUserRole(User user) throws Exception;
	
	public int delUserRole(User user) throws Exception;
	
	public List<User> getLoginName(User user);
	
	//动态判断登录名重复
	public int selLoginname(String loginname);
	
	public int update(User user);
	
	List<Role> roleSelect(String officeId);
	
	User getUser(String userId);
	
	int delete(String userId);

	public void updateUserLoginIp(@Param("ip")String ip,@Param("userId") String userId);
	
	
	// 用户登录  
	public User login(@Param("account")String account,@Param("pwd")String pwd);

	/**
	 *  更新用户个人信息  ，用户自己修改
	 */
	public void updateUserInfo(UserSettiingForm form);

	/**
	 *  查询超管所对应的  用户ID列表
	 */
	public List<String> querySupperAdminUserId();
	
}
