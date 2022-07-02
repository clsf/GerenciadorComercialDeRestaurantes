/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 23/06/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

package model.entities;
/**
 * Classe para cria��o de Clientes
 * @author Cl�udia In�s Sales Freitas
 *
 */

public class Cliente {
	
	private static Integer ultimoId=1; //Salva o �ltimo ID utilizado, atributo pertencente a classe
	private String nome;				//Nome do cliente
	private String email;				//Email do cliente
	private Integer cpf;				//CPF do cliente
	private Integer telefone;			//Telefone do cliente
	private Integer id;					//C�digo de identifica��o do cliente
	
	/**
	 * Construtor do objeto cliente permitindo instanciar sem fornecer o ID
	 * @param nome	Nome do cliente
	 * @param email Email do cliente
	 * @param cpf   CPF do cliente
	 * @param telefone Telefone do cliente
	 */
	
	public Cliente(String nome, String email, Integer cpf, Integer telefone) {	
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id= ultimoId;
		ultimoId+=1;
	}


	/**
	 * Construtor do objeto Cliente para instanciar junto com o ID,
	 * servir� para edi��o de clientes ja existentes
	 * @param nome	Nome do cliente
	 * @param email	Email do Cliente
	 * @param cpf CPF do cliente
	 * @param telefone Telefone do Cliente
	 * @param id C�digo de identifica��o do cliente
	 */
	public Cliente(String nome, String email, Integer cpf, Integer telefone, Integer id) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id = id;
	}
	/**
	 * Met�do para pegar o nome do cliente
	 * @return String com o nome do cliente
	 */

	public String getNome() {
		return nome;
	}
	
	/**
	 * Met�do para alterar o nome do cliente
	 * @param nome Nome do cliente em String
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Met�do para pegar o email do cliente
	 * @return String com o email do cliente
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Met�do para alterar o email do cliente
	 * @param email Email do cliente em String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Met�do para pegar CPF do cliente
	 * @return Integer com o cpf do cliente
	 */
	public Integer getCpf() {
		return cpf;
	}

	/**
	 * Met�do para alterar o CPF do cliente
	 * @param cpf CPF do cliente em Integer
	 */
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	/**
	 * Met�do para pegar o telefone do cliente
	 * @return Integer com o telefone do cliente
	 */
	public Integer getTelefone() {
		return telefone;
	}
	/**
	 * Met�do para alterar o telefone do cliente
	 * @param telefone Telefone do cliente em Integer
	 */

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	/**
	 * Met�do para pegar o ID do cliente
	 * @return Integer com o ID do cliente
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Met�do para pegar o �ltimo ID utilizado em clientes
	 * @return Integer contendo o �ltimo ID
	 */
	public static Integer getUltimoId() {		
		return ultimoId;
	}


	public static void setUltimoId(int i) {
		ultimoId = i;
		
	}
	
	
}
