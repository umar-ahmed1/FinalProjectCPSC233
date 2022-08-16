package application;

import java.util.ArrayList;

public class InvestingAccount extends Account {

	
	
	/** 
     * Constructor that takes in a username, password, balance, and arraylist. If the account does not have the same username as any one in the arraylist, the account will be created
     * If the balance input is not valid, an exception will be thrown
     * @param usernameInput (username to be registered)
     * @param passwordInput (password to be registered)
     * @param balanceInput (balance to be validated and registered)
     * @param accounts (arraylist of accounts check if the username is taken or not)
     */
	public InvestingAccount(String usernameInput, String passwordInput, String balanceInput,ArrayList<Account> accounts) throws InvalidBalanceException {
		//call constructor of super class
		super(usernameInput,passwordInput,balanceInput,accounts);
	}
	
	
	
	
}
