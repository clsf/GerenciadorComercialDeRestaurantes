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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.exceptions.DomainException;
import model.entities.Cliente;
import model.entities.Fornecedor;
import model.entities.Funcionario;
import model.entities.Gerente;
import model.entities.Prato;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Venda;
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.gerenciadores.GerenciadorUsuarios;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Facade;

public class GerenciadorUsuariosTest {
	GerenciadorUsuarios gu = new GerenciadorUsuarios();
	
	//Método para inicializar a lista com alguns usuários 
	@BeforeEach
	public void init() throws DomainException {
		Facade.initialize();
	}
	
	//Metódo para limpar a lista e último ID para não dar erro nas posições
	//no momento do teste 
	@AfterEach
	public void setUp() {
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
	
	//Teste de adicionar usuários do gerenciador
	@Test
	public void adicionarUsuarioTeste() throws DomainException {
		assertEquals(2,GerenciadorUsuarios.qtd());
		Usuario u3 = new Funcionario("NON","5487ss","Ninguém");
		GerenciadorUsuarios.addOuEdit(u3);
		assertEquals(3,GerenciadorUsuarios.qtd());
		assertSame(u3,GerenciadorUsuarios.getUsuario(3));
		
		Usuario u4 = new Gerente("Robson","MaisUm","Robson Santos");
		GerenciadorUsuarios.addOuEdit(u4);		
		assertEquals(4,GerenciadorUsuarios.qtd());
		assertSame(u4,GerenciadorUsuarios.getUsuario(4));
		
		assertEquals(5,Usuario.getUltimoId());
		
		assertNotNull(GerenciadorUsuarios.cadastrarUsuario("0","0","0",1));
		assertNotNull(GerenciadorUsuarios.cadastrarUsuario("1","1","1",2));
		try {
			GerenciadorUsuarios.cadastrarUsuario("0","0","0",1);
		}catch(DomainException e) {
			assertTrue(true);
		}
	}
	
	//Teste de edição do usuário do gerenciador
	@Test
	public void editarUsuarioTeste() throws DomainException {
		assertEquals(2,GerenciadorUsuarios.qtd());
		assertEquals("claus",GerenciadorUsuarios.getUsuario(1).getLogin());
		assertEquals("cometa",GerenciadorUsuarios.getUsuario(1).getSenha());
		assertEquals("Cláudia Inês",GerenciadorUsuarios.getUsuario(1).getNome());
		Usuario u3 = new Usuario(1,"CLS_F","Cometa08","Cláudia Inês Sales");
		GerenciadorUsuarios.addOuEdit(u3);
		assertEquals("CLS_F",GerenciadorUsuarios.getUsuario(1).getLogin());
		assertEquals("Cometa08",GerenciadorUsuarios.getUsuario(1).getSenha());
		assertEquals("Cláudia Inês Sales",GerenciadorUsuarios.getUsuario(1).getNome());
		
		u3 = new Gerente(1,"CLS_F","Cometa08","Cláudia Inês Sales");
		GerenciadorUsuarios.addOuEdit(u3);
		assertEquals("CLS_F",GerenciadorUsuarios.getUsuario(1).getLogin());
		assertEquals("Cometa08",GerenciadorUsuarios.getUsuario(1).getSenha());
		assertEquals("Cláudia Inês Sales",GerenciadorUsuarios.getUsuario(1).getNome());
		
		u3 = new Funcionario(1,"CLS_F","Cometa08","Cláudia Inês Sales");
		GerenciadorUsuarios.addOuEdit(u3);
		assertEquals("CLS_F",GerenciadorUsuarios.getUsuario(1).getLogin());
		assertEquals("Cometa08",GerenciadorUsuarios.getUsuario(1).getSenha());
		assertEquals("Cláudia Inês Sales",GerenciadorUsuarios.getUsuario(1).getNome());
		assertEquals(2,GerenciadorUsuarios.qtd());
	
	}
	
	//Teste de remoção de usuário do gerenciador
	@Test
	public void removerUsuarioTeste() throws DomainException {
		assertEquals(2,GerenciadorUsuarios.qtd());
		Usuario u3 = new Usuario("Ninguém","54587","Ninguem SS");
		GerenciadorUsuarios.addOuEdit(u3);
		GerenciadorUsuarios.remover(2);
		assertFalse(3==GerenciadorUsuarios.qtd());
		assertEquals(2,GerenciadorUsuarios.qtd());
		GerenciadorUsuarios.remover(3);
		assertEquals(1,GerenciadorUsuarios.qtd());
		GerenciadorUsuarios.remover(1);
		assertEquals(0,GerenciadorUsuarios.qtd());
	}
	
	
	//Teste de listagem dos do gerenciador
	@Test
	public void ListagemDeUsuariosTeste() throws DomainException {
		assertEquals(2,GerenciadorUsuarios.qtd());
		Usuario u3 = new Usuario("Ninguém","54587","Ninguem SS");
		GerenciadorUsuarios.addOuEdit(u3);
		assertEquals(3,GerenciadorUsuarios.qtd());
		Usuario u4 = new Usuario("Ninguém2","54587","Ninguem SS");
		Usuario u5 = new Usuario("Ninguém3","54587","Ninguem SS"); 
		GerenciadorUsuarios.addOuEdit(u4);
		assertFalse(GerenciadorUsuarios.qtd()==2);
		assertSame(u4,GerenciadorUsuarios.getUsuario(4));
		GerenciadorUsuarios.addOuEdit(u5);
		assertEquals("Ninguem SS",GerenciadorUsuarios.getUsuario(5).getNome());
		assertEquals("54587",GerenciadorUsuarios.getUsuario(5).getSenha());
		
		assertEquals(5,GerenciadorUsuarios.qtd());
		
		assertNotNull(u5.infoUsuario(u5));
		assertNotNull(GerenciadorUsuarios.getListaDeUsuarios());
		assertNotNull(GerenciadorUsuarios.listagem());
	}
	
	//Teste de login
	@Test
	public void loginTeste() throws DomainException {
		Usuario u= GerenciadorUsuarios.login("claus","cometa");
		assertNotNull(u);
		try{
			u=GerenciadorUsuarios.login("nenhum", "nada");
			
		}catch(DomainException e) {
			assertTrue(true);
		}
		
	}
	
}
