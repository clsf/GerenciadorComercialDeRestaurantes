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

import model.enums.CategoriaPrato;

/**
 * Classe para criação de objetos do tipo Prato (Que nada mais é do que a comida feita com
 * a junção dos produtos)
 * @author Cláudia Inês Sales
 *
 */
public class Prato {
	private static Integer ultimoId=1; // Salva o último ID utilizado, atributo pertencente a classe
	private Integer id;				// Id do Prato
	private String nome;			//Nome do Prato
	private Double preco;			//Preço do Prato
	private CategoriaPrato categoria; //Categoria podendo ser uma massa, entrada, bebida etc.
	private String descricao;		//Descrição do Prato 	

	private List<Ingredientes> ingredientes = new ArrayList<>(); //Lista de Ingredientes que compõe o prato
	
	/**
	 * Construtor do objeto Prato permitindo instanciar sem fornecer o ID
	 * @param nome Nome do prato
	 * @param preco Preço do prato
	 * @param categoria Categoria do prato, podendo ser uma massa, entrada, bebida etc
	 * @param descricao	Descrição do prato	
	 * @param ingredientes 	Lista contendo os ingredientes que compoem o prato
	 */
	
	public Prato(String nome, Double preco, CategoriaPrato categoria, String descricao, 
			List<Ingredientes> ingredientes) {
		this.id = ultimoId;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		this.descricao = descricao;
		this.ingredientes = ingredientes;
		ultimoId++;
	}
	
	/**
	 * Construtor do objeto Prato para instanciar junto com o ID,
	 * servirá para edição de Pratos ja existentes
	 * @param id Identidade do prato
	 * @param nome Nome do prato
	 * @param preco Preço do prato
	 * @param categoria Categoria do prato, podendo ser uma massa, entrada, bebida etc
	 * @param descricao	Descrição do prato	
	 * @param ingredientes 	Lista contendo os ingredientes que compoem o prato
	 */
	public Prato(Integer id, String nome, Double preco, CategoriaPrato categoria, String descricao,
			List<Ingredientes> ingredientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		this.descricao = descricao;
		this.ingredientes = ingredientes;
	}

	
	/**
	 * Metódo para pegar o nome do prato
	 * @return String - nome do prato
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metódo para configurar o nome do Prato
	 * @param nome Nome do Prato
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metódo para pegar preço do prato
	 * @return Double - preço do prato
	 */
	public Double getPreco() {
		return preco;
	}
	
	/**
	 * Metódo para configurar o preço do prato 
	 * @param preco Preço do prato
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	/**
	 * Metódo para pegar categoria do prato
	 * @return Objeto do tipo CategoriaPrato - Categoria do prato
	 */
	public CategoriaPrato getCategoria() {
		return categoria;
	}
	
	/**
	 * Metódo para configurar a categoria do prato
	 * @param categoria Objeto do tipo CategoriaPrato
	 */
	public void setCategoria(CategoriaPrato categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Metódo para pegar descrição do prato
	 * @return String - descrição do prato 
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Metódo para configurar a descrição do prato
	 * @param descricao String com a descrição do prato
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Metódo para pegar o ID do prato 
	 * @return Integer - Id do prato
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * Metódo para pegar o último ID utilizado pela classe	 * 
	 * @return Integer - último ID utilizado pela classe
	 */
	public static Integer getUltimoId() {
		return ultimoId;
	}

	/**
	 * Metódo para configurar o último ID da classe, esse metódo só será utilizado 
	 * nos testes para fazer o resete
	 * @param ultimoId Último ID que será referência para adição dos próximos
	 */
	public static void setUltimoId(Integer ultimoId) {
		Prato.ultimoId = ultimoId;
	}

	/**
	 * Metódo para pegar a lista de ingredientes
	 * @return Uma lista do tipo Ingredientes contendo os ingredientes
	 */
	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}
	/**
	 * Metódo para alterar a lista de Ingredientes
	 * @param ingredientes Lista do tipo ingredientes
	 */

	public void setIngredientes(List<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	/**
	 * Metódo para visualizar as informações de um prato
	 * @param p Prato 
	 * @return String contendo as informações do prato
	 */
	
	public String infoPrato(Prato p) {
		String info= "Código:" + p.getId() +"\nNome: "+p.getNome() + "\nDescrição:" +p.getDescricao()+"\nCategoria do prato: "+p.getCategoria()+
					"\nPreço: "+p.getPreco()+" R$"+"\nIngredientes: ";
		for(int i=0;i<ingredientes.size();i++) {
			info+=ingredientes.get(i).getId()+", ";
		}
		return info;
		
	}
	

}
