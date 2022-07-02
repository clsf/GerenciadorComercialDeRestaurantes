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
/**
 * Controlador da view de função imprimir relatórios de fornecedores
 * @author Cláudia Inês Sales Freitas
 *
 */
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
    
    /**
     * Metódo para inicializar a view
     */

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
	/**
	 * Metódo para inicializar os nós da tabela
	 */
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnProdutos.setCellValueFactory(new PropertyValueFactory<>("produtos"));
	}
	/**
	 * Metódo para atualizar a table view dos fornecedores
	 */
	public void updateData() {	
		tableViewFornecedores.refresh();	
		obsList = FXCollections.observableArrayList(lista);
		tableViewFornecedores.setItems(obsList);	
		
	}

	/**
	 * Metódo para voltar a página anterior
	 * @param event Evento
	 * @throws IOException Exceção
	 */
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	/**
	 * Metódo para imprimir o relatório
	 * @param event Evento
	 */
	public void onBtImprimir(ActionEvent event) {		
		if(tipo.equals(1)) {
			if(lista.size()!=0) {
				Relatorios.gerarRelatorioFornecedor(lista, tipo, nomeProduto);;
			}			
		}else {			
			Relatorios.gerarRelatorioFornecedor(lista, tipo, null);
		}
	}
	
	/**
	 * Metódo para alterar a lista  do controller
	 * @param lista Lista de fornecedores a ser impresso 
	 */
	public static void setLista(List<Fornecedor> lista) {
		FornecedoresImprimirController.lista = lista;
	}
	
	/**
	 * Metódo para altrar o tipo de relatório a ser impresso
	 * @param tipo Inteiro que classifica o tipo de relatório que será impresso
	 */


	public static void setTipo(Integer tipo) {
		FornecedoresImprimirController.tipo = tipo;
	}
	/**
	 * Metódo para alterar o nome do Produto que será mostrado na impressão
	 * @param nomeProduto Nome do produto
	 */
	public static void setNomeProduto(String nomeProduto) {
		FornecedoresImprimirController.nomeProduto = nomeProduto;
	}
	
	

}
