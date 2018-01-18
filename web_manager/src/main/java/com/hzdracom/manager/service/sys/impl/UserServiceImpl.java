package com.hzdracom.manager.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hzdracom.manager.bean.form.UserForm;
import com.hzdracom.manager.bean.form.UserSettiingForm;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.bean.sys.User;
import com.hzdracom.manager.dao.sys.UserDao2;
import com.hzdracom.manager.service.sys.IUserService;
import com.hzdracom.manager.util.DateUtil;
@Service
public class UserServiceImpl implements IUserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao2 dao;
	
	@Override
	public PageMsg<User> getUserList(UserForm user) throws Exception {
		 List<User> list = dao.getUserList(user);
		 PageMsg<User> pageObj = new PageMsg<User>();
		 pageObj.setListResult(list);
		 pageObj.setTotalNum(dao.getUserListTotal(user));
		 pageObj.setPageNum(user.getSize());
		 pageObj.setPage(user.getCurr());
		 pageObj.setTotalPage();
		 return pageObj;
	}
	
	
	@Override
	public boolean addUser(User user) throws Exception {
		List<User> list = dao.getLoginName(user);
		if (list != null && list.size() > 0) return false;
		
		int i = dao.addUser(user);
		dao.delUserRole(user);
		dao.addUserRole(user);
		return i > 0;
	}
	
	
	/**
	 * 用户登录帐号、密码验证判断
	 * @param account 帐号
	 * @param pwd 密码
	 * @return 正确返回user，不存在或错误返回null
	 * @throws Exception
	 */
	public User checkUserLogin(String account, String pwd) throws Exception {
		return dao.login(account, pwd);
	}


	@Override
	public boolean update(User user) throws Exception {
		List<User> list = dao.getLoginName(user);
		if (list != null && list.size() > 0) return false;
		
		int i = dao.update(user);
		dao.delUserRole(user);
		dao.addUserRole(user);
		return i > 0;
	}


	@Override
	public List<Role> roleSelect(String officeId) throws Exception {
		
		return dao.roleSelect(officeId);
	}


	@Override
	public User getUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.getUser(userId);
	}


	@Override
	public boolean delete(String userId) throws Exception {
		// TODO Auto-generated method stub
		int i = dao.delete(userId);
		return i > 0;
	}

	@Override
	public void updateUserLoginIp(String loginIp,String userId){
		dao.updateUserLoginIp(loginIp, userId);
	}


	@Override
	public List<User> userExecl(UserForm user) throws Exception {
		// TODO Auto-generated method stub
		user.setCurr(1);
		user.setSize(10000000);
		List<User> list = dao.getUserList(user);
		for (User v : list) {
			v.setModifyTimeStr(DateUtil.dateToString(v.getModifyTime(), "yyyy-MM-dd hh:mm:ss"));
		}
		return list;
	}


	@Override
	public void updateUserInfo(UserSettiingForm form) {
			dao.updateUserInfo(form);
	}


	@Override
	public List<String> querySupperAdminUserId() {
		return dao.querySupperAdminUserId();
	}


	@Override
	public int selloginname(String name) throws Exception {
			//重复 ：1  不重复 0；
		return dao.selLoginname(name);
	}
}
