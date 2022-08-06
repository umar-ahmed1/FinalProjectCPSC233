package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginSceneController{
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	private Stage stage;
	private Scene scene;
	private Parent registerRoot;
	public Account account;
	public String errorMessage;
	public ArrayList<Account> accounts = new ArrayList<Account>();
	boolean registerable = true;


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
		FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("RegisterSceneView.fxml"));
		registerRoot = registerLoader.load();
		RegisterSceneController registerController = registerLoader.getController();

		if (registerable) {
		stage = (Stage)((Node)swapToRegisterLayout.getSource()).getScene().getWindow();		
		scene = new Scene(registerRoot);
		stage.setScene(scene);
		stage.show();
		}
		else {
			registrationErrorMessage.setText("You have already registered. Please login");
		}
	}
	@FXML
	void resetUsername(ActionEvent resetButtonPressed) throws IOException{
		FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("RegisterSceneView.fxml"));
		registerRoot = registerLoader.load();
		RegisterSceneController registerController = registerLoader.getController();

		
	}
	@FXML
	void resetPassword(ActionEvent resetButtonPressed) throws IOException{
		
	}
	
	
		
	
	
	
}
