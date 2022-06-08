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

import model.entities.Fornecedor;
/**
 * Classe para criação do objeto Gerenciador de Fornecedores
 * @author Cláudia Inês Sales
 *
 */

public class GerenciadorFornecedores {
	
		
	private static List <Fornecedor> listaFornecedores = new ArrayList<>(); //Lista contendo os fornecedores
	
	/**
	 * Construtor para inicializar o Gerenciador de Fornecedores
	 */
	public GerenciadorFornecedores() {
		
	}
	
	/**
	 * Metódo para adicionar o fornecedor na lista 
	 * @param fornecedor Objeto do tipo Fornecedor
	 */
	private static void add(Fornecedor fornecedor) {		
		GerenciadorFornecedores.listaFornecedores.add(fornecedor); 
		
	}
	
	/**
	 * Metódo para editar fornecedor já existente na lista
	 * @param fornecedorEdit Fornecedor que já existe e será editado
	 * @param alterarFornecedor Fornecedor utilizado para parâmetro de substituição
	 */
	
	private static void editar(Fornecedor fornecedorEdit,Fornecedor alterarFornecedor) {
		//Troca o CNPJ do fornecedor se for diferente
		if (fornecedorEdit.getCnpj() != alterarFornecedor.getCnpj()) {
			fornecedorEdit.setCnpj(alterarFornecedor.getCnpj());
		}
		//Troca o Endereço do fornecedor se for diferente
		if(fornecedorEdit.getEndereco() != alterarFornecedor.getEndereco()) {
			fornecedorEdit.setEndereco(alterarFornecedor.getEndereco());
		}
		// Troca o nome do fornecedor se for diferente
		if(fornecedorEdit.getName() != alterarFornecedor.getName()) {
			fornecedorEdit.setName(alterarFornecedor.getName());
		}
		//Troca a lista de ID's de produtos fornecidos caso sejam diferentes
		if(fornecedorEdit.getIdProdutosFornecidos() != alterarFornecedor.getIdProdutosFornecidos()) {
			fornecedorEdit.setProdutosFornecidos(alterarFornecedor.getIdProdutosFornecidos());
		}
	}
	
	/**
	 * Metódo que será utilizado por outras classes para adicionar ou editar um fornecedor
	 * Nele o objeto passado será verificado se já existe na lista, se não existir será
	 * adicionado, caso já exista será editado.
	 * @param fornecedor Objeto do tipo Fornecedor
	 */
	
	public static void addOuEdit(Fornecedor fornecedor) {
		//Verifica a existência do fornecedor na lista através do ID
		Fornecedor fornecedorExistente = GerenciadorFornecedores.listaFornecedores.stream().filter(x -> x.getId() == fornecedor.getId())
				.findFirst().orElse(null);
		
		//Se existir o fornecedor é editado, se não ele é adicionado na lista
		if(fornecedorExistente!=null) {
			editar(fornecedorExistente, fornecedor);
		}else {
			add(fornecedor);
		}
	
	}
	
	/**
	 * Metódo para recuperar um fornecedor específio na lista
	 * @param id Id do fornecedor que vai ser procurado na lista
	 * @return Fornecedor - Objeto do tipo fornecedor 
	 */
	public static Fornecedor getFornecedor(Integer id) {
		//Procura Fornecedor através do ID na lista de fornecedores
		Fornecedor fornecedor = listaFornecedores.stream().filter(x -> x.getId() == id)
				.findFirst().orElse(null);
		return fornecedor;
	}
	
	/**
	 * Metódo para pegar o ID do fornecedor na lista de fornecedores
	 * @param f  Objeto do tipo fornecedor
	 * @return Integer - ID do fornecedor
	 */
	public static Integer getFornecedor(Fornecedor f) {
		return f.getId();
	}
	
	/**
	 * Metódo para remover fornecedor da lista através do ID dele
	 * @param id ID do fornecedor a ser removido
	 */
	public static void remover(Integer id) {
		Fornecedor result = listaFornecedores.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if(result!=null) {
			listaFornecedores.remove(result);
			
		}
	}
	
	
	public static void remover(Fornecedor fornecedor) {
		listaFornecedores.remove(fornecedor);
	}
	
	/**
	 * Metódo para limpar a lista de fornecedores, somente será utilizado nos Testes
	 */
	public static void limparLista() {
		listaFornecedores.clear();
		
		
	}
	
	/**
	 * Metódo para fazer a listagem dos fornecedores
	 * @return String - Listagem dos fornecedores
	 */

	public static String listagem() {
		String listagem=" ";
		for(Fornecedor fornecedor : listaFornecedores) {
			listagem +="\nCódigo: "+fornecedor.getId()+"\nCNPJ: "+fornecedor.getCnpj()+
					"\nNome: "+fornecedor.getName()+"\nEndereco:"+fornecedor.getEndereco()+
					"\nCódigo dos Produtos Fornecidos: ";
			for(int i=0; i<fornecedor.getIdProdutosFornecidos().size();i++) {
				listagem+=fornecedor.getIdProdutosFornecidos().get(i)+", ";
			}
		}
		
		return listagem;
	}
	
	public static List<Fornecedor> buscar(String nome){
		List<Fornecedor> list = new ArrayList<>();
		for(Fornecedor f: listaFornecedores) {
			if(f.getName().equals(nome)) {
				list.add(f);
			}
		}
		
		return list;
	}
	
	/**
	 * Metódo para pegar a lista completa dos fornecedores
	 * @return Lista Fornecedor - Lista de fornecedores
	 */
	public static List<Fornecedor> getListaDeFornecedores(){
		return GerenciadorFornecedores.listaFornecedores;
	}
	
	/**
	 * Metódo para pegar a quantidade de fornecedores na lista
	 * @return Integer - Quantidade de fornecedores na lista
	 */
	public Integer qtd() {
		return listaFornecedores.size();
	}
	
	/**
	 * Método para cadastrar um fornecedor 
	 * @param nome Nome do fornecedor
	 * @param cnpj Cnpj do fornecedor
	 * @param endereco Endereço do fornecedor
	 * @param ids ID's dos produtos que ele fornece
	 * @return Objeto do tipo fornecedor cadastrado
	 */
	public static Fornecedor cadastrarFornecedor(String nome, Integer cnpj, String endereco, List<Integer> ids) {
		Fornecedor f1=new Fornecedor(cnpj,nome,endereco,ids);
		GerenciadorFornecedores.addOuEdit(f1);
		return f1;
		
	}
	
	
	

}
