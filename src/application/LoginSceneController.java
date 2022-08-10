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
		Account loginAccount = new Account(usernameLoginField.getText(),passwordLoginField.getText());
		for (Account acc : accounts) {
			System.out.println("account x: " + acc.toString());
		}	
		
			if (loginAccount != null && account!= null) {
				System.out.println(account.toString());
				
				if (loginAccount.toCompare(account)) System.out.println("account details match with an account in our database");
				else loginErrorLabel.setText("Details do not match with registered account");
				}
			//if acc is null we want to set the error label to ask to create an account
			else {
				loginErrorLabel.setText("Create an account before logging in");
			}
			
	}
	
	public Account getAccount() {
		return account;
	}
	
	
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided. 
     * @param no parameters
     * @return no return
     */
	@FXML
	void registerButtonPressedLoginScene(ActionEvent swapToRegisterLayout) throws IOException {


		
		
	}
	@FXML
	void resetUsername(ActionEvent resetButtonPressed) throws IOException{
			
	}
	
	void resetPassword(Scene scene, String newPassword, String newPasswordConfirmField) {
		
		if (newPassword.equals(newPasswordConfirmField)){
		this.account.setPassword(newPassword);
		}
		else
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
	
		//create the button and let it have an action on event
		Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> resetPassword(loginScene,topField.getText(),botField.getText()));
		
		//margins, order is top right bottom left in the (0,0,0,0)
		VBox.setMargin(topLabel, new Insets(10,100,0,100));
		VBox.setMargin(topField, new Insets(0,100,0,100));	
		VBox.setMargin(botLabel, new Insets(10,100,0,100));
		VBox.setMargin(botField, new Insets(0,100,0,100));
		
		
		root.getChildren().addAll(topLabel,topField,botLabel,botField);
		
		Scene resetScene = new Scene(root,400,200);
		
		applicationStage.setScene(resetScene);
		

		
		
	}
	
	
		
	
	
	
}
