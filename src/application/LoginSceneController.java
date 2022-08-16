package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginSceneController{
	//instance variables
	Stage applicationStage;
	public Account account;
	public String errorMessage;
	public ArrayList<Account> accountsList = new ArrayList<Account>();
	public Account loggedInAccount;
	
	@FXML
	private TextField usernameLoginField;
	@FXML
	private TextField passwordLoginField;
	@FXML
	private Label registrationErrorMessage;
	@FXML
	private Label loginErrorLabel;
	
	
	
	/** 
     * Method that is called whenever the login button is pressed. This method will compare the username and password in the textfields of the login screen to all accounts stored. 
     * If the comparison returns true, the method will switch to the main bank scene. Else, it will return an appropriate error message.
     * @param event (login button pressed in the login scene)
     */
	@FXML
	public void loginButtonPressed(ActionEvent event) {
		loginErrorLabel.setText("");
		//create an account based off the username and password fields in login, (dont assign it a balance as we are only using it to confirm login details)
		Account temporaryloginAccount = new Account(usernameLoginField.getText(),passwordLoginField.getText());
		
			//if nothing is null (no errors) and there is atleast one account created
			if (temporaryloginAccount != null && accountsList.size()!= 0 && accountsList != null) {
				//compare the account from the login fields to all accounts registered, if it has a username password equal to any of the accounts in the list
				//the account in the list will be returned, and we can check to see that by asking if loggedInAccount is null or not
				loggedInAccount = temporaryloginAccount.compareToAllLogins(accountsList);
				//if it is not null set the scene to the bank scene
				if (loggedInAccount != null) { 
					mainBankSceneCreator();
				}
				//else display appropriate error messages
				else {
					loginErrorLabel.setText("Details do not match with registered account");
				}
			}
			//if no account exists ask user to create account via an error message
			else {
				loginErrorLabel.setText("Create an account before logging in");
			}	
	}
	
	
	
	
	/** 
     * Method that creates and changes the scene to the register scene when the register button is pressed in the login scene.
     *  If the done button within this new register scene is pressed, the createAccount method will be called.
     * @param swapToRegisterLayout (pressing the register button in the login screen)
     */
	@FXML
	public void registerButtonPressed(ActionEvent swapToRegisterLayout) throws IOException {	
		Scene loginScene = applicationStage.getScene();
		
		//create the labels,textfields, and the button
		VBox root = new VBox(5);
		Label userLabel = new Label("Username");
		TextField userField = new TextField();	
		TextField passField = new TextField();
		Label passLabel = new Label("Password");
		TextField balField = new TextField();
		Label balLabel = new Label("Initial deposit");
		Label errorLabel = new Label("");
		errorLabel.setTextFill(Color.RED);
		Label typeLabel = new Label("Type of Account");
		Label typeLabel2 = new Label("(\"Investment\") or (\"Debit\")");
		TextField typeField = new TextField();
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> createAccount(loginScene, userField.getText(),passField.getText(),balField.getText(),errorLabel,typeField.getText()));
    	
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(userLabel, new Insets(10,100,0,100));
		VBox.setMargin(userField, new Insets(0,100,0,100));	
		VBox.setMargin(passLabel, new Insets(10,100,0,100));
		VBox.setMargin(passField, new Insets(0,100,0,100));
		VBox.setMargin(balLabel, new Insets(10,100,0,100));
		VBox.setMargin(balField, new Insets(0,100,0,100));
		VBox.setMargin(typeLabel, new Insets(10,100,0,100));
		VBox.setMargin(typeLabel2, new Insets(0,100,0,100));
		VBox.setMargin(typeField, new Insets(0,100,0,100));
		VBox.setMargin(errorLabel, new Insets(10,10,0,25));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		//add elements to root and create and set the scene to the new scene
		root.getChildren().addAll(userLabel,userField,passLabel,passField,balLabel,balField,typeLabel,typeLabel2,typeField,errorLabel,doneButton);
		Scene resetScene = new Scene(root,400,400);	
		applicationStage.setScene(resetScene);	
	}
	
	
	
	
	/** 
     * Method that is called when the done button is pressed in the resetPasswordScene. Compares the two passwords inputted, if they are equal, changes the account password to the new password.
     * Then switches the scene back to the login scene.
     * @param scene (the previous scene to return to when the method is done) 
     * @param newField (String obtained from the first textfield)
     * @param newConfirmField (String obtained from the second textfield)
     * @param errorLabel (Label that an error message can be written to)
     * @param userToResetPasswordFor (the username of the account that needs its password reset, since there are multiple accounts)
     */
	public void resetPassword(Scene scene, String newField, String newConfirmField, Label errorLabel, String userToResetPasswordFor) {
		errorLabel.setText("");
		//if the two fields match, then change the password
		if (newField.equals(newConfirmField)){
			//loop through the users 
			for (Account acc : accountsList) {
				//if the username given is equal to any registerd username, reset the password for that username
				if (userToResetPasswordFor.equals(acc.getUsername())) {
						acc.setPassword(newField);
						//complete the transfer and exit out
						applicationStage.setScene(scene);
						break;
					}
					//else the loop is complete and no user was found
					else {
						errorLabel.setText("User not found");
					}
			}
		}
		//if any of the fields are blank then set an appropriate error message
		else if (newField.equals("") || newConfirmField.equals("")) errorLabel.setText("Error. One or more of the fields are blank");
		//if the two fields do not match, then set an appropriate error message
		else if (!newField.equals(newConfirmField)) errorLabel.setText("Passwords do not match. Please try again");
	}
	
	
	
	/** 
     * Method that is called when the done button in the register scene is pressed. Tries to create an account if all parameters are valid (balance must be a string >= 0 and parsable to a double)
     * If unable to create account, displays appropriate error message thrown by the account constructor.
     * @param scene (the previous scene to return to when the method is done) 
     * @param user (the username to be registered)
     * @param pass (the password to be registered) 
     * @param bal (the balance to register) 
     * @param errorLabel (to show error messages)
     */
	public void createAccount(Scene scene, String user, String pass, String bal, Label errorLabel,String accountType) {
		errorLabel.setText("");
		//Try to create a new account with the provided details
		try {
			if (accountType.equals("Investment")){
			account = new InvestingAccount(user,pass,bal,accountsList);
			accountsList.add(account);
			applicationStage.setScene(scene);	
			}
			if (accountType.equals("Debit")){
				account = new Account(user,pass,bal,accountsList);
				accountsList.add(account);
				applicationStage.setScene(scene);	
				}
			else {
				errorLabel.setText("Account type invalid. Try again");
			}
			
		//If an error occurs, the account class will determine why the error has occured and the account will not be created
		//Instead, an error message will be displayed which the account constructor has thrown
		} catch (InvalidBalanceException ige) {
			errorLabel.setText(ige.getMessage());	
		}
			
	}

	
	
	
	/** 
     * Method that creates and changes the scene to the reset password scene when the forgot password button is pressed in the login scene.
     *  If the done button within this new register scene is pressed, the resetField method will be called.
     * @param resetButtonPressed (pressing the forgot password button in the login screen)
     */
	@FXML
	public void resetPasswordSceneCreator(ActionEvent resetButtonPressed) throws IOException{
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox(5);
		Label userLabel = new Label("Username to reset password for");
		TextField userField = new TextField();	
		Label topLabel = new Label("New Password");
		TextField topField = new TextField();	
		TextField botField = new TextField();
		Label botLabel = new Label("Confirm Password");
		Label forgetErrorLabel = new Label("");
		forgetErrorLabel.setTextFill(Color.RED);
		//create the button and set it to reset the password
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> resetPassword(loginScene,topField.getText(),botField.getText(),forgetErrorLabel,userField.getText()));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(userLabel, new Insets(2,100,0,100));
		VBox.setMargin(userField, new Insets(0,100,0,100));	
		VBox.setMargin(topLabel, new Insets(2,100,0,100));
		VBox.setMargin(topField, new Insets(0,100,0,100));	
		VBox.setMargin(botLabel, new Insets(2,100,0,100));
		VBox.setMargin(botField, new Insets(0,100,0,100));
		VBox.setMargin(forgetErrorLabel, new Insets(10,25,0,85));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		//add all the elements to root, create the scene, and as long as there is atleast one account registered, set the scene to the new scene, otherwise display an error
		root.getChildren().addAll(userLabel,userField,topLabel,topField,botLabel,botField,doneButton,forgetErrorLabel);
		Scene resetScene = new Scene(root,400,250);
		if (accountsList.size() != 0) applicationStage.setScene(resetScene);
		else loginErrorLabel.setText("Error, no account to reset password for");	
	}
	
	
	
	
	/** 
     * Method that creates and changes the scene to the reset username scene when the forgot password button is pressed in the login scene.
     * This method will display all registered usernames to the user.
     * If the done button within this new scene is pressed, return to the login scene.
     * @param resetButtonPressed (pressing the forgot username button in the login screen)
     */
	@FXML
	public void resetUsernameSceneCreator(ActionEvent resetButtonPressed) throws IOException{
		Scene loginScene = applicationStage.getScene();
		
    	//create the labels,textfields,buttons
    	VBox allRows = new VBox();
    	Label topLabel = new Label("All registered usernames:");
    	allRows.getChildren().add(topLabel);
    	VBox.setMargin(topLabel, new Insets(10,50,0,50));
    	Button doneButton = new Button("Done");
    	//if done button is pressed, return to the previous scene (loginScene)
    	doneButton.setOnAction(doneEvent -> applicationStage.setScene(loginScene));
		
    	//create a list to store all the usernames
    	int rowCounter = 0;
    	while (rowCounter < accountsList.size()) {
    		HBox userRow = new HBox();
           	Label userLabel = new Label("User #" + (rowCounter+1) + ": " + accountsList.get(rowCounter).getUsername());  
           	HBox.setMargin(userLabel, new Insets(0,50,0,50));
        	userRow.getChildren().addAll(userLabel);
        	allRows.getChildren().addAll(userRow);
        	rowCounter++;
    	}
    	allRows.getChildren().add(doneButton);
    	VBox.setMargin(doneButton, new Insets(10,50,0,50));
    	
    	//create the scene, length of scene is based on how many accounts are registered, set the scene to the new scene if atleast 1 account is registered
    	//otherwise dislay an error message.
		Scene usersListScene = new Scene(allRows,250,70 + accountsList.size() * 30);
		if (accountsList.size() !=0) applicationStage.setScene(usersListScene);
		else loginErrorLabel.setText("Error, no usernames registered");	
	}
	
	
	
	/** 
     * Method called by loginButtonPressed that creates and changes the scene to the main bank scene. 
     * This method will display the bank card, balance, contacts list, transaction history, and the ability to deposit withdraw,etransfer, and if its an investment account then invest button.
     */
	public void mainBankSceneCreator() {
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		Label forgetErrorLabel = new Label("");
		Label welcomeLabel = new Label("Welcome To Your Account: " + loggedInAccount.getUsername());
		Label loggedInBalanceLabel = new Label("$" + loggedInAccount.getBalance());
		//lists for transactions and transfers
		VBox transactionList = new VBox();
		VBox transferList = new VBox();
		VBox cardStacks = new VBox();
		HBox cardAndLists = new HBox();
		
		//rectangle and their stack panes. rectangle creation is (x,y,width,height)
		StackPane rectangleStack = new StackPane();
		Label cardType = new Label("Chequing");
		StackPane rectangleStack2 = new StackPane();
		Label cardNumber = new Label(loggedInAccount.getCardNumber());
		Rectangle rectangleCard = new Rectangle(100,100,300,100);
		rectangleCard.setFill(Color.rgb(0,151,230));
		Rectangle rectangleCard2 = new Rectangle(100,250,300,50);
		rectangleCard2.setFill(Color.rgb(151,230,255));
		rectangleStack.getChildren().addAll(rectangleCard,cardType,loggedInBalanceLabel);
		rectangleStack2.getChildren().addAll(rectangleCard2,cardNumber);
		cardStacks.getChildren().addAll(rectangleStack,rectangleStack2);
		
		//Buttons
		HBox buttons = new HBox(10);
		Button depositButton = new Button("Deposit");
		Button withdrawButton = new Button("Withdraw");
		Button transferButton = new Button("eTransfer");
		Button investButton = new Button("Invest");
		//only if its an instance of InvestingAccount should it be visible
		if (loggedInAccount instanceof InvestingAccount) investButton.setVisible(true);
		else investButton.setVisible(false);
		Button logoutButton = new Button ("Logout");
		//create the deposit scene when deposit button is pressed and withdraw scene when withdraw button is pressed
		//pass both functions loggedInBalanceLabel so the balance on the card can be updated in the main scene
		depositButton.setOnAction(doneEvent -> depositSceneCreator(loggedInBalanceLabel,transactionList));		
		withdrawButton.setOnAction(doneEvent -> withdrawSceneCreator(loggedInBalanceLabel,transactionList));
		transferButton.setOnAction(doneEvent -> transferSceneCreator(loggedInBalanceLabel,transactionList));
		investButton.setOnAction(doneEvent-> investSceneCreator(loggedInBalanceLabel,transactionList));
		logoutButton.setOnAction(doneEvent -> applicationStage.setScene(loginScene));
		buttons.getChildren().addAll(depositButton,withdrawButton,transferButton,investButton);
		cardStacks.getChildren().addAll(buttons,logoutButton);
		
		//Transfer List
		Label contactsTitle = new Label ("E-Transfer Contacts");
		transferList.getChildren().add(contactsTitle);
		
		//create a list to store all the accounts usernames (e transfer contacts)
    	int rowCounter = 0;
    	while (rowCounter < accountsList.size()) {
    		HBox userRow = new HBox();
    		if (!accountsList.get(rowCounter).getUsername().equals(loggedInAccount.getUsername())) {
    			Label userLabel = new Label("User #" + (rowCounter+1) + ": " + accountsList.get(rowCounter).getUsername());  
    			userRow.getChildren().addAll(userLabel);
    			transferList.getChildren().addAll(userRow);
    			rowCounter++;
    		}
    		else rowCounter++;
    	}
		
		//Transactions List
		Label transactionTitle = new Label ("Latest Transactions");
		transactionList.getChildren().addAll(transactionTitle);
		
		
		//add the two lists to an hbox
		HBox listsHBox = new HBox(100);
		listsHBox.getChildren().addAll(transferList,transactionList);
		cardAndLists.getChildren().addAll(cardStacks,listsHBox);
		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(forgetErrorLabel, new Insets(10,25,0,85));
		VBox.setMargin(rectangleStack2, new Insets(0,0,0,50));
		VBox.setMargin(rectangleStack,new Insets(25,0,0,50));
		VBox.setMargin(welcomeLabel,new Insets(0,0,0,0));
		VBox.setMargin(buttons,new Insets(0,0,0,50));
		HBox.setMargin(listsHBox,new Insets(25,0,0,50));
		StackPane.setAlignment(cardType, Pos.TOP_LEFT);
		StackPane.setAlignment(rectangleCard, Pos.TOP_LEFT);
		StackPane.setAlignment(cardNumber, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(rectangleCard2, Pos.TOP_LEFT);
		StackPane.setAlignment(loggedInBalanceLabel, Pos.CENTER_LEFT);
		VBox.setMargin(listsHBox,new Insets(50,0,0,50));
		VBox.setMargin(logoutButton, new Insets(75,0,0,150));
		//setting the text sizes
		welcomeLabel.setFont(new Font("Arial",30));
		cardNumber.setFont(new Font("Arial", 15));
		contactsTitle.setFont(new Font("Arial", 15));
		transactionTitle.setFont(new Font("Arial", 15));
		loggedInBalanceLabel.setFont(new Font("Arial", 25));
		cardType.setFont(new Font("Arial",15));

		//add all elements to the scene and set the application stage scene to this new scene
		root.getChildren().addAll(welcomeLabel,cardAndLists);	
		Scene bankScene = new Scene(root,800,400);
		applicationStage.setScene(bankScene);
	}

	
	
	
	
	/** 
     * Method called by eTransfer button in main bank scene that creates and changes the scene to the transfer. 
     * This method will ask for a username and amount in textfields and upon the done button being pressed will call the eTransfer method.
     * @param loggedInBalanceLabel (label that shows balance that needs to be updated)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	public void transferSceneCreator(Label loggedInBalanceLabel,VBox transactionList) {
		Scene mainScene = applicationStage.getScene();
		
		//create the labels,textfields, and button
		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		Label userToTransferToLabel = new Label("User To Transfer To");
		TextField userToTransferToField = new TextField();	
		Label titleLabel = new Label("eTransfer");
		Label amountLabel = new Label("Amount");
		TextField amountField = new TextField();
		Label transferErrorLabel = new Label("");
		transferErrorLabel.setTextFill(Color.RED);
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> eTransfer(loggedInBalanceLabel,userToTransferToField.getText(),transferErrorLabel,mainScene,amountField.getText(),transactionList));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(userToTransferToLabel, new Insets(2,0,0,0));
		VBox.setMargin(userToTransferToField, new Insets(0,150,0,150));	
		VBox.setMargin(amountField, new Insets(0,150,0,150));	
		VBox.setMargin(transferErrorLabel, new Insets(10,0,0,0));
		VBox.setMargin(doneButton, new Insets(10,0,0,0));
		titleLabel.setFont(new Font("Arial", 20));
		
		//add all elements to the scene and set the application stage scene to this new scene
		root.getChildren().addAll(titleLabel,userToTransferToLabel,userToTransferToField,amountLabel,amountField,doneButton,transferErrorLabel);
		Scene transferScene = new Scene(root,450,250);
		applicationStage.setScene(transferScene);	
	}

	
	/** 
     * Method that is called when the done button in the etransfer scene is pressed. 
     * Tries to transfer the amount passed to userToTransferTo if all given parameters are valid.
     * If unable to transfer, displays appropriate error message thrown by the account constructor.
     * @param loggedInBalanceLabel (label to be updated to show new balance)
     * @param userToTransferTo (the username of the account that will recieve the transfer
     * @param errorLabel (to show error messages)
     * @param mainScene (the previous scene to return to when the method is done) 
     * @param amount (string of amount to be deposited into account)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	public void eTransfer(Label loggedInBalanceLabel, String userToTransferTo, Label errorLabel, Scene mainScene,String amount,VBox transactionList) {
		errorLabel.setText("");
		try {
			//loop through the users 
			for (Account acc : accountsList) {
				//if you put in your own username then display an error message because you cant transfer to yourself
				if (userToTransferTo.equals(loggedInAccount.getUsername())) {
					errorLabel.setText("Cant eTransfer to yourself");
					break;
				}
				//if the username given is not the logged in username and matches some username from the accounts list then transfer
				if (userToTransferTo.equals(acc.getUsername()) && !userToTransferTo.equals(loggedInAccount.getUsername())) {
						//complete the transfer and exit out
						loggedInAccount.transfer(amount,acc);
						transactionList.getChildren().addAll(new Label ("Sent $" + amount + " to " + userToTransferTo));
						applicationStage.setScene(mainScene);
						loggedInBalanceLabel.setText("$" + Double.toString(loggedInAccount.getBalance()));
						break;
					}
					//else the loop is complete and no user was found
					else {
						errorLabel.setText("User not found");
					}
				}
		
		//if the amount is invalid string display error
		} catch (InvalidBalanceException ige) {
			errorLabel.setText(ige.getMessage());
		}
	}

	

	/** 
     * Method that creates and changes the scene to the deposit scene when the deposit button is pressed in the main bank scene.
     *  If the done button within this new scene is pressed, the depositOrWithdraw method will be called.
     * @param loggedInBalanceLabel (the label that displays the balance so that it can be updated when the deposit is made)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	private void depositSceneCreator(Label loggedInBalanceLabel, VBox transactionList) {
		Scene mainScene = applicationStage.getScene();
		
		//create the labels,textfields, and button
		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		Label amountLabel = new Label("Amount");
		TextField amountField = new TextField();	
		Label depositLabel = new Label("Deposit");
		Label depositErrorLabel = new Label("");
		depositErrorLabel.setTextFill(Color.RED);
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> depositOrWithdraw(amountField.getText(),depositErrorLabel,mainScene,loggedInBalanceLabel,"Deposit",transactionList));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(amountLabel, new Insets(2,0,0,0));
		VBox.setMargin(amountField, new Insets(0,150,0,150));	
		VBox.setMargin(depositErrorLabel, new Insets(10,0,0,0));
		VBox.setMargin(doneButton, new Insets(10,0,0,0));
		depositLabel.setFont(new Font("Arial", 20));
		
		//add all elements to the scene and set the application stage scene to this new scene
		root.getChildren().addAll(depositLabel,amountLabel,amountField,doneButton,depositErrorLabel);
		Scene depositScene = new Scene(root,450,175);
		applicationStage.setScene(depositScene);
	}

	
	
	/** 
     * Method that creates and changes the scene to the withdraw scene when the withdraw button is pressed in the main bank scene.
     *  If the done button within this new scene is pressed, the depositOrWithdraw method will be called.
     * @param loggedInBalanceLabel (the label that displays the balance so that it can be updated when the withdrawal is made)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	private void withdrawSceneCreator(Label loggedInBalanceLabel,VBox transactionList) {
		Scene mainScene = applicationStage.getScene();
		
		//create the labels,textfields, and button
		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		Label withdrawLabel = new Label("Withdraw");
		Label amountLabel = new Label("Amount");
		TextField amountField = new TextField();	
		Label withdrawErrorLabel = new Label("");
		withdrawErrorLabel.setTextFill(Color.RED);
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> depositOrWithdraw(amountField.getText(),withdrawErrorLabel,mainScene,loggedInBalanceLabel,"Withdraw",transactionList));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(amountLabel, new Insets(2,0,0,0));
		VBox.setMargin(amountField, new Insets(0,150,0,150));	
		VBox.setMargin(withdrawErrorLabel, new Insets(10,0,0,0));
		VBox.setMargin(doneButton, new Insets(10,0,0,0));
		withdrawLabel.setFont(new Font("Arial", 20));
		
		//add all elements to the scene and set the application stage scene to this new scene
		root.getChildren().addAll(withdrawLabel,amountLabel,amountField,doneButton,withdrawErrorLabel);
		Scene withdrawScene = new Scene(root,450,175);
		applicationStage.setScene(withdrawScene);
	}
	
	

	/** 
     * Method that is called when the done button in the deposit or withdraw scene is pressed. 
     * Tries to deposit/withdraw the amount passed if all parameters are valid (amount must be a string >= 0 and parsable to a double)
     * If unable to deposit/withdraw, displays appropriate error message thrown by the account constructor.
     * @param amount (string of amount to be deposited into account)
     * @param errorLabel (to show error messages)
     * @param scene (the previous scene to return to when the method is done) 
     * @param loggedInBalanceLabel (label to be updated to show new balance)
     * @param identifier (used to determine if depositing or withdrawing) 
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	private void depositOrWithdraw(String amount,Label errorLabel,Scene scene,Label loggedInBalanceLabel,String identifier,VBox transactionList) {
		errorLabel.setText("");
		//Try to create a new account with the provided details
		try {
			if (identifier.equals("Deposit")) {
				loggedInAccount.deposit(amount);
				transactionList.getChildren().addAll(new Label ("Deposited $" + amount));
			}
			if (identifier.equals("Withdraw")) {
				loggedInAccount.withdraw(amount);
				transactionList.getChildren().addAll(new Label ("Withdrawn $" + amount));
			}
			applicationStage.setScene(scene);	
			loggedInBalanceLabel.setText("$" + Double.toString(loggedInAccount.getBalance()));
			//If an error occurs, the account class will determine why the error has occurred and the account will not be created
			//Instead, an error message will be displayed.
		} catch (InvalidBalanceException ige) {
			errorLabel.setText(ige.getMessage());	
		}

	}
	
	
	
	/** 
     * Method called by invest button in main bank scene that creates and changes the scene to the invest scene if the account is investment account. 
     * This method will ask for a amount in textfields and upon the done button being pressed will call the investCalculator method.
     * @param loggedInBalanceLabel (label that shows balance that needs to be updated)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	public void investSceneCreator(Label loggedInBalanceLabel,VBox transactionList) {
		Scene mainScene = applicationStage.getScene();
		
		//create the labels,textfields, and button
		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		Label titleLabel = new Label("Investment Center");
		Label amountLabel = new Label("Amount");
		Label warningLabel = new Label("Investments work by taking a random number between 0-2");
		warningLabel.setTextFill(Color.RED);
		Label warningLabel2 = new Label("and multiplying your amount by that number. Proceed with caution");
		warningLabel2.setTextFill(Color.RED);
		TextField amountField = new TextField();
		Label transferErrorLabel = new Label("");
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> investCalculator(amountField.getText(),transferErrorLabel,mainScene,loggedInBalanceLabel,transactionList));
    	
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(amountField, new Insets(10,150,0,150));
		VBox.setMargin(amountLabel, new Insets(15,0,0,0));
		VBox.setMargin(transferErrorLabel, new Insets(10,0,0,0));
		VBox.setMargin(warningLabel, new Insets(10,0,0,0));
		VBox.setMargin(doneButton, new Insets(10,0,0,0));
		titleLabel.setFont(new Font("Arial", 20));
		
		//add all elements to the scene and set the application stage scene to this new scene
		root.getChildren().addAll(titleLabel,warningLabel,warningLabel2,amountLabel,amountField,doneButton,transferErrorLabel);
		Scene transferScene = new Scene(root,450,275);
		applicationStage.setScene(transferScene);	
	}
	
	/** 
     * Method that is called when the done button is pressed in the investment scene
     * Tries to call the InvestingAccount invest method and updates the account balance accordingly.
     * If unable, displays appropriate error message thrown by the account constructor.
     * @param amount (string of amount to be invested into account)
     * @param errorLabel (to show error messages)
     * @param scene (the previous scene to return to when the method is done) 
     * @param loggedInBalanceLabel (label to be updated to show new balance)
     * @param transactionList (VBox that displays all current transactions on the screen)
     */
	private void investCalculator(String amount,Label errorLabel,Scene scene,Label loggedInBalanceLabel,VBox transactionList) {
		errorLabel.setText("");
		//Try to create a new account with the provided details
		try {
			//we cast here because we are 100% sure that this button will only be clicked from an investing account
			((InvestingAccount) loggedInAccount).invest(amount);
			applicationStage.setScene(scene);
			loggedInBalanceLabel.setText("$" + Double.toString(loggedInAccount.getBalance()));
			transactionList.getChildren().addAll(new Label ("Invested $" + amount));
			//If an error occurs, the account class will determine why the error has occurred and the account will not be created
			//Instead, an error message will be displayed.
		} catch (InvalidBalanceException ige) {
			errorLabel.setText(ige.getMessage());	
		}

	}
	
	
}
