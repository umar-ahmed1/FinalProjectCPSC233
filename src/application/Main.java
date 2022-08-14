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
			//add the FXML loader so we can load the FXML files we have created
			FXMLLoader loader = new FXMLLoader();
			//Load the FXML file for the GradeCalculatorView
			VBox root = loader.load(new FileInputStream("src/application/LoginSceneView.fxml"));
			LoginSceneController controller = (LoginSceneController) loader.getController();
			controller.applicationStage = primaryStage;
			//set the primary stage
			Scene scene = new Scene(root,400,300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Umars final project");
			primaryStage.show(); 
			primaryStage.setResizable(false);		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
