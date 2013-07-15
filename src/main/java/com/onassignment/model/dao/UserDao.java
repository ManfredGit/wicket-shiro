package com.onassignment.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.onassignment.model.User;

public class UserDao extends BaseDao {

	@Transactional(readOnly=true)
	@Override
	public Object load(long id) {
		Object user = currentSession().get(User.class, id);
		return user;
	}
	
	@Transactional
	public User loadByLoginId(String loginId) {
		Query query = currentSession().createQuery("from User where active = true and loginId = :loginId ");
		query.setParameter("loginId", loginId);
		
		List users = query.list();
		
		if (users.size() == 1) {
			return (User) users.get(0);
		}
		
		return null;
	}

}
