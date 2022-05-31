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
 * Classe para criação objetos do tipo Gerente que é um usuário
 * @author Cláudia Inês Sales
 *
 */
public class Gerente extends Usuario{
	/**
	 * Construtor para instanciar o Gerente sem fornecer o ID
	 * @param login Uma das identificações do gerente
	 * @param senha de acesso do gerente
	 * @param nome do gerente 
	 */

	public Gerente(String login, String senha, String nome) {
		super(login, senha, nome);
		
	}
	
	/**
	 * Construtor do objeto gerente para instanciar junto com o ID,
	 * servirá para edição de gerentes ja existentes
	 * @param id Identificação do gerente
	 * @param login Uma das identificações do gerente
	 * @param senha de acesso do gerente
	 * @param nome do gerente 
	 */
	public Gerente(Integer id, String login, String senha, String nome) {
		super(id, login, senha, nome);
	}
	

}
