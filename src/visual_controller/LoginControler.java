package visual_controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginControler implements Initializable{
	@FXML
	private AnchorPane panel;
	@FXML
	private Pane pantalla;
	@FXML
	private ImageView image1;
	@FXML
	private TextField user;
	@FXML
	private Label titulo;
	@FXML
	private ImageView image2;
	@FXML
	private PasswordField password;
	@FXML
	private Button aceptar;
	@FXML
	private Button cancelar;
	@FXML
	
	private static Stage stage;
	
	public static void setStage (Stage setStage){
		stage = setStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
