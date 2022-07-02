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

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import model.entities.Cliente;
import model.entities.Fornecedor;
import model.entities.Prato;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Venda;
import model.exceptions.DomainException;
import model.gerenciadores.*;
import model.utils.Facade;


public class GerenciadorFornecedorTest {	
	
	
	 
	 
	//Metódo para inicializar a lista com alguns fornecedores a cada teste
	@BeforeEach
	public void init() throws DomainException {
		Facade.initialize();
	}
	
	//Metódo para limpar a lista e último ID para não dar erro nas posições
	//	no momento do teste 
	@AfterEach
	public void setUp2() {
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
	
	
	
	//Teste de adicionar Fornecedores no gerenciador
	@Test
	public void adicionarFornecedorTeste() {
		
		assertEquals(2,GerenciadorFornecedores.qtd());
		
		List<Integer> produtos3 = new ArrayList<>();
		produtos3.add(3); produtos3.add(4);
		Fornecedor f3 = new Fornecedor(68544,"SEM NOME","SSA", produtos3);
		GerenciadorFornecedores.addOuEdit(f3);
		assertEquals(3,GerenciadorFornecedores.qtd());
		
		List<Integer>produtos4=new ArrayList<>();
		Fornecedor f4 = new Fornecedor(8799,"NENHUM","Vilas",produtos4);
		GerenciadorFornecedores.addOuEdit(f4);
		assertFalse(GerenciadorFornecedores.qtd()==3);
		assertTrue(GerenciadorFornecedores.qtd()==4);
		
		assertEquals(f4,GerenciadorFornecedores.getFornecedor(4));
		assertEquals(f3,GerenciadorFornecedores.getFornecedor(3));	
		
		assertSame(5,Fornecedor.getUltimoId());
		assertNotNull(GerenciadorFornecedores.cadastrarFornecedor("Nenhum",1,"Nenhum", produtos4));
	}
	
	//Teste de edição do fornecedor
	@Test
	public void editarFornecedor() {
		assertEquals(2,GerenciadorFornecedores.qtd());
		assertEquals(1123,GerenciadorFornecedores.getFornecedor(1).getCnpj());
		
		List<Integer> produtos1 = new ArrayList<>();
		produtos1.add(1); produtos1.add(2);
		Fornecedor f1 = new Fornecedor(1,7878,"CLS EMPRESA","Tomba",produtos1);
		GerenciadorFornecedores.addOuEdit(f1);
		assertEquals(7878,GerenciadorFornecedores.getFornecedor(1).getCnpj());
		assertTrue("Tomba"==GerenciadorFornecedores.getFornecedor(1).getEndereco());
		
		f1 = new Fornecedor(1,1234,"CLS S EMPRESA","Papagaio",produtos1);
		GerenciadorFornecedores.addOuEdit(f1);
		assertFalse("CLS EMPRESA"==GerenciadorFornecedores.getFornecedor(1).getName());
		assertTrue("CLS S EMPRESA"==GerenciadorFornecedores.getFornecedor(1).getName());
		
	}
	
	//Teste de remoção de fornecedor
	@Test
	public void removerFornecedor() {
		List<Integer> produtos3 = new ArrayList<>();
		produtos3.add(3); produtos3.add(4);
		Fornecedor f3 = new Fornecedor(8744,"TuudoCerto","NDA",produtos3);
		GerenciadorFornecedores.addOuEdit(f3);	
		assertEquals(3, GerenciadorFornecedores.qtd());
		GerenciadorFornecedores.remover(3);
		assertEquals(2, GerenciadorFornecedores.qtd());
		
		GerenciadorFornecedores.remover(2);
		GerenciadorFornecedores.remover(1);
		assertTrue(0==GerenciadorFornecedores.qtd());
		
		GerenciadorFornecedores.addOuEdit(f3);
		assertEquals(1, GerenciadorFornecedores.qtd());
		
		GerenciadorFornecedores.remover(10);
		assertEquals(1, GerenciadorFornecedores.qtd());
		
		

	}
	
	//Teste de listagem dos fornecedores
	@Test
	public void ListagemDeFornecedores() {
		List<Integer>produtos5 = new ArrayList<>();
		produtos5.add(5); produtos5.add(9);
		Fornecedor f5 = new Fornecedor(545454, "Fornecedor 3", "Nenhum",produtos5);
		GerenciadorFornecedores.addOuEdit(f5);	
		
		assertNotNull(f5.infoFornecedor(f5));
		assertNotNull(GerenciadorFornecedores.getFornecedor(f5));
		assertNotNull(GerenciadorFornecedores.getFornecedor(2));
		assertNotNull(GerenciadorFornecedores.listagem());
		assertNotNull(GerenciadorFornecedores.getListaDeFornecedores());
		
		assertEquals(3, GerenciadorFornecedores.qtd());		
		assertTrue("Fornecedor 3"==GerenciadorFornecedores.getFornecedor(3).getName());	
		GerenciadorFornecedores.remover(3);
		assertEquals(2,GerenciadorFornecedores.qtd());
		
		GerenciadorFornecedores.remover(2);
		GerenciadorFornecedores.remover(1);
		assertEquals(0,GerenciadorFornecedores.qtd());
		
		GerenciadorFornecedores.addOuEdit(f5);
		assertFalse(0==GerenciadorFornecedores.qtd());
		assertEquals(545454,GerenciadorFornecedores.getFornecedor(3).getCnpj());
		
		
		
		
	}
}
