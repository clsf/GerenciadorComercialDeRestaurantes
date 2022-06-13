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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.exceptions.DomainException;
import model.entities.Cliente;
import model.entities.Prato;
import model.entities.Venda;
import model.enums.FormaDePagamento;
/**
 * Classe para criação do objeto Gerenciador de Vendas
 * @author Cláudia Inês Sales
 *
 */

public class GerenciadorVendas {
	
	
	private static List<Venda> listaDeVendas = new ArrayList<>(); //Lista de vendas cadastradas
	
	/**
	 * Construtor para inicializar o Gerenciador de vendas
	 */
	
	public GerenciadorVendas() {
		
	}
	
	/**
	 * Metódo para adicionar a venda na lista de vendas
	 * @param venda Objeto do tipo venda que será adicionada
	 */
	private static void add(Venda venda) {
		GerenciadorVendas.listaDeVendas.add(venda);
	}
	
	/**
	 * Metódo para editar venda já existente na lista
	 * @param vendaEdit Objeto venda já existente na lista
	 * @param alterarVenda Objeto venda que será usado como parâmetro para editar o original
	 */
	private static void editar(Venda vendaEdit, Venda alterarVenda) {
		listaDeVendas.set(listaDeVendas.indexOf(vendaEdit),alterarVenda);
		/*	//Se a data estiver diferente será trocada
			if(vendaEdit.getData() != alterarVenda.getData()) {
				vendaEdit.setData(alterarVenda.getData());
			}
			//Se a forma de pagamento estiver diferente será trocada
			if(vendaEdit.getFormaDePagamento() != alterarVenda.getFormaDePagamento()) {
				vendaEdit.setFormaDePagamento(alterarVenda.getFormaDePagamento());
			}
			//Se a lista de itens for diferente será trocada
			if(vendaEdit.getItens() != alterarVenda.getItens()) {
				vendaEdit.setItens(alterarVenda.getItens());
			}*/
	}
	/**
	 * Metódo que será utilizado por outras classes para adicionar ou editar uma venda
	 * Nele o objeto passado será verificado se já existe na lista, se não existir será
	 * adicionado, caso já exista será editado. 
	 * @param venda Objeto do tipo venda que será adicionado ou editado
	 */
	
	public static void addOuEdit(Venda venda) {
		//Verifica a existência de uma venda com o mesmo ID
		Venda vendaExistente =GerenciadorVendas.listaDeVendas.stream().filter(x-> x.getId() == venda.getId())
				.findFirst().orElse(null);
		//Se existir a venda será editada, se não será adicionada
		if(vendaExistente != null) {
			editar(vendaExistente, venda);
		}
		else {
			add(venda);
		}

	}
	/**
	 * Metódo para remover a venda da lista através do ID dela
	 * @param id ID da venda
	 */
	public static void remover(Integer id) {
		Venda result = GerenciadorVendas.listaDeVendas.stream().filter(x-> x.getId() == id).findFirst().orElse(null);
		
		if(result != null) {
			GerenciadorVendas.listaDeVendas.remove(result);
		}
	}
	
	public static void remover(Venda v) {
		listaDeVendas.remove(v);
	}
	
	/**
	 * Metódo para pegar a lista completa das vendas
	 * @return Lista venda - Lista das vendas cadastradas
	 */
	public static List<Venda> getListaDeVendas(){
		return GerenciadorVendas.listaDeVendas;
	}
	
	/**
	 * Metódo para pegar uma venda específica da lista
	 * @param id ID da venda
	 * @return Venda - Objeto do tipo venda
	 */
	public static Venda getVenda(Integer id) {
		Venda venda = GerenciadorVendas.listaDeVendas.stream().filter(x-> x.getId() == id).findFirst().orElse(null);
		return venda;
	}
	
	/**
	 * Metódo para fazer a listagem das vendas
	 * @param pratos Lista dos pratos que serão utilizados para pegar os preços
	 * @return String - Listagem das vendas cadastradas
	 */
	
	public static String listagem(List<Prato> pratos) {
		String listagem = "";
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		for(Venda venda: GerenciadorVendas.listaDeVendas) {
			String prt="";
			for(Integer idPrato:venda.getItens()) {
				Prato prato = GerenciadorPratos.getPrato().stream().filter(x->x.getId() == idPrato)
						.findFirst().orElse(null);
				if(prato!=null) {
					prt+=prato.getNome()+", ";
				}
				
			}
			listagem += "\nCódigo: "
					+ venda.getId() +
					"\nData: "
					+sdf1.format(venda.getData())+
					"\nPratos: "+ prt+
					"\nPreco Total: "
					+venda.precoTotal(pratos)+
					"R$"+
					"\nForma de pagamento: "+
					venda.getFormaDePagamento()+"\n";
		}
		return listagem;
	}

	/**
	 * Metódo para pegar a quantidade de vendas cadastradas
	 * @return Integr - Quantidade de vendas cadastradas
	 * 
	 */
	public Integer qtd() {
		return GerenciadorVendas.listaDeVendas.size();
	}
	
	/**
	 * Metódo para limpar a lista de vendas, somente será utilizado nos Testes
	 */
	public void limparLista() {
		GerenciadorVendas.listaDeVendas.clear();
	}
	
	/**
	 * Metódo para verificar a existência do prato
	 * @param idPrato ID do prato que vai ser adicionado na venda
	 * @param pratos Lista de pratos do gerenciador de pratos
	 * @throws DomainException Erro caso o prato não esteja cadastrado no catálogo 
	 */
	public static Boolean verificarPrato(Integer idPrato,List<Prato> pratos) throws DomainException {
		Prato prato = pratos.stream().filter(x -> x.getId() == idPrato)
				.findFirst().orElse(null);
		if(prato==null) {
			throw new DomainException("Este prato não existe no catálogo!");
		}
		return true;
	}
	
	/**
	 * Metódo para cadastrar uma venda
	 * @param formaDePagamento Forma de pagamento da venda
	 * @param data Data que está sendo realizada a venda
	 * @param pratos Pratos que compões a venda
	 * @return Venda 
	 */
	public static Venda cadastrarVenda(FormaDePagamento formaDePagamento, Date data, List<Integer> pratos) {
		Venda v=new Venda(formaDePagamento,data,pratos);
		GerenciadorVendas.addOuEdit(v);
		return v;
		
	}
	
	public static List<Venda> buscarCliente(String nome) {
		List<Cliente> list = new ArrayList<>();
		List<Venda> listaVenda = new ArrayList<>();
		list = GerenciadorClientes.buscar(nome);
		for(Venda v: listaDeVendas) {
			if(v.getCliente().getNome().equals(nome)){
				listaVenda.add(v);
			}
		}
		
		return listaVenda;
		
	}
}


