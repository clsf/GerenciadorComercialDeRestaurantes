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
 * Controller da view de relatório de estoque
 * @author Cláudia Inês Sales Freitas
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
	  * Método para inicializar a view
	  */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkTotal.setSelected(true);
		restringir();
		
	}
	/**
	 * Método para restringir a entrada do usuário
	 */
	public void restringir() {
		Restringir.setTextFieldInteger(textProduto);
	}
	
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	
	public void onChectotal(ActionEvent event) {
		if(checkTotal.isSelected()) {
			checkProduto.setSelected(false);
			checkAvencer.setSelected(false);
		}	
		
	}
	
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	
	
	public void onCheckProduto(ActionEvent event) {
		if(checkProduto.isSelected()) {
			checkAvencer.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	
	public void onCheckAvencer(ActionEvent event) {
		if(checkAvencer.isSelected()){
			checkProduto.setSelected(false);
			checkTotal.setSelected(false);
		}
	}
	/**
	 * Método do botão Gerar relatório
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
			Alerts.showAlert("Estoque vazio", "Sem estoque!","Não há produto cadastrados." , AlertType.INFORMATION);

		}
	
	}
	
}
