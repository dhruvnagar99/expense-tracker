package com.expense.mvc.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.expense.mvc.dao.UserDao;
import com.expense.mvc.model.User;
import com.expense.mvc.model.UserProfile;
import com.expense.mvc.repository.UserRepository;
import com.expense.mvc.service.UserProfileService;


@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserRepository  userRepository;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	private UserDao dao;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public UserController(UserRepository userRepository) {
		
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
	
	@PostMapping("/newUser")
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//dao.save(user);
		userRepository.save(user);
		return ResponseEntity.created(new URI("/api/user"+user.getId())).body(user);
		
		
	}
	
	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
//	@PostMapping("/newUser")
//	public String saveRegistration(@Valid User user,
//			BindingResult result, ModelMap model) {
//
//		if (result.hasErrors()) {
//			System.out.println("There are errors");
//			return "newuser";
//		}
//		userService.save(user);
//		
//		System.out.println("First Name : "+user.getFirstName());
//		System.out.println("Last Name : "+user.getLastName());
//		System.out.println("SSO ID : "+user.getSsoId());
//		System.out.println("Password : "+user.getPassword());
//		System.out.println("Email : "+user.getEmail());
//		System.out.println("Checking UsrProfiles....");
//		if(user.getUserProfiles()!=null){
//			for(UserProfile profile : user.getUserProfiles()){
//				System.out.println("Profile : "+ profile.getType());
//			}
//		}
//		
//		model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
//		return "registrationsuccess";
//	}

	
	
//	@PostMapping("/newUser")
//	public User saveRegistration(@Valid User user,
//			BindingResult result, ModelMap model) {
//
//		if (result.hasErrors()) {
//			System.out.println("There are errors");
//			return null;
//		}
//		
//		
//		System.out.println("First Name : "+user.getFirstName());
//		System.out.println("Last Name : "+user.getLastName());
//		System.out.println("SSO ID : "+user.getSsoId());
//		System.out.println("Password : "+user.getPassword());
//		System.out.println("Email : "+user.getEmail());
//		System.out.println("Checking UsrProfiles....");
//		if(user.getUserProfiles()!=null){
//			for(UserProfile profile : user.getUserProfiles()){
//				System.out.println("Profile : "+ profile.getType());
//			}
//		}
//		
//		model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
//		return userRepository.save(user);
//	}
	
	
	
	
	
	
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newuser";
	}

	
	
	
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	
	
	
	
	

}
