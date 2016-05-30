package com.techfocus.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Account {

	 @Id
	  private String id;
	  private String username;
	  private String password;
	  private String[] roles;
	  
	  @DBRef
	  private Account admin;
	  
	  public Account(){}
	  
	  public Account(String username, String password, String[] roles) {
	    this.username = username;
	    this.password = password;
	    this.roles= roles;
	  }
	  public Account(String username, String password, String[] roles,Account admin) {
		    this.username = username;
		    this.password = password;
		    this.roles= roles;
		    this.admin = admin;
		  }
	  
	  public String getId() {
	    return id;
	  }
	  public void setId(String id) {
	    this.id = id;
	  }
	  public String getUsername() {
	    return username;
	  }
	  public void setUsername(String username) {
	    this.username = username;
	  }
	  public String getPassword() {
	    return password;
	  }
	  public void setPassword(String password) {
	    this.password = password;
	  }

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Account getAdmin() {
		return admin;
	}

	public void setAdmin(Account admin) {
		this.admin = admin;
	}
	  
	
}
