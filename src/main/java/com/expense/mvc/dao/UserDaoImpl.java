package com.expense.mvc.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.expense.mvc.model.User;


@Repository("userDao")
@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User save(User user) {
		persist(user);
		return user;
	}
	
	public User findById(long id) {
		return getByKey(id);
	}

	public User findBySSO(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		return (User) crit.uniqueResult();
	}

}
