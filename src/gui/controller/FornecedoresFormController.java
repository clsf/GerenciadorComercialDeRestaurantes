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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Fornecedor;
import model.entities.Produto;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Restringir;
/**
 * Controlador do formulário de edição e criação de fornecedores
 * @author Cláudia Inês Sales Freitas
 *
 */
public class FornecedoresFormController implements Initializable {
	
	private static Fornecedor fornecedor;
	
    @FXML
    private Button cancelar;

    @FXML
    private ComboBox<Produto> comboProdutos;

    @FXML
    private Button salvar;
    
    @FXML
    private Button adicionar;

    @FXML
    private TableView<Produto> tableViewProdutos;
    

    @FXML
    private TableColumn<Produto, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Produto, Produto> tableColumnExcluir;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;


    @FXML
    private TextField textCnpj;

    @FXML
    private TextField textCodigo;

    @FXML
    private TextField textEndereco;

    @FXML
    private TextField textNome;
    
    @FXML
    private ObservableList<Produto> obsList;
    
    @FXML
    private ObservableList<Produto> obsList2;
    
    private List<Integer> idProdutos = new ArrayList<>();
    
    
	/**
	 * Metódo para inicializar o formulário de fornecedores
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initExcluirButtons();
		initalizeNode();
		obsList = FXCollections.observableArrayList(GerenciadorProdutos.getListaDeProdutos());
		comboProdutos.setItems(obsList);
		if(fornecedor==null) {
			textCodigo.setText(String.valueOf(Fornecedor.getUltimoId()));
			obsList2 = null;
			tableViewProdutos.setItems(obsList2);			
		}
		else {
			textCodigo.setText(String.valueOf(fornecedor.getId()));
			textNome.setText(fornecedor.getName());
			textEndereco.setText(fornecedor.getEndereco());
			textCnpj.setText(String.valueOf(fornecedor.getCnpj()));
			
			List<Produto> produtos = new ArrayList<>();
			for(Integer idProduto: fornecedor.getIdProdutosFornecidos()) {
				if(GerenciadorProdutos.getProduto(idProduto)!=null) {
					produtos.add(GerenciadorProdutos.getProduto(idProduto));
					idProdutos.add(idProduto);					
				}
			}
			obsList2 = FXCollections.observableArrayList(produtos);
			tableViewProdutos.setItems(obsList2);
			
		}
		
		restringir();
		
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
	 * Metódo para restringir as caixas de entrada do usuário
	 */
	
	public void restringir() {
		Restringir.setTextFieldInteger(textCnpj);		
	}
	
	/**
	 * Função para ao clicar no botão adicionar, salva produto na lista de produtos fornecidos 
	 */
    
    public void onBtAdicionar(ActionEvent event) {
    	
    	if(comboProdutos.getSelectionModel().getSelectedItem()!=null) {    		
    		Produto produto = comboProdutos.getSelectionModel().getSelectedItem();
    		boolean contem = false;
    		for(Integer idProduto: idProdutos) {
    			if(idProduto.equals(produto.getId())) {
    				contem=true;
    			}
    		}
    		if(!contem) {
    				idProdutos.add(produto.getId());
    		}
    	}    	
    	updateData();    	
    }
    
/**
 * Função para o botõa excluir produto da lista de produtos fornecidos
 * @param event Evento
 * @param produto Produto a ser excluído da lista
 */

    
	private void onBtExcluir(ActionEvent event, Produto produto) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir produto?");
		
		if(opcao.get()==ButtonType.OK) {
			idProdutos.removeIf(x-> x.equals(produto.getId()));
		}
		updateData();
	}
	
	/**
	 * Função para o botão cancelar, fechando a tela atual
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
	 * Metódo para salvar o fornecedor no gerenciador ao clicar no botão salvar
	 */
	public void onBtSalvar() {
		if(fornecedor!=null) {
			Fornecedor f = new Fornecedor(Integer.valueOf(textCodigo.getText()), Integer.valueOf(textCnpj.getText()), textNome.getText(), textEndereco.getText(), idProdutos);
			GerenciadorFornecedores.addOuEdit(f);
		}
		else {
			Fornecedor f = new Fornecedor(Integer.valueOf(textCnpj.getText()), textNome.getText(), textEndereco.getText(), idProdutos);
			GerenciadorFornecedores.addOuEdit(f);
		}
		
	    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
		
	}
	/**
	 * Metódo para atualizar a table view de produtos fornecidos
	 */
	
	public void updateData() {			
		List<Produto> produtos = new ArrayList<>();
		for(Integer idProduto: idProdutos) {
			if(GerenciadorProdutos.getProduto(idProduto)!=null) {
				produtos.add(GerenciadorProdutos.getProduto(idProduto));
			}
		}
		obsList2 = FXCollections.observableArrayList(produtos);
		tableViewProdutos.setItems(obsList2);
		
	}
	/**
	 * Metódo para inicializar os nós da tabela
	 */
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	}
	/**
	 * Metódo para inicializar os botões de excluir da tabela
	 */
	private void initExcluirButtons() {
		tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnExcluir.setCellFactory(param -> new TableCell<Produto, Produto>() {
			private final Button button = new Button("Excluir");

			@Override
			protected void updateItem(Produto obj, boolean empty) {
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
	 * Metódo para alterar o fornecedor do controller
	 * @param fornecedor2 Fornecedor a ser alterado
	 */
	public static void setFornecedor(Fornecedor fornecedor2) {
		fornecedor = fornecedor2;
		
	}
    
    
    

}
