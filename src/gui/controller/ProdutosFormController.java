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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.entities.Produto;
import model.entities.Usuario;
import model.exceptions.DomainException;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Alerts;
import model.utils.Restringir;
/**
 * Controller da view de formul�rio de produtos
 * @author Cl�udia In�s Sales Freitas
 *
 */
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


	
	/**
	 * Met�do para inicializar o formul�rio de clientes
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(produto==null) {
			textCodigo.setText(String.valueOf(Produto.getUltimoId()));
		}else {
			textCodigo.setText(String.format("%d",produto.getId()));
			textQuantidade.setText(String.format("%.2f",produto.getQuantidade()));
			textValidade.setText(produto.getValidade());
			textNome.setText(produto.getNome());
			textPreco.setText(String.format("%.2f",produto.getPreco()));

		}		
		restringir();
	}
	/**
	 * Met�do para restringir as caixas de entrada do usu�rio
	 */
	private void restringir() {
		Restringir.setTextFieldDouble(textPreco);
		Restringir.setTextFieldDouble(textQuantidade);
	}
	
	/**
	 * Fun��o para ao clicar no bot�o salvar, salvar o Cliente no gerenciador
	 */
	
	public void onBtSalvar() throws NumberFormatException, ParseException {
		Locale.setDefault(Locale.US);
		boolean fechar = false;
		if(produto!=null) {	
				try {
					Date data = sdf1.parse(textValidade.getText());
					GerenciadorProdutos.cadastrarProduto(Integer.valueOf(textCodigo.getText()),textNome.getText(),Double.parseDouble(textPreco.getText()),data,Double.parseDouble(textQuantidade.getText()));
					fechar = true;
				}catch (DomainException e) {				
					Alerts.showAlert("Erro!", null, e.getLocalizedMessage(), AlertType.ERROR);
				}catch (ParseException e) {
					Alerts.showAlert("Erro!", null, "Data inv�lida! Utilize o formato dd/mm/aaaa", AlertType.ERROR);
				}
				
				
		}
		else {
			try {
				Date data = sdf1.parse(textValidade.getText());
				GerenciadorProdutos.cadastrarProduto(null,textNome.getText(),Double.parseDouble(textPreco.getText()),data,Double.parseDouble(textQuantidade.getText()));				
				fechar=true;
			}
			catch (DomainException e) {				
				Alerts.showAlert("Erro!", null, e.getLocalizedMessage(), AlertType.ERROR);
			}catch (ParseException e) {
				Alerts.showAlert("Erro!", null, "Data inv�lida! Utilize o formato dd/mm/aaaa", AlertType.ERROR);
			}
			
		}
		if(fechar) {
		    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}

		
	}
	
	/**
	 * Fun��o para o bot�o cancelar, fechando a tela atual
	 * @throws IOException Erro ao obter tela
	 */
	public void onBtCancelar() throws IOException {
		Optional<ButtonType> opcao = Alerts.showConfirmation("Sim","Cancelar?");
		
		if(opcao.get()==ButtonType.OK) {
			Stage stage = (Stage) cancelar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}
	}
	/**
	 * Met�do para pegar o produto que est� sendo editado
	 * @return
	 */
	public static Produto getProduto() {
		return produto;
	}

	/**
	 * Met�do para alterar o produto do controllador
	 * @param produto
	 */
	public static void setProduto(Produto produto) {
		ProdutosFormController.produto = produto;
	}	

}
