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
import model.gerenciadores.GerenciadorClientes;
import model.gerenciadores.GerenciadorFornecedores;
import model.gerenciadores.GerenciadorPratos;
import model.gerenciadores.GerenciadorProdutos;
import model.gerenciadores.GerenciadorUsuarios;
import model.gerenciadores.GerenciadorVendas;
import model.utils.Facade;

public class GerenciadorClientesTest {
	
	
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
	
	//Teste de adicionar clientes do gerenciador
	@Test
	public void adicionarClienteTeste() throws DomainException {
		assertEquals(2,GerenciadorClientes.qtd());
		GerenciadorClientes.addOuEdit(new Cliente("Nome1","Endereço1",1245,54587));
		assertEquals(3,GerenciadorClientes.qtd());
		GerenciadorClientes.addOuEdit(new Cliente("Nome2","Endereço2",1245,54587));
		assertEquals(4,GerenciadorClientes.qtd());

	}
	
	//Teste de edição do cliente do gerenciador
	@Test
	public void editarClienteTeste() throws DomainException {
		assertEquals(2,GerenciadorClientes.qtd());
		assertEquals("Nome1",GerenciadorClientes.getCliente(1).getNome());
		assertEquals("Endereço1",GerenciadorClientes.getCliente(1).getEmail());
		assertEquals(1245,GerenciadorClientes.getCliente(1).getCpf());
		assertEquals(54587,GerenciadorClientes.getCliente(1).getTelefone());
		Cliente c1 = new Cliente("editado","editadoEmail",1,2,1);
		GerenciadorClientes.addOuEdit(c1);
		assertEquals("editado",GerenciadorClientes.getCliente(1).getNome());
		assertEquals("editadoEmail",GerenciadorClientes.getCliente(1).getEmail());
		assertEquals(1,GerenciadorClientes.getCliente(1).getCpf());
		assertEquals(2,GerenciadorClientes.getCliente(1).getTelefone());
		

	}
	
	//Teste de remoção de usuário do gerenciador
	@Test
	public void removerUsuarioTeste() throws DomainException {
		assertEquals(2,GerenciadorClientes.qtd());
		Cliente c1 = new Cliente("editado","editadoEmail",1,2);
		GerenciadorClientes.addOuEdit(c1);
		GerenciadorClientes.remover(2);
		assertFalse(3==GerenciadorClientes.qtd());
		assertEquals(2,GerenciadorClientes.qtd());
		GerenciadorClientes.remover(3);
		assertEquals(1,GerenciadorClientes.qtd());
		GerenciadorClientes.remover(1);
		assertEquals(0,GerenciadorClientes.qtd());
	}
	
	
	//Teste de listagem dos do gerenciador
	@Test
	public void ListagemDeUsuariosTeste() throws DomainException {
		assertEquals(2,GerenciadorClientes.qtd());
		assertNotNull(GerenciadorClientes.listagem());
		assertNotNull(GerenciadorClientes.getListaDeClientes());
		
	}
	

	
}
