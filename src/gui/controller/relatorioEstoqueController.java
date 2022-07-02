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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Produto;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Relatorios;
import model.utils.Restringir;
/**
 * Controller da view de relat�rio de estoque
 * @author Cl�udia In�s Sales Freitas
 *
 */
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
	 
	 /**
	  * M�todo para inicializar a view
	  */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkTotal.setSelected(true);
		restringir();
		
	}
	/**
	 * M�todo para restringir a entrada do usu�rio
	 */
	public void restringir() {
		Restringir.setTextFieldInteger(textProduto);
	}
	
	/**
	 * M�todo para alterar as ativa��es da checkBox
	 * @param event Evento
	 */
	
	public void onChectotal(ActionEvent event) {
		if(checkTotal.isSelected()) {
			checkProduto.setSelected(false);
			checkAvencer.setSelected(false);
		}	
		
	}
	
	/**
	 * M�todo para alterar as ativa��es da checkBox
	 * @param event Evento
	 */
	
	
	public void onCheckProduto(ActionEvent event) {
		if(checkProduto.isSelected()) {
			checkAvencer.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	
	/**
	 * M�todo para alterar as ativa��es da checkBox
	 * @param event Evento
	 */
	
	public void onCheckAvencer(ActionEvent event) {
		if(checkAvencer.isSelected()){
			checkProduto.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	/**
	 * M�todo do bot�o Gerar relat�rio
	 * @param event Evento
	 * @throws IOException Erro 
	 */
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
		
		if(lista.size()!=0) {
			  root = FXMLLoader.load(getClass().getResource("/gui/view/ProdutosImprimirView.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.setResizable(false);
			  stage.show();
		}else {
			Alerts.showAlert("Estoque vazio", "Sem estoque!","N�o h� produto cadastrados." , AlertType.INFORMATION);

		}
	
	}
	
}
