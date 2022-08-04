package application;

public class Account {
	private double balance;
	private String username;
	private String password;
	
	
	//Default constructor
	public Account(double balanceInput,String usernameInput, String passwordInput) {
		setBalance(balanceInput);
		username = usernameInput;
		setPassword(passwordInput);
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
	
	
	


}
