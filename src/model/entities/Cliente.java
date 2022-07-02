/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 23/06/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package model.entities;
/**
 * Classe para criação de Clientes
 * @author Cláudia Inês Sales Freitas
 *
 */

public class Cliente {
	
	private static Integer ultimoId=1; //Salva o último ID utilizado, atributo pertencente a classe
	private String nome;				//Nome do cliente
	private String email;				//Email do cliente
	private Integer cpf;				//CPF do cliente
	private Integer telefone;			//Telefone do cliente
	private Integer id;					//Código de identificação do cliente
	
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
	 * servirá para edição de clientes ja existentes
	 * @param nome	Nome do cliente
	 * @param email	Email do Cliente
	 * @param cpf CPF do cliente
	 * @param telefone Telefone do Cliente
	 * @param id Código de identificação do cliente
	 */
	public Cliente(String nome, String email, Integer cpf, Integer telefone, Integer id) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id = id;
	}
	/**
	 * Metódo para pegar o nome do cliente
	 * @return String com o nome do cliente
	 */

	public String getNome() {
		return nome;
	}
	
	/**
	 * Metódo para alterar o nome do cliente
	 * @param nome Nome do cliente em String
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metódo para pegar o email do cliente
	 * @return String com o email do cliente
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Metódo para alterar o email do cliente
	 * @param email Email do cliente em String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Metódo para pegar CPF do cliente
	 * @return Integer com o cpf do cliente
	 */
	public Integer getCpf() {
		return cpf;
	}

	/**
	 * Metódo para alterar o CPF do cliente
	 * @param cpf CPF do cliente em Integer
	 */
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	/**
	 * Metódo para pegar o telefone do cliente
	 * @return Integer com o telefone do cliente
	 */
	public Integer getTelefone() {
		return telefone;
	}
	/**
	 * Metódo para alterar o telefone do cliente
	 * @param telefone Telefone do cliente em Integer
	 */

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	/**
	 * Metódo para pegar o ID do cliente
	 * @return Integer com o ID do cliente
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metódo para pegar o último ID utilizado em clientes
	 * @return Integer contendo o último ID
	 */
	public static Integer getUltimoId() {		
		return ultimoId;
	}


	public static void setUltimoId(int i) {
		ultimoId = i;
		
	}
	
	
}
