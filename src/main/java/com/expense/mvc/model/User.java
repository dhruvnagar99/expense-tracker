package com.expense.mvc.model;

import java.util.Date;
import java.util.HashSet;
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

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@Column(name="STATE", nullable=false)
	private String state=State.ACTIVE.getState();
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	private Date createdAt;
//	
//	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@UpdateTimestamp
	private Date updatedAt;
//	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
//				+ ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles +"]";
//	}
	
	
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
//	
//	private Set<Category> categories;
	
	

}
