package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/view/loginView.fxml"));		
			Scene scene = new Scene(parent);
			stage.setScene(scene);
		    scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 			
			stage.setTitle("login");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}