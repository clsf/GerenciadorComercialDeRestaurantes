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

import model.entities.Usuario;

/**
 * Classe para criação de usuário
 * @author Cláudia Inês Sales 
 *
 */
public class Usuario {
	private static Integer ultimoId=1; // Salva o último ID utilizado, atributo pertencente a classe
	private Integer id;				  // Id	do usuário
	private String login;			// Login do Usuário
	private String senha;   		// Senha do Usuário
	private String nome;			// Nome do Usuário
	private String cargo;
	
	
	
	
	/**
	 * Construtor do objeto usuário permitindo instanciar sem fornecer o ID
	 * @param login Identificação do usuário
	 * @param senha Senha do usuário
	 * @param nome 	Nome do Usuário
	 */
	
	public Usuario(String login, String senha, String nome) {
		this.id = ultimoId;  //Coloca o último ID disponível
		this.login = login;  //Login do Usuário
		this.senha = senha;  // Senha do Usuário
		this.nome = nome;    // Nome do usuário
		ultimoId++; 		//Incrementa o ID toda vez que adicionado um usuário
		
	}

	
	/**
	 * Construtor do objeto usuário para instanciar junto com o ID,
	 *  servirá para edição de usuários ja existentes
	 * @param id   Identidade do usuário 
	 * @param login Identificação do usuário
	 * @param senha  Senha do usuário
	 * @param nome   Nome do usuário
	 */
	public Usuario(Integer id, String login, String senha, String nome) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		
	}

	/** Metódo para pegar o Nome do usuário
	 * 
	 * @return String - nome do usuário
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metódo para configurar o nome do usuário
	 * @param nome Nome do usuário em String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metódo para recuperar ID do usuário
	 * @return Integer - ID do usuário
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metódo para recuperar Login do usuário
	 * @return String - login do usuário
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metódo para configurar o Login do usuário
	 * @param login Uma String com o Login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metódo para recuperar a senha do usuário
	 * @return String - senha do usuário
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Metódo para configurar a senha do usuário
	 * @param senha Uma String com a senha 
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Metódo para recuperar o último ID salvo
	 * @return Integer - último ID usado
	 */
	public static Integer getUltimoId() {
		return ultimoId;
	}

	/**
	 * Metódo para configurar o último ID. Ele será utilizado apenas nos Testes, 
	 * permitindo zerá-lo
	 * @param ultimoId Um Integer com o ID que será referência
	 */
	public static void setUltimoId(Integer ultimoId) {
		Usuario.ultimoId = ultimoId;
	}
	
	/**
	 * Metódo para exibir as informações de um usuário
	 * @param u Usuário que será exibido as informações
	 * @return String contendo as informações do usuário
	 */
	public String infoUsuario(Usuario u) {
		String info= "Código:" + u.getId() +"\nNome: " +u.getNome() + "\nLogin:" +u.getLogin();
		return info;
		
	}
	

	public String getCargo() {
		return getClass().getSimpleName();
	}
}
