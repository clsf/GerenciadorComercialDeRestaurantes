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
package model.gerenciadorTeste;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.exceptions.DomainException;
import model.entities.Cliente;
import model.entities.Fornecedor;
import model.entities.Prato;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Venda;
import model.enums.CategoriaPrato;
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.gerenciadores.GerenciadorUsuarios;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Facade;
import model.utils.Relatorios;
//import model.utils.main;

public class RelatoriosTest {

	@BeforeEach
	public void setUp() throws Exception {
		Facade.initialize();
	}

	@AfterEach
	public void tearDown() throws Exception {
		GerenciadorFornecedores.limparLista();
		GerenciadorVendas.limparLista();
		GerenciadorUsuarios.limparLista();
		GerenciadorPratos.limpaLista();
		GerenciadorProdutos.limparLista();
		GerenciadorClientes.limparLista();
		Fornecedor.setUltimoId(1);
		Venda.setUltimoId(1);
		Usuario.setUltimoId(1);
		Prato.setUltimoId(1);
		Produto.setUltimoId(1);
		Cliente.setUltimoId(1);
	}
	//Teste para retorno de listas contendo os objetos
	@Test
	public void RelatoriosTeste() throws DomainException, ParseException {
		assertNotNull(Relatorios.relatorioVendaPorPeriodo(new Date()));
		assertNotNull(Relatorios.relatorioVendaPorPrato(CategoriaPrato.BEBIDA));
		assertNotNull(Relatorios.relatorioFornecedorePorProduto(1));
		assertNotNull(Relatorios.relatorioFornecedor(1));
		assertNotNull(Relatorios.relatorioEstoquePorProduto(1));
		assertNotNull(Relatorios.relatorioEstoqueProdutosAvencer());		
		
	}
	
	//Teste para saber se retorna uma string que exibir� as informa��es na tela
	@Test 
	public void ImpressaoRelatorios() throws DomainException{
		List<Venda> venda= new ArrayList<>();
		List<Produto> produtos = new ArrayList<>();
		assertNotNull(Relatorios.imprimirRelatorioVenda(GerenciadorVendas.getListaDeVendas()));
		assertEquals(0.0,Relatorios.precoTotalVenda(venda));
		assertNotNull(Relatorios.imprimirRelatorioProduto(GerenciadorProdutos.getListaDeProdutos()));
		assertEquals(0.0,Relatorios.precoTotalProduto(produtos));
		assertNotNull(Relatorios.imprimirRelatorioFornecedor(GerenciadorFornecedores.getListaDeFornecedores()));
		
	}

}
