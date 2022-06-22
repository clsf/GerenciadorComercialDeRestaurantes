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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.DomainException;
import model.enums.FormaDePagamento;
import model.enums.StatusDaVenda;
import model.gerenciadores.GerenciadorPratos;


/**
 * Classe para criação de objeto do tipo Venda
 * @author Cláudia Inês Sales
 *
 */
public class Venda {
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");	
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
	private static Integer ultimoId=1; // Salva o último ID utilizado, atributo pertencente a classe
	private Integer id;   			   // Id da Venda
	private FormaDePagamento formaDePagamento; // Forma de pagamento podendo ser Débito, a vista, 
												//Pix ou crédito
	private Date data; 				//Data da venda		
	private List<Integer> itens = new ArrayList<>(); //Lista com ID's de Pratos que foram comprados
	private StatusDaVenda status = StatusDaVenda.ABERTO; //Status da venda, se já foi realizada, não pode editar caso esteja fechada
	
	private Cliente cliente;
	
	/**
	 * Construtor do objeto venda permitindo instanciar sem fornecer o ID
	 * @param formaDePagamento Forma de Pagamento, podendo ser Débito, Pix, Crédito, etc...
	 * @param data Data que foi realizada a venda
	 * @param itens Lista com ID's de pratos que fazem parte da venda
	 */
	public Venda(FormaDePagamento formaDePagamento, Date data, List<Integer> itens) {
		this.id = ultimoId;
		this.formaDePagamento = formaDePagamento;		
		this.data = data;
		this.itens = itens;
		ultimoId++;
	}
	
	/**
	 * Construtor do objeto usuário para instanciar junto com o ID,
	 * servirá para edição de usuários ja existentes
	 * @param id Identidade do usuário
	 * @param formaDePagamento Forma de Pagamento, podendo ser Débito, Pix, Crédito, etc...
	 * @param data Data que foi realizada a venda
	 * @param itens Lista com ID's de pratos que fazem parte da venda
	 */

	public Venda(Integer id,  FormaDePagamento formaDePagamento, Date data, StatusDaVenda status,
			List<Integer> itens) {
		this.id = id;
		this.formaDePagamento = formaDePagamento;
		this.data = data;
		this.itens = itens;
	}
	
	
	public Venda(Integer id,  FormaDePagamento formaDePagamento, Date data, StatusDaVenda status,
			List<Integer> itens, Cliente cliente) {
		this.id = id;
		this.formaDePagamento = formaDePagamento;
		this.data = data;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
	}
	

	public Venda(FormaDePagamento formaDePagamento, Date data, List<Integer> itens, StatusDaVenda status,
			Cliente cliente) {
		this.id = ultimoId;
		this.formaDePagamento = formaDePagamento;
		this.data = data;
		this.itens = itens;
		this.status = status;
		this.cliente = cliente;
		ultimoId++;
		
	}

	/**
	 * Metódo para pegar a forma de pagamento da venda
	 * @return FormaDePagamento - Forma de pagamento da venda
	 */

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	
	/**
	 * Metódo para configurar a forma de pagamento da venda
	 * @param formaDePagamento  Forma de pagamento da venda
	 */
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	/**
	 * Metódo para pegar data da venda
	 * @return Data - Data da venda
	 */

	public String getData() {
		return sdf1.format(data);
	}
	
	public String getDataR() {
		return sdf2.format(data);
	}
	public Date getDataR2() {
		return this.data;
	}
	
	/**
	 * Metódo para configurar data da venda
	 * @param data - Data da venda
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * Metódo para pegar ID da venda
	 * @return Integer - ID da venda
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Metódo para pegar lista com os ID's dos itens que fazem parte da venda
	 * @return Lista do tipo Integer - ID's dos pratos da venda
	 */

	public List<Integer> getItens() {
		return itens;
	}

	/**
	 * Metódo para configurar lista de ID's  com os itens que fazem parte da venda
	 * @param itens Lista de Itens que fazem da parte da venda
	 */
	public void setItens(List<Integer> itens) {
		this.itens=itens;
	}
	
	public void addItens(Integer item) {
		itens.add(item);
	}
	
	/**
	 * Metódo para calcular o preço total da venda 
	 * @param pratos Pratos cadastrados na classe Pratos 
	 * @return Double - Preço total da venda
	 */
	
	public Double precoTotal(List<Prato> pratos) {
		Double precototal=(double) 0;
		for(Integer item : this.itens) {
			Prato prato = pratos.stream().filter(x -> x.getId() == item)
					.findFirst().orElse(null);
			precototal+= prato.getPreco();
		}
		
		return precototal;
	}
	
	public Double getPrecoTotal(List<Prato> pratos) {
		Double precototal=(double) 0;
		for(Integer item : this.itens) {
			Prato prato = pratos.stream().filter(x -> x.getId() == item)
					.findFirst().orElse(null);
			precototal+= prato.getPreco();
		}
		
		return precototal;
	}
	
	/**
	 * Metódo para pegar o último ID utilizado pela classe 
	 * @return Integer - último ID utilizado pela classe
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
		Venda.ultimoId = ultimoId;
	}
	
	
	/**
	 * Metódo para pegar o status da venda
	 * @return Status da venda
	 */
	
	
	public StatusDaVenda getStatus() {
		return status;
	}

	/**
	 * Metódo para realizar a venda, abatendo os produtos que compõe os pratos
	 * @param pratos Lista de pratos cadastrados no gerenciador de pratos
	 * @param produtos Lista de produtos cadastrados no gerenciador de produtos
	 * @throws DomainException Erro caso tente realizar venda fechada, ou sem prato ou sem produto
	 */

	public void realizarVenda(List<Prato> pratos, List<Produto> produtos) throws DomainException {
		if(this.status==StatusDaVenda.FECHADO) {
			throw new DomainException("Esta venda está fechada!");
		}
		for(Integer item: this.itens) {
			Prato prato = pratos.stream().filter(x -> x.getId() == item)
					.findFirst().orElse(null);
			if(prato==null) {
				throw new DomainException("Este prato não existe no catálogo!");
			}
			else {
				for(Ingredientes ingrediente: prato.getIngredientes()) {
					Produto produto = produtos.stream().filter(x -> x.getId() == item)
							.findFirst().orElse(null);
					if(produto==null) {
						throw new DomainException("Este produto não existe no catálogo!");
					}
					else {
						produto.atualizarEstoque(ingrediente.getQuantidade());
					}
				}
			}
		}
		
		this.status=StatusDaVenda.FECHADO;
	}
	
	/**
	 * Metódo para exibir informações de uma venda
	 * @param v Venda que será exibida
	 * @return String com as informações da venda
	 */
	public String infoVenda(Venda v) {
		String info= "Código:" + v.getId() +"\nForma de Pagamento: "+v.getFormaDePagamento() +
					"\nTotal: "+v.precoTotal(GerenciadorPratos.getPrato())+" R$"+"\nPratos: ";
		for(int i=1;i<=itens.size();i++) {
			String prato = GerenciadorPratos.getPrato(i).getNome();
			info+=prato+", ";
		}
		return info;
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getNomeCliente() {
		return cliente.getNome();
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void removerPrato(Integer id) {
		for(Integer prato: itens) {
			if(prato.equals(id)) {
				itens.remove(prato);
			}
		}
	}
	
	public Double getPreco() {
		return precoTotal(GerenciadorPratos.getPrato());
	}
	
	public String getPratos() {
		String pratos = "";
		
		for(Integer idPrato: this.itens) {
			if(GerenciadorPratos.getPrato(idPrato)!=null) {
				pratos+="("+idPrato+")"+" "+GerenciadorPratos.getPrato(idPrato).getNome()+",";
			}
		}
		
		pratos = pratos.substring(0, pratos.length()-1);
		return pratos;
	}
	

}

