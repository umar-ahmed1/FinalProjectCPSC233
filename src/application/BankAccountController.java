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

public class BankAccountController {
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	public Account account;
	
	@FXML
	private TextField usernameLoginField;
	
	@FXML
	private TextField passwordLoginField;
	
	/** 
     * Checks if the values provided are equal to the stored username and password values. 
     * @param ?
     * @return ?
	 * @throws IOException 
     */
	@FXML
	void loginButtonPressed(ActionEvent event) throws IOException {
		System.out.println(account.toString());
		System.out.println("test");
			if (account.toCompareIndividual(usernameLoginField.getText(), passwordLoginField.getText())) System.out.println("account details match with an account in our database");
			else System.out.println("you suck");
		}
	
	
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided 
     * @param mainScene, the login scene to be stored so that it can be returned to.
     * @return ?
     */
	@FXML
	void registerButtonPressedLoginScene(ActionEvent swapToRegisterLayout) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterSceneView.fxml"));
		root = loader.load();	
		//my source for this is https://www.youtube.com/watch?v=wxhGKR3PQpo
		RegisterSceneController registerController = loader.getController();
	
		stage = (Stage)((Node)swapToRegisterLayout.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
		
	
	
	
}
