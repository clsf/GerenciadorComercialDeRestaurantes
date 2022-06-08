package gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import model.entities.Produto;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Restringir;

public class ProdutosController implements Initializable {
	
	private static Integer count=0;
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	@FXML
	private Button voltar;
	
	@FXML
	private Button adicionar;
	
	@FXML
	private Button buscar;
	
	@FXML
	private TextField campoBusca;	
	
	@FXML 
	private RadioButton radioCodigo;
	
	@FXML 
	private RadioButton radioNome;
	
	
	@FXML
	private TableView<Produto> tableViewProduto;
	
	
	@FXML
	private TableColumn<Produto,String> tableColumnNome;
	
	@FXML
	private TableColumn<Produto,String> tableColumnValidade;
	
	@FXML
	private TableColumn<Produto,String> tableColumnQuantidade;
	@FXML
	private TableColumn<Produto,Integer> tableColumnCodigo;
	
	private ObservableList<Produto> obsList;
	
	@FXML
	private TableColumn<Produto,Produto> tableColumnEDIT;
	@FXML
	private TableColumn<Produto,Produto> tableColumnExcluir;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNode();
		if(count==0) {
		
			try {
				Date data1 = sdf1.parse("03/04/2022");
				Date data2 = sdf1.parse("04/03/2022");
				Produto p1 = new Produto("Refrigerante",5.0,data1,5.0);		
				GerenciadorProdutos.addOuEdit(p1);						
				Produto p2 = new Produto("Arroz", 4.0, data2,6.0);
				GerenciadorProdutos.addOuEdit(p2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		updateData();
		initInfoButtons();
		initExcluirButtons();
		radioCodigo.setSelected(true);
		radioNome.setSelected(false);
	
		
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
		List<Produto> list = new ArrayList<>();
		if(radioCodigo.isSelected()) {
			if(campoBusca.getText()!="") {
				try {
					Integer id = Integer.parseInt(campoBusca.getText());
					list.add(GerenciadorProdutos.getProduto(id));
					updateData(list);
				}catch(Exception e) {
					Alerts.showAlert("Dado inválido", "Dado inválido!","O id só pode ser um número inteiro" , AlertType.ERROR);
				}
			
			}
			else {
				updateData();
			}
		}else {	
			if(campoBusca.getText()!=null) {
				System.out.println(campoBusca.getText()+"oiii");
				String nome = campoBusca.getText();
				list = GerenciadorProdutos.buscar(nome);
				System.out.println(list.size());
				updateData(list);
				
			}
			else {
				updateData();
			}
		}
	}
	
	
	public void updateData() {	
		tableViewProduto.refresh();
		List<Produto> list = new ArrayList<>();
		list.addAll(GerenciadorProdutos.getListaDeProdutos());
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);	
		
	}
	
	public void updateData(List<Produto> produto) {	
		tableViewProduto.refresh();
		List<Produto> list = new ArrayList<>();
		list.addAll(produto);
		obsList = FXCollections.observableArrayList(list);
		tableViewProduto.setItems(obsList);	
		
	}
	
	 public void onBtAdicionarAction() throws IOException {
		 	ProdutosFormController.setProduto(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProdutosFormView.fxml"));
			Pane pane = loader.load();	

			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			updateData();
	 }
	 
		public void initalizeNode() {
			tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			tableColumnValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
			tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	}
		
		private void mudarTelaEditar(ActionEvent event,Produto produto) throws IOException {	
			
		 	ProdutosFormController.setProduto(produto);	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProdutosFormView.fxml"));
			Pane pane = loader.load();	

			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

			updateData();
			
			
		}
		
		private void onBtExcluir(ActionEvent event, Produto produto) {
			Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir Cliente?");
			
			if(opcao.get()==ButtonType.OK) {
				GerenciadorProdutos.remover(produto);
			}
			updateData();
		}
		
		private void initInfoButtons() {
			tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
			tableColumnEDIT.setCellFactory(param -> new TableCell<Produto, Produto>() {
				private final Button button = new Button("Edit");

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
		
		public void onBtVoltar(ActionEvent event) throws IOException {
			  root = FXMLLoader.load(getClass().getResource("/gui/MenuView.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
		}

}
