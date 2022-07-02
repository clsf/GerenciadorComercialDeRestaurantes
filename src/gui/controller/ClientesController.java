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
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
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
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.gerenciadores.GerenciadorClientes;
import model.utils.Alerts;
import model.utils.buttons;
/**
 * Controlador da tela principal de clientes
 * @author Cl�udia In�s Sales Freitas
 *
 */
public class ClientesController implements Initializable {
	
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
	private TableView<Cliente> tableViewCliente;
	
	
	@FXML
	private TableColumn<Cliente,String> tableColumnNome;
	
	@FXML
	private TableColumn<Cliente,String> tableColumnEmail;
	
	@FXML
	private TableColumn<Cliente,Integer> tableColumnTelefone;
	@FXML
	private TableColumn<Cliente,Integer> tableColumnCodigo;
	
	private ObservableList<Cliente> obsList;
	
	@FXML
	private TableColumn<Cliente,Cliente> tableColumnEDIT;
	@FXML
	private TableColumn<Cliente,Cliente> tableColumnExcluir;
	
	//Caminho para os �cones de editar e remover
	public final String PEN_SOLID = "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" ;
	public final String Remove_Solid = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Met�do para inicializar a tela principal de clientes
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
	 * Met�do para buscar Cliente atrav�s do c�digo ou nome
	 */
	public void onBtBuscar() {
		List<Cliente> list = new ArrayList<>();
		if(radioCodigo.isSelected()) {
			if(campoBusca.getText()!="") {
				try {
					Integer id = Integer.parseInt(campoBusca.getText());
					list.add(GerenciadorClientes.getCliente(id));
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
				list = GerenciadorClientes.buscar(nome);
				updateData(list);
				
			}
			else {
				updateData();
			}
		}
	}
	/**
	 * Met�do para atualizar a table view de Clientes a com todos os  clientes no gerenciador
	 */
	
	public void updateData() {	
		tableViewCliente.refresh();
		List<Cliente> list = new ArrayList<>();
		list.addAll(GerenciadorClientes.getListaDeClientes());
		obsList = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsList);	
		
	}
	/**
	 * Met�do para atualizar a table view de Cliente a partir de uma lista de Clientes especifica
	 * @param cliente
	 */
	public void updateData(List<Cliente> cliente) {	
		tableViewCliente.refresh();
		List<Cliente> list = new ArrayList<>();
		list.addAll(cliente);
		obsList = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsList);	
		
	}
	
	/**
	 * Met�do para chamar a tela de adicionar Clientes
	 * @throws IOException Caso n�o consiga abrir a tela
	 */
	
	 public void onBtAdicionarAction() throws IOException {
		 	ClientesFormController.setCliente(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/ClientesFormView.fxml"));
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
			tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}
	
	/**
	 * Fun��o para chamar a tela de editar cliente
	 * @param event Evento
	 * @param cliente Cliente a ser editado
	 * @throws IOException Erro ao abrir a tela
	 */
	
	private void mudarTelaEditar(ActionEvent event,Cliente cliente) throws IOException {	
	
	 	ClientesFormController.setCliente(cliente);	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/ClientesFormView.fxml"));
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
	 * Fun��o para o bot�o excluir cliente
	 * @param event Evento
	 * @param cliente Cliente a ser exclu�do
	 */
	private void onBtExcluir(ActionEvent event, Cliente cliente) {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Deseja realmente excluir Cliente?");
		
		if(opcao.get()==ButtonType.OK) {
			GerenciadorClientes.remover(cliente);
		}
		updateData();
	}
	/**
	 * Fun��o para inicializar os bot�es de editar
	 */
	private void initInfoButtons() {
		tableColumnEDIT.setStyle("-fx-alignment: CENTER");
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Cliente, Cliente>() {
			private final Button button = createIconButton(PEN_SOLID,20);

			@Override
			protected void updateItem(Cliente obj, boolean empty) {
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
	 *Fun��o para inicializar bot�es excluir para cada Cliente na tabela
	 */
	private void initExcluirButtons() {
		tableColumnExcluir.setStyle("-fx-alignment: CENTER");
		tableColumnExcluir.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnExcluir.setCellFactory(param -> new TableCell<Cliente, Cliente>() {
			private final Button button = createIconButton(Remove_Solid, 20);

			@Override
			protected void updateItem(Cliente obj, boolean empty) {
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
	 * Met�do para colocar �cones como bot�es 
	 * @param svgAbsolutePath Caminho para o �cone
	 * @param size tamanho do �cone
	 * @return Bot�o
	 */
	
	  // Cria um bot�o com o �cone svg dentro
	  public static Button createIconButton(String svgAbsolutePath, int size) {
	    SVGPath path = new SVGPath();
	    path.setContent(svgAbsolutePath);
	    Bounds bounds = path.getBoundsInLocal();

	    // scale to size size x size (max)
	    double scaleFactor = size / Math.max(bounds.getWidth(), bounds.getHeight());
	    path.setScaleX(scaleFactor);
	    path.setScaleY(scaleFactor);
	   // path.getStyleClass().add(colorClassName); // define a cor do �cone

	    Button button = new Button();
	    button.setPickOnBounds(true); // Garente que o bot�o ter� o fundo transparente
	    button.setGraphic(path); // inseri o �cone gerado pelo svg no bot�o
	    button.setAlignment(Pos.CENTER);
	    button.getStyleClass().add("icon-button"); // classe criada para n�o ter o fundo cinza
	    // necess�rios para o �cone ficar contido dentro do bot�o
	    button.setMaxWidth(size);
	    button.setMaxHeight(size);
	    button.setMinWidth(size);
	    button.setMinHeight(size);
	    button.setPrefWidth(size);
	    button.setPrefHeight(size);
	    return button;
	  }
	/**
	 * Met�do acionado ao clicar no bot�o voltar, retorna a tela de Menu
	 * @param event Evento
 	 * @throws IOException Erro ao abrir a tela
	 */
	
		
	public void onBtVoltar(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("/gui/view/MenuView.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();		 
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm()); 
		  stage.setScene(scene);
		  stage.show();
	}
		
		
	
	

}
