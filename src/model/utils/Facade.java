/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 26/06/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/
package model.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import model.entities.*;
import model.enums.*;
import model.exceptions.DomainException;
import model.gerenciadores.*;


public class Facade {
	
	public static void initialize() throws DomainException {
		initializeUsuarios();
		initializeClientes();
		initializeProdutos();
		initializeVendas();
		initializePratos();
		initializeFornecedores();
		
		
	}
	
	public static void initializeUsuarios() throws DomainException {
		//Cria��o de usu�rios
		Usuario u1 = new Gerente("claus","cometa","Cl�udia In�s");
		Usuario u2 = new Funcionario("adm","1","Adm");
		GerenciadorUsuarios.addOuEdit(u1);
		GerenciadorUsuarios.addOuEdit(u2);
	}
	
	public static void initializeProdutos() {
		//Cria��o de produtos
		Date data1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data1);
		cal.add(Calendar.DAY_OF_MONTH, 60);
		data1 = cal.getTime();		
		Produto p1 = new Produto("Refrigerante",5.0,data1,500.0);		
		GerenciadorProdutos.addOuEdit(p1);			
		Produto p2 = new Produto("Arroz", 4.0, data1,600.0);
		GerenciadorProdutos.addOuEdit(p2); 
	}
	public static void initializeClientes() {
		GerenciadorClientes.addOuEdit(new Cliente("Nome1","Endere�o1",1245,54587));
		GerenciadorClientes.addOuEdit(new Cliente("Nome2","Endere�o2",1245,54587));
	}
	public static void initializePratos() {
		//Cria��o de pratos
		List<Ingredientes> ingrediente = new ArrayList<>();
		ingrediente.add(new Ingredientes(1,5.0,UnidadeDeMedida.L)); ingrediente.add(new Ingredientes(1,5.0,UnidadeDeMedida.KG));
		Prato pr1 = new Prato("Macarr�o",8.5,CategoriaPrato.MASSA,"Macarr�o ao molho",ingrediente);
		Prato pr2 = new Prato("Refigerante",5.0,CategoriaPrato.BEBIDA,"Refrigerante de Uva",ingrediente);
		GerenciadorPratos.addOuEdit(pr1);
		GerenciadorPratos.addOuEdit(pr2);
	}
	
	public static void initializeVendas() {
		//Cria��o de vendas
		List<Integer> itens1 = new ArrayList<>();
		itens1.add(1); itens1.add(2);		
		Date data3 = new Date();		
		Venda venda1 = new Venda(FormaDePagamento.AVISTA , data3, itens1,StatusDaVenda.ABERTO,GerenciadorClientes.getCliente(1));
		GerenciadorVendas.addOuEdit(venda1);		
		Date data4 = new Date();
		Venda venda2 = new Venda(FormaDePagamento.PIX, data4, itens1, StatusDaVenda.ABERTO,GerenciadorClientes.getCliente(2));
		GerenciadorVendas.addOuEdit(venda2);
	}
	
	public static void initializeFornecedores() {
		//Cria��o de fornecedores
		List<Integer> produtos1 = new ArrayList<>();
		produtos1.add(1); produtos1.add(2);		
		Fornecedor f1 = new Fornecedor(1123,"CLS EMPRESA","Tomba",produtos1);
		GerenciadorFornecedores.addOuEdit(f1);	
		Fornecedor f2 = new Fornecedor(5454,"LTDA Fornecedor","Feira",produtos1);
		GerenciadorFornecedores.addOuEdit(f2);
	}
	

}
