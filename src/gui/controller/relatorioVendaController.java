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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Venda;
import model.enums.CategoriaPrato;
import model.exceptions.DomainException;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Alerts;
import model.utils.Relatorios;
/***
 * Controller da view de relatório de Venda 
 * @author Cláudia Inês Sales Freitas
 *
 */
public class relatorioVendaController implements Initializable {
	SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
	
    @FXML
    private Button btGerar;

    @FXML
    private CheckBox checkGeral;

    @FXML
    private CheckBox checkPeriodo;

    @FXML
    private CheckBox checkPrato;

    @FXML
    private TextField textPeriodo;

    @FXML
    private ComboBox<CategoriaPrato> comboCategoria;
    
    @FXML 
    private ObservableList<CategoriaPrato> obsList;
    
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
	 /**
	  * Método para inicializar a view
	  */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkGeral.setSelected(true);
		initializeCombo();
		comboCategoria.setValue(CategoriaPrato.ENTRADA);
		
	}
	/**
	 * Método para inicializar a comboBox
	 */
	public void initializeCombo() {
		List<CategoriaPrato> categorias = new ArrayList<>(EnumSet.allOf(CategoriaPrato.class));
		obsList = FXCollections.observableArrayList(categorias);
		comboCategoria.setItems(obsList);
	}
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	public void onCheckGeral(ActionEvent event) {
		if(checkGeral.isSelected()) {
			checkPrato.setSelected(false);
			checkPeriodo.setSelected(false);
		}			
	}
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	public void onCheckPrato(ActionEvent event) {
		if(checkPrato.isSelected()) {
			checkGeral.setSelected(false);
			checkPeriodo.setSelected(false);
		}			
	}
	/**
	 * Método para alterar as ativações da checkBox
	 * @param event Evento
	 */
	public void onCheckPeriodo(ActionEvent event) {
		if(checkPeriodo.isSelected()) {
			checkPrato.setSelected(false);
			checkGeral.setSelected(false);
		}			
	}
	
	/**
	 * Método do botão Gerar relatório
	 * @param event Evento
	 * @throws IOException Erro 
	 */
	
	public void onBtGerar(ActionEvent event) throws IOException {
		List<Venda> lista = new ArrayList<>();
		if(checkGeral.isSelected()) {
			lista = GerenciadorVendas.getListaDeVendas();
			VendasImprimirController.setVendas(lista);
			VendasImprimirController.setTipo(1);
		}else if(checkPeriodo.isSelected()) {
			if(textPeriodo.getText()!="") {
				try {
					lista = Relatorios.relatorioVendaPorPeriodo(sdf1.parse(textPeriodo.getText()));
					VendasImprimirController.setVendas(lista);
					VendasImprimirController.setTipo(2);
				} catch (ParseException e) {
					Alerts.showAlert("Data inválida", "Data inválida!","Digite uma data no formato mm/aaaa" , AlertType.ERROR);
				}
			}
			
		
		
		}else {
			
			try {
				lista = Relatorios.relatorioVendaPorPrato(comboCategoria.getValue());
			} catch (DomainException e) {
				Alerts.showAlert("ERRO!", "Erro ao buscar prato",e.getMessage() , AlertType.INFORMATION);
			}
			VendasImprimirController.setVendas(lista);
			VendasImprimirController.setCategoria(comboCategoria.getValue());
			VendasImprimirController.setTipo(3);
			
		}
		if(lista.size()!=0){
			  root = FXMLLoader.load(getClass().getResource("/gui/view/VendasImprimirView.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setResizable(false);
			  stage.setScene(scene);
			  stage.show();
		}
		
		else {
		Alerts.showAlert("Vendas vazias", "Sem vendas!","Não há vendas cadastradas ou não há vendas cadastradas nesse período." , AlertType.INFORMATION);

	}
	}
	
	
	
	
	 
}
