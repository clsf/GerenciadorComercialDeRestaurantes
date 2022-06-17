package gui;

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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeCombos();
		initExcluirButtons();
		initalizeNode();
		
		
		
		if(venda==null) {
			textCodigo.setText(String.valueOf(Venda.getUltimoId()));
			labelStatus.setText("ABERTO");	
			btRealizarVenda.setDisable(true);
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
			
			if(venda.getStatus().equals(StatusDaVenda.FECHADO)) {
				Alerts.showAlert("Venda Fechada", "Essa venda está fechada!","Uma venda fechada não pode ser editada." , AlertType.INFORMATION);			
				
				textData.setDisable(true);
				comboPagamento.setDisable(true);
				comboClientes.setDisable(true);
				salvar.setDisable(true);
				adicionar.setDisable(true);
				
			}
		}
		updateData();
	}
	
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
	
	private void onBtExcluir(ActionEvent event, Prato prato) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir ingrediente?");
		
		if(opcao.get()==ButtonType.OK) {
			Integer idPrato = prato.getId();
			itens.remove(idPrato);
		}
		updateData();
	}
	
	public void onBtCancelar(ActionEvent event) throws IOException {
		
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	    
	}
	
	public void onBtAdicionar(ActionEvent event) {
		if(comboPratos.getSelectionModel().getSelectedItem()!=null) {
			Prato prato = comboPratos.getSelectionModel().getSelectedItem();			
			itens.add(prato.getId());
		}
		updateData();
	}
	
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
			Alerts.showAlert("ERRO!", "Faltando informações!","Não é possível salvar sem Forma de Pagamento ou Cliente!", AlertType.ERROR);
		}
	}
	
	public void onBtRealizarVenda(ActionEvent event) {
		try {
			Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente realizar a venda? Não poderá ser editada após a realização!");
				if(opcao.get()==ButtonType.OK) {
				venda.realizarVenda(GerenciadorPratos.getPrato(), GerenciadorProdutos.getListaDeProdutos());
				
				labelStatus.setText(String.valueOf(venda.getStatus()));
			}
			
		} catch (DomainException e) {
			Alerts.showAlert("ERRO!", "Faltando informações!",e.getMessage(), AlertType.ERROR);
		}
	}
	
	
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
	
	public void initalizeNode() {
		tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		tableColumnPrato.setCellValueFactory(new PropertyValueFactory<>("nome"));	
	}
	
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

	public static void setVenda(Venda object) {
		venda = object; 
		
	}



}
