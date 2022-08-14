package application;

import java.util.ArrayList;
import java.util.Random;

public class Account {
	private String username;
	private String password;
	private Double balance;
	private Integer cardNumber;
	Random random = new Random();
	
	
	//Default constructor
	public Account(String usernameInput, String passwordInput, String balanceInput) throws InvalidBalanceException {
		username = usernameInput;
		password = passwordInput;
		//this creates a random number between 45200000 and 45209999 inclusive
		cardNumber = random.nextInt(45209999+1-45200000) + 45200000;
		
		//try to set the balance to the input string, if not then throw an error
		try {
			if (Double.parseDouble(balanceInput) >=0) balance = Double.parseDouble(balanceInput);
			//if the string is greater than max value but its valid throw an exception
			else throw new InvalidBalanceException("Invalid balance. Entered value is less than 0.");
		}
		//if the string is not able to be to converted to a double throw a numberformatexception that throws an invalid grade exception
		catch (NumberFormatException e){
			//want to figure out why the balance is invalid, so we do some checking here
			String errorMessage = "";
			boolean validBal = true;
			int counter = 0;
		
			for (char c : balanceInput.toCharArray()) {
        		if (!Character.isDigit(c)) {
        			//If the character is not a decimal point
        			if (c != '.') {
        				errorMessage = ("Balance should be a number. Don't include char: " + c);
        				validBal = false;
        			}
        			if (c == '.') counter++;
        			if (counter >1) {
        				errorMessage = ("Balance should be a number. Don't include multiple decimal points");
        				validBal = false;
        			}
        		}
        	}
			if (validBal) {
		
				if (Double.parseDouble(balanceInput) <= 0) {
					errorMessage = ("Entered grade lower than 0. Grade should be a number between 0-10");
					
				}
			}
			throw new InvalidBalanceException(errorMessage);
		
		
		}
	}
	//constructor that just has username password
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
	public boolean compareLogins(Account other) {
		if (this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword())) return true;
		else return false;
	}
	
	//method to compare given username/pass to all username pass
	public boolean compareToStoredAccounts(ArrayList<Account> accounts) {
		for (Account account : accounts) {
			if (this.compareLogins(account)) return true;
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
		return "username:" + this.getUsername() + " password:" + this.getPassword() + " card no:" + this.cardNumber + " balance:" + this.balance;
	}
	


}
