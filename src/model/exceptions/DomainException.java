/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 07/05/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package model.exceptions;
/**
 * Classe do tipo Exception para cuidar dos erros relacionados ao funcionamento do programa
 * @author Cláudia Inês Sales
 *
 */

public class DomainException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Metódo para mandar a mensagem ser exebida através da superclasse Exception
	 * @param msg Mensagem a ser exibida
	 */
	public DomainException (String msg) {
		super(msg);
	}
	
}
