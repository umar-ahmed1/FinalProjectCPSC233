package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
     * When login button is pressed, Checks if the values in the username and password are equal to the stored username and password values. If so prints a statement. 
     * @param ?
     * @return ?
	 * @throws IOException 
     */
	@FXML
	void loginButtonPressed(ActionEvent event) throws IOException {
		loginErrorLabel.setText("");
		for (Account acc : accounts) {
			System.out.println("accx: " + acc.toString());
		}
		
		Account loginAccount = new Account(usernameLoginField.getText(),passwordLoginField.getText());
		
			if (loginAccount != null && accounts.size()!= 0 && accounts != null) {
				
				if (loginAccount.compareToStoredAccounts(accounts)) {
					loggedInAccount = new Account(account);
					
					//we are now logged in, create the bank scene
					
				}
				else loginErrorLabel.setText("Details do not match with registered account");
				}
			//if acc is null we want to set the error label to ask to create an account
			else {
				loginErrorLabel.setText("Create an account before logging in");
			}
			
	}
	
	
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided. 
     * @param no parameters
     * @return no return
     */
	@FXML
	void registerButtonPressedLoginScene(ActionEvent swapToRegisterLayout) throws IOException {	
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox(5);
		Label topLabel = new Label("Username");
		TextField topField = new TextField();	
		TextField botField = new TextField();
		Label botLabel = new Label("Password");
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> createAccount(loginScene, topField.getText(),botField.getText()));
    	
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(topLabel, new Insets(10,100,0,100));
		VBox.setMargin(topField, new Insets(0,100,0,100));	
		VBox.setMargin(botLabel, new Insets(10,100,0,100));
		VBox.setMargin(botField, new Insets(0,100,0,100));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		root.getChildren().addAll(topLabel,topField,botLabel,botField,doneButton);
		
		Scene resetScene = new Scene(root,400,200);	
		applicationStage.setScene(resetScene);	
	}
	
	void resetField(Scene scene, String newField, String newConfirmField, Label errorMessage,String whichOne) {
		errorMessage.setText("");
		
		if (newField.equals(newConfirmField)){
			if (whichOne.equals("Usernames")) this.account.setUsername(newField);
			if (whichOne.equals("Passwords")) this.account.setPassword(newField);
		}
		else errorMessage.setText(whichOne + " do not match. Please try again");
		applicationStage.setScene(scene);
	}
	
	
	
	void createAccount(Scene scene, String user, String pass) {
		account = new Account(user,pass);
		accounts.add(account);
		applicationStage.setScene(scene);		
	}
	
	@FXML
	void resetPasswordScene(ActionEvent resetButtonPressed) throws IOException{
		Scene loginScene = applicationStage.getScene();
		
		//create the labels and the textfields
		VBox root = new VBox(5);
		Label topLabel = new Label("Password");
		TextField topField = new TextField();	
		TextField botField = new TextField();
		Label botLabel = new Label("Confirm Password");
		Label forgetErrorLabel = new Label("");
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> resetField(loginScene,topField.getText(),botField.getText(),forgetErrorLabel,"Passwords"));
    		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(topLabel, new Insets(10,100,0,100));
		VBox.setMargin(topField, new Insets(0,100,0,100));	
		VBox.setMargin(botLabel, new Insets(10,100,0,100));
		VBox.setMargin(botField, new Insets(0,100,0,100));
		VBox.setMargin(forgetErrorLabel, new Insets(10,100,0,100));
		VBox.setMargin(doneButton, new Insets(10,100,0,175));
		
		root.getChildren().addAll(topLabel,topField,botLabel,botField,doneButton,forgetErrorLabel);
		
		Scene resetScene = new Scene(root,400,200);
		
		if (account!= null) applicationStage.setScene(resetScene);
		else loginErrorLabel.setText("Error, no account to reset password for");
	
	}
	
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
           	Label userLabel = new Label("User #" + (rowCounter+1) + " " + accounts.get(rowCounter).getUsername());  
           	HBox.setMargin(userLabel, new Insets(0,50,0,50));
        	userRow.getChildren().addAll(userLabel);
        	allRows.getChildren().addAll(userRow);
        	rowCounter++;
    	}
    	
    	allRows.getChildren().add(doneButton);
    	VBox.setMargin(doneButton, new Insets(10,50,0,50));
    	
		Scene usersListScene = new Scene(allRows,250,70 + accounts.size() * 30);
		
		if (account!= null) applicationStage.setScene(usersListScene);
		else loginErrorLabel.setText("Error, no account to reset username for");	
	}
	
		
	
	
	
}
