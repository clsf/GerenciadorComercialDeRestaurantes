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
/**
 * Controlador da view de imprimir vendas
 */
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
    
    /**
     * M�todo para inicializar a view
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTotal.setText(String.valueOf(Relatorios.precoTotalVenda(vendas)));
		if(tipo==1) {
			SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy");
			Date atual= new Date();
			labelTitulo.setText("Relat�rio de Venda - "+sdf2.format(atual));
		}else if(tipo==2) {
			
			labelTitulo.setText( "Relat�rio de venda por per�odo -"+ vendas.get(0).getDataR());
		}
		else if(tipo==3) {
			labelTitulo.setText("Relat�rio de venda por tipo de prato -" + categoria);
		}
		
		initalizeNode();
		updateData();
		
		
	}
	/**
	 * Inicializa as colunas da table view
	 */
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
		tableColumnPratos.setCellValueFactory(new PropertyValueFactory<>("pratos"));
		tableColumnTotal.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tableColumnPagamento.setCellValueFactory(new PropertyValueFactory<>("formaDePagamento"));
	}
	/**
	 * Atualiza os dados da table view
	 */
	public void updateData() {	
		tableViewVendas.refresh();	
		obsList = FXCollections.observableArrayList(vendas);
		tableViewVendas.setItems(obsList);	
		
	}
	/**
	 * M�todo para o bot�o voltar, retorna a tela anterior
	 * @param event Evento 
	 * @throws IOException Erro
	 */
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	
	/**
	 * M�todo para o bot�o imprimir, imprime o relat�rio
	 * @param event Evento
	 */
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(1)) {			
				Relatorios.gerarRelatorioVenda(vendas, tipo, null, null,Relatorios.precoTotalVenda(vendas));
						
		}else if(tipo.equals(2)){			
			Relatorios.gerarRelatorioVenda(vendas, tipo, vendas.get(0).getData(), null,Relatorios.precoTotalVenda(vendas));
		}else {
			Relatorios.gerarRelatorioVenda(vendas, tipo, null, categoria,Relatorios.precoTotalVenda(vendas));

		}
	}

	/**
	 * M�todo para alterar a lista de vendas a ser impressa
	 * @param vendas Vendas a serem impressas
	 */
	public static void setVendas(List<Venda> vendas) {
		VendasImprimirController.vendas = vendas;
	}

	/**
	 * M�todo para alterar o tipo de impress�o 
	 * @param tipo Integer que determina o tipo a ser imprimido
	 */
	public static void setTipo(Integer tipo) {
		VendasImprimirController.tipo = tipo;
	}
	
	/**
	 * M�todo para alterar a categoria 
	 * @param categoria Categoria do prato a ser impresso
	 */
	public static void setCategoria(CategoriaPrato categoria) {
		VendasImprimirController.categoria = categoria;
	}
	
	
	

}
