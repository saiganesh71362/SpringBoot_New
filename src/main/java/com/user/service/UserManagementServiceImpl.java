package com.user.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.user.binding.ActivateAccount;
import com.user.binding.Login;
import com.user.binding.User;
import com.user.entity.UserMaster;
import com.user.repository.UserMasterRepository;
import com.user.utils.EmailUtils;


@Service
public class UserManagementServiceImpl  implements UserManagementService
{
	@Autowired
	private UserMasterRepository userMasterRepository;
   
	@Autowired
	private EmailUtils emailUtils;


	@Override
	public boolean addUser(User user)
	{
		UserMaster entity = new UserMaster();
		BeanUtils.copyProperties(user, entity);
		entity.setAccountStatus("In_Active");
		entity.setPassword(generateRandomPassword());
		UserMaster save = userMasterRepository.save(entity);
		
		String subject = "Your Registration is Success";
		String fileName = "REG-EMAIL-BODY.txt";
		String body = readEmailBody(entity.getFullName(), entity.getPassword(), fileName);
		emailUtils.sendMail(user.getEmail(), subject, body);

		
		return save.getUserId() != null;
	}

	@Override
	public boolean activateAccount(ActivateAccount activateAccount) {
	    UserMaster entity = new UserMaster();
	    entity.setEmail(activateAccount.getEmail());
	    entity.setPassword(activateAccount.getTempPassword());

	    Example<UserMaster> example = Example.of(entity);
	    List<UserMaster> users = userMasterRepository.findAll(example);

	    if (users.isEmpty()) {
	        return false; // No matching user found
	    } else {
	        UserMaster userMaster = users.get(0); // Assuming only one user should match
	        if (activateAccount.getNewPassword().equals(activateAccount.getConfirmPassword())) {
	            userMaster.setPassword(activateAccount.getNewPassword());
	        }
	        userMaster.setAccountStatus("Active");
	        userMasterRepository.save(userMaster); // Update existing user
	        return true;
	    }
	}

	@Override
	public List<User> getAllUsers() 
	{
		List<UserMaster> allUsersEntities = userMasterRepository.findAll();
		List<User> allUsers= new ArrayList<>();
		for(UserMaster userMaster : allUsersEntities)
		{
			User user = new User();
			BeanUtils.copyProperties(userMaster, user);
			allUsers.add(user);
		}
		
		return allUsers;
	}

	@Override
	public User getUserById(Integer userId)
	{
		Optional<UserMaster> findUser = userMasterRepository.findById(userId);
		if(findUser.isPresent())
		{
			UserMaster userMaster = findUser.get();
			User user =  new User();
			BeanUtils.copyProperties(userMaster, user);
			return user;
		}
		
		return null;
	}

	@Override
	public boolean deleteByUserId(Integer userId)
	{
		boolean status = false;
		try {
		userMasterRepository.deleteById(userId);
		status = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String status)
	{
		Optional<UserMaster> findUser = userMasterRepository.findById(userId);
		if(findUser.isPresent())
		{
			UserMaster userMasterData = findUser.get();
			userMasterData.setAccountStatus(status);
			return true;
		}
		return false;
	}

	@Override
	public String login(Login login)
	{
		String msg = "";
		
		UserMaster userMaster = new UserMaster();
		userMaster.setEmail(login.getEmail());
		userMaster.setPassword(login.getPassword());
		
		Example<UserMaster> of = Example.of(userMaster);
		List<UserMaster> allUsers = userMasterRepository.findAll();
		if(allUsers.isEmpty())
		{
			msg = "Invalid Credentials";
		}
		else
		{
			UserMaster userMasterData = allUsers.get(0);
			if(userMasterData.getAccountStatus().equals("Active"))
			{
				msg = "Login SuccessFully";
			}
			else
			{
				msg = "Account Not Activated";
			}
		}
		return msg;
	}

	@Override
	public String forgotPassword(String email) {
		UserMaster entity = userMasterRepository.findByEmail(email);
		if (entity == null) {
			return "Invalid Email ID";
		}
		String subject = "Forgot Password";
		String fileName = "RECOVER-PWD-BODY.txt";
		String body = readEmailBody(email, entity.getPassword(), fileName);
		boolean sendMail = emailUtils.sendMail(entity.getEmail(), subject, body);

		if (sendMail) {
			return "Password sent to your registered mail once Check your Mail";
		}
		return null;
	}
	
	
	
	private static String generateRandomPassword()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvwxyz"+"@#$%&*";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<=6;i++)
		{
			int index = (int) (AlphaNumericString.length()*Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public User updateUser(User user, Integer userId)
	{
	    UserMaster existingUser = userMasterRepository.findById(userId).orElse(null);
	    
	    if (existingUser != null) {
	        // Preserve createdBy value
	        String createdBy = existingUser.getCreatedBy();
	        
	        // Update other fields
	        existingUser.setFullName(user.getFullName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setMobileNumber(user.getMobileNumber());
	        existingUser.setGender(user.getGender());
	        existingUser.setDateOfBirth(user.getDateOfBirth());
	        existingUser.setSsn(user.getSsn());
	        existingUser.setUpdatedBy(user.getUpdatedBy());
	        
	        // Set back the original createdBy value
	        existingUser.setCreatedBy(createdBy);
	        
	        // Save the updated user
	        UserMaster updatedUser = userMasterRepository.save(existingUser);
	        
	        // Convert UserMaster to User and return
	        return convertToUser(updatedUser);
	    } else {
	        return null;
	    }
    }
    
    private User convertToUser(UserMaster userMaster) 
    {
        return new User(
            userMaster.getFullName(),
            userMaster.getEmail(),
            userMaster.getMobileNumber(),
            userMaster.getGender(),
            userMaster.getDateOfBirth(),
            userMaster.getSsn(),
            userMaster.getCreatedBy(),
            userMaster.getUpdatedBy()
        );
    
    

	}
    
    // read  mail
    
    private String readEmailBody(String fullName, String password, String fileName) {
        String url = ""; // You might want to set the URL here
        String mailBody = null;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringBuilder buffer = new StringBuilder(); // Using StringBuilder for better performance
            while (line != null) {
                buffer.append(line);
                line = br.readLine();
            }
            br.close();
            mailBody = buffer.toString();
            mailBody = mailBody.replace("{FULLNAME}", fullName)
                               .replace("{TEMP-PWD}", password)
                               .replace("{URL}", url);

        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
        return mailBody;
    }
	


}
