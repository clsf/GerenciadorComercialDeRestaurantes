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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Cliente;
import model.entities.Ingredientes;
import model.entities.Prato;
import model.entities.Produto;
import model.entities.Venda;
import model.enums.CategoriaPrato;
import model.enums.FormaDePagamento;
import model.enums.StatusDaVenda;
import model.exceptions.DomainException;
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Alerts;
import model.utils.Relatorios;
/**
 * Controller da view de formul�rio de venda
 * @author Cl�udia In�s Sales Freitas
 *
 */
public class VendasFormController implements Initializable {
	
	private static Venda venda;
	private List<Integer> itens = new ArrayList<>();
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @FXML
    private Button adicionar;

    @FXML
    private Button cancelar;
    
    @FXML
    private Button btRealizarVenda;
    
    @FXML 
    private Button btEmitirNota;

    @FXML
    private ComboBox<Cliente> comboClientes;

    @FXML
    private ComboBox<FormaDePagamento> comboPagamento;

    @FXML
    private ComboBox<Prato> comboPratos;

    @FXML
    private Label labelPreco;
    @FXML
    private Label labelStatus;
    
    @FXML
    private Button salvar;

    @FXML
    private TableColumn<Prato, Prato> tableColumnExcluir;

    @FXML
    private TableColumn<Prato, Double> tableColumnPreco;

    @FXML
    private TableColumn<Prato, String> tableColumnPrato;

    @FXML
    private TableView<Prato> tableViewPratos;

    @FXML
    private TextField textCodigo;

    @FXML
    private TextField textData;
    
    @FXML
    private ObservableList<FormaDePagamento> obsListPagamento;
    
    @FXML
    private ObservableList<Cliente> obsListCliente;
    
    @FXML
    private ObservableList<Prato> obsListPrato;
    
    @FXML
    private ObservableList<Prato> obsListPratoTable;
    
    
    /**
     * Inicializar a view
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeCombos();
		initExcluirButtons();
		initalizeNode();		
		
		if(venda==null) {
			textCodigo.setText(String.valueOf(Venda.getUltimoId()));
			labelStatus.setText("ABERTO");	
			btRealizarVenda.setDisable(true);
			btEmitirNota.setDisable(true);
			Date atual = new Date();
			textData.setText(sdf2.format(atual));
		}
		else {
			textCodigo.setText(String.valueOf(venda.getId()));
			textData.setText(venda.getData());
			comboPagamento.setValue(venda.getFormaDePagamento());
			comboClientes.setValue(venda.getCliente());
			labelStatus.setText(String.format("%s", venda.getStatus()));
			labelPreco.setText(String.format("%.2f",venda.precoTotal(GerenciadorPratos.getPrato())));			
			itens.addAll( venda.getItens());
			btEmitirNota.setDisable(true);
			if(venda.getStatus().equals(StatusDaVenda.FECHADO)) {
								
				textData.setDisable(true);
				comboPagamento.setDisable(true);
				comboClientes.setDisable(true);
				salvar.setDisable(true);
				adicionar.setDisable(true);
				btEmitirNota.setDisable(false);
				btRealizarVenda.setDisable(true);
				Alerts.showAlert("Venda Fechada", "Essa venda est� fechada!","Uma venda fechada n�o pode ser editada apenas emitida." , AlertType.INFORMATION);			
				
			}
		}
		updateData();
	}
	/**
	 * Inicializar a combox
	 */
	public void initializeCombos() {
		List<FormaDePagamento> pagamento = new ArrayList<>(EnumSet.allOf(FormaDePagamento.class));
		obsListPagamento = FXCollections.observableArrayList(pagamento);
		comboPagamento.setItems(obsListPagamento);
		
		obsListCliente = FXCollections.observableArrayList(GerenciadorClientes.getListaDeClientes());
		comboClientes.setItems(obsListCliente); 
		
		obsListPrato = FXCollections.observableArrayList(GerenciadorPratos.getPrato());
		comboPratos.setItems(obsListPrato);
		
		
		Callback<ListView<Cliente>, ListCell<Cliente>> factory = lv -> new ListCell<Cliente>() {
			 @Override
			 protected void updateItem(Cliente item, boolean empty) {
			 super.updateItem(item, empty);
			 setText(empty ? "" : item.getNome());
			 }
			};
			comboClientes.setCellFactory(factory);
			comboClientes.setButtonCell(factory.call(null));
			
		Callback<ListView<Prato>, ListCell<Prato>> factory2 = lv -> new ListCell<Prato>() {
				 @Override
				 protected void updateItem(Prato item, boolean empty) {
				 super.updateItem(item, empty);
				 setText(empty ? "" : item.getNome());
				 }
				};
				comboPratos.setCellFactory(factory2);
				comboPratos.setButtonCell(factory2.call(null));	
		
		
	}
	/**
	 * Met�do para excluir prato da venda
	 * @param event Evento
	 * @param prato prato a ser exclu�do
	 */
	private void onBtExcluir(ActionEvent event, Prato prato) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir prato?");
		
		if(opcao.get()==ButtonType.OK) {
			Integer idPrato = prato.getId();
			itens.remove(idPrato);
		}
		updateData();
	}
	
	/**
	 * M�todo do bot�o cancelar para fechar a view
	 * @param event Evento 
	 * @throws IOException Erro
	 */
	public void onBtCancelar(ActionEvent event) throws IOException {
		
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	    
	}
	/**
	 * M�todo para adicionar prato na venda
	 * @param event
	 */
	public void onBtAdicionar(ActionEvent event) {
		if(comboPratos.getSelectionModel().getSelectedItem()!=null) {
			Prato prato = comboPratos.getSelectionModel().getSelectedItem();			
			itens.add(prato.getId());
		}
		updateData();
	}
	/**
	 * Met�do para salvar a venda
	 * @param event Evento 
	 * @throws NumberFormatException Erro 
	 * @throws ParseException Erro
	 */
	public void onBtSalvar(ActionEvent event) throws NumberFormatException, ParseException {
		if(comboPagamento.getSelectionModel().getSelectedItem()!=null && comboClientes.getSelectionModel().getSelectedItem()!=null) {
			StatusDaVenda status;
			if(labelStatus.getText().equals("ABERTO")) {
				status = StatusDaVenda.ABERTO;
			}else {
				status = StatusDaVenda.FECHADO;
			}
			
			if(venda!=null) {
				
				Venda v = new Venda(Integer.valueOf(textCodigo.getText()), comboPagamento.getSelectionModel().getSelectedItem(),
						sdf1.parse(textData.getText()), status,itens, comboClientes.getSelectionModel().getSelectedItem() );
				GerenciadorVendas.addOuEdit(v);

			}
			else {
				Venda v = new Venda(comboPagamento.getSelectionModel().getSelectedItem(),sdf1.parse(textData.getText()), itens, status,comboClientes.getSelectionModel().getSelectedItem() );
				GerenciadorVendas.addOuEdit(v);
			}
			  Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
			    stage.close(); //Fechando o Stage
		}
		else {
			Alerts.showAlert("ERRO!", "Faltando informa��es!","N�o � poss�vel salvar sem Forma de Pagamento ou Cliente!", AlertType.ERROR);
		}
	}
	/**
	 * M�todo para o bot�o realizar a venda
	 * @param event Evento
	 */
	public void onBtRealizarVenda(ActionEvent event) {
		try {
			Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente realizar a venda? N�o poder� ser editada ap�s a realiza��o!");
				if(opcao.get()==ButtonType.OK) {
				venda.realizarVenda(GerenciadorPratos.getPrato(), GerenciadorProdutos.getListaDeProdutos());
				
				labelStatus.setText(String.valueOf(venda.getStatus()));
				btEmitirNota.setDisable(false);				
				textData.setDisable(true);
				comboPagamento.setDisable(true);
				comboClientes.setDisable(true);
				salvar.setDisable(true);
				adicionar.setDisable(true);
				btRealizarVenda.setDisable(true);
				Alerts.showAlert("Salvo", "Informa��es salvas", "Informa��es salvas com sucesso, n�o poder� ser alterada", AlertType.INFORMATION);
				
				
			}
			
		} catch (DomainException e) {
			Alerts.showAlert("ERRO!", "Faltando informa��es!",e.getMessage(), AlertType.ERROR);
		}
	}
	/**
	 * Atualiza a table view de pratos
	 */
	
	public void updateData() {
		List<Prato> pratos = new ArrayList<>();
		for(Integer idPrato: itens) {
			if(GerenciadorPratos.getPrato(idPrato)!=null) {
				pratos.add(GerenciadorPratos.getPrato(idPrato));
			}
		}	
	
		obsListPratoTable = FXCollections.observableArrayList(pratos);
		tableViewPratos.setItems(obsListPratoTable);
		
	}
	/**
	 * Inicializa os n�s da table view
	 */
	public void initalizeNode() {
		tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tableColumnPrato.setCellValueFactory(new PropertyValueFactory<>("nome"));	
	}
	/***
	 * Inicializa os bot�es de excluir da tabela
	 */
	private void initExcluirButtons() {
		tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnExcluir.setCellFactory(param -> new TableCell<Prato, Prato>() {
			private final Button button = new Button("Excluir");

			@Override
			protected void updateItem(Prato obj, boolean empty) {
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
	 * M�todo para alterar venda do controller
	 * @param object Venda a ser editada
	 */
	public static void setVenda(Venda object) {
		venda = object; 
		
	}
	/**
	 * M�todo para o bot�o emitir nota
	 * @param event Evento
	 */
	public void onBtEmitirNota(ActionEvent event) {		
		Relatorios.emitirNota(venda);
	}



}
