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
	 
	 public void onBtUsuario(ActionEvent event) throws IOException {
		 
			  root = FXMLLoader.load(getClass().getResource("/gui/view/ViewUsuarios.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			  stage.setTitle("Usuários");
			  stage.show();
	 }
	 
	 public void onBtClientes(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/ClientesView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Clientes");
		  stage.show();
	 }
	 
	 
	 public void onBtProdutos(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/ProdutosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Produtos");
		  stage.show();
	 }
	 
	 public void onBtFornecedores(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/FornecedoresView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Fornecedores");
		  stage.show();
	 }
	 
	 public void onBtCardapio(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/view/PratosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setTitle("Pratos");
		  stage.show();
	 }
	 
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
