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

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.exceptions.DomainException;
import model.entities.Produto;
import model.gerenciadores.GerenciadorProdutos;
import model.utils.Facade;

public class GerenciadorProdutoTest {
	
	GerenciadorProdutos gp = new GerenciadorProdutos();
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	//Método para inicializar a lista com alguns produtos
	@BeforeEach
	public void setUp() throws Exception {
		Facade.initializeProdutos();
	}

	//Metódo para limpar a lista e último ID para não dar erro nas posições
	//no momento do teste 

	@AfterEach
	public void tearDown() throws Exception {
		GerenciadorProdutos.limparLista();
		Produto.setUltimoId(1);
	}
	
	//Teste de adicionar pratos no gerenciador 
	@Test
	 void adicionarTeste() throws ParseException, DomainException {
		
		assertEquals(2,gp.qtd());		
		Date data2 = sdf1.parse("04/03/2022");
		Produto p3 = new Produto("Feijão", 6.0, data2,40.0);
		GerenciadorProdutos.addOuEdit(p3);		
		assertEquals(3,gp.qtd());
		
		Produto p4= new Produto("Mussarela",5.0,data2,15.0);
		GerenciadorProdutos.addOuEdit(p4);
		assertFalse(gp.qtd()==3);
		assertEquals(4,gp.qtd());
		assertSame(p4,GerenciadorProdutos.getProduto(4));
		assertSame(p3,GerenciadorProdutos.getProduto(3));
		
		assertEquals(5,Produto.getUltimoId());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf1.parse("25/10/2022");
		assertNotNull(GerenciadorProdutos.cadastrarProduto("nenhum", 2.0, d, 200.0));
		try {
			GerenciadorProdutos.cadastrarProduto("nenhum", 2.0, data2, 200.0);
		}catch(DomainException e) {
			assertTrue(true);
		}
	}
	
	
	//Teste de remoção de produtos do gerenciador
	@Test 
	void removerTest() throws ParseException{
		assertEquals(2,gp.qtd());
		
		GerenciadorProdutos.remover(1);		
		assertEquals(1,gp.qtd());		
		
		Date data2 = sdf1.parse("04/03/2022");
		Produto p3 = new Produto("Feijão", 6.0, data2,10.0);
		GerenciadorProdutos.addOuEdit(p3);
		assertEquals(2,gp.qtd());
		
		GerenciadorProdutos.remover(2);
		assertEquals(1,gp.qtd());
		GerenciadorProdutos.remover(3);
		assertEquals(0,gp.qtd());
		
	}
	
	//Teste de edição de produtos do gerenciador
	@Test
	 void  editarTeste() throws ParseException {
		Date data1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data1);
		cal.add(Calendar.DAY_OF_MONTH, 60);
		data1 = cal.getTime();		
		
		assertEquals(2, gp.qtd());
		assertEquals("Refrigerante",GerenciadorProdutos.getProduto(1).getNome());
		assertEquals(5.0,GerenciadorProdutos.getProduto(1).getPreco());
		assertEquals(sdf1.format(data1),GerenciadorProdutos.getProduto(1).getValidade());
		assertEquals(500.0,GerenciadorProdutos.getProduto(1).getQuantidade());
		
		Date data2 = sdf1.parse("04/03/2022");
		Produto p3 = new Produto(1,"Suco",4.0,data2,15.0);
		GerenciadorProdutos.addOuEdit(p3);
		
		assertEquals("Suco",GerenciadorProdutos.getProduto(1).getNome());
		assertEquals(4.0,GerenciadorProdutos.getProduto(1).getPreco());
		assertEquals("04/03/2022",GerenciadorProdutos.getProduto(1).getValidade());	
		assertEquals(15.0,GerenciadorProdutos.getProduto(1).getQuantidade());
		assertEquals(2, gp.qtd());
		
		
	}
	
	//Teste de listagem dos produtos do gerenciador
	@Test
	void listarTeste() throws ParseException {
		Date data1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data1);
		cal.add(Calendar.DAY_OF_MONTH, 60);
		data1 = cal.getTime();	
		assertEquals(2, gp.qtd());
		
		
		Produto p3 = new Produto("Suco", 4.0, data1,10.0);
		GerenciadorProdutos.addOuEdit(p3);
		assertFalse(2==gp.qtd());
		assertEquals(3,gp.qtd());
		
		Produto p4 = new Produto("Feijão", 6.0, data1,2.0);
		GerenciadorProdutos.addOuEdit(p4);
		assertFalse(3==gp.qtd());
		assertEquals(4,gp.qtd());
		assertSame(p4,GerenciadorProdutos.getProduto(4));
		assertNotNull(GerenciadorProdutos.listagem());
		
		GerenciadorProdutos.remover(1);
		GerenciadorProdutos.remover(2);
		GerenciadorProdutos.remover(3);
		GerenciadorProdutos.remover(4);
		assertEquals(0,gp.qtd());
		
		assertNotNull(p4.infoProduto(p4));
		
	} 
	
	
	//Teste para atualização do estoque
	@Test
	public void estoqueTeste() throws DomainException {
		try {
			GerenciadorProdutos.getProduto(1).atualizarEstoque(6.0);
		}catch(DomainException e) {
			assertTrue(true);
		}
		
		
	}

}
