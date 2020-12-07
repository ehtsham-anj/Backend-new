package com.boot.fullstack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.fullstack.model.User;
import com.boot.fullstack.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;


	@GetMapping("/users")
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable int id) throws AttributeNotFoundException {
		User employee = userRepo.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	// update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<User> updateEmployee(@PathVariable int id, @RequestBody User userDetails) throws AttributeNotFoundException{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Employee not exist with id :" + id));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
	
		
		User updatedUser = userRepo.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete employee rest api
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable int id) throws AttributeNotFoundException{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Employee not exist with id :" + id));
		
		userRepo.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
