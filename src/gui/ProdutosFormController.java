package gui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Produto;
import model.entities.Usuario;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Restringir;

public class ProdutosFormController implements Initializable {
	
	private static Produto produto;
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cancelar;
	
	@FXML
	private TextField textCodigo;
	
	@FXML
	private TextField textNome;
	
	@FXML
	private TextField textQuantidade;
	
	@FXML
	private TextField textValidade;
	
	@FXML
	private TextField textPreco;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(produto==null) {
			textCodigo.setText(String.valueOf(Produto.getUltimoId()));
		}else {
			textCodigo.setText(String.format("%d",produto.getId()));
			textQuantidade.setText(String.format("%f",produto.getQuantidade()));
			textValidade.setText(produto.getValidade());
			textNome.setText(produto.getNome());
			textPreco.setText(String.format("%f",produto.getPreco()));

		}		
		Restringir.setTextFieldDouble(textPreco);
	}
	
	public void onBtSalvar() throws NumberFormatException, ParseException {
		Locale.setDefault(Locale.US);
		if(produto!=null) {			
				Produto pEdit = new Produto(Integer.valueOf(textCodigo.getText()),textNome.getText(),Double.parseDouble(textPreco.getText()),sdf1.parse(textValidade.getText()),Double.parseDouble(textQuantidade.getText()));
				GerenciadorProdutos.addOuEdit(pEdit);
				
		}
		else {
			Produto pEdit = new Produto(textNome.getText(),Double.parseDouble(textPreco.getText()),sdf1.parse(textValidade.getText()),Double.parseDouble(textQuantidade.getText()));
			GerenciadorProdutos.addOuEdit(pEdit);
		}
		
	    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
	    stage.close(); //Fechando o Stage
		
	}
	
	public void onBtCancelar() throws IOException {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	}

	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		ProdutosFormController.produto = produto;
	}
	
	
	

}
