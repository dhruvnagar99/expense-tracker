package com.expense.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.mvc.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
