package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.gerenciadores.GerenciadorClientes;


public class ClientesFormController implements Initializable {
	
	private static Cliente cliente;
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cancelar;
	
	@FXML
	private TextField textCodigo;
	
	@FXML
	private TextField textNome;
	
	@FXML
	private TextField textEmail;
	
	@FXML
	private TextField textCpf;
	
	@FXML
	private TextField textTelefone;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(cliente==null) {			
			textCodigo.setText(String.valueOf(Cliente.getUltimoId()));
		}else {
			textCodigo.setText(String.format("%d",cliente.getId()));
			textEmail.setText(cliente.getEmail());
			textCpf.setText(String.format("%d",cliente.getCpf()));
			textNome.setText(cliente.getNome());
			textTelefone.setText(String.format("%d",cliente.getTelefone()));

		}	
		
	}
	
	public void onBtSalvar() {
		if(cliente!=null) {
			GerenciadorClientes.addOuEdit(new Cliente(textNome.getText(),textEmail.getText(),Integer.parseInt(textCpf.getText()),Integer.parseInt(textTelefone.getText()),Integer.parseInt(textCodigo.getText())));
		}
		else {
			GerenciadorClientes.addOuEdit(new Cliente(textNome.getText(),textEmail.getText(),Integer.parseInt(textCpf.getText()),Integer.parseInt(textTelefone.getText())));

		}		
	    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
		
	}
	
	public void onBtCancelar() throws IOException {
	    Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}

	public static void setCliente(Cliente c) {
		cliente = c;
		
	}
	
	
		
	}
	



