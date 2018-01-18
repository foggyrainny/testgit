package raky.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.goodjobs.cms.utils.Pager;
import raky.ssh.dao.CoreDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class CoreDaoImpl<T extends Serializable> extends HibernateDaoSupport implements CoreDao<T> {
	
	private Class<T> entityClass;
	
	public CoreDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	@Override
	public Serializable insert(T t) {
		return this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {		
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T getOne(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}
	
	@Override
	public List<T> getList() {
		String queryString = "from " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	@Override
	public List<T> getList(final String queryString, final Object...values) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(queryString);
				for (int i = 0; i < values.length; i++)
					query.setParameter(i, values[i]);
				return (List<T>) query.list();
			}
		});
		return list;
	}
	
	@Override
	public int getCount(final String queryString, final Object...values) {
		int beginIndex = queryString.toLowerCase().indexOf("from");
		String countQueryString = "select count(*) " + queryString.substring(beginIndex);
		int totalCount = ((Long) this.getHibernateTemplate().find(countQueryString, values).get(0)).intValue();
		return totalCount;
	}

	@Override
	public List<T> getPaginatedList(final String queryString, final Pager pager, final Object...values) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(queryString);
				for (int i = 0; i < values.length; i++)
					query.setParameter(i, values[i]);
				return (List<T>) query.setFirstResult(pager.getFirstRow())
						.setMaxResults(pager.getPageSize()).list();
			}
		});
		return list;
	}
}
