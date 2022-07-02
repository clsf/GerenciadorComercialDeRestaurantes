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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * Controller da view do Menu
 * @author Cláudia Inês Sales Freitas
 *
 */
public class MenuController {
	
	@FXML
	private Button btUsuario;
	
	@FXML
	private Button btClientes;
	
	@FXML
	private Button btProdutos;
	
	@FXML
	private Button btFornecedores;
	
	@FXML
	private Button btCardapio;
	
	@FXML
	private Button btVendas;
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 /**
	  * Metódo do botão Usuário, chama view de Usuários 
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 
	 public void onBtUsuario(ActionEvent event) throws IOException {
		 
			  root = FXMLLoader.load(getClass().getResource("/gui/view/ViewUsuarios.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			  stage.setTitle("Usuários");
			  stage.show();
	 }
	 /**
	  * Metódo do botão Clientes, chama view de Clientes
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 public void onBtClientes(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/ClientesView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Clientes");
		  stage.show();
	 }
	 
	 /**
	  * Metódo do botão Produtos, chama view de Produtos 
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 public void onBtProdutos(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/ProdutosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Produtos");
		  stage.show();
	 }
	 /**
	  * Metódo do botão Fornecedores, chama view de Fornecedores 
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 public void onBtFornecedores(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/FornecedoresView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Fornecedores");
		  stage.show();
	 }
	 /**
	  * Metódo do botão Cardapio, chama view de Cardápio 
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 public void onBtCardapio(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/PratosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Pratos");
		  stage.show();
	 }
	 /**
	  * Metódo do botão Venda, chama view de Venda
	  * @param event Evento
	  * @throws IOException Exceção 
	  */
	 public void onBtVenda(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/VendasView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Venda");
		  stage.show();
	 }

}
