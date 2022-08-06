package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController {
	private Stage stage;
	private Scene scene;
	private Parent loginRoot;
	LoginSceneController loginController;
	private String thePassword;

	@FXML
	private TextField forgotPasswordTextField;
	
	@FXML
	private TextField forgotPasswordConfirmTextField;
	
	@FXML
	private Label forgotPasswordErrorLabel;
	
	@FXML
	void resetPasswordButtonPressed(ActionEvent event) throws IOException {
		forgotPasswordErrorLabel.setText("");
		//swap to login scene
		if (forgotPasswordTextField.getText().equals(forgotPasswordConfirmTextField.getText())) {
			thePassword = forgotPasswordTextField.getText();
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
			loginRoot = loginLoader.load();
			loginController = loginLoader.getController();
			loginController.account.setPassword(thePassword);
					
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();	
			scene = new Scene(loginRoot);
			stage.setScene(scene);
			stage.show();			
		}
		else forgotPasswordErrorLabel.setText("Passwords do not match, please try again");
		
	
		
	}
	
	
	
	
	
}
