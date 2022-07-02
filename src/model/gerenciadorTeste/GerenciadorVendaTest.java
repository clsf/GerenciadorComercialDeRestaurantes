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
package model.gerenciadorTeste;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.exceptions.DomainException;
import model.entities.Cliente;
import model.entities.Fornecedor;
import model.entities.Ingredientes;
import model.entities.Prato;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Venda;
import model.enums.CategoriaPrato;
import model.enums.FormaDePagamento;
import model.enums.StatusDaVenda;
import model.enums.UnidadeDeMedida;
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.gerenciadores.GerenciadorUsuarios;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Facade;

public class GerenciadorVendaTest {
	
	
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	//Método para inicializar a lista com algumas vendas
	@BeforeEach
	public void setUp() throws Exception {		
		Facade.initialize();
	}
	

	//Metódo para limpar a lista e último ID para não dar erro nas posições
	//no momento do teste 
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
	
	//Teste de adicionar vendas do gerenciador
	@Test
	public void adicionarTeste() throws ParseException, DomainException {
		assertEquals(2,GerenciadorVendas.qtd());
		
		List<Integer> itens2 = new ArrayList<>();
		itens2.add(3); itens2.add(4);
		Date data3 = sdf1.parse("05/03/2022");
		Venda venda3 = new Venda(FormaDePagamento.CREDITO, data3, itens2);
		GerenciadorVendas.addOuEdit(venda3);
		assertEquals(3,GerenciadorVendas.qtd());
		assertSame(venda3,GerenciadorVendas.getVenda(3));
		assertEquals(StatusDaVenda.ABERTO,venda3.getStatus());
		
		Venda venda4 = new Venda(FormaDePagamento.DEBITO,data3,itens2);
		GerenciadorVendas.addOuEdit(venda4);
		assertEquals(4,GerenciadorVendas.qtd());
		assertSame(venda4,GerenciadorVendas.getVenda(4));
		
		assertTrue(GerenciadorVendas.verificarPrato(1, GerenciadorPratos.getPrato()));
		try {
			GerenciadorVendas.verificarPrato(0,GerenciadorPratos.getPrato());
		}catch(DomainException e) {
			assertTrue(true);
		}
		
		assertNotNull(GerenciadorVendas.cadastrarVenda(FormaDePagamento.AVISTA, new Date(), itens2));
	}
	
	//Teste de edição de venda do gerenciador
	@Test
	public void editarTeste() throws ParseException {
		Date data1= new Date();
		
		assertEquals(2,GerenciadorVendas.qtd());
		assertEquals(FormaDePagamento.AVISTA,GerenciadorVendas.getVenda(1).getFormaDePagamento());
		assertEquals(sdf1.format(data1),sdf1.format(GerenciadorVendas.getVenda(1).getDataR2()));
		
		List<Integer> itens1 = new ArrayList<>();
		itens1.add(1); itens1.add(2);
		
		Date data11 = sdf1.parse("08/04/2022");		
		Venda venda1 = new Venda(1,FormaDePagamento.CREDITO , data11,StatusDaVenda.ABERTO, itens1);
		GerenciadorVendas.addOuEdit(venda1);
		
		assertEquals(FormaDePagamento.CREDITO,GerenciadorVendas.getVenda(1).getFormaDePagamento());
		assertEquals("08/04/2022",sdf1.format(GerenciadorVendas.getVenda(1).getDataR2()));
		
		assertEquals(2,GerenciadorVendas.qtd());
		
	}
	
	//Teste de remoção das vendas do gerenciador
	@Test 
	public void removerTeste() throws ParseException {
		assertEquals(2,GerenciadorVendas.qtd());
		
		GerenciadorVendas.remover(1);
		assertEquals(1,GerenciadorVendas.qtd());
		GerenciadorVendas.remover(2);
		assertEquals(0,GerenciadorVendas.qtd());
		
		List<Integer> itens2 = new ArrayList<>();
		itens2.add(3); itens2.add(4);
		Date data3 = sdf1.parse("05/03/2022");
		Venda venda3 = new Venda(FormaDePagamento.CREDITO, data3, itens2);
		GerenciadorVendas.addOuEdit(venda3);
		assertEquals(1,GerenciadorVendas.qtd());
		
		GerenciadorVendas.remover(3);
		assertEquals(0,GerenciadorVendas.qtd());
		
	}
	
	//Teste de listagem das vendas do gerenciador
	@Test
	public void ListagemTeste() throws ParseException {
		assertEquals(2,GerenciadorVendas.qtd());
		
		List<Integer> itens2 = new ArrayList<>();
		itens2.add(1); itens2.add(2);
		Date data3 = sdf1.parse("05/03/2022");
		Venda venda3 = new Venda(FormaDePagamento.CREDITO, data3, itens2);
		GerenciadorVendas.addOuEdit(venda3);
		assertEquals(3,GerenciadorVendas.qtd());
		assertSame(venda3,GerenciadorVendas.getVenda(3));
		
		assertEquals(4,Venda.getUltimoId());
		Venda venda4 = new Venda(FormaDePagamento.PIX, data3, itens2);
		GerenciadorVendas.addOuEdit(venda4);
		assertEquals(4,GerenciadorVendas.qtd());
		assertSame(venda4,GerenciadorVendas.getVenda(4));
		
		assertNotNull(GerenciadorVendas.listagem(GerenciadorPratos.getPrato()));
		assertNotNull(GerenciadorVendas.getListaDeVendas());
		
		GerenciadorVendas.remover(1);
		GerenciadorVendas.remover(2);
		GerenciadorVendas.remover(3);
		GerenciadorVendas.remover(4);
		assertEquals(0,GerenciadorVendas.qtd());
		
		
	
	}
	
	//Teste calcular preço total da venda
	@Test
	public void PrecototalTeste() throws DomainException {
		assertEquals(2,GerenciadorVendas.qtd());	
		
		assertEquals(13.5,GerenciadorVendas.getVenda(1).precoTotal(GerenciadorPratos.getPrato()));
		assertNotNull(GerenciadorVendas.getVenda(1).infoVenda(GerenciadorVendas.getVenda(1)));
		GerenciadorVendas.getVenda(1).realizarVenda(GerenciadorPratos.getPrato(),GerenciadorProdutos.getListaDeProdutos());
		assertEquals(StatusDaVenda.FECHADO,GerenciadorVendas.getVenda(1).getStatus());
		
	}
}
