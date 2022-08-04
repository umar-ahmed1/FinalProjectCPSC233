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
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/BankAccountView.fxml"));
			//set the primary stage
			Scene scene = new Scene(root,400,400);
			//create the controller so it can modify the stage
			BankAccountController controller = (BankAccountController) loader.getController();
			controller.applicationStage = primaryStage;
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Umars final project");
			primaryStage.show(); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
