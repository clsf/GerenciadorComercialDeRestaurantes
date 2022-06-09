package gui;

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
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 public void onBtUsuario(ActionEvent event) throws IOException {
		 
			  root = FXMLLoader.load(getClass().getResource("/gui/ViewUsuarios.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
	 }
	 
	 public void onBtClientes(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/ClientesView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	 
	 
	 public void onBtProdutos(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/ProdutosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	 
	 public void onBtFornecedores(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/FornecedoresView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	 
	 public void onBtCardapio(ActionEvent event) throws IOException {
		 
		  root = FXMLLoader.load(getClass().getResource("/gui/PratosView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }

}
