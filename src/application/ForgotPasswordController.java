package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController {
	private Stage stage;
	private Scene scene;
	private Parent loginRoot;
	LoginSceneController loginController;

	@FXML
	private TextField forgotPasswordTextField;
	
	@FXML
	private TextField forgotPasswordConfirmTextField;
	
	@FXML
	void resetPasswordButtonPressed(ActionEvent event) throws IOException {
		//swap to login scene
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
		loginRoot = loginLoader.load();
		loginController = loginLoader.getController();
		loginController.account.setPassword(forgotPasswordTextField.getText());
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
		scene = new Scene(loginRoot);
		stage.setScene(scene);
		stage.show();	
		
		
	}
	
	
	
	
	
}
