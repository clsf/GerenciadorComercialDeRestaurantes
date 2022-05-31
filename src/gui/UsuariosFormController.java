package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Funcionario;
import model.entities.Gerente;
import model.entities.Usuario;
import model.gerenciadores.GerenciadorUsuarios;

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
	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	

	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(usuario==null) {
			System.out.print(Usuario.getUltimoId());
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
	
	public void onBtSalvar(ActionEvent event) {
		if(usuario!=null) {
			if(comboCargo.getValue().equals("Gerente")) {
				GerenciadorUsuarios.addOuEdit(new Gerente(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText()));
				
			}else {
				GerenciadorUsuarios.addOuEdit(new Funcionario(Integer.valueOf(textCodigo.getText()),textLogin.getText(),textSenha.getText(),textNome.getText()));
	
			}}
		else {
			if(comboCargo.getValue().equals("Gerente")) {
				GerenciadorUsuarios.addOuEdit(new Gerente(textLogin.getText(),textSenha.getText(),textNome.getText()));
				
			}else {
				GerenciadorUsuarios.addOuEdit(new Funcionario(textLogin.getText(),textSenha.getText(),textNome.getText()));
	
			}
		}
		System.out.print(Usuario.getUltimoId());
		
	    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
		
	}
	
	public void onBtCancelar(ActionEvent event) throws IOException {
	    Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	public void initalizeComb() {
		List<String> list = new ArrayList<>();
		list.add("Gerente");
		list.add("Funcionário");
		obsList = FXCollections.observableArrayList(list);
		comboCargo.setItems(obsList);
		comboCargo.setValue("Gerente");
		
	}
	
	
	public static void setUsuario(Usuario u) {
			usuario = u;
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
	


}
