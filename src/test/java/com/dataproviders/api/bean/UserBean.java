package com.dataproviders.api.bean;

import com.poiji.annotation.ExcelCellName;

public class UserBean {
	/*
	 * In pojo for csv mapping keeping the instance variable names same as column names
	 * if they are different then mapping wont happen
	 * Even if it happens that instance name is different from column names in csv file
	 * then use annotation of opencsv library for each instance name
	 * e.g.
	 *  @CsvBindByName(column ="username")
	 *  private String x;
	 *  
	 *  
	 *  Bean vs POJO -> Bean is also a POJO but here no-argument constructor is compulsory whereas in POJO it is optional
	 *  We don't need parameterized constructor in Bean but we may need in POJO
	 *  
	 *  
	 *  @ExcelCellName("emails") -> this annotation for separate library -> POIJI  nothing to do with csv bean
	 *  
	 *  We can add multiple annotation on same instance variable
	 *  
	 *  @ExcelCellName("username")
	 *  @CsvBindByName(column ="username")
	 *  private String x;
	 */
	
	@ExcelCellName("username")
	private String username;
	@ExcelCellName("password")
	private String password;
	public UserBean() {
		// TODO Auto-generated constructor stub
	}

//	public UserPOJO(String username, String password) {
//		super();
//		this.username = username;
//		this.password = password;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
