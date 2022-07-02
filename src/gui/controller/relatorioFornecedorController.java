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
import model.entities.Fornecedor;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Relatorios;
import model.utils.Restringir;
/**
 * Controller da view de relat�rio de fornecedores
 * @author Cl�udia In�s Sales Freitas
 *
 */
public class relatorioFornecedorController implements Initializable {


    @FXML
    private Button btGerar;

    @FXML
    private CheckBox checkFornecedor;

    @FXML
    private CheckBox checkPorProduto;

    @FXML
    private TextField textFornecedor;

    @FXML
    private TextField textProduto;
    
    
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 /**
	  * M�todo de inicializa��o da view
	  */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkPorProduto.setSelected(true);
		restringir();
		
	}
	/**
	 * M�todo para restringir a entrada do usu�rio
	 */
	private void restringir() {
		Restringir.setTextFieldInteger(textFornecedor);
		Restringir.setTextFieldInteger(textProduto);
	}
	/**
	 * M�todo para alterar as ativa��es da checkBox
	 * @param event Evento
	 */
	public void onCheckFornecedor() {
		if(checkFornecedor.isSelected()) {
			checkPorProduto.setSelected(false);			
		}
	}
	/**
	 * M�todo para alterar as ativa��es da checkBox
	 * @param event Evento
	 */
	public void onCheckPorProduto() {
		if(checkPorProduto.isSelected()) {
			checkFornecedor.setSelected(false);
		}
	}
	/**
	 * M�todo do bot�o Gerar relat�rio
	 * @param event Evento
	 * @throws IOException Erro 
	 */
	public void onBtGerar(ActionEvent event) throws IOException {
		List<Fornecedor> lista = new ArrayList<>();
		if(checkFornecedor.isSelected()) {
			if(textFornecedor.getText()=="") {
				lista = GerenciadorFornecedores.getListaDeFornecedores();
			}else {
				lista = Relatorios.relatorioFornecedor(Integer.valueOf(textFornecedor.getText()));	
			}
			FornecedoresImprimirController.setLista(lista);
			FornecedoresImprimirController.setTipo(2);
					
		}else {
			
			if(textProduto.getText()!="") {
				if(GerenciadorProdutos.getProduto(Integer.valueOf(textProduto.getText()))!=null){
					lista = Relatorios.relatorioFornecedorePorProduto(Integer.valueOf(textProduto.getText()));
					FornecedoresImprimirController.setLista(lista);
					FornecedoresImprimirController.setTipo(1);
					FornecedoresImprimirController.setNomeProduto(GerenciadorProdutos.getProduto(Integer.valueOf(textProduto.getText())).getNome());
				}else {
					Alerts.showAlert("Produto n�o existe", "C�digo inv�lido!","� preciso informar c�digo do produto v�lido" , AlertType.ERROR);
				}
				
			}else {
				Alerts.showAlert("Dado inv�lido", "Dado inv�lido!","� preciso informar c�digo do produto" , AlertType.ERROR);

			}
		}
		
		if(lista.size()!=0) {
			  root = FXMLLoader.load(getClass().getResource("/gui/view/FornecedoresImprimirView.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.setResizable(false);
			  stage.show();
		}else {
			Alerts.showAlert("Sem fornecedores", "Sem fornecedores!","N�o h� fornecedores cadastrados." , AlertType.INFORMATION);

		}
		
	}

}
