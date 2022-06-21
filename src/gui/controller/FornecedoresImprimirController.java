package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Fornecedor;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Relatorios;

public class FornecedoresImprimirController implements Initializable {
	
	private static List<Fornecedor> lista = new ArrayList<>();
	private static Integer tipo;
	private static String nomeProduto;
	
    @FXML
    private Button btImprimir;

    @FXML
    private Button btVoltar;

    @FXML
    private Label labelTitulo;

    @FXML
    private TableColumn<Fornecedor, Integer> tableColumnCNPJ;

    @FXML
    private TableColumn<Fornecedor, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnNome;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnProdutos;

    @FXML
    private TableView<Fornecedor> tableViewFornecedores;
    
    @FXML
    private ObservableList<Fornecedor> obsList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(tipo==1) {
			if(lista.size()!=0) {
				labelTitulo.setText("Relatório de Fornecedores por Produto - "+ GerenciadorProdutos.getProduto(lista.get(0).getIdProdutosFornecidos().get(0)).getNome());
			}else {
				labelTitulo.setText("Relatório de Fornecedores por produto -");
			}
			
		}else {
			labelTitulo.setText("Relatório de Fornecedor");			
		}
		initalizeNode();
		updateData();
	}
	
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnProdutos.setCellValueFactory(new PropertyValueFactory<>("produtos"));
	}
	
	public void updateData() {	
		tableViewFornecedores.refresh();	
		obsList = FXCollections.observableArrayList(lista);
		tableViewFornecedores.setItems(obsList);	
		
	}

	
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(1)) {
			if(lista.size()!=0) {
				Relatorios.gerarRelatorioFornecedor(lista, tipo, nomeProduto);;
			}			
		}else {			
			Relatorios.gerarRelatorioFornecedor(lista, tipo, null);
		}
	}
	

	public static void setLista(List<Fornecedor> lista) {
		FornecedoresImprimirController.lista = lista;
	}



	public static void setTipo(Integer tipo) {
		FornecedoresImprimirController.tipo = tipo;
	}

	public static void setNomeProduto(String nomeProduto) {
		FornecedoresImprimirController.nomeProduto = nomeProduto;
	}
	
	

}
