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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.exceptions.DomainException;

/**
 * Classe para criação do objeto Produto
 * @author Cláudia Inês Sales
 *
 */

public class Produto {
	private static Integer ultimoId=1; // Salva o último ID utilizado, atributo pertencente a classe 
	private Integer id;				// Id do produto
	private String nome;			// Nome do produto
	private Double preco;			// Preço do produto
	private Date validade;       // Validade do produto 
	private Double quantidade;   //Quantidade dos ingredientes 
	 


	/**
	 * Construtor do objeto usuário permitindo instanciar sem fornecer o ID
	 * @param nome Nome do produto
	 * @param preco Preço do produto 
	 * @param validade Validade do produto 
	 */
	
	public Produto(String nome, Double preco,Date validade,Double quantidade) {
		super();
		this.id = ultimoId;
		this.nome = nome;
		this.preco = preco;
		this.validade = validade;
		this.quantidade = quantidade;
		ultimoId++;
	}

	/**
	 * Construtor do objeto usuário para instanciar junto com o ID,
	 * servirá para edição de usuários ja existentes 
	 * @param id Identidade do produto
	 * @param nome Nome do produto
	 * @param preco Preço do produto 
	 * @param validade Validade do produto 
	 */
	
	public Produto(Integer id, String nome, Double preco, Date validade, Double quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.validade = validade;
		this.quantidade = quantidade;
	}

	/**
	 * Metódo para pegar o nome do produto
	 * @return String - nome do produto
	 */

	public String getNome() {
		return nome;
	}
	
	/**
	 * Metódo para configurar o nome do produto 
	 * @param nome Nome do produto
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metódo para pegar o preço do produto
	 * @return Double - Preço do produto
	 */
	public Double getPreco() {
		return preco;
	}
	
	/**
	 * Metódo para configurar o preço do produto
	 * @param preco Preço do produto
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	/**
	 * Metódo para pegar validade do produto em dias
	 * @return  Integer - validade do produto
	 */
	public String getValidade() {
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		return sdf1.format(validade);
	}
	
	

	
	/**
	 * Metódo para configurar validade do produto em dias
	 * @param validade Validade do produto
	 */

	public void setValidade (Date validade) {
		this.validade = validade;
	}
	
	/**
	 * Metódo para pegar ID do produto
	 * @return Integer - ID do produto
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Metódo para pegar o último ID utilizado pela classe
	 * @return Integer - último ID
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
		Produto.ultimoId = ultimoId;
	}
	
	/**
	 * Metódo para pegar a quantidade do produto
	 * @return Quantidade em double do produto
	 */
	public Double getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Metódo para alterar a quantidade do produto
	 * @param quantidade Quantidade do produto
	 */
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * Metódo para atualizar o estoque do produto após uma venda
	 * @param quantidade Quantidade utilizada na produção do prato
	 * @throws DomainException Erro caso tente produzir o prato sem a quantidade do produto necessária
	 */
	public void atualizarEstoque(Double quantidade)throws DomainException {
		if(this.quantidade<quantidade) {
			throw new DomainException ("Não há quantidade suficiente do produto "+getNome());
		}
		else {
			setQuantidade(this.quantidade-quantidade);
		}
	}
	
	/**
	 * Metódo para pegar as informações de um produto específico 
	 * @param p Produto que terá as informações apresentadas
	 * @return String contendo as informações do produto
	 */

	public String infoProduto(Produto p) {
		SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
		String info= "Código:" + p.getId() +"\nNome: " +p.getNome() + "\nPreço:" +p.getPreco()+
						"\nValidade: "+sdf1.format(p.getValidade())+"\nQuantidade: "+p.getQuantidade();
		return info;
		
	}

	public Date getValidadeD() {
		
		return validade;
	}
	
	
}
