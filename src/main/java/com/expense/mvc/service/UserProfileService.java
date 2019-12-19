package com.expense.mvc.service;

import java.util.List;

import com.expense.mvc.model.UserProfile;


public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
