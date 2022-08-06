package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {
			//load the FXML file.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSceneView.fxml"));
			VBox root = loader.load();
			LoginSceneController controller = new LoginSceneController();
			controller.applicationStage = primaryStage;
			//set the primary stage
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Umars final project");
			primaryStage.show(); 
			primaryStage.setResizable(false);
			//load the FXML file.

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
