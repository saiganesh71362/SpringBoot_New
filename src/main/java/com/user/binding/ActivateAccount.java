package com.user.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateAccount 
{
	private String email;
	
	private String tempPassword;
	
	private String newPassword;
	
	private String confirmPassword;

}
