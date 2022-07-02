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
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Ingredientes;
import model.entities.Prato;
import model.entities.Produto;
import model.enums.CategoriaPrato;
import model.enums.UnidadeDeMedida;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Restringir;
/**
 * Controller para a view de formul�rio de pratos
 * @author Cl�udia In�s Sales Freitas
 *
 */
public class PratosFormController implements Initializable{
	
	private List<Ingredientes> ingredientes = new ArrayList<>();
	private static Prato prato;
	
	 @FXML
	  private Button adicionar;

	  @FXML
	  private Button cancelar;

	  @FXML
	  private ComboBox<CategoriaPrato> comboCategoria;

	  @FXML
	  private ComboBox<UnidadeDeMedida> comboMedida;

	  @FXML
	  private ComboBox<Produto> comboProdutos;

	  @FXML
	  private Button salvar;

	  @FXML
	  private TableColumn<Ingredientes, Ingredientes> tableColumnExcluir;

	  @FXML
	  private TableColumn<Ingredientes, UnidadeDeMedida> tableColumnMedida;

	  @FXML
	  private TableColumn<Ingredientes, String> tableColumnProduto;

	  @FXML
	  private TableColumn<Ingredientes, Double> tableColumnQuantidade;

	  @FXML
	  private TableView<Ingredientes> tableViewIngredientes;

	  @FXML
	  private TextField textCodigo;

	  @FXML
	  private TextField textNome;

	  @FXML
	  private TextField textPreco;

	  @FXML
	  private TextField textQuantidade;
	  @FXML
	  private TextField textDescricao;
	  
	  @FXML
	  private ObservableList<Ingredientes> obsListIngredientes;
	  
	  @FXML
	  private ObservableList<Produto> obsListProdutos;
	  
	  @FXML
	  private ObservableList<CategoriaPrato> obsListCategoria;
	  
	  @FXML
	  private ObservableList<UnidadeDeMedida> obsListMedida;
	  
	/**
	 * Met�do para inicializar as informa��es na view de Formul�rio de pratos  
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initExcluirButtons();
		initalizeNode();
		obsListProdutos = FXCollections.observableArrayList(GerenciadorProdutos.getListaDeProdutos());
		comboProdutos.setItems(obsListProdutos);
		List<CategoriaPrato> categorias = new ArrayList<>(EnumSet.allOf(CategoriaPrato.class));
		obsListCategoria = FXCollections.observableArrayList(categorias);
		comboCategoria.setItems(obsListCategoria);
		List<UnidadeDeMedida> unidades = new ArrayList<>(EnumSet.allOf(UnidadeDeMedida.class));
		obsListMedida = FXCollections.observableArrayList(unidades);
		comboMedida.setItems(obsListMedida);
		
		if(prato==null) {
			textCodigo.setText(String.valueOf(Prato.getUltimoId()));					
			
		}else {
			textCodigo.setText(String.valueOf(prato.getId()));
			textNome.setText(prato.getNome());
			textPreco.setText(String.valueOf(prato.getPreco()));
			textDescricao.setText(prato.getDescricao());
			ingredientes.addAll(prato.getIngredientes());
		}
		restringir();
		updateData();
		
		Callback<ListView<Produto>, ListCell<Produto>> factory = lv -> new ListCell<Produto>() {
			 @Override
			 protected void updateItem(Produto item, boolean empty) {
			 super.updateItem(item, empty);
			 setText(empty ? "" : item.getNome());
			 }
			};
			comboProdutos.setCellFactory(factory);
			comboProdutos.setButtonCell(factory.call(null));
	
	
	}
	/**
	 * Met�do para restringir as entradas
	 */
	private void restringir() {
		Restringir.setTextFieldDouble(textPreco);
		Restringir.setTextFieldDouble(textQuantidade);
	}
	/**
	 * Fun��o para ao clicar no bot�o salvar, salvar o Cliente no gerenciador
	 */
    public void onBtAdicionar(ActionEvent event) {
    	
    	if(comboProdutos.getSelectionModel().getSelectedItem()!=null && comboMedida.getSelectionModel().getSelectedItem()!=null && textQuantidade.getText()!="") {    		
    		Produto produto = comboProdutos.getSelectionModel().getSelectedItem();
    		UnidadeDeMedida medida = comboMedida.getSelectionModel().getSelectedItem();
    		Double quantidade = Double.valueOf(textQuantidade.getText());
    		
    		boolean contem = false;
    		if(ingredientes!=null) {
	    		for(Ingredientes ingrediente: ingredientes) {
	    			if(ingrediente.getId().equals(produto.getId())) {
	    				contem = true;
	    			}
    		}}
    		if(!contem) {
    			ingredientes.add(new Ingredientes(produto.getId(),quantidade,medida));
    		}
    		else {
    			Alerts.showAlert("ERRO!", "Ingrediente existente","N�o � poss�vel adicionar ingredientes iguais, por favor exclua um e adicione outro." , AlertType.ERROR);
    		}    	
    	updateData();    	
    	
    }
    }
    
    /**
     * Met�do para retirar o ingredite da lista de ingredientes
     * @param event Evento
     * @param ingrediente Ingrediente a ser removido
     */
	private void onBtExcluir(ActionEvent event, Ingredientes ingrediente) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir ingrediente?");
		
		if(opcao.get()==ButtonType.OK) {
			ingredientes.remove(ingrediente);			
		}
		updateData();
	}
	/**
	 * Fun��o para o bot�o cancelar, fechando a tela atual
	 * @throws IOException Erro ao obter tela
	 */
	public void onBtCancelar(ActionEvent event) throws IOException {
		
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	    
	}
	/**
	 * Met�do do bot�o salvar para salvar o prato no gerenciador 
	 */
	public void onBtSalvar() {
		if(comboCategoria.getSelectionModel().getSelectedItem()!=null) {
			if(prato!=null) {
				Prato p = new Prato(Integer.valueOf(textCodigo.getText()),textNome.getText(), Double.valueOf(textPreco.getText()),
						comboCategoria.getSelectionModel().getSelectedItem(),textDescricao.getText(), ingredientes);
				GerenciadorPratos.addOuEdit(p);
			}
			else {
				Prato p = new Prato(textNome.getText(), Double.valueOf(textPreco.getText()),
						comboCategoria.getSelectionModel().getSelectedItem(),textDescricao.getText(), ingredientes);
				GerenciadorPratos.addOuEdit(p);
			}
			
		    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}else {
			Alerts.showAlert("ERRO!", "Sem categoria","N�o � poss�vel salvar sem categoria do prato!", AlertType.ERROR);
		}
	}
	
	/**
	 * Met�do para atualizar a table view de Ingredientes 
	 */
	public void updateData() {	
		List<Ingredientes> ingredientesOFC = new ArrayList<>();
		if(ingredientes!=null) {
			for(Ingredientes ingrediente: ingredientes) {
				if(GerenciadorProdutos.getProduto(ingrediente.getId())!=null) {
					ingredientesOFC.add(ingrediente);
				}
		}}
		obsListIngredientes = FXCollections.observableArrayList(ingredientesOFC);
		tableViewIngredientes.setItems(obsListIngredientes);
		
	}
	/**
	 * Met�do para inicializar as colunas da tabela
	 */
	public void initalizeNode() {
		tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnMedida.setCellValueFactory(new PropertyValueFactory<>("unidadeDeMeida"));
	}
	/**
	 * Met�do para inicializar bot�es de excluir da tabela
	 */
	private void initExcluirButtons() {
		tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnExcluir.setCellFactory(param -> new TableCell<Ingredientes, Ingredientes>() {
			private final Button button = new Button("Excluir");

			@Override
			protected void updateItem(Ingredientes obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> {
							onBtExcluir(event, obj);
						});
				
			}
			
		});
		
	}
	/**
	 * Met�do para alterar o prato do controller
	 * @param prato Prato a ser editado
	 */
	public static void setPrato(Prato prato) {
		PratosFormController.prato = prato;
	}
	
	
	

}
