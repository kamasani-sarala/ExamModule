package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private long mobileNo;
  
  
  public User() {


}


public User(String firstName, String lastName, String email, String password, long mobileNo) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.mobileNo = mobileNo;
}


public int getUserId() {
	return userId;
}


public void setUserId(int userId) {
	this.userId = userId;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public long getMobileNo() {
	return mobileNo;
}


public void setMobileNo(long mobileNo) {
	this.mobileNo = mobileNo;
}

}
