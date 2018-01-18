package cn.goodjobs.cms.service;

import java.util.List;

import cn.goodjobs.cms.entity.Users;
import raky.ssh.service.CoreService;

public interface UsersService extends CoreService<Users> {
	
	public List<Users> getList(Users users);

}
