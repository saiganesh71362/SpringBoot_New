package com.user.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_MASTER_NEW")
public class UserMaster 
{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "FULL_NAME")
	private String fullName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MOBILE_NUMBER")
	private Long mobileNumber;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dateOfBirth;
	@Column(name = "SSN")
	private Long ssn;
	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;
	@Column(name ="PASSWORD")
	private String password;
    @Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATE_BY")
	private String updatedBy;
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name = "UPDATE_DATE",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	
	public UserMaster()
	{
		
	}
	public UserMaster(Integer userId, String fullName, String email, Long mobileNumber, String gender,
			LocalDate dateOfBirth, Long ssn, String accountStatus, String password, String createdBy, String updatedBy,
			LocalDate createdDate, LocalDate updatedDate) {
		
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
		this.accountStatus = accountStatus;
		this.password = password;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}




//public class RandomPasswordGenerator {
//
//private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
//private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
//private static final String NUMBER = "0123456789";
//private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
//private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
//
//private static SecureRandom random = new SecureRandom();
//
//public static void main(String[] args) {
//    int passwordLength = 10;
//    String password = generateRandomPassword(passwordLength);
//    System.out.println("Generated Password: " + password);
//}
//
//public static String generateRandomPassword(int length) {
//    if (length < 4) {
//        throw new IllegalArgumentException("Password length must be at least 4 characters");
//    }
//
//    StringBuilder password = new StringBuilder(length);
//    for (int i = 0; i < length - 3; i++) {
//        int randomIndex = random.nextInt(PASSWORD_ALLOW_BASE.length());
//        password.append(PASSWORD_ALLOW_BASE.charAt(randomIndex));
//    }
//
//    // Ensure at least one character from each character set
//    password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
//    password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
//    password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
//    password.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));
//
//    // Shuffle the password
//    for (int i = 0; i < length; i++) {
//        int randomPosition = random.nextInt(length);
//        char temp = password.charAt(i);
//        password.setCharAt(i, password.charAt(randomPosition));
//        password.setCharAt(randomPosition, temp);
//    }
//
//    return password.toString();
//}
//}
//

