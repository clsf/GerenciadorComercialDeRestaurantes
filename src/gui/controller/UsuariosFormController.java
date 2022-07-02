/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 24/06/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
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
 * Controller da view de formul�rio de usu�rios
 * @author Cl�udia In�s Sales Freitas
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
	 * Met�do para inicializar o formul�rio de usuarios
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
	 * Met�do para salvar o usu�rio
	 */
	public void onBtSalvar() {
		boolean fechar = true;
		if(usuario!=null) {
			if(comboCargo.getValue().equals("Gerente")) {
				Usuario uEdit = new Gerente(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText());
				try {
					GerenciadorUsuarios.addOuEdit(uEdit);
				} catch (DomainException e) {
					Alerts.showAlert("Login j� est� sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}				
				
			}else {
				Usuario uEdit = new Funcionario(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText());
				try {
					GerenciadorUsuarios.addOuEdit(uEdit);
				} catch (DomainException e) {
					Alerts.showAlert("Login j� est� sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}		
	
			}}
		else {
			if(comboCargo.getValue().equals("Gerente")) {	
				try {
					GerenciadorUsuarios.cadastrarUsuario(textLogin.getText(),textSenha.getText(),textNome.getText(),1);
				} catch (DomainException e) {					
					Alerts.showAlert("Login j� est� sendo utilizado", null, e.getMessage(), AlertType.ERROR);
					fechar =false;
				}
				
			}else {
				try {
					GerenciadorUsuarios.cadastrarUsuario(textLogin.getText(),textSenha.getText(),textNome.getText(),2);
				} catch (DomainException e) {					
					Alerts.showAlert("Login j� est� sendo utilizado", null, e.getMessage(), AlertType.ERROR);
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
	 * M�todo para cancelar e retornar a outra view
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
	 * M�todo para inicializar a ComboBox
	 */
	public void initalizeComb() {
		List<String> list = new ArrayList<>();
		list.add("Gerente");
		list.add("Funcion�rio");
		obsList = FXCollections.observableArrayList(list);
		comboCargo.setItems(obsList);
		comboCargo.setValue("Gerente");
		
	}
	
	/**
	 * M�todo para alterar o usuario do controller
	 * @param u Usu�rio a ser editado
	 */
	public static void setUsuario(Usuario u) {
			usuario = u;
	}
	
	/**
	 * M�todo para pegar o usu�rio do controller
	 * @return Usu�rio do controller
	 */
	
	public static Usuario getUsuario() {
		return usuario;
	}
	


}
