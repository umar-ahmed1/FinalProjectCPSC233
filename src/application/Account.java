package application;

import java.util.ArrayList;

public class Account {
	private double balance;
	private String username;
	private String password;
	
	
	
	//Default constructor
	public Account(String usernameInput, String passwordInput) {
		username = usernameInput;
		password = passwordInput;
	}

	
	//method to compare two accounts
	public boolean toCompare(Account other) {
		if (this.getUsername() == other.getUsername() && this.getPassword() == other.getPassword()) return true;
		else return false;
	}
	
	//method to compare an individual username and password not in an account with an account
	public boolean toCompareIndividual(String username1, String password1) {
		if (this.getUsername().equals(username1) && this.getPassword().equals(password1)) return true;
		else return false;
	} 
	
	//method to compare given username/pass to all username pass
	public boolean compareTwoAccounts(Account account1,ArrayList<Account> accounts) {
		for (Account account : accounts) {
			if (account1.toCompare(account)) return true;
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
	//getter and setter for balance
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "username: " + this.getUsername() + "password: " + this.getPassword();
	}
	


}
