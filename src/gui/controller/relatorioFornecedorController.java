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
import model.entities.Fornecedor;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Relatorios;
import model.utils.Restringir;
/**
 * Controller da view de relatório de fornecedores
 * @author Cláudia Inês Sales Freitas
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
	  * Método de inicialização da view
	  */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkPorProduto.setSelected(true);
		restringir();
		
	}
	/**
	 * Método para restringir a entrada do usuário
	 */
	private void restringir() {
		Restringir.setTextFieldInteger(textFornecedor);
		Restringir.setTextFieldInteger(textProduto);
	}
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	public void onCheckFornecedor() {
		if(checkFornecedor.isSelected()) {
			checkPorProduto.setSelected(false);			
		}
	}
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	public void onCheckPorProduto() {
		if(checkPorProduto.isSelected()) {
			checkFornecedor.setSelected(false);
		}
	}
	/**
	 * Método do botão Gerar relatório
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
					Alerts.showAlert("Produto não existe", "Código inválido!","É preciso informar código do produto válido" , AlertType.ERROR);
				}
				
			}else {
				Alerts.showAlert("Dado inválido", "Dado inválido!","É preciso informar código do produto" , AlertType.ERROR);

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
			Alerts.showAlert("Sem fornecedores", "Sem fornecedores!","Não há fornecedores cadastrados." , AlertType.INFORMATION);

		}
		
	}

}
