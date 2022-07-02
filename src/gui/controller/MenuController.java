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
 * @author Cl�udia In�s Sales Freitas
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
	  * Met�do do bot�o Usu�rio, chama view de Usu�rios 
	  * @param event Evento
	  * @throws IOException Exce��o 
	  */
	 
	 public void onBtUsuario(ActionEvent event) throws IOException {
		 
			  root = FXMLLoader.load(getClass().getResource("/gui/view/ViewUsuarios.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			  stage.setTitle("Usu�rios");
			  stage.show();
	 }
	 /**
	  * Met�do do bot�o Clientes, chama view de Clientes
	  * @param event Evento
	  * @throws IOException Exce��o 
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
	  * Met�do do bot�o Produtos, chama view de Produtos 
	  * @param event Evento
	  * @throws IOException Exce��o 
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
	  * Met�do do bot�o Fornecedores, chama view de Fornecedores 
	  * @param event Evento
	  * @throws IOException Exce��o 
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
	  * Met�do do bot�o Cardapio, chama view de Card�pio 
	  * @param event Evento
	  * @throws IOException Exce��o 
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
	  * Met�do do bot�o Venda, chama view de Venda
	  * @param event Evento
	  * @throws IOException Exce��o 
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
