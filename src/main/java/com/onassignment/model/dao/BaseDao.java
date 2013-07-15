package com.onassignment.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.onassignment.model.PersistentEntity;

public abstract class BaseDao {

	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Object object) {
		PersistentEntity obj = (PersistentEntity) object;
		currentSession().saveOrUpdate(obj);
	}

	@Transactional
	public abstract Object load(long id);

	@Transactional
	public void delete(Object object) {
		// TODO: this method!
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
