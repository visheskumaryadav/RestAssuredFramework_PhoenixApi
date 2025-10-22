package com.demo.csv;

public class UserPOJO {
	/*
	 * In pojo for csv mapping keeping the instance variable names same as column names
	 * if they are different then mapping wont happen
	 * Even if it happens that instance name is different from column names in csv file
	 * then use annotation of opencsv library for each instance name
	 * e.g.
	 *  @CsvBindByName(column ="username")
	 *  private String x;
	 */
	private String username;
	private String password;
	public UserPOJO() {
		// TODO Auto-generated constructor stub
	}

	public UserPOJO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
