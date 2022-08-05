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
	public Account accountRegistered;
	
	@FXML
	private TextField usernameRegisterField;
	
	@FXML
	private TextField passwordRegisterField;
	
	
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided 
     * @param mainScene, the login scene to be stored so that it can be returned to.
     * @return ?
     */
	@FXML
	void registerButtonPressedRegisterScene(ActionEvent swapToLoginLayout) throws IOException {
		//create the account and add to list of accounts
		if (usernameRegisterField.getText() != "" && passwordRegisterField.getText() != "" ) {
		accountRegistered = new Account(usernameRegisterField.getText(),passwordRegisterField.getText());
		System.out.println("account registered: " + accountRegistered.toString());
		}
		//creates a loginController (for the login scene) so we can send data back to that scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
		root = loader.load();	
		BankAccountController loginController = loader.getController();
		//send the account created back to the loginController - needs encapsulation 
		loginController.count = 2;
		loginController.account = accountRegistered;
		//load in the login scene
		stage = (Stage)((Node)swapToLoginLayout.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
		
	}
	
	public Account getAccountRegistered() {
		return accountRegistered;
	}
	
		
	
	
	
}
