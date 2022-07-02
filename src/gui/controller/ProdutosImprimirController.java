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
/**
 * Controller da view de imprimir produtos
 * @author Cláudia Inês Sales Freitas
 *
 */
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
	
	
    /**
     * Método para inicializar a view
     */	
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
			labelTitulo.setText("Relatório de produtos a vencer");
		}
		initalizeNode();
		updateData();
		
	}
	/**
	 * Método para inicializar os nós da tabela
	 */
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	}
	
	/**
	 * Método para atualizar a table view dos fornecedores
	 */
	
	public void updateData() {	
		tableViewProdutos.refresh();	
		obsList = FXCollections.observableArrayList(produtos);
		tableViewProdutos.setItems(obsList);	
		
	}
	
	/**
	 * Método para voltar a página anterior
	 * @param event Evento
	 * @throws IOException Exceção
	 */
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	/**
	 * Método para imprimir o relatório
	 * @param event Evento
	 */
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(2)) {
			if(produtos.size()!=0) {
				Relatorios.gerarRelatorioProduto(produtos, tipo, produtos.get(0).getNome(), produtos.size());
			}			
		}else {			
			Relatorios.gerarRelatorioProduto(produtos, tipo, null, produtos.size());
		}
	}
	
	/**
	 * Método alterar a lista de produtos a ser impresso
	 * @param produtos Lista de produtos
	 */

	public static void setProdutos(List<Produto> produtos) {
		ProdutosImprimirController.produtos = produtos;
	}
	
	/**
	 * Método para alterar o tipo de impressão 
	 * @param tipo Integer representando o tipo de impressão
	 */
	public static void setTipo(Integer tipo) {
		ProdutosImprimirController.tipo = tipo;
	}
	


}
