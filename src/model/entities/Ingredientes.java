/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 07/05/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package model.entities;

/**
 * Classe para criação de ingredientes que compõe o prato
 * @author Cláudia Inês Sales
 *
 */

import model.enums.UnidadeDeMedida;
import model.gerenciadores.GerenciadorProdutos;

public class Ingredientes {
	private Integer id; //Id do produto que faz parte do ingrediente
	private Double quantidade; //Quantidade do produto 
	private UnidadeDeMedida unidadeDeMeida;	//Unidade de medida podendo ser em KG ou L
	private String nomeProduto;

	/**
	 * Construtor do objeto Ingrediente 
	 * @param id ID do produto que compõe o ingrediente
	 * @param quantidade Quantidade do produto que compõe o ingrediente
	 * @param unidadeDeMeida Unidade de medida podendo ser em KG ou L
	 */

	public Ingredientes(Integer id, Double quantidade, UnidadeDeMedida unidadeDeMeida) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.unidadeDeMeida = unidadeDeMeida;
	}
	
	/**
	 * Metódo para pegar a quantidade do produto que é utilizado no ingrediente
	 * @return Double - Quantidade do Ingrediente
	 */

	public Double getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Metódo para alterar a quantidade de ingrediente
	 * @param quantidade Quantidade de ingrediente
	 */

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}


	/**
	 * Metódo para pegar a unidade de medida do ingrediente
	 * @return UnidadeDeMedida - Unidade de medida do ingrediente
	 */
	public UnidadeDeMedida getUnidadeDeMeida() {
		return unidadeDeMeida;
	}

	/**
	 * Metódo para alterar a unidade de medida do ingrediente
	 * @param unidadeDeMeida Unidade de medida do ingrediente
	 */
	public void setUnidadeDeMeida(UnidadeDeMedida unidadeDeMeida) {
		this.unidadeDeMeida = unidadeDeMeida;
	}
	
	/**
	 * Metódo para pegar o ID do produto que compõe o ingrediente
	 * @return Integer - ID do ingrediente
	 */

	public Integer getId() {
		return id;
	}
	
	public String getNomeProduto() {
		String nome;
		if(GerenciadorProdutos.getProduto(id)!=null) {
			nome = GerenciadorProdutos.getProduto(id).getNome();
		}else {
			nome = "Produto Excluido!";
		}
		return nome;
	}
	
	
}
