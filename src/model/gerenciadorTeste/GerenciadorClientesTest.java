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
	
	
	//M�todo para inicializar a lista com alguns usu�rios 
	@BeforeEach
	public void init() throws DomainException {
		Facade.initialize();
	}
	
	//Met�do para limpar a lista e �ltimo ID para n�o dar erro nas posi��es
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
		GerenciadorClientes.addOuEdit(new Cliente("Nome1","Endere�o1",1245,54587));
		assertEquals(3,GerenciadorClientes.qtd());
		GerenciadorClientes.addOuEdit(new Cliente("Nome2","Endere�o2",1245,54587));
		assertEquals(4,GerenciadorClientes.qtd());

	}
	
	//Teste de edi��o do cliente do gerenciador
	@Test
	public void editarClienteTeste() throws DomainException {
		assertEquals(2,GerenciadorClientes.qtd());
		assertEquals("Nome1",GerenciadorClientes.getCliente(1).getNome());
		assertEquals("Endere�o1",GerenciadorClientes.getCliente(1).getEmail());
		assertEquals(1245,GerenciadorClientes.getCliente(1).getCpf());
		assertEquals(54587,GerenciadorClientes.getCliente(1).getTelefone());
		Cliente c1 = new Cliente("editado","editadoEmail",1,2,1);
		GerenciadorClientes.addOuEdit(c1);
		assertEquals("editado",GerenciadorClientes.getCliente(1).getNome());
		assertEquals("editadoEmail",GerenciadorClientes.getCliente(1).getEmail());
		assertEquals(1,GerenciadorClientes.getCliente(1).getCpf());
		assertEquals(2,GerenciadorClientes.getCliente(1).getTelefone());
		

	}
	
	//Teste de remo��o de usu�rio do gerenciador
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
