/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 11/04/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/
package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.enums.CategoriaPrato;

/**
 * Classe para cria��o de objetos do tipo Prato (Que nada mais � do que a comida feita com
 * a jun��o dos produtos)
 * @author Cl�udia In�s Sales
 *
 */
public class Prato {
	private static Integer ultimoId=1; // Salva o �ltimo ID utilizado, atributo pertencente a classe
	private Integer id;				// Id do Prato
	private String nome;			//Nome do Prato
	private Double preco;			//Pre�o do Prato
	private CategoriaPrato categoria; //Categoria podendo ser uma massa, entrada, bebida etc.
	private String descricao;		//Descri��o do Prato 	

	private List<Ingredientes> ingredientes = new ArrayList<>(); //Lista de Ingredientes que comp�e o prato
	
	/**
	 * Construtor do objeto Prato permitindo instanciar sem fornecer o ID
	 * @param nome Nome do prato
	 * @param preco Pre�o do prato
	 * @param categoria Categoria do prato, podendo ser uma massa, entrada, bebida etc
	 * @param descricao	Descri��o do prato	
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
	 * servir� para edi��o de Pratos ja existentes
	 * @param id Identidade do prato
	 * @param nome Nome do prato
	 * @param preco Pre�o do prato
	 * @param categoria Categoria do prato, podendo ser uma massa, entrada, bebida etc
	 * @param descricao	Descri��o do prato	
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
	 * Met�do para pegar o nome do prato
	 * @return String - nome do prato
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Met�do para configurar o nome do Prato
	 * @param nome Nome do Prato
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Met�do para pegar pre�o do prato
	 * @return Double - pre�o do prato
	 */
	public Double getPreco() {
		return preco;
	}
	
	/**
	 * Met�do para configurar o pre�o do prato 
	 * @param preco Pre�o do prato
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	/**
	 * Met�do para pegar categoria do prato
	 * @return Objeto do tipo CategoriaPrato - Categoria do prato
	 */
	public CategoriaPrato getCategoria() {
		return categoria;
	}
	
	/**
	 * Met�do para configurar a categoria do prato
	 * @param categoria Objeto do tipo CategoriaPrato
	 */
	public void setCategoria(CategoriaPrato categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Met�do para pegar descri��o do prato
	 * @return String - descri��o do prato 
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Met�do para configurar a descri��o do prato
	 * @param descricao String com a descri��o do prato
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Met�do para pegar o ID do prato 
	 * @return Integer - Id do prato
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * Met�do para pegar o �ltimo ID utilizado pela classe	 * 
	 * @return Integer - �ltimo ID utilizado pela classe
	 */
	public static Integer getUltimoId() {
		return ultimoId;
	}

	/**
	 * Met�do para configurar o �ltimo ID da classe, esse met�do s� ser� utilizado 
	 * nos testes para fazer o resete
	 * @param ultimoId �ltimo ID que ser� refer�ncia para adi��o dos pr�ximos
	 */
	public static void setUltimoId(Integer ultimoId) {
		Prato.ultimoId = ultimoId;
	}

	/**
	 * Met�do para pegar a lista de ingredientes
	 * @return Uma lista do tipo Ingredientes contendo os ingredientes
	 */
	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}
	/**
	 * Met�do para alterar a lista de Ingredientes
	 * @param ingredientes Lista do tipo ingredientes
	 */

	public void setIngredientes(List<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	/**
	 * Met�do para visualizar as informa��es de um prato
	 * @param p Prato 
	 * @return String contendo as informa��es do prato
	 */
	
	public String infoPrato(Prato p) {
		String info= "C�digo:" + p.getId() +"\nNome: "+p.getNome() + "\nDescri��o:" +p.getDescricao()+"\nCategoria do prato: "+p.getCategoria()+
					"\nPre�o: "+p.getPreco()+" R$"+"\nIngredientes: ";
		for(int i=0;i<ingredientes.size();i++) {
			info+=ingredientes.get(i).getId()+", ";
		}
		return info;
		
	}
	

}
