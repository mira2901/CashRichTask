package com.cashrish.Controller;

import java.util.Optional;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashrish.Entity.User;
import com.cashrish.Repository.UserRepository;
import com.cashrish.Services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
		System.out.println("Received signup request: " + user.toString());
		 if (userService.findByUsername(user.getUsername()).isPresent()) {
	            return ResponseEntity.badRequest().body(null);
	        }
	        return ResponseEntity.ok(userService.saveUser(user));
    }
	
	@PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
		System.out.println("Received login request: " + user.toString());
		Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok(existingUser.get());
        }
        return ResponseEntity.status(401).body(null);
    }
	
	@PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
		
		System.out.println("Received update request: " + user.toString());
		Optional<User> existingUser = userService.findByUsername(user.getUsername());
		
		if (existingUser.isPresent())
		{
			User existUser = existingUser.get();
			existUser.setMobile(user.getMobile());
			existUser.setEmail(user.getEmail());
			existUser.setFirstName(user.getFirstName());
			existUser.setLastName(user.getLastName());
			existUser.setPassword(passwordEncoder.encode(user.getPassword()));
			return ResponseEntity.ok(userService.saveUser(existUser));
		}
		
		return ResponseEntity.status(401).body(null);
    }


}
