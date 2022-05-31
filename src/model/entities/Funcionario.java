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
package model.entities;


/**
 * Classe para criação de Usuário do tipo Funcionário
 * @author Cláudia Inês Sales
 *
 */
public class Funcionario extends Usuario {
	/**
	 * Construtor do objeto Funcionario permitindo instanciar sem fornecer o ID
	 * @param login Identificação do funcionario 
	 * @param senha Senha do funcionário
	 * @param nome 	Nome do funcionário

	 */
	public Funcionario(String login, String senha, String nome) {
		super(login, senha, nome);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construtor do objeto usuário para instanciar junto com o ID,
	 * servirá para edição de usuários ja existentes
	 * @param id Identidade do funcionário
	 * @param login Identificação do funcionario 
	 * @param senha Senha do funcionário
	 * @param nome Nome do funcionário
	 */
	public Funcionario(Integer id, String login, String senha, String nome) {
		super(id, login, senha, nome);
	}
	
	

}
