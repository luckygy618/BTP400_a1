package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * The main class of the application which loads the app's login window.
 * 
 * @author Tim Lai
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {
	
	/**
	 * The start() method starts the application by loading the login window.
	 * 
	 * @param primaryStage The primary stage of the application.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
			Scene scene = new Scene(root, 500, 500);
			primaryStage.setTitle("Login to System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method launches the application.
	 * 
	 * @param args Arguments which are passed to the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
