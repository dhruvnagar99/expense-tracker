package com.expense.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.mvc.model.Category;
import com.expense.mvc.model.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	

	
}
