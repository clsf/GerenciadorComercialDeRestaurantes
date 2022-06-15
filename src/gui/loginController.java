package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Usuario;
import model.exceptions.DomainException;
import model.gerenciadores.GerenciadorUsuarios;
import model.utils.Alerts;
import model.utils.Facade;

public class loginController implements Initializable {

	@FXML
	private TextField fieldLogin;
	
	@FXML
	private TextField fieldSenha;
	
	@FXML
	private Button btEntrar;
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Facade.initialize();
		} catch (DomainException e) {
			Alerts.showAlert("Erro ao inicializar", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	public void onBtEntrar(ActionEvent event) throws IOException {
		
		try {
			Usuario usuario = GerenciadorUsuarios.login(fieldLogin.getText(), fieldSenha.getText());
			System.out.println(usuario.getLogin());
			if(usuario!=null) {
				  root = FXMLLoader.load(getClass().getResource("/gui/MenuView.fxml"));
				  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				  scene = new Scene(root);
				  stage.setScene(scene);
				  stage.setResizable(false);
				  stage.show();
			}
		} catch (DomainException e) {
			Alerts.showAlert("Usuario não reconhecido!", "Verificar usuário e senha.", e.getMessage(), AlertType.ERROR);
		}
		
		
	}

}
