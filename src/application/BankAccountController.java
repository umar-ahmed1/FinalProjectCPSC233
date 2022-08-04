package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankAccountController {
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	
	@FXML
	private TextField usernameLoginField;
	
	@FXML
	private TextField passwordLoginField;
	
	/** 
     * Checks if the values provided are equal to the stored username and password values. 
     * @param ?
     * @return ?
     */
	@FXML
	void loginButtonPressed(ActionEvent event) {
		System.out.println("login button pressed");
	}
	/** 
     * Method that changes the scene to the register scene and creates an account based on the information provided 
     * @param mainScene, the login scene to be stored so that it can be returned to.
     * @return ?
     */
	@FXML
	void registerButtonPressed(ActionEvent event) throws IOException {
		//store original scene so we can return to it
		loginScene = applicationStage.getScene();
		
		if (applicationStage.getScene() == loginScene) {
		
		
		applicationStage.setScene(registerScene);
		}
		
		if (applicationStage.getScene() == registerScene) {
			System.out.println("test");
			applicationStage.setScene(loginScene);
			
			}
		}
	
	
	
}
