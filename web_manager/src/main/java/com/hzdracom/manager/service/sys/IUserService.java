package com.hzdracom.manager.service.sys;

import java.util.List;

import com.hzdracom.manager.bean.form.UserForm;
import com.hzdracom.manager.bean.form.UserSettiingForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.bean.sys.User;


public interface IUserService {
	
	public PageMsg<User> getUserList(UserForm user) throws Exception;
	
	public boolean addUser(User user) throws Exception;
	
	public int selloginname(String name) throws Exception;
	
	public boolean update(User user) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public boolean delete(String userId) throws Exception;
	
	public List<User> userExecl(UserForm user)throws Exception;
	
	/**
	 * 定制角色下啦列表
	 * @param officeId 机构id
	 * @return
	 * @throws Exception
	 */
	public List<Role> roleSelect(String officeId) throws Exception;
	
	
	/**
	 * 用户登录帐号、密码验证判断
	 * @param account 帐号
	 * @param pwd 密码
	 * @return 正确返回userId，不存在或错误返回null
	 * @throws Exception
	 */
	public User checkUserLogin(String account, String pwd) throws Exception;

	/**
	 *  更新用戶的登录IP 和登录 时间
	 */
	void updateUserLoginIp(String loginIp, String userId);

	
	/**
	 *  更新用户个人信息
	 */
	public void updateUserInfo(UserSettiingForm form);
	
	/**
	 *  查询超级管理员对应的 所有 用户ID
	 */
	public List<String> querySupperAdminUserId();

	
}
