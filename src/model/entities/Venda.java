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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.DomainException;
import model.enums.FormaDePagamento;
import model.enums.StatusDaVenda;
import model.gerenciadores.GerenciadorPratos;


/**
 * Classe para cria��o de objeto do tipo Venda
 * @author Cl�udia In�s Sales
 *
 */
public class Venda {
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");	
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
	private static Integer ultimoId=1; // Salva o �ltimo ID utilizado, atributo pertencente a classe
	private Integer id;   			   // Id da Venda
	private FormaDePagamento formaDePagamento; // Forma de pagamento podendo ser D�bito, a vista, 
												//Pix ou cr�dito
	private Date data; 				//Data da venda		
	private List<Integer> itens = new ArrayList<>(); //Lista com ID's de Pratos que foram comprados
	private StatusDaVenda status = StatusDaVenda.ABERTO; //Status da venda, se j� foi realizada, n�o pode editar caso esteja fechada
	
	private Cliente cliente;
	
	/**
	 * Construtor do objeto venda permitindo instanciar sem fornecer o ID
	 * @param formaDePagamento Forma de Pagamento, podendo ser D�bito, Pix, Cr�dito, etc...
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
	 * Construtor do objeto venda para instanciar junto com o ID,
	 * servir� para edi��o de vendas ja existentes
	 * @param id Identidade do usu�rio
	 * @param formaDePagamento Forma de Pagamento, podendo ser D�bito, Pix, Cr�dito, etc...
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
	
	/**
	 * Construtor do objeto venda inserindo o cliente, permitindo instanciar junto com o ID, servir� para
	 * edi��o de vendas j� existentes
	 * @param id 	ID da venda
	 * @param formaDePagamento	Forma de Pagamento, podendo ser D�bito, Pix, Cr�dito, etc...
	 * @param data	Data que foi realizada a venda
	 * @param status	Status da venda se est� aberta ou fechada
	 * @param itens	 Lista com ID's de pratos que fazem parte da venda
	 * @param cliente Cliente da venda
	 */
	
	public Venda(Integer id,  FormaDePagamento formaDePagamento, Date data, StatusDaVenda status,
			List<Integer> itens, Cliente cliente) {
		this.id = id;
		this.formaDePagamento = formaDePagamento;
		this.data = data;
		this.itens = itens;
		this.cliente = cliente;
		this.status = status;
	}
	
	/**
	 * Construtor do objeto venda inserindo o cliente
	 * @param formaDePagamento Forma de Pagamento, podendo ser D�bito, Pix, Cr�dito, etc...
	 * @param data	Data que foi realizada a venda
	 * @param itens	 Lista com ID's de pratos que fazem parte da venda
	 * @param status Status da venda se est� aberta ou fechada
	 * @param cliente Cliente da venda
	 */

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
	 * Met�do para pegar a forma de pagamento da venda
	 * @return FormaDePagamento - Forma de pagamento da venda
	 */

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	
	/**
	 * Met�do para configurar a forma de pagamento da venda
	 * @param formaDePagamento  Forma de pagamento da venda
	 */
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	/**
	 * Met�do para pegar data da venda no formato dd/MM/aaaa
	 * @return String com data da venda no formato dd/MM/aaaa
	 */

	public String getData() {
		return sdf1.format(data);
	}
	/**
	 * Met�do para pegar a data da venda no formato MM/aaaa
	 * @return String com data da venda no formato MM/aaaa
	 */
	public String getDataR() {
		return sdf2.format(data);
	}
	/**
	 * Met�do para pegar a data da venda no formato Date 
	 * @return Data da venda no formato Date
	 */
	public Date getDataR2() {
		return this.data;
	}
	
	/**
	 * Met�do para configurar data da venda
	 * @param data - Data da venda
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * Met�do para pegar ID da venda
	 * @return Integer - ID da venda
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Met�do para pegar lista com os ID's dos itens que fazem parte da venda
	 * @return Lista do tipo Integer - ID's dos pratos da venda
	 */

	public List<Integer> getItens() {
		return itens;
	}

	/**
	 * Met�do para configurar lista de ID's  com os itens que fazem parte da venda
	 * @param itens Lista de Itens que fazem da parte da venda
	 */
	public void setItens(List<Integer> itens) {
		this.itens=itens;
	}
	/**
	 * Adiciona itens na venda
	 * @param item ID do item do card�pio para ser adicionado na venda
	 */
	public void addItens(Integer item) {
		itens.add(item);
	}
	
	/**
	 * Met�do para calcular o pre�o total da venda 
	 * @param pratos Pratos cadastrados na classe Pratos 
	 * @return Double - Pre�o total da venda
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
	/**
	 * Met�do para retornar o pre�o total da venda
	 * @param pratos lista de pratos que comp�em a venda
	 * @return Double com o pre�o total da venda
	 */
	
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
	 * Met�do para pegar o �ltimo ID utilizado pela classe 
	 * @return Integer - �ltimo ID utilizado pela classe
	 */

	public static Integer getUltimoId() {
		return ultimoId;
	}

	/**
	 * Met�do para configurar o �ltimo ID da classe, esse met�do s� ser� utilizado 
	 * nos testes para fazer o resete
	 * @param ultimoId Ultimo ID que ser� refer�ncia para adi��o dos pr�ximos
	 */
	public static void setUltimoId(Integer ultimoId) {
		Venda.ultimoId = ultimoId;
	}
	
	
	/**
	 * Met�do para pegar o status da venda
	 * @return Status da venda
	 */
	
	
	public StatusDaVenda getStatus() {
		return status;
	}

	/**
	 * Met�do para realizar a venda, abatendo os produtos que comp�e os pratos
	 * @param pratos Lista de pratos cadastrados no gerenciador de pratos
	 * @param produtos Lista de produtos cadastrados no gerenciador de produtos
	 * @throws DomainException Erro caso tente realizar venda fechada, ou sem prato ou sem produto
	 */

	public void realizarVenda(List<Prato> pratos, List<Produto> produtos) throws DomainException {
		if(this.status==StatusDaVenda.FECHADO) {
			throw new DomainException("Esta venda est� fechada!");
		}
		for(Integer item: this.itens) {
			Prato prato = pratos.stream().filter(x -> x.getId() == item)
					.findFirst().orElse(null);
			if(prato==null) {
				throw new DomainException("Este prato n�o existe no cat�logo!");
			}
			else {
				for(Ingredientes ingrediente: prato.getIngredientes()) {
					Produto produto = produtos.stream().filter(x -> x.getId() == item)
							.findFirst().orElse(null);
					if(produto==null) {
						throw new DomainException("Este produto n�o existe no cat�logo!");
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
	 * Met�do para exibir informa��es de uma venda
	 * @param v Venda que ser� exibida
	 * @return String com as informa��es da venda
	 */
	public String infoVenda(Venda v) {
		String info= "C�digo:" + v.getId() +"\nForma de Pagamento: "+v.getFormaDePagamento() +
					"\nTotal: "+v.precoTotal(GerenciadorPratos.getPrato())+" R$"+"\nPratos: ";
		for(int i=1;i<=itens.size();i++) {
			String prato = GerenciadorPratos.getPrato(i).getNome();
			info+=prato+", ";
		}
		return info;
		
	}
	/**
	 * Met�do para pegar o cliente da venda;
	 * @return
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * Met�do para pegar o nome do Cliente da venda
	 * @return String contendo o nome do cliente
	 */
	public String getNomeCliente() {
		return cliente.getNome();
	}
	
	/**
	 * Met�do para alterar o cliente
	 * @param cliente Cliente da venda
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Met�do para remover prato da venda
	 * @param id ID do prato que est� na venda
	 */
	
	public void removerPrato(Integer id) {
		for(Integer prato: itens) {
			if(prato.equals(id)) {
				itens.remove(prato);
			}
		}
	}
	
	/**
	 * Met�do para pegar o pre�o total da venda
	 * @return Double com o pre�o total da venda
	 */
	public Double getPreco() {
		return precoTotal(GerenciadorPratos.getPrato());
	}
	
	/**
	 * Met�do para pegar o nome dos pratos que compoem a venda
	 * @return String contendo os nomes dos pratos
	 */
	
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

