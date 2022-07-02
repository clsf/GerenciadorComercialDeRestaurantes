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
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.gerenciadores.GerenciadorClientes;
import model.utils.Restringir;
/**
 * Controlador do formul�rio de edi��o e cria��o de clientes
 * @author Cl�udia In�s Sales Freitas
 *
 */

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
	
	/**
	 * Met�do para inicializar o formul�rio de clientes
	 */
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
		restringir();
		
	}
	/**
	 * Met�do para restringir as caixas de entrada do usu�rio
	 */
	public void restringir() {
		Restringir.setTextFieldInteger(textCpf);
		Restringir.setTextFieldInteger(textTelefone);
	}
	/**
	 * Fun��o para ao clicar no bot�o salvar, salvar o Cliente no gerenciador
	 */
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
	/**
	 * Fun��o para o bot�o cancelar, fechando a tela atual
	 * @throws IOException Erro ao obter tela
	 */
	public void onBtCancelar() throws IOException {
	    Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	/**
	 * Met�do para alterar o cliente do controlador
	 * @param c Cliente
	 */
	public static void setCliente(Cliente c) {
		cliente = c;
		
	}
	
	
		
	}
	



