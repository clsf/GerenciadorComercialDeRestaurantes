/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 07/05/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/
package model.exceptions;
/**
 * Classe do tipo Exception para cuidar dos erros relacionados ao funcionamento do programa
 * @author Cl�udia In�s Sales
 *
 */

public class DomainException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Met�do para mandar a mensagem ser exebida atrav�s da superclasse Exception
	 * @param msg Mensagem a ser exibida
	 */
	public DomainException (String msg) {
		super(msg);
	}
	
}
