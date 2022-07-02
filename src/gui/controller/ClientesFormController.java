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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.gerenciadores.GerenciadorClientes;
import model.utils.Restringir;
/**
 * Controlador do formulário de edição e criação de clientes
 * @author Cláudia Inês Sales Freitas
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
	 * Metódo para inicializar o formulário de clientes
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
	 * Metódo para restringir as caixas de entrada do usuário
	 */
	public void restringir() {
		Restringir.setTextFieldInteger(textCpf);
		Restringir.setTextFieldInteger(textTelefone);
	}
	/**
	 * Função para ao clicar no botão salvar, salvar o Cliente no gerenciador
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
	 * Função para o botão cancelar, fechando a tela atual
	 * @throws IOException Erro ao obter tela
	 */
	public void onBtCancelar() throws IOException {
	    Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	/**
	 * Metódo para alterar o cliente do controlador
	 * @param c Cliente
	 */
	public static void setCliente(Cliente c) {
		cliente = c;
		
	}
	
	
		
	}
	



