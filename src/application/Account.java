package application;

import java.util.ArrayList;

public class Account {
	private String username;
	private String password;
	
	
	
	//Default constructor
	public Account(String usernameInput, String passwordInput) {
		username = usernameInput;
		password = passwordInput;
	}

	//constructor to copy another account
	public Account(Account toCopy) {
	this.username = toCopy.getUsername();
	this.password = toCopy.getPassword();
	}
	
	//method to compare two accounts
	public boolean toCompare(Account other) {
		if (this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword())) return true;
		else return false;
	}
	
	//method to compare an individual username and password not in an account with an account
	public boolean toCompareIndividual(String username1, String password1) {
		if (this.getUsername().equals(username1) && this.getPassword().equals(password1)) return true;
		else return false;
	} 
	
	//method to compare given username/pass to all username pass
	public boolean compareToStoredAccounts(ArrayList<Account> accounts) {
		for (Account account : accounts) {
			if (this.toCompare(account)) return true;
		}
		return false;
	}

	
	
	
	
	
	
	//Getter and setter for password 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//Getter and setter for username
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "username: " + this.getUsername() + "password: " + this.getPassword();
	}
	


}
