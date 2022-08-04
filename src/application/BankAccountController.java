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

public class BankAccountController {
	Stage applicationStage;
	Scene loginScene;
	Scene registerScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
	void registerButtonPressedLoginScene(ActionEvent swapToRegisterLayout) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterSceneView.fxml"));
		root = loader.load();	
		
		RegisterSceneController registerController = loader.getController();
		stage = (Stage)((Node)swapToRegisterLayout.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		/*
		//store and hide original scene so we can return to it
		loginScene = applicationStage.getScene();
		applicationStage.getScene().getWindow().hide();
		//create new stage with the register scene
		Stage register = new Stage();
		FXMLLoader loader = new FXMLLoader();
		VBox root = loader.load(new FileInputStream("src/application/RegisterSceneView.fxml"));
		Scene scene = new Scene(root);
		register.setScene(scene);
		register.show();
		*/
	}
	
	
		
	
	
	
}
