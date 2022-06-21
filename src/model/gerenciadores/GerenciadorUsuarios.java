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
package model.gerenciadores;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.DomainException;
import model.entities.Funcionario;
import model.entities.Gerente;
import model.entities.Usuario;

/**
 * Classe para criação do objeto Gerenciador de Pratos
 * @author Cláudia Inês Sales
 *
 */

public class GerenciadorUsuarios {	
	
	
	private static List <Usuario> listaUsuarios = new ArrayList<>(); //Lista de Usuários cadastrados
	
	/**
	 * Construtor para inicializar o Gerenciador de Usuários
	 */
	public GerenciadorUsuarios() {
		
	}
	
	/**
	 * Metódo para adicionar o usuário na lista 
	 * @param usuario Objeto do tipo Usuário que será adicionado
	 */
	private static void add(Usuario usuario) {		
		GerenciadorUsuarios.listaUsuarios.add(usuario);		
	}
	
	/**
	 *  Metódo para editar fornecedor já existente na lista
	 * @param usuarioEdit Objeto do tipo Usuário que já existe na lista  e será editado
	 * @param alterarUsuario Objeto do tipo Usuário que será utilizado como parâmetro para
	 * substituição
	 * @throws DomainException 
	 */
	private static void editar(Usuario usuarioEdit, Usuario alterarUsuario) throws DomainException {
		
		Usuario usuario = GerenciadorUsuarios.getListaDeUsuarios().stream().filter(x -> x.getLogin().equals(alterarUsuario.getLogin())).findFirst().orElse(null);
		
		if(usuario!=null && !usuario.getId().equals(usuarioEdit.getId())) {			
			throw new DomainException("Login já está sendo utilizado!");
		}else {
			listaUsuarios.set(listaUsuarios.indexOf(usuarioEdit),alterarUsuario);
		}
		
		
		
		/*listaUsuarios.add(alterarUsuario);
		listaUsuarios.remove(usuarioEdit);
		System.out.println(usuarioEdit.getCargo());
		System.out.println(alterarUsuario.getCargo());
		//Se o login for diferente será trocado
		if (usuarioEdit.getLogin() != alterarUsuario.getLogin()){
			usuarioEdit.setLogin(alterarUsuario.getLogin());
		}
		//Se a senha for diferente será trocada
		if(usuarioEdit.getSenha() != alterarUsuario.getSenha()) {
			usuarioEdit.setSenha(alterarUsuario.getSenha());
		}
		//Se o nome for diferente será trocado
		if(usuarioEdit.getNome() != alterarUsuario.getNome()) {
			usuarioEdit.setNome(alterarUsuario.getNome());
		}
		if(alterarUsuario.getCargo()!=usuarioEdit.getCargo()) { 
			
			System.out.println("Entrou primeiro if");
		
			if(alterarUsuario.getCargo().equals("Funcionario")) {
				System.out.println("Entrou segundo if");
				usuarioEdit=(Funcionario) usuarioEdit;
			}else {
				usuarioEdit = (Gerente) usuarioEdit;
			}
		}*/
	}	
	
	/**
	 * Metódo que será utilizado por outras classes para adicionar ou editar um usuário
	 * Nele o objeto passado será verificado se já existe na lista, se não existir será
	 * adicionado, caso já exista será editado.
	 * @param usuario Objeto do tipo usuário que será adicionado ou editado
	 * @throws DomainException 
	 */
	
	public static void addOuEdit(Usuario usuario) throws DomainException {
		//Verifica a existência do usuário na lista através do ID
		Usuario usuarioExistente = GerenciadorUsuarios.listaUsuarios.stream().filter(x -> x.getId() == usuario.getId())
				.findFirst().orElse(null);
		//Se o ID já existir o usuários será editado, se não será adicionado
		if(usuarioExistente != null) {
			editar(usuarioExistente, usuario);
		}
		else {
			add(usuario);
		}
	}
	
	/**
	 * Metódo para remover usuário da lista através do ID dele
	 * 
	 * @param id ID do usuário que será removido da lista
	 */
	public static void remover(Integer id) {
		Usuario result = GerenciadorUsuarios.listaUsuarios.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if(result!=null) {
			GerenciadorUsuarios.listaUsuarios.remove(result);
			
		}
	}
	
	
	public static void remover(Usuario usuario) {
		listaUsuarios.remove(usuario);
	}
	
	/**
	 * Metódo para pegar a lista completa dos usuários
	 * @return Lista de Usuários - Lista dos usuários cadastrados
	 */
	
	public static List<Usuario> getListaDeUsuarios(){
		return GerenciadorUsuarios.listaUsuarios;
	}
	
	/**
	 * Metódo para pegar um usuário específio na lista através do seu ID
	 * @param id ID do usuário
	 * @return Usuário - Objeto do tipo usuário
	 */
	
	public static Usuario getUsuario(Integer id) {
		Usuario usuario =GerenciadorUsuarios.listaUsuarios.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return usuario;
	}
	
	
	
	public static List<Usuario> buscar(String nome){
		List<Usuario> list = new ArrayList<>();
		for(Usuario u: listaUsuarios) {
			if(u.getNome().equals(nome)) {
				list.add(u);
			}
		}
		
		return list;
	}
	
	
	/**
	 * Metódo para fazer a listagem dos usuários
	 * @return String - Listagem dos usuários cadastrados
	 */
	public static String listagem() {
		String listagem=" ";
		//Percorre a lista concatenando as informações todas em uma variável do tipo String
		for(Usuario usuario : GerenciadorUsuarios.listaUsuarios) {
			listagem +="\nCódigo: "+usuario.getId()+"\nLogin: "+usuario.getLogin()+
					"\nSenha: "+usuario.getSenha()+"\n";
		}
		
		return listagem;
	}
	/**
	 *Metódo para pegar a quantidade de usuários cadastrados na lista 
	 * @return Integer - Quantidade de usuários cadastrados
	 */
	public static Integer qtd() {
		return listaUsuarios.size();
	}
	
	/**
	 * Metódo para limpar a lista de fornecedores, somente será utilizado nos Testes
	 */
	public void limparLista() {
		GerenciadorUsuarios.listaUsuarios.clear();
	}
	
	/**
	 * Metódo para logar um usuário no sistema
	 * @param login Login do usuário
	 * @param senha Senha do Usuário
	 * @return Objeto do tipo usuário confirmando o login ou null não reconhecendo o usuário
	 * @throws DomainException 
	 */
	public static Usuario login(String login, String senha) throws DomainException {

		Usuario usuario =GerenciadorUsuarios.listaUsuarios.stream().filter(x -> x.getLogin().equals(login)).findFirst().orElse(null);


		if(usuario!=null) {			
			if(usuario.getSenha().equals(senha)) {				
				return usuario;
			}
		}			
		throw new DomainException("Usuário não encontrado! Por favor verificar login e senha.");
	}
	/**
	 * Metódo para cadastrar um usuário 
	 * @param login Login único do usuário
	 * @param senha Senha do usuário
	 * @param nome Nome do usuário
	 * @param opcao Tipo do usuário, gerente ou funcionário (1 ou 2)
	 * @return Objeto do tipo usuário cadastrado
	 * @throws DomainException Erro ao criar usuário, login já sendo utilizado
	 */
	public static Usuario cadastrarUsuario(String login, String senha, String nome,Integer opcao) throws DomainException {
		Usuario usuario = GerenciadorUsuarios.getListaDeUsuarios().stream().filter(x -> x.getLogin().equals(login)).findFirst().orElse(null);
		
		if(usuario!=null) {
			throw new DomainException("Login já está sendo utilizado!");
		}
		else {	
			if(opcao==1) {
				usuario = new Gerente(login, senha, nome);				
			}
			else {
				usuario=new Funcionario(login,senha,nome);
			}
			GerenciadorUsuarios.addOuEdit(usuario);
		}
		
		return usuario;
		
	}
}

	
