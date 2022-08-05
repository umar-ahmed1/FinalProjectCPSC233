package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

public class RegisterSceneController {
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	//ArrayList<Account> accountsRegistered = new ArrayList<Account>();
	//int counts = 0;
	public Account accountRegistered;
	
	@FXML
	private TextField usernameRegisterField;
	
	@FXML
	private TextField passwordRegisterField;
	
	/** 
     * Checks if the values provided are equal to the stored username and password values. 
     * @param ?
     * @return ?
     */
	
	
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided 
     * @param mainScene, the login scene to be stored so that it can be returned to.
     * @return ?
     */
	@FXML
	void registerButtonPressedRegisterScene(ActionEvent swapToLoginLayout) throws IOException {
		
		//create the account and add to list of accounts
		accountRegistered = new Account(usernameRegisterField.getText(),passwordRegisterField.getText());
		System.out.println("account registered: " + accountRegistered.toString());

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
		root = loader.load();	
		BankAccountController loginController = loader.getController();
		loginController.account = accountRegistered;
		
		stage = (Stage)((Node)swapToLoginLayout.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	
	String createAccountDetailsUsernameField() {
		System.out.println("username field accessed");
		return usernameRegisterField.getText();
	}
	String createAccountDetailsPasswordField() {
		System.out.println("password field accessed");
		return passwordRegisterField.getText();	
	}
	
	public Account getAccountRegistered() {
		return accountRegistered;
	}
	
		
	
	
	
}
