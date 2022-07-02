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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.entities.Funcionario;
import model.entities.Gerente;
import model.entities.Usuario;
import model.exceptions.DomainException;
import model.gerenciadores.GerenciadorUsuarios;
import model.utils.Alerts;
/**
 * Controller da view de formulário de usuários
 * @author Cláudia Inês Sales Freitas
 *
 */
public class UsuariosFormController implements Initializable{

	private static Usuario usuario;

	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cancelar;
	
	@FXML
	private TextField textCodigo;
	
	@FXML
	private TextField textNome;
	
	@FXML
	private TextField textLogin;
	
	@FXML
	private TextField textSenha;
	
	@FXML
	private ComboBox<String> comboCargo;
	
	@FXML
	private ObservableList<String> obsList;
	

	
	/**
	 * Metódo para inicializar o formulário de usuarios
	 */
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(usuario==null) {
			textCodigo.setText(String.valueOf(Usuario.getUltimoId()));
			
		}else {
			textCodigo.setText(String.format("%d",usuario.getId()));
			textLogin.setText(usuario.getLogin());
			textSenha.setText(usuario.getSenha());
			textNome.setText(usuario.getNome());
			comboCargo.setValue(String.format(usuario.getCargo()));
		}
		
		initalizeComb();
		
	}
	
	/**
	 * Metódo para salvar o usuário
	 */
	public void onBtSalvar() {
		boolean fechar = true;
		if(usuario!=null) {
			if(comboCargo.getValue().equals("Gerente")) {
				Usuario uEdit = new Gerente(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText());
				try {
					GerenciadorUsuarios.addOuEdit(uEdit);
				} catch (DomainException e) {
					Alerts.showAlert("Login já está sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}				
				
			}else {
				Usuario uEdit = new Funcionario(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText());
				try {
					GerenciadorUsuarios.addOuEdit(uEdit);
				} catch (DomainException e) {
					Alerts.showAlert("Login já está sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}		
	
			}}
		else {
			if(comboCargo.getValue().equals("Gerente")) {	
				try {
					GerenciadorUsuarios.cadastrarUsuario(textLogin.getText(),textSenha.getText(),textNome.getText(),1);
				} catch (DomainException e) {					
					Alerts.showAlert("Login já está sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}
				
			}else {
				try {
					GerenciadorUsuarios.cadastrarUsuario(textLogin.getText(),textSenha.getText(),textNome.getText(),2);
				} catch (DomainException e) {					
					Alerts.showAlert("Login já está sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}
	
			}
		}
		
		if(fechar) {
		    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
		

		
	}
	/**
	 * Método para cancelar e retornar a outra view
	 * @throws IOException Erro ao abrir a tela
	 */
	public void onBtCancelar() throws IOException {
		
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	    
	}
	/**
	 * Método para inicializar a ComboBox
	 */
	public void initalizeComb() {
		List<String> list = new ArrayList<>();
		list.add("Gerente");
		list.add("Funcionário");
		obsList = FXCollections.observableArrayList(list);
		comboCargo.setItems(obsList);
		comboCargo.setValue("Gerente");
		
	}
	
	/**
	 * Método para alterar o usuario do controller
	 * @param u Usuário a ser editado
	 */
	public static void setUsuario(Usuario u) {
			usuario = u;
	}
	
	/**
	 * Método para pegar o usuário do controller
	 * @return Usuário do controller
	 */
	
	public static Usuario getUsuario() {
		return usuario;
	}
	


}
