package com.expense.mvc.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@UpdateTimestamp
	private Date updatedAt;
	
//	@ManyToMany(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//	@JoinTable(
//	    name = "user_category", 
//	joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false), 
//	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//	)
//	
//	private Set<User> users;
	
	

}
