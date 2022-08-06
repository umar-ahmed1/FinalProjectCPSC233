package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	RegisterSceneController registerController;
	LoginSceneController loginController;
	private Parent loginRoot;
	private Parent registerRoot;
	private Parent root;
	private Stage stage;
	private Scene scene;

	public void switchtoLoginScene(ActionEvent swapToLoginLayout) throws IOException {
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
		loginController = loginLoader.getController();
		loginRoot = loginLoader.load();
		stage = (Stage)((Node)swapToLoginLayout.getSource()).getScene().getWindow();	
		scene = new Scene(loginRoot);
		stage.setScene(scene);
		stage.show();	
				
	}
	public void switchtoRegisterScene(ActionEvent swapToRegisterLayout) throws IOException {
		FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("RegisterSceneView.fxml"));
		registerController = registerLoader.getController();
		registerRoot = registerLoader.load();
		stage = (Stage)((Node)swapToRegisterLayout.getSource()).getScene().getWindow();		
		scene = new Scene(registerRoot);
		stage.setScene(scene);
		stage.show();
	}
	public void interaction() {
		loginController.account = registerController.accountRegistered;
	}
	
	
	
	public LoginSceneController getLoginController() {
		return loginController;
		
	}
	public RegisterSceneController getRegisterController() {
		return registerController;
	}
}
