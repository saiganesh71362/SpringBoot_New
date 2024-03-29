package com.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.user.binding.ActivateAccount;
import com.user.binding.Login;
import com.user.binding.User;
import com.user.service.UserManagementService;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/userManagement")
public class UserManagmentController {
	private UserManagementService userManagementService;

	public UserManagmentController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		boolean newUser = userManagementService.addUser(user);
		if (newUser) {
			new ResponseEntity<>("New User Add Success Fully", HttpStatus.CREATED);

		} else {
			new ResponseEntity<>("User Not Creatde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;

	}

	@PostMapping("/activatedAccount")
	public ResponseEntity<String> activateAccount(@RequestBody ActivateAccount activateAccount) {
		boolean activateAccounts = userManagementService.activateAccount(activateAccount);

		if (activateAccounts) {
			return new ResponseEntity<>("Account Activated Success Fully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid email or temp password", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userManagementService.getAllUsers();

		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		User userById = userManagementService.getUserById(userId);

		return new ResponseEntity<>(userById, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}")
	 public ResponseEntity<String> deleteById(@PathVariable Integer userId)
	{
		boolean deleteByUserId = userManagementService.deleteByUserId(userId);
		if(deleteByUserId) {
			return new ResponseEntity<>("User Delete SuccessFully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("User Delete Faild",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/users/{userId}/{status}")
	public  ResponseEntity<String> statusChange(@PathVariable Integer userId, @PathVariable String status)
	{
		boolean changeAccountStatus = userManagementService.changeAccountStatus(userId,status);
		if(changeAccountStatus) 
		{
			return new ResponseEntity<>("Status Changed Success Fully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Status Faild ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
    @PutMapping("/userUpdate/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer userId) {
        User updatedUser = userManagementService.updateUser(user, userId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
	@PostMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email){
		String status = userManagementService.forgotPassword(email);
		return new ResponseEntity<String> (status, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login){
		String loginStatus = userManagementService.login(login);
		return new ResponseEntity<String>(loginStatus, HttpStatus.OK);
	}


}
