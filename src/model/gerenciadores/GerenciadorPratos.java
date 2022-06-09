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
package model.gerenciadores;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.*;
import model.entities.Ingredientes;
import model.entities.Prato;
import model.entities.Produto;
import model.enums.CategoriaPrato;
import model.enums.UnidadeDeMedida;

/**
 * Classe para criação do objeto Gerenciador de Pratos
 * @author Cláudia Inês Sales
 *
 */
public class GerenciadorPratos {	
		
	private static List <Prato> listaDePratos = new ArrayList<>();//Lista contendo os Pratos

	
	/**
	 * Construtor para inicializar o Gerenciador de Pratos
	 */
	public GerenciadorPratos() {
		
	}	

	/**
	 * Metódo para adicionar o prato na lista 
	 * @param prato Objeto do tipo Prato
	 */
	private static void add(Prato prato) {
		listaDePratos.add(prato);		
	}
	
	/**
	 *  Metódo para editar prato já existente na lista
	 * @param pratoEdit Prato que já existe e será editado
	 * @param alterarPrato Prato que será utilizado como parâmetro para substituição
	 */
	
	private static void editar(Prato pratoEdit, Prato alterarPrato) {		
		//Troca a categoria do prato se for diferente
		if(pratoEdit.getCategoria() != alterarPrato.getCategoria()) {
			pratoEdit.setCategoria(alterarPrato.getCategoria());
		}
		//Troca descrição do prato se for diferente
		if(pratoEdit.getDescricao() != alterarPrato.getDescricao()) {
			pratoEdit.setDescricao(alterarPrato.getDescricao());
		}
		//Troca nome do prato se for diferente
		if(pratoEdit.getNome() != alterarPrato.getNome()) {
			pratoEdit.setNome(alterarPrato.getNome());
		}
		//Troca o preço do prato se for diferente
		if(pratoEdit.getPreco() != alterarPrato.getPreco()) {
			pratoEdit.setPreco(alterarPrato.getPreco());
		}
		if(pratoEdit.getIngredientes() != alterarPrato.getIngredientes()) {
			pratoEdit.setIngredientes(alterarPrato.getIngredientes());
		}
		
	}
	
	/**
	 * Metódo que será utilizado por outras classes para adicionar ou editar um prato
	 * Nele o objeto passado será verificado se já existe na lista, se não existir será
	 * adicionado, caso já exista será editado
	 * @param prato Prato que será adicionado ou editado
	 */
	
	public static void addOuEdit(Prato prato) {
		//Procura a existência do prato na lista através do ID
		Prato pratoExistente = GerenciadorPratos.listaDePratos.stream().filter(x->x.getId() == prato.getId())
				.findFirst().orElse(null);
		//Se o prato já existir na lista será editado, se não será adicionado
		if(pratoExistente != null) {
			editar(pratoExistente, prato);
		}
		else {
			add(prato);
		}

	}
	
	/**
	 * Metódo para remover prato da lista através do ID dele
	 * @param id ID do prato
	 */
	public static void remover(Integer id) {
		Prato result = GerenciadorPratos.listaDePratos.stream().filter(x->x.getId() == id).findFirst().orElse(null);
		
		if(result != null) {
			GerenciadorPratos.listaDePratos.remove(result);
		}
	
	}
	
	/**
	 * Metódo para pegar a lista completa dos pratos
	 * @return Lista Prato - Lista de pratos existentes no gerenciador
	 */

	public static List<Prato> getPrato(){
		return GerenciadorPratos.listaDePratos; 
	}
	
	/**
	 * Metódo para recuperar um prato específio na lista de pratos
	 * @param id ID do prato a ser recuperado
	 * @return Prato - Objeto do tipo prato
	 */
	public static Prato getPrato(Integer id) {
		Prato prato = GerenciadorPratos.listaDePratos.stream().filter(x->x.getId() == id).findFirst().orElse(null);
		return prato;
	}
	
	/**
	 * Metódo para fazer a listagem dos fornecedores
	 * @return String - Listagem completa dos fornecedores
	 */
	
	public static String listagem() {
		
		String listagem= " ";
		
		for(Prato prato : GerenciadorPratos.listaDePratos) {
			listagem +="\nCódigo: "+ prato.getId()+"\nNome: " + prato.getNome() + "\nCategoria: " + prato.getCategoria()+ 
					"\nDescricao: " +
					prato.getDescricao() +
					"\nPreco:  " +
					prato.getPreco();				
 		}
		
		return listagem;
	}
	

	
	/**
	 * Metódo para pegar a quantidade de fornecedores na lista
	 * @return Integer - Quantidade de Fornecedores cadastrados
	 */
	
	public Integer qtd() {
		return GerenciadorPratos.listaDePratos.size();
	}
	
	/**
	 * Metódo para limpar a lista de fornecedores, somente será utilizado nos Testes
	 */
	public void limpaLista() {
		GerenciadorPratos.listaDePratos.clear();
	}
	
	/**
	 * Metódo para adicionar os ingredientes verificando a existência dos produtos
	 * @param id ID do produto
	 * @param quantidade Quantidade do produto
	 * @param unm Unidade de medida do produto
	 * @return Ingrediente 
	 * @throws DomainException Erro caso o produto não seja reconhecido
	 */
	public static Ingredientes adicionarIngredientes(Integer id, Double quantidade,UnidadeDeMedida unm) throws DomainException {
		Produto p=GerenciadorProdutos.getProduto(id);
		if(p==null) {
			throw new DomainException("Produto não reconhecido!");
		}else{
			Ingredientes ingrediente = new Ingredientes(id,quantidade,unm);
			return ingrediente;
		}
	}
	
	/**
	 * Metódo para cadastrar Prato
	 * @param nome Nome do prato
	 * @param preco preço do prato
	 * @param categoria Categoria do prato, podendo ser uma entrada, bebida, massa etc
	 * @param descricao Descrição do prato
	 * @param ingredientes Ingredientes que compõe o prato
	 * @return Prato cadastrado 
	 */
	
	public static Prato cadastrarPrato(String nome, Double preco,CategoriaPrato categoria,String descricao,List<Ingredientes> ingredientes) {
		Prato p=new Prato(nome,preco,categoria,descricao,ingredientes);
		GerenciadorPratos.addOuEdit(p);
		return p;
	}
	
	public static List<Prato> buscar(String nome){
		List<Prato> list = new ArrayList<>();
		for(Prato p: listaDePratos) {
			if(p.getNome().equals(nome)) {
				list.add(p);
			}
		}
		
		return list;
	}

	public static void remover(Prato prato) {
		listaDePratos.remove(prato);
		
	}
	
	

}
