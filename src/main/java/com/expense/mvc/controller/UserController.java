package com.expense.mvc.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.mvc.model.User;
import com.expense.mvc.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserRepository  userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	Collection<User> users(){
		return userRepository.findAll();
		
	}
	
	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id){
		Optional<User> user = userRepository.findById(id);
		return user.map(response-> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/user")
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException{
		User result = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/user"+result.getId())).body(result);
		
		
	}

}
