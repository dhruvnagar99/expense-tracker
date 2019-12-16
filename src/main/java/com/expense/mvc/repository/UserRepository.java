package com.expense.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.mvc.model.Category;
import com.expense.mvc.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	

	
}
