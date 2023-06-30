package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.UserDto;
import com.demo.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	


	@Query("from User u where u.userId=:userId")
	public User getUserbyuserId(@Param(value="userId")Integer userId );

	
	@Query("from User u where u.email=:email and u.password=:password")
	public User findUserByEmailPassword(@Param(value="email") String email, @Param(value="password")String password);


	public User save(UserDto user);


	
}

