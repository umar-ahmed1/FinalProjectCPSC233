package application;

import java.util.ArrayList;
import java.util.Random;

public class Account {
	private String username;
	private String password;
	private Double balance = 0.0;
	private Integer cardNumber;
	Random random = new Random();
	private ArrayList<Account> subaccounts = new ArrayList<Account>();
	
	
	//Default constructor
	public Account(String usernameInput, String passwordInput, String balanceInput,ArrayList<Account> accounts) throws InvalidBalanceException {
		
		if (!usernameInput.equals("")) username = usernameInput;
		else throw new InvalidBalanceException("Please enter a username");
		//check to see if the username input is equal to any username already created, if so throw an exception
		for (Account account : accounts) {
			if (usernameInput.equals(account.getUsername())) throw new InvalidBalanceException ("Username is already taken");
		}
		
		if (!passwordInput.equals("")) password = passwordInput;
		else throw new InvalidBalanceException("Please enter a password");
		
		//this creates a random number between 45200000 and 45209999 inclusive
		cardNumber = random.nextInt(45209999+1-45200000) + 45200000;
		
		//try to set the balance to the input string, if not then throw an error
		try {
			if (Double.parseDouble(balanceInput) >=0) balance = Double.parseDouble(balanceInput);
			//if the string is greater than max value but its valid throw an exception
			else throw new InvalidBalanceException("Invalid balance. Entered value is less than 0.");
			}
		//if the string is not able to be to converted to a double throw a number format exception that throws an invalid grade exception
		catch (NumberFormatException e){
			//want to figure out why the balance is invalid, so we do some checking here
			String errorMessage = "";
			int counter = 0;
		
			if (balanceInput.equals("")) {
				errorMessage = ("Please enter a balance");
				}
			for (char c : balanceInput.toCharArray()) {
        		if (!Character.isDigit(c)) {
        			//If the character is not a decimal point
        			if (c != '.') {
        				errorMessage = ("Balance should be a number. Don't include char: " + c);
        				}
        			if (c == '.') counter++;
        			if (counter >1) {
        				errorMessage = ("Balance should be a number. Don't include multiple decimal points");
        				}
        			}
        		}
			throw new InvalidBalanceException(errorMessage);
		}
	}
	//constructor that just has username password (used for logging in)
	public Account(String usernameInput, String passwordInput) {
		username = usernameInput;
		password = passwordInput;
	}
	

	//constructor to create a deep copy of another account
	public Account(Account toCopy) {
	this.username = toCopy.getUsername();
	this.password = toCopy.getPassword();
	}
	
	
	//method to compare given username/pass to all username pass
	public Account compareToAllLogins(ArrayList<Account> accounts) {
		for (Account account : accounts) {
			if (this.getUsername().equals(account.getUsername()) && this.getPassword().equals(account.getPassword())) return account;
		}
		return null;
	}
	
	public void withdraw(Double amount) {
		if (amount <= this.getBalance()) balance -= amount;
	}
	
	public void deposit(String amount) throws InvalidBalanceException {
		try {
			if (Double.parseDouble(amount) >=0) balance += Double.parseDouble(amount);
			//if the string is greater than max value but its valid throw an exception
			else throw new InvalidBalanceException("Invalid balance. Entered value is less than 0.");
			}
		//if the string is not able to be to converted to a double throw a number format exception that throws an invalid grade exception
		catch (NumberFormatException e){
			//want to figure out why the balance is invalid, so we do some checking here
			String errorMessage = "";
			int counter = 0;
		
			if (amount.equals("")) {
				errorMessage = ("Please enter a balance");
				}
			for (char c : amount.toCharArray()) {
        		if (!Character.isDigit(c)) {
        			//If the character is not a decimal point
        			if (c != '.') {
        				errorMessage = ("Balance should be a number. Don't include char: " + c);
        				}
        			if (c == '.') counter++;
        			if (counter >1) {
        				errorMessage = ("Balance should be a number. Don't include multiple decimal points");
        				}
        			}
        		}
			throw new InvalidBalanceException(errorMessage);
		}
	}
	
	//public void transfer(String amount, Account transferTo) {
		//if (amount <= this.getBalance()) transferTo.deposit(amount);
	//}
	

	
	//Getter and setter for password 
	public String getPassword() {
		return password;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
	//Getter and setter for username
	public String getUsername() {
		return username;
	}
	public void setUsername(String user) {
		this.username = user;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double bal){
		this.balance = bal;
	}
	public ArrayList<Account> getSubAccounts(){
		return subaccounts;
	}
	
	

	@Override
	public String toString() {
		return "username:" + this.getUsername() + " password:" + this.getPassword() + " card no:" + this.cardNumber + " balance:" + this.balance;
	}
	public String getCardNumber() {
		return Integer.toString(cardNumber);
	}
	


}
