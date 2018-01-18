package cn.goodjobs.cms.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.goodjobs.cms.dao.UsersDao;
import cn.goodjobs.cms.entity.Users;
import raky.ssh.dao.impl.CoreDaoImpl;

@SuppressWarnings("unchecked")
@Repository
public class UsersDaoImpl extends CoreDaoImpl<Users> implements UsersDao {

	@Override
	public List<Users> getList(Users users) {
		return (List<Users>)this.getCriteria(users).list();
	}

	@Override
	public int getCount(Users users) {
		List<Users> list = this.getList(users);
		return list != null && list.size() > 0  ? list.size() : 0;
	}

	@Override
	public List<Users> getPaginatedList(Users users) {
		return (List<Users>)this.getCriteria(users).setFirstResult(users.getFirstRow()).setMaxResults(users.getRowCount()).list();
	}
	
	private Criteria getCriteria(Users users){
		Criteria c = this.currentSession().createCriteria(Users.class);
		if(users.getId() != null){
			c.add(Restrictions.eq("id", users.getId()));
		}
		if(StringUtils.isNotBlank(users.getName())){
			c.add(Restrictions.like("name", users.getName(),MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(users.getPass())){
			c.add(Restrictions.eq("pass", users.getPass()));
		}
		if(users.getAge() != null){
			c.add(Restrictions.eq("age", users.getAge()));
		}
		return c;
	}
}