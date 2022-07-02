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


import org.junit.platform.suite.api.*;

@Suite
@SelectClasses({
	GerenciadorFornecedorTest.class,
	GerenciadorPratoTest.class,
	GerenciadorProdutoTest.class,
	GerenciadorUsuariosTest.class,
	GerenciadorVendaTest.class,
	GerenciadorClientesTest.class,
	RelatoriosTest.class
	
})
public class SuiteTest {
}
