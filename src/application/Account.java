package application;

import java.util.ArrayList;
import java.util.Random;

public class Account {
	//instance variables
	private String username;
	private String password;
	private Double balance = 0.0;
	private Integer cardNumber;
	Random random = new Random();
	private ArrayList<Account> subaccounts = new ArrayList<Account>();
	
	
	
	
	/** 
     * Constructor that takes in a username, password, balance, and arraylist. If the account does not have the same username as any one in the arraylist, the account will be created
     * If the balance input is not valid, an exception will be thrown
     * @param usernameInput (username to be registered)
     * @param passwordInput (password to be registered)
     * @param balanceInput (balance to be validated and registered)
     * @param accounts (arraylist of accounts check if the username is taken or not)
     */
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
	
	
	
	/** 
     * Constructor that only takes in a username and password as String inputs, used to confirm if login details are correct since we are only require two parameters for login.
     * @param usernameInput (username to be registered)
     * @param passwordInput (password to be registered)
     */
	public Account(String usernameInput, String passwordInput) {
		username = usernameInput;
		password = passwordInput;
	}
	

	
	/** 
     * Constructor to create a deep copy of another account
     * @param toCopy (account that needs to be copied)
     */
	public Account(Account toCopy) {
	this.username = toCopy.getUsername();
	this.password = toCopy.getPassword();
	this.balance = toCopy.getBalance();
	}
	
	
	
	/** 
     * Method that compares an account to a list of accounts and returns the appropriate account.
     * @param accounts (arraylist of accounts to be checked)
     * @return account (the account that has the same username and password as one of the stored accounts)
     * @return null (if no account matches)
     */
	public Account compareToAllLogins(ArrayList<Account> accounts) {
		for (Account account : accounts) {
			if (this.getUsername().equals(account.getUsername()) && this.getPassword().equals(account.getPassword())) return account;
		}
		return null;
	}
	
	
	
	/** 
     * Method that validates a string input (valid means parsable to double and less than the balance of the account).
     * If valid, the input is subtracted from the balance.
     * Otherwise an appropriate error message is displayed
     * @param amount (to be withdrawn from the account)
     */
	public void withdraw(String amount) throws InvalidBalanceException {
		try {
			if (Double.parseDouble(amount) <= balance && Double.parseDouble(amount) >=0) balance -= Double.parseDouble(amount);
			//if the string is greater than max value but its valid throw an exception
			else throw new InvalidBalanceException("Invalid amount. Entered value is less than 0 or greater than balance.");
			}
		//if the string is not able to be to converted to a double throw a number format exception that throws an invalid grade exception
		catch (NumberFormatException e){
			//want to figure out why the balance is invalid, so we do some checking here
			String errorMessage = "";
			int counter = 0;
		
			if (amount.equals("")) {
				errorMessage = ("Please enter an amount");
				}
			for (char c : amount.toCharArray()) {
	    		if (!Character.isDigit(c)) {
	    			//If the character is not a decimal point
	    			if (c != '.') {
	    				errorMessage = ("Amount should be a number. Don't include char: " + c);
	    				}
	    			if (c == '.') counter++;
	    			if (counter >1) {
	    				errorMessage = ("Amount should be a number. Don't include multiple decimal points");
	    				}
	    			}
	    		}
			throw new InvalidBalanceException(errorMessage);
		}
	}
	
	
	

	/** 
     * Method that validates a string input and converts it to a double, then it adds the input to the balance if the input is valid.
     * Otherwise an appropriate error message is displayed
     * @param amount (to be deposited into the account)
     */
	public void deposit(String amount) throws InvalidBalanceException {
		try {
			if (Double.parseDouble(amount) >=0) balance += Double.parseDouble(amount);
			//if the string is greater than max value but its valid throw an exception
			else throw new InvalidBalanceException("Invalid amount. Entered value is less than 0.");
			}
		//if the string is not able to be to converted to a double throw a number format exception that throws an invalid grade exception
		catch (NumberFormatException e){
			//want to figure out why the balance is invalid, so we do some checking here
			String errorMessage = "";
			int counter = 0;
		
			if (amount.equals("")) {
				errorMessage = ("Please enter an amount");
				}
			for (char c : amount.toCharArray()) {
        		if (!Character.isDigit(c)) {
        			//If the character is not a decimal point
        			if (c != '.') {
        				errorMessage = ("Amount should be a number. Don't include char: " + c);
        				}
        			if (c == '.') counter++;
        			if (counter >1) {
        				errorMessage = ("Amount should be a number. Don't include multiple decimal points");
        				}
        			}
        		}
			throw new InvalidBalanceException(errorMessage);
		}
	}
	
	
	
	//public void transfer(String amount, Account transferTo) {
		//if (amount <= this.getBalance()) transferTo.deposit(amount);
	//}
	

	
	/** 
     * Method that gets the encapsulated password
     * @return password
     */
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * Takes a String as input and sets the accounts password to the string.
	 * @param pass (String that is inputted)
	 */
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	
	/** 
     * Method that gets the encapsulated username
     * @return username
     */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Takes a String as input and sets the accounts username to the string.
	 * @param user (String that is inputted)
	 */
	public void setUsername(String user) {
		this.username = user;
	}
	
	/** 
     * Method that gets the encapsulated balance
     * @return balance
     */
	public Double getBalance() {
		return balance;
	}
	
	
	/**
	 * Takes a double as input and sets the accounts balance to the double.
	 * @param bal (Double that is inputted)
	 */
	public void setBalance(Double bal){
		this.balance = bal;
	}
	
	
	
	public ArrayList<Account> getSubAccounts(){
		return subaccounts;
	}
	
	
	/** 
     * Method that gets the encapsulated cardNumber (randomly generated number between 4520-0000 and 45209999)
     * @return cardNumber
     */
	public String getCardNumber() {
		return Integer.toString(cardNumber);
	}
	
	
	/**
	 * Method that displays all information relating to a bank account (username,password,card number, balance)
	 */
	@Override
	public String toString() {
		return "username:" + this.getUsername() + " password:" + this.getPassword() + " card no:" + this.cardNumber + " balance:" + this.balance;
	}
	
	
	


}
