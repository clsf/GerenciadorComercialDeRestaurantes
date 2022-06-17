package gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;
import model.utils.Relatorios;

public class ProdutosImprimirController implements Initializable{
	private static List<Produto> produtos = new ArrayList<>();
	private static Integer tipo;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private Button btImprimir;
	
	@FXML 
	private TableView<Produto> tableViewProdutos;
	
	@FXML
	private TableColumn<Produto,Integer> tableColumnCodigo;
	
	@FXML
	private TableColumn<Produto,String> tableColumnNome;
	
	@FXML
	private TableColumn<Produto,String> tableColumnValidade;
	
	@FXML
	private TableColumn<Produto,Double> tableColumnQuantidade;
	
	@FXML
	private ObservableList<Produto> obsList;
	
	@FXML
	private Label labelTotal;
	
	@FXML
	private Label labelTitulo;
	
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTotal.setText(String.valueOf(produtos.size()));
		if(tipo==1) {
			SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
			Date atual= new Date();
			labelTitulo.setText("Relatório de Estoque - "+sdf1.format(atual));
		}else if(tipo==2) {
			if(produtos.size()!=0) {
				labelTitulo.setText("Relatório de Estoque por tipo -"+ produtos.get(0).getNome());
			}else {
				labelTitulo.setText("Relatório de estoque");
			}
			
		}
		else if(tipo==3) {
			labelTitulo.setText("Relatório de produtos a vencer nos próximos 60 dias ");
		}
		initalizeNode();
		updateData();
		
	}
	
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	}
	
	public void updateData() {	
		tableViewProdutos.refresh();	
		obsList = FXCollections.observableArrayList(produtos);
		tableViewProdutos.setItems(obsList);	
		
	}
	
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(2)) {
			if(produtos.size()!=0) {
				Relatorios.gerarRelatorioProduto(produtos, tipo, produtos.get(0).getNome(), produtos.size());
			}			
		}else {			
			Relatorios.gerarRelatorioProduto(produtos, tipo, null, produtos.size());
		}
	}


	public static void setProdutos(List<Produto> produtos) {
		ProdutosImprimirController.produtos = produtos;
	}

	public static void setTipo(Integer tipo) {
		ProdutosImprimirController.tipo = tipo;
	}
	


}
