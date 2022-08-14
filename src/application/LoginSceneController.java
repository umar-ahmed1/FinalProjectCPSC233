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
	Stage applicationStage;
	public Account account;
	public String errorMessage;
	public ArrayList<Account> accounts = new ArrayList<Account>();
	boolean registerable = true;
	public String passwordToReferTo;
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
	void loginButtonPressed(ActionEvent event) {
		loginErrorLabel.setText("");
		for (Account acc : accounts) {
			System.out.println("accx: " + acc.toString());
		}
		System.out.println("-----------------------------------------------------------");
		
		Account loginAccount = new Account(usernameLoginField.getText(),passwordLoginField.getText());
		
			if (loginAccount != null && accounts.size()!= 0 && accounts != null) {
				
				loggedInAccount = loginAccount.compareToAllLogins(accounts);
				if (loggedInAccount != null) mainBankScene();
					
					//we are now logged in, create the bank scene
				
				else loginErrorLabel.setText("Details do not match with registered account");
				}
			//if acc is null we want to set the error label to ask to create an account
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
	void registerButtonPressedLoginScene(ActionEvent swapToRegisterLayout) throws IOException {	
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox(5);
		Label userLabel = new Label("Username");
		TextField userField = new TextField();	
		TextField passField = new TextField();
		Label passLabel = new Label("Password");
		TextField balField = new TextField();
		Label balLabel = new Label("Initial deposit");
		Label errorLabel = new Label("");
		
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> createAccount(loginScene, userField.getText(),passField.getText(),balField.getText(),errorLabel));
    	
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(userLabel, new Insets(10,100,0,100));
		VBox.setMargin(userField, new Insets(0,100,0,100));	
		VBox.setMargin(passLabel, new Insets(10,100,0,100));
		VBox.setMargin(passField, new Insets(0,100,0,100));
		VBox.setMargin(balLabel, new Insets(10,100,0,100));
		VBox.setMargin(balField, new Insets(0,100,0,100));
		VBox.setMargin(errorLabel, new Insets(10,10,0,25));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		root.getChildren().addAll(userLabel,userField,passLabel,passField,balLabel,balField,errorLabel,doneButton);
		
		Scene resetScene = new Scene(root,400,300);	
		applicationStage.setScene(resetScene);	
	}
	
	
	/** 
     * Method that is called when the done button is pressed in the resetPasswordScene. Compares the two passwords inputted, if they are equal, changes the account password to the new password.
     * Then switches the scene back to the login scene.
     * @param scene (the previous scene to return to when the method is done) 
     * @param newField (String obtained from the first textfield)
     * @param newConfirmField (String obtained from the second textfield)
     * @param errorMessage (Label that an error message can be written to)
     * @param whichOne //needs to be changed ***
     */
	void resetField(Scene scene, String newField, String newConfirmField, Label errorMessage,String whichOne) {
		errorMessage.setText("");
		
		if (newField.equals(newConfirmField)){
			if (whichOne.equals("Usernames")) this.account.setUsername(newField);
			if (whichOne.equals("Passwords")) this.account.setPassword(newField);
			applicationStage.setScene(scene);
		}		
		else if (newField.equals("") || newConfirmField.equals("")) errorMessage.setText("Error. One or more of the fields are blank");
		else if (!newField.equals(newConfirmField)) errorMessage.setText(whichOne + " do not match. Please try again");
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
	void createAccount(Scene scene, String user, String pass, String bal, Label errorLabel) {
		errorLabel.setText("");
		//Try to create a new account with the provided details
		try {
			account = new Account(user,pass,bal,accounts);
			accounts.add(account);
			applicationStage.setScene(scene);	
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
	void resetPasswordScene(ActionEvent resetButtonPressed) throws IOException{
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
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> resetField(loginScene,topField.getText(),botField.getText(),forgetErrorLabel,"Passwords"));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(userLabel, new Insets(2,100,0,100));
		VBox.setMargin(userField, new Insets(0,100,0,100));	
		VBox.setMargin(topLabel, new Insets(2,100,0,100));
		VBox.setMargin(topField, new Insets(0,100,0,100));	
		VBox.setMargin(botLabel, new Insets(2,100,0,100));
		VBox.setMargin(botField, new Insets(0,100,0,100));
		VBox.setMargin(forgetErrorLabel, new Insets(10,25,0,85));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		root.getChildren().addAll(userLabel,userField,topLabel,topField,botLabel,botField,doneButton,forgetErrorLabel);
		
		Scene resetScene = new Scene(root,400,250);
		
		if (accounts.size() != 0) applicationStage.setScene(resetScene);
		else loginErrorLabel.setText("Error, no account to reset password for");
	
	}
	
	/** 
     * Method that creates and changes the scene to the reset username scene when the forgot password button is pressed in the login scene.
     * This method will display all registered usernames to the user.
     * If the done button within this new scene is pressed, return to the login scene.
     * @param resetButtonPressed (pressing the forgot username button in the login screen)
     */
	@FXML
	void resetUsernameScene(ActionEvent resetButtonPressed) throws IOException{
		Scene loginScene = applicationStage.getScene();
		
    	//create the scene
    	VBox allRows = new VBox();
    	Label topLabel = new Label("All registered usernames:");
    	allRows.getChildren().add(topLabel);
    	VBox.setMargin(topLabel, new Insets(10,50,0,50));
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> applicationStage.setScene(loginScene));
		
    	
    	
    	//create a list to store all the quiz grades
    	int rowCounter = 0;
    	while (rowCounter < accounts.size()) {
    		HBox userRow = new HBox();
           	Label userLabel = new Label("User #" + (rowCounter+1) + ": " + accounts.get(rowCounter).getUsername());  
           	HBox.setMargin(userLabel, new Insets(0,50,0,50));
        	userRow.getChildren().addAll(userLabel);
        	allRows.getChildren().addAll(userRow);
        	rowCounter++;
    	}
    	
    	allRows.getChildren().add(doneButton);
    	VBox.setMargin(doneButton, new Insets(10,50,0,50));
    	
		Scene usersListScene = new Scene(allRows,250,70 + accounts.size() * 30);
		
		if (accounts.size() !=0) applicationStage.setScene(usersListScene);
		else loginErrorLabel.setText("Error, no usernames registered");	
	}
	
	
	void mainBankScene() {
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		Label forgetErrorLabel = new Label("");
		Label welcomeLabel = new Label("Welcome To Your Account: " + loggedInAccount.getUsername());
		Label loggedInBalance = new Label("$" + loggedInAccount.getBalance());
		
		StackPane rectangleStack = new StackPane();
		Label cardType = new Label("Chequing");
		StackPane rectangleStack2 = new StackPane();
		Label cardNumber = new Label(loggedInAccount.getCardNumber());
		//rectange x,y,width,height
		Rectangle rectangleCard = new Rectangle(100,100,250,100);
		rectangleCard.setFill(Color.rgb(0,151,230));
		Rectangle rectangleCard2 = new Rectangle(100,250,250,50);
		rectangleCard2.setFill(Color.rgb(151,230,255));
		rectangleStack.getChildren().addAll(rectangleCard,cardType,loggedInBalance);
		rectangleStack2.getChildren().addAll(rectangleCard2,cardNumber);
		
		//Buttons
		HBox buttons = new HBox(30);
		Button depositButton = new Button("Deposit");
		depositButton.setOnAction(doneEvent -> depositScene(loggedInBalance));
		Button withdrawButton = new Button("Withdraw");
		Button transferButton = new Button("eTransfer");
		buttons.getChildren().addAll(depositButton,withdrawButton,transferButton);
		
		//Transfer List
		VBox transferList = new VBox();
		Label contacts = new Label ("E-Transfer Contacts");
		transferList.getChildren().add(contacts);
		//create a list to store all the quiz grades
    	int rowCounter = 0;
    	while (rowCounter < accounts.size()) {
    		HBox userRow = new HBox();
           	Label userLabel = new Label("User #" + (rowCounter+1) + ": " + accounts.get(rowCounter).getUsername());  
           	HBox.setMargin(userLabel, new Insets(0,50,0,50));
        	userRow.getChildren().addAll(userLabel);
        	transferList.getChildren().addAll(userRow);
        	rowCounter++;
    	}
		
		//Transactions List
		VBox transactionList = new VBox();
		Label testLabel = new Label ("Transaction History");
		transactionList.getChildren().addAll(testLabel);
		
		//e
		HBox listsBox = new HBox(100);
		listsBox.getChildren().addAll(transferList,transactionList);
		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(forgetErrorLabel, new Insets(10,25,0,85));
		VBox.setMargin(rectangleStack2, new Insets(0,0,0,50));
		VBox.setMargin(rectangleStack,new Insets(25,0,0,50));
		VBox.setMargin(welcomeLabel,new Insets(0,0,0,0));
		VBox.setMargin(buttons,new Insets(0,0,0,50));
		StackPane.setAlignment(cardType, Pos.TOP_LEFT);
		StackPane.setAlignment(rectangleCard, Pos.TOP_LEFT);
		StackPane.setAlignment(cardNumber, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(rectangleCard2, Pos.TOP_LEFT);
		StackPane.setAlignment(loggedInBalance, Pos.CENTER_LEFT);
		
		welcomeLabel.setFont(new Font("Arial",30));
		cardNumber.setFont(new Font("Arial", 15));
		contacts.setFont(new Font("Arial", 15));
		testLabel.setFont(new Font("Arial", 15));
		loggedInBalance.setFont(new Font("Arial", 25));
		
		root.getChildren().addAll(welcomeLabel,rectangleStack,rectangleStack2,buttons,listsBox,forgetErrorLabel);
		
		Scene bankScene = new Scene(root,800,500);
		applicationStage.setScene(bankScene);
	}


	
	private void depositScene(Label loggedInBalance) {
		Scene mainScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox(5);
		Label amountLabel = new Label("Amount");
		TextField amountField = new TextField();	
		Label depositErrorLabel = new Label("");
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> deposit(amountField.getText(),depositErrorLabel,mainScene,loggedInBalance));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(amountLabel, new Insets(2,100,0,100));
		VBox.setMargin(amountField, new Insets(0,100,0,100));	
		VBox.setMargin(depositErrorLabel, new Insets(10,25,0,25));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		root.getChildren().addAll(amountLabel,amountField,doneButton,depositErrorLabel);
		
		Scene depositScene = new Scene(root,450,150);
		applicationStage.setScene(depositScene);
	}


	
	private void deposit(String amount,Label errorLabel,Scene scene,Label loggedInBalance) {
		errorLabel.setText("");
		//Try to create a new account with the provided details
		try {
			loggedInAccount.deposit(amount);
			applicationStage.setScene(scene);	
			loggedInBalance.setText("$" + Double.toString(loggedInAccount.getBalance()));
		//If an error occurs, the account class will determine why the error has occured and the account will not be created
		//Instead, an error message will be displayed which the account constructor has thrown
		} catch (InvalidBalanceException ige) {
			errorLabel.setText(ige.getMessage());	
		}
		
	}
	
}
