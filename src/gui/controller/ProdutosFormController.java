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
 * Controller da view de formulário de produtos
 * @author Cláudia Inês Sales Freitas
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
	 * Metódo para inicializar o formulário de clientes
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
	 * Metódo para restringir as caixas de entrada do usuário
	 */
	private void restringir() {
		Restringir.setTextFieldDouble(textPreco);
		Restringir.setTextFieldDouble(textQuantidade);
	}
	
	/**
	 * Função para ao clicar no botão salvar, salvar o Cliente no gerenciador
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
					Alerts.showAlert("Erro!", null, "Data inválida! Utilize o formato dd/mm/aaaa", AlertType.ERROR);
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
				Alerts.showAlert("Erro!", null, "Data inválida! Utilize o formato dd/mm/aaaa", AlertType.ERROR);
			}
			
		}
		if(fechar) {
		    Stage stage = (Stage) salvar.getScene().getWindow(); //Obtendo a janela atual
		    stage.close(); //Fechando o Stage
		}

		
	}
	
	/**
	 * Função para o botão cancelar, fechando a tela atual
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
	 * Metódo para pegar o produto que está sendo editado
	 * @return
	 */
	public static Produto getProduto() {
		return produto;
	}

	/**
	 * Metódo para alterar o produto do controllador
	 * @param produto
	 */
	public static void setProduto(Produto produto) {
		ProdutosFormController.produto = produto;
	}	

}
