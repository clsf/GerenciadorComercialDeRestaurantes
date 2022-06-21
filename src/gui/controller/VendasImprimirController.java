package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import model.gerenciadores.GerenciadorVendas;
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
import model.entities.Venda;
import model.enums.CategoriaPrato;
import model.enums.FormaDePagamento;
import model.utils.Relatorios;

public class VendasImprimirController implements Initializable{
	
	private static List<Venda> vendas = new ArrayList<>();
	private static Integer tipo;
	private static CategoriaPrato categoria;
	
	
    @FXML
    private Button btImprimir;

    @FXML
    private Button btVoltar;

    @FXML
    private Label labelTitulo;

    @FXML
    private Label labelTotal;

    @FXML
    private TableColumn<Venda, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Venda, String> tableColumnData;

    @FXML
    private TableColumn<Venda, FormaDePagamento> tableColumnPagamento;

    @FXML
    private TableColumn<Venda, String> tableColumnPratos;

    @FXML
    private TableColumn<Venda, Double> tableColumnTotal;

    @FXML
    private TableView<Venda> tableViewVendas;
    
    @FXML
    private ObservableList<Venda> obsList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTotal.setText(String.valueOf(Relatorios.precoTotalVenda(vendas)));
		if(tipo==1) {
			SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy");
			Date atual= new Date();
			labelTitulo.setText("Relatório de Venda - "+sdf2.format(atual));
		}else if(tipo==2) {
			
			labelTitulo.setText( "Relatório de venda por período -"+ vendas.get(0).getDataR());
		}
		else if(tipo==3) {
			labelTitulo.setText("Relatório de venda por tipo de prato -" + categoria);
		}
		
		initalizeNode();
		updateData();
		
		
	}
	
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
		tableColumnPratos.setCellValueFactory(new PropertyValueFactory<>("pratos"));
		tableColumnTotal.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tableColumnPagamento.setCellValueFactory(new PropertyValueFactory<>("formaDePagamento"));
	}
	
	public void updateData() {	
		tableViewVendas.refresh();	
		obsList = FXCollections.observableArrayList(vendas);
		tableViewVendas.setItems(obsList);	
		
	}
	
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(1)) {			
				Relatorios.gerarRelatorioVenda(vendas, tipo, null, null,Relatorios.precoTotalVenda(vendas));
						
		}else if(tipo.equals(2)){			
			Relatorios.gerarRelatorioVenda(vendas, tipo, vendas.get(0).getData(), null,Relatorios.precoTotalVenda(vendas));
		}else {
			Relatorios.gerarRelatorioVenda(vendas, tipo, null, categoria,Relatorios.precoTotalVenda(vendas));

		}
	}

	public static void setVendas(List<Venda> vendas) {
		VendasImprimirController.vendas = vendas;
	}

	public static void setTipo(Integer tipo) {
		VendasImprimirController.tipo = tipo;
	}

	public static void setCategoria(CategoriaPrato categoria) {
		VendasImprimirController.categoria = categoria;
	}
	
	
	

}
