package visual;

import visual_controller.LoginControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GetVisual extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(GetVisual.class.getResource("login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		LoginControler.setStage(primaryStage);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
