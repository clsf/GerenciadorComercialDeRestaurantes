package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Produto;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Relatorios;

public class relatorioEstoqueController implements Initializable{
	
	@FXML
	private CheckBox checkTotal;
	
	@FXML
	private CheckBox checkProduto;
	
	@FXML
	private CheckBox checkAvencer;
	
	@FXML 
	private TextField textProduto;
	
	@FXML
	private Button btGerar;
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkTotal.setSelected(true);
		
	}
	
	public void onChectotal(ActionEvent event) {
		if(checkTotal.isSelected()) {
			checkProduto.setSelected(false);
			checkAvencer.setSelected(false);
		}	
		
	}
	
	public void onCheckProduto(ActionEvent event) {
		if(checkProduto.isSelected()) {
			checkAvencer.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	
	public void onCheckAvencer(ActionEvent event) {
		if(checkAvencer.isSelected()){
			checkProduto.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	
	public void onBtGerar(ActionEvent event) throws IOException {
		List<Produto> lista = new ArrayList<>();
		if(checkTotal.isSelected()) {
			lista = GerenciadorProdutos.getListaDeProdutos();
			ProdutosImprimirController.setProdutos(lista);
			ProdutosImprimirController.setTipo(1);
		}else if(checkProduto.isSelected()) {
			if(textProduto.getText()!=" ") {
				lista = Relatorios.relatorioEstoquePorProduto(Integer.parseInt(textProduto.getText()));
				ProdutosImprimirController.setProdutos(lista);
				ProdutosImprimirController.setTipo(2);
			}
			
		}else if(checkAvencer.isSelected()) {
			lista = Relatorios.relatorioEstoqueProdutosAvencer();
			ProdutosImprimirController.setProdutos(lista);
			ProdutosImprimirController.setTipo(3);
		}
		
		  root = FXMLLoader.load(getClass().getResource("/gui/ProdutosImprimirView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
}
