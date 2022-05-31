package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ViewUsuariosController implements Initializable{
	

	
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
	private TableView<Usuario> tableViewPessoa;
	
	
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
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNode();
		
		GerenciadorUsuarios.addOuEdit(new Gerente("nome","senha","login"));
		GerenciadorUsuarios.addOuEdit(new Funcionario("nome2","senha2","login2"));
	
		updateData();
		initInfoButtons();
	}
		
	public void updateData() {	
		tableViewPessoa.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(GerenciadorUsuarios.getListaDeUsuarios());
		obsList = FXCollections.observableArrayList(list);
		tableViewPessoa.setItems(obsList);	
		
	}
	

	 public void onBtAdicionarAction(ActionEvent event) throws IOException {
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
								System.out.print(obj.getNome());
								mudarTelaEditar(event,obj);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
				
			}
			
		});
		
	}
	
	
	
	
	
	

}
