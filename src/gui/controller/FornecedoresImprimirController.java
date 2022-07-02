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
 * Controlador da view de fun��o imprimir relat�rios de fornecedores
 * @author Cl�udia In�s Sales Freitas
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
     * Met�do para inicializar a view
     */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(tipo==1) {
			if(lista.size()!=0) {
				labelTitulo.setText("Relat�rio de Fornecedores por Produto - "+ GerenciadorProdutos.getProduto(lista.get(0).getIdProdutosFornecidos().get(0)).getNome());
			}else {
				labelTitulo.setText("Relat�rio de Fornecedores por produto -");
			}
			
		}else {
			labelTitulo.setText("Relat�rio de Fornecedor");			
		}
		initalizeNode();
		updateData();
	}
	/**
	 * Met�do para inicializar os n�s da tabela
	 */
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnProdutos.setCellValueFactory(new PropertyValueFactory<>("produtos"));
	}
	/**
	 * Met�do para atualizar a table view dos fornecedores
	 */
	public void updateData() {	
		tableViewFornecedores.refresh();	
		obsList = FXCollections.observableArrayList(lista);
		tableViewFornecedores.setItems(obsList);	
		
	}

	/**
	 * Met�do para voltar a p�gina anterior
	 * @param event Evento
	 * @throws IOException Exce��o
	 */
	public void onBtVoltar(ActionEvent event) throws IOException {
		Stage stage = (Stage) btVoltar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
	}
	/**
	 * Met�do para imprimir o relat�rio
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
	 * Met�do para alterar a lista  do controller
	 * @param lista Lista de fornecedores a ser impresso 
	 */
	public static void setLista(List<Fornecedor> lista) {
		FornecedoresImprimirController.lista = lista;
	}
	
	/**
	 * Met�do para altrar o tipo de relat�rio a ser impresso
	 * @param tipo Inteiro que classifica o tipo de relat�rio que ser� impresso
	 */


	public static void setTipo(Integer tipo) {
		FornecedoresImprimirController.tipo = tipo;
	}
	/**
	 * Met�do para alterar o nome do Produto que ser� mostrado na impress�o
	 * @param nomeProduto Nome do produto
	 */
	public static void setNomeProduto(String nomeProduto) {
		FornecedoresImprimirController.nomeProduto = nomeProduto;
	}
	
	

}
