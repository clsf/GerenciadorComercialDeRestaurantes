/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 24/06/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package gui.controller;

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
/**
 * Controller da view de Login
 * @author Cláudia Inês Sales Freitas
 *
 */
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
	 
	 /**
	  * Metódo para inicializar a view
	  */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Facade.initialize();
		} catch (DomainException e) {
			Alerts.showAlert("Erro ao inicializar", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	/**
	 * Metódo para o botão entrar
	 * @param event Evento 
	 * @throws IOException Exceção 
	 */
	public void onBtEntrar(ActionEvent event) throws IOException {
		
		try {
			Usuario usuario = GerenciadorUsuarios.login(fieldLogin.getText(), fieldSenha.getText());
			if(usuario!=null) {
				  root = FXMLLoader.load(getClass().getResource("/gui/view/MenuView.fxml"));
				  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				  scene = new Scene(root);
				  stage.setScene(scene);
				  stage.setTitle("Menu principal");
			      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
				  stage.setResizable(false);
				  stage.show();
			}
		} catch (DomainException e) {
			Alerts.showAlert("Usuario não reconhecido!", "Verificar usuário e senha.", e.getMessage(), AlertType.ERROR);
		}
		
		
	}

}
