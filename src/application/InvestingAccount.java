package application;

import java.text.DecimalFormat;
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
	
	
	
	/**
	 * Method that validates an input amount and then multiplies it by a factor between 0 and investment ratio, then it adds that new amount to the balance, creating a 
	 * "random" investment.
	 * @param amount (amount to be invested)
	 * @throws InvalidBalanceException
	 */	
	public void invest(String amount) throws InvalidBalanceException {
		try {
			//use random from super class to generate a random number between 0-1 and multiply it by 2 to get a random number between 0 and 2;
			Double investmentRatio = random.nextDouble() * 2.0;
			//multiply amount by that ratio and subtract amount to get the net change and then add it to balance
			if (Double.parseDouble(amount) >=0 && Double.parseDouble(amount) <= balance) {
				balance += (Double.parseDouble(amount) * investmentRatio) - Double.parseDouble(amount);
				DecimalFormat df = new DecimalFormat("##.##");
				balance = Double.parseDouble(df.format(balance));
			}
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
	
	
	
}
