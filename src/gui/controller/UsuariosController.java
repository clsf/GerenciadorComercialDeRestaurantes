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
 * Controller da view de Usuários
 * @author Cláudia Inês Sales Freitas
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
	
	//Caminho para os ícones de editar e remover
	public final String PEN_SOLID = "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" ;
	public final String Remove_Solid = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Metódo para inicializar a tela principal de fornecedores
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
	 * Metódo para par alterar o radio
	 */
	public void onRadioSelectedCodigo() {
		if(radioCodigo.isSelected()) {
			radioNome.setSelected(false);
		}else {
			radioNome.setSelected(true);		
		}
	}
	
	/**
	 * Metódo para par alterar o radio
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
	 * Metódo para buscar Usuário através do código ou nome
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
					Alerts.showAlert("Dado inválido", "Dado inválido!","O id só pode ser um número inteiro" , AlertType.ERROR);
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
	 * Metódo para atualizar a table view de Usuário a com todos os  usuários do gerenciador
	 */
	public void updateData() {
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(GerenciadorUsuarios.getListaDeUsuarios());
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	/**
	 * Metódo para atualizar a table view de Usuários a partir de uma lista de usuários especifica 
	 */
	public void updateData(List<Usuario> usuario) {	
		tableViewUsuario.refresh();
		List<Usuario> list = new ArrayList<>();
		list.addAll(usuario);
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);	
		
	}
	
	/**
	 * Metódo para chamar a tela de adicionar Usuários
	 * @throws IOException Caso não consiga abrir a tela
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
	  * Função para inicializar as tabelas, ou os nós da tabela
	  */
	 
	public void initalizeNode() {
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		tableColumnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
	}
	
	/**
	 * Função para chamar a tela de editar usuário
	 * @param event Evento 
	 * @param usuario Usuário a ser editado 
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
	 * Função para o botão excluir
	 * @param event Evento 
	 * @param usuario Usuário a ser excluído
	 */
	private void onBtExcluir(ActionEvent event, Usuario usuario) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir usuário?");
		
		if(opcao.get()==ButtonType.OK) {
			GerenciadorUsuarios.remover(usuario);
		}
		updateData();
	}
	
	/**
	 * Função para o botão voltar
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
	 * Função para inicializar os botões de editar
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
	 *Função para inicializar botões excluir para cada usuário na tabela
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
