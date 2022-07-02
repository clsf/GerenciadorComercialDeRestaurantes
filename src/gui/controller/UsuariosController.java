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
import model.utils.buttons;
/**
 * Controller da view de Usu�rios
 * @author Cl�udia In�s Sales Freitas
 *
 */
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
	
	//Caminho para os �cones de editar e remover
	public final String PEN_SOLID = "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" ;
	public final String Remove_Solid = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Met�do para inicializar a tela principal de fornecedores
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNode();	
		updateData();
		initInfoButtons();
		initExcluirButtons();
		radioCodigo.setSelected(true);
		radioNome.setSelected(false);
		
		
	}
	
	/**
	 * Met�do para par alterar o radio
	 */
	public void onRadioSelectedCodigo() {
		if(radioCodigo.isSelected()) {
			radioNome.setSelected(false);
		}else {
			radioNome.setSelected(true);		
		}
	}
	
	/**
	 * Met�do para par alterar o radio
	 */
	public void onRadioSelectedCNome() {	
		if(radioNome.isSelected()) {
			radioCodigo.setSelected(false);	
			
		}
		else {
			radioCodigo.setSelected(true);
			
		}
	}
	
	/**
	 * Met�do para buscar Usu�rio atrav�s do c�digo ou nome
	 */
	public void onBtBuscar() {
		List<Usuario> list = new ArrayList<>();
		if(radioCodigo.isSelected()) {
			if(campoBusca.getText()!="") {
				try {
					Integer id = Integer.parseInt(campoBusca.getText());
					list.add(GerenciadorUsuarios.getUsuario(id));
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
				list = GerenciadorUsuarios.buscar(nome);
				updateData(list);
				
			}
			else {
				updateData();
			}
		}
	}
	
	/**
	 * Met�do para atualizar a table view de Usu�rio a com todos os  usu�rios do gerenciador
	 */
	public void updateData() {
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(GerenciadorUsuarios.getListaDeUsuarios());
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	/**
	 * Met�do para atualizar a table view de Usu�rios a partir de uma lista de usu�rios especifica 
	 */
	public void updateData(List<Usuario> usuario) {	
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(usuario);
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	
	/**
	 * Met�do para chamar a tela de adicionar Usu�rios
	 * @throws IOException Caso n�o consiga abrir a tela
	 */
	 public void onBtAdicionarAction() throws IOException {
		 	UsuariosFormController.setUsuario(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/UsuariosFormView.fxml"));
			Pane pane = loader.load();	
			pane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			updateData();
	 }
	 
	 /**
	  * Fun��o para inicializar as tabelas, ou os n�s da tabela
	  */
	 
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		tableColumnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
	}
	
	/**
	 * Fun��o para chamar a tela de editar usu�rio
	 * @param event Evento 
	 * @param usuario Usu�rio a ser editado 
	 * @throws IOException Erro ao abrir a tela
	 */
	private void mudarTelaEditar(ActionEvent event,Usuario usuario) throws IOException {
	
		  	UsuariosFormController.setUsuario(usuario);	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/UsuariosFormView.fxml"));
			Pane pane = loader.load();	

			Stage dialogStage = new Stage();			
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);	
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

			updateData();
			
			
	}
	/**
	 * Fun��o para o bot�o excluir
	 * @param event Evento 
	 * @param usuario Usu�rio a ser exclu�do
	 */
	private void onBtExcluir(ActionEvent event, Usuario usuario) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir usu�rio?");
		
		if(opcao.get()==ButtonType.OK) {
			GerenciadorUsuarios.remover(usuario);
		}
		updateData();
	}
	
	/**
	 * Fun��o para o bot�o voltar
	 * @param event Evento
	 * @throws IOException Erro ao abrir a tela
	 */
	public void onBtVoltar(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/gui/view/MenuView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setScene(scene);
		  stage.setTitle("Menu principal");
		  stage.show();
	}
	
	/**
	 * Fun��o para inicializar os bot�es de editar
	 */
	private void initInfoButtons() {
		tableColumnEDIT.setStyle("-fx-alignment: CENTER");
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = buttons.createIconButton(PEN_SOLID,20);
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
	/**
	 *Fun��o para inicializar bot�es excluir para cada usu�rio na tabela
	 */
	private void initExcluirButtons() {
		tableColumnExcluir.setStyle("-fx-alignment: CENTER");
		tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnExcluir.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = buttons.createIconButton(Remove_Solid,20);

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
