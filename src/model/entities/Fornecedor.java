/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 11/04/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.gerenciadores.GerenciadorProdutos;
/**
 * Classe para criação dos Fornecedores
 * @author Cláudia Inês Sales
 *
 */
public class Fornecedor {
	private static Integer ultimoId=1; // Salva o último ID utilizado, atributo pertencente a classe
	private Integer id;					// Id	do fornecedor
	private Integer cnpj;				// CNJP do fornecedor
	private String name;				// Nome do fornecedor
	private String endereco;			//Endereço do fornecedor
	private List<Integer> idProdutosFornecidos = new ArrayList<>(); //Lista contendo o ID dos produtos fornecidos
	
	/**
	 * Construtor do objeto fornecedor permitindo instanciar sem fornecer o ID
	 * @param cnpj CNPJ do Fornecedor
	 * @param name Nome do Fornecedor
	 * @param endereco Endereco do Fornecedor
	 * @param idProdutosFornecidos Lista contendo ID dos produtos fornecidos por ele
	 */
	
	public Fornecedor(Integer cnpj, String name, String endereco, List<Integer> idProdutosFornecidos) {
		this.id=ultimoId;
		this.cnpj = cnpj;
		this.name = name;
		this.endereco = endereco;
		this.idProdutosFornecidos = idProdutosFornecidos;
		ultimoId++;
		
	}
	
	/**
	 * Construtor do objeto fornecedor para instanciar junto com o ID,
	 * servirá para edição de usuários ja existentes
	 * 
	 * @param id Identidade do Fornecedor
	 * @param cnpj CNPJ do Fornecedor
	 * @param name Nome do Fornecedor
	 * @param endereco Endereço do Fornecedor
	 * @param idProdutosFornecidos Lista contendo ID dos produtos fornecidos por ele
	 */
	
	public Fornecedor(Integer id, Integer cnpj, String name, String endereco, List<Integer>idProdutosFornecidos) {
		this.id = id;
		this.cnpj = cnpj;
		this.name = name; 
		this.endereco = endereco;
		this.idProdutosFornecidos = idProdutosFornecidos;
	}
	
	/**
	 * Metódo para pegar o CNPJ do Fornecedor
	 * @return Integer com o CNPJ 
	 */

	public Integer getCnpj() {
		return cnpj;
	}

	/**
	 * Metódo para configurar o CNPJ do Fornecedor 
	 * @param cnpj CNPJ do Fornecedor
	 */
	
	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}
	
	/**
	 * Metódo para pegar nome do Fornecedor
	 * @return String - nome do Fornecedor 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metódo para configurar o nome do Fornecedor 
	 * @param name Nome do Fornecedor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metódo para pegar o endereço do Fornecedor
	 * @return String - Endereço do Fornecedor
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Metódo paa configurar o endereço do Fornecedor
	 * @param endereco Endereço do Fornecedor
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Metódo para pegar o ID do Fornecedor
	 * @return Integer - ID do Fornecedor
	 */
	
	public Integer getId() {
		return id;
	}


	/**
	 * Metódo para pegar todos os ID's dos produtos fornecidos
	 * @return Lista do tipo Integer - ID's dos produtos fornecidos
	 */
	public List<Integer> getIdProdutosFornecidos() {
		return idProdutosFornecidos;
	}

	/**
	* Metódo para configurar a lista de ID's dos produtos fornecidos 
	* @param idProdutosFornecidos Lista do tipo Integer com os ID's dos produtos fornecidos 
	*/
	public void setProdutosFornecidos(List<Integer> idProdutosFornecidos) {
		this.idProdutosFornecidos = idProdutosFornecidos;
	}

	/**
	 * Metódo para pegar o último ID utilizado na classe Fornecedor
	 * @return Integer - último ID utilizado
	 */
	public static Integer getUltimoId() {
		return ultimoId;
	}
	
	/**
	 * Metódo para configurar o último ID da classe, esse metódo só será utilizado 
	 * nos testes para fazer o resete
	 * @param ultimoId Ultimo ID que será referência para adição dos próximos
	 */
	public static void setUltimoId(Integer ultimoId) {
		Fornecedor.ultimoId = ultimoId;
	}
	
	/**
	 * Metódo para pegar as informações de um fornecedor
	 * @param f Objeto do tipo fornecedor
	 * @return Uma string contendo todas as informações do fornecedor
	 */
	public String infoFornecedor(Fornecedor f) {
		String info= "Código:" + f.getId() +"\nNome: " +f.getName() + "\nCnpj:" +f.getCnpj()+"\nEdenreço:"+
						f.getEndereco()+"\n Código de Produtos Fornecidos:";
		for(int i=0;i<idProdutosFornecidos.size();i++) {
			info+= idProdutosFornecidos.get(i)+", ";
		}
		return info;
		
	}
	
	public String getProdutos() {
		String produtos = "";
		
		for(Integer idProduto: this.idProdutosFornecidos) {
			if(GerenciadorProdutos.getProduto(idProduto)!=null) {
				produtos+="("+idProduto+")"+" "+GerenciadorProdutos.getProduto(idProduto).getNome()+",";
			}
		}
		
		produtos = produtos.substring(0, produtos.length()-1);
		return produtos;
	}
	
}
