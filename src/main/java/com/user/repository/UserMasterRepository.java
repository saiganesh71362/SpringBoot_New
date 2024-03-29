package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer>
{
	public UserMaster findByEmailAndPassword(String email, String password);
  
    public UserMaster findByEmail(String email);
}

//@Query(value = "SELECT * FROM USER_MASTER WHERE EMAIL = ?1 AND PASSWORD = ?2", nativeQuery = true)
//@Query(value = "SELECT * FROM UserMaster WHERE email = :email", nativeQuery = true)
