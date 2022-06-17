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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Fornecedor;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Relatorios;
import model.utils.Restringir;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkPorProduto.setSelected(true);
		restringir();
		
	}
	
	private void restringir() {
		Restringir.setTextFieldInteger(textFornecedor);
		Restringir.setTextFieldInteger(textProduto);
	}
	
	public void onCheckFornecedor() {
		if(checkFornecedor.isSelected()) {
			checkPorProduto.setSelected(false);			
		}
	}
	
	public void onCheckPorProduto() {
		if(checkPorProduto.isSelected()) {
			checkFornecedor.setSelected(false);
		}
	}
	
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
			  root = FXMLLoader.load(getClass().getResource("/gui/FornecedoresImprimirView.fxml"));
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
