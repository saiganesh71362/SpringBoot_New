package com.user.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User 
{
	private String FullName;
	
	private String email;
	
	private Long mobileNumber;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private Long ssn;
	
	private String createdBy;
	
	private String updatedBy;

}
