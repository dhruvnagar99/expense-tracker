package com.expense.mvc.service;

import com.expense.mvc.model.User;

public interface UserService {

	void save(User user);
	
	User findById(long id);
	
	User findBySso(String sso);
	
}