package gui;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Funcionario;
import model.entities.Gerente;
import model.entities.Usuario;
import model.gerenciadores.GerenciadorUsuarios;
import model.utils.Alerts;

public class UsuariosController implements Initializable{
	
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
	private TableView<Usuario> tableViewUsuario;
	
	
	@FXML
	private TableColumn<Usuario,String> tableColumnNome;
	
	@FXML
	private TableColumn<Usuario,String> tableColumnLogin;
	
	@FXML
	private TableColumn<Usuario,String> tableColumnCargo;
	@FXML
	private TableColumn<Usuario,Integer> tableColumnCodigo;
	
	private ObservableList<Usuario> obsList;
	
	@FXML
	private TableColumn<Usuario,Usuario> tableColumnEDIT;
	@FXML
	private TableColumn<Usuario,Usuario> tableColumnExcluir;

	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNode();	
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
		List<Usuario> list = new ArrayList<>();
		if(radioCodigo.isSelected()) {
			if(campoBusca.getText()!="") {
				try {
					Integer id = Integer.parseInt(campoBusca.getText());
					list.add(GerenciadorUsuarios.getUsuario(id));
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
				String nome = campoBusca.getText();
				list = GerenciadorUsuarios.buscar(nome);
				updateData(list);
				
			}
			else {
				updateData();
			}
		}
	}
		
	public void updateData() {
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(GerenciadorUsuarios.getListaDeUsuarios());
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	
	public void updateData(List<Usuario> usuario) {	
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(usuario);
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	

	 public void onBtAdicionarAction() throws IOException {
		 	UsuariosFormController.setUsuario(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UsuariosFormView.fxml"));
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
		tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		tableColumnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
	}
	
	private void mudarTelaEditar(ActionEvent event,Usuario usuario) throws IOException {
	
		  	UsuariosFormController.setUsuario(usuario);	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UsuariosFormView.fxml"));
			Pane pane = loader.load();	

			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

			updateData();
			
			
	}
	
	private void onBtExcluir(ActionEvent event, Usuario usuario) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir usuário?");
		
		if(opcao.get()==ButtonType.OK) {
			GerenciadorUsuarios.remover(usuario);
		}
		updateData();
	}
	
	public void onBtVoltar(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/gui/MenuView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	private void initInfoButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Usuario obj, boolean empty) {
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
		tableColumnExcluir.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = new Button("Excluir");

			@Override
			protected void updateItem(Usuario obj, boolean empty) {
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
