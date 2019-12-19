package com.expense.mvc.dao;

import com.expense.mvc.model.User;

public interface UserDao {

	void save(User user);
	
	User findById(long id);
	
	User findBySSO(String sso);
	
}
