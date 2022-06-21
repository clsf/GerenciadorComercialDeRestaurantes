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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.entities.Venda;
import model.enums.FormaDePagamento;
import model.enums.StatusDaVenda;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Alerts;
import model.utils.buttons;

public class VendasController implements Initializable {
	
    @FXML
    private Button adicionar;
    
    @FXML
    private Button btRelatorios;

    @FXML
    private Button buscar;

    @FXML
    private TextField campoBusca;

    @FXML
    private Label campoBuscaNome;

    @FXML
    private RadioButton radioCodigo;

    @FXML
    private RadioButton radioNome;

    @FXML
    private TableColumn<Venda, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<Venda, String> tableColumnData;

    @FXML
    private TableColumn<Venda, Venda> tableColumnEDIT;

    @FXML
    private TableColumn<Venda, Venda> tableColumnExcluir;

    @FXML
    private TableColumn<Venda, FormaDePagamento> tableColumnPagamento;

    @FXML
    private TableColumn<Venda, Double> tableColumnPreco;

    @FXML
    private TableView<Venda> tableViewVenda;
    
    @FXML
    private TableColumn<Venda,Cliente> tableColumnCliente;
    
    @FXML
    private TableColumn<Venda,StatusDaVenda> tableColumnStatus;

    @FXML
    private Button voltar;
    
    @FXML
    private ObservableList obsList;
    
	public final String PEN_SOLID = "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" ;
	public final String Remove_Solid = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNode();		
		initInfoButtons();
		initExcluirButtons();
		radioCodigo.setSelected(true);
		radioNome.setSelected(false);
		
		updateData();
		
	}
	
	public void onRadioSelectedCodigo() {
		if(radioCodigo.isSelected()) {
			radioNome.setSelected(false);
			

		}else {
			radioNome.setSelected(true);
			
			
		}
	}
	
	public void onRadioSelectedCNome() {	
		if(radioNome.isSelected()) {
			radioCodigo.setSelected(false);	
			
		}
		else {
			radioCodigo.setSelected(true);
			
		}
	}
	
	public void onBtBuscar() {
		List<Venda> list = new ArrayList<>();
		if(radioCodigo.isSelected()) {
			if(campoBusca.getText()!="") {
				try {
					Integer id = Integer.parseInt(campoBusca.getText());
					list.add(GerenciadorVendas.getVenda(id));
					updateData(list);
				}catch(Exception e) {
					Alerts.showAlert("Dado inv�lido", "Dado inv�lido!","O id s� pode ser um n�mero inteiro" , AlertType.ERROR);
				}
			
			}
			else {
				updateData();
			}
		}else {
			if(campoBusca.getText()!="") {
				String nome = campoBusca.getText();
				list = GerenciadorVendas.buscarCliente(nome);
				updateData(list);
				
			}
			else {
				updateData();
			}
		}
	}
	
	public void updateData() {
		tableViewVenda.refresh();
		List<Venda> list = new ArrayList<>();
		list.addAll(GerenciadorVendas.getListaDeVendas());
		obsList = FXCollections.observableArrayList(list);
		tableViewVenda.setItems(obsList);	
		
	}
	
	public void updateData(List<Venda> venda) {	
		tableViewVenda.refresh();
		List<Venda> list = new ArrayList<>();
		list.addAll(venda);
		obsList = FXCollections.observableArrayList(list);
		tableViewVenda.setItems(obsList);	
		
	}
	
	 public void onBtAdicionarAction() throws IOException {
		 	VendasFormController.setVenda(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/VendasFormView.fxml"));
			Pane pane = loader.load();	
			pane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setTitle("Vendas");
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			updateData();
	 }
	 
		public void initalizeNode() {
			tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
			tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
			tableColumnPagamento.setCellValueFactory(new PropertyValueFactory<>("formaDePagamento"));
			tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
			tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		}
		
		private void mudarTelaEditar(ActionEvent event,Venda venda) throws IOException {
			VendasFormController.setVenda(venda);	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/VendasFormView.fxml"));
			Pane pane = loader.load();	
		    pane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.setTitle("Vendas");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

			updateData();
			
			
		}
		
		private void onBtExcluir(ActionEvent event, Venda venda) {
			Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir a venda?");
			
			if(opcao.get()==ButtonType.OK) {
				GerenciadorVendas.remover(venda);;
			}
			updateData();
		}
		
		public void onBtVoltar(ActionEvent event) throws IOException {
			  root = FXMLLoader.load(getClass().getResource("/gui/view/MenuView.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
			  stage.setScene(scene);
			  stage.setTitle("Menu principal");
			  stage.show();
		}
		
		private void initInfoButtons() {
			tableColumnEDIT.setStyle("-fx-alignment: CENTER");
			tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
			tableColumnEDIT.setCellFactory(param -> new TableCell<Venda, Venda>() {
				private final Button button = buttons.createIconButton(PEN_SOLID,20);

				@Override
				protected void updateItem(Venda obj, boolean empty) {
					super.updateItem(obj, empty);
					if (obj == null) {
						setGraphic(null);
						return;
					}
					setGraphic(button);
					button.setOnAction(
							event -> {
								try {								
									mudarTelaEditar(event,obj);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}});
					
				}
				
			});
			
		}
		
		private void initExcluirButtons() {
			tableColumnExcluir.setStyle("-fx-alignment: CENTER");
			tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
			tableColumnExcluir.setCellFactory(param -> new TableCell<Venda, Venda>() {
				private final Button button = buttons.createIconButton(Remove_Solid,20);

				@Override
				protected void updateItem(Venda obj, boolean empty) {
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
		
	public void obBtRelatorios(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/relatorioVendaView.fxml"));
		Pane pane = loader.load();	
		pane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		Stage dialogStage = new Stage();			
		dialogStage.setScene(new Scene(pane));
		dialogStage.setResizable(false);	
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.showAndWait();
	}
}