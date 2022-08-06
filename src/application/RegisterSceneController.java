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

public class RegisterSceneController{
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	private Stage stage;
	private Scene scene;
	private Parent loginRoot;
	public Account accountRegistered;
	LoginSceneController loginController;
	
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
		
		//swap to login scene
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
		loginRoot = loginLoader.load();
		loginController = loginLoader.getController();
		System.out.println("test");
		loginController.account = accountRegistered;
		loginController.registerable = false;
		
		stage = (Stage)((Node)swapToLoginLayout.getSource()).getScene().getWindow();	
		scene = new Scene(loginRoot);
		stage.setScene(scene);
		stage.show();	
			
	}
	
	public Account getAccountRegistered() {
		return accountRegistered;
	}
	
	public ArrayList<Account> getAccountsRegistered(){
		return accountsRegistered;
	}
	
}
