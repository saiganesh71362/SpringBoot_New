package com.user.service;

import java.util.List;

import com.user.binding.ActivateAccount;
import com.user.binding.Login;
import com.user.binding.User;
import com.user.entity.UserMaster;

public interface UserManagementService 
{
	 public boolean addUser(User user);
     public User updateUser(User user,Integer userId);
     public boolean activateAccount(ActivateAccount activateAccount);
	 public List<UserMaster> getAllUsers();
	 public User getUserById(Integer userId);
	 public boolean deleteByUserId(Integer userId);
	 public boolean changeAccountStatus(Integer userId, String status);
	 public String login(Login login);
	 public String forgotPwd(String email);

}
