package gui;

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
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorPratos;
import model.utils.Alerts;

public class VendasFormController implements Initializable {
	
	private static Venda venda;

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
		}
		else {
			textCodigo.setText(String.valueOf(venda.getId()));
			
		}
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
			venda.removerPrato(idPrato);
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
	
	public void updateData() {
		List<Prato> pratos = new ArrayList<>();
		for(Integer idPrato: venda.getItens()) {
			if(GerenciadorPratos.getPrato(idPrato)!=null) {
				pratos.add(GerenciadorPratos.getPrato(idPrato));
			}
		}		
		obsListPratoTable = FXCollections.observableArrayList(pratos);
		tableViewPratos.setItems(obsListPrato);
		
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



}
