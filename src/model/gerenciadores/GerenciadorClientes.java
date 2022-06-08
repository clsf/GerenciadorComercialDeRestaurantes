package model.gerenciadores;

import java.util.ArrayList;
import java.util.List;

import model.entities.*;

public class GerenciadorClientes {
	private static  List<Cliente> listaDeClientes = new ArrayList<>();
	
	
	private static void add(Cliente cliente) {		
		listaDeClientes.add(cliente);	
	}
	
	private static void editar(Cliente clienteEdit, Cliente alterarCliente) {
		
		listaDeClientes.set(listaDeClientes.indexOf(clienteEdit),alterarCliente);
		
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
	
	public static void addOuEdit(Cliente cliente) {
		//Verifica a existência do usuário na lista através do ID
		Cliente clienteExistente = GerenciadorClientes.listaDeClientes.stream().filter(x -> x.getId() == cliente.getId())
				.findFirst().orElse(null);
		//Se o ID já existir o usuários será editado, se não será adicionado
		if(clienteExistente != null) {
			editar(clienteExistente, cliente);
		}
		else {
			add(cliente);
		}
	}
	
	public static void remover(Integer id) {
		Cliente result = GerenciadorClientes.listaDeClientes.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if(result!=null) {
			GerenciadorClientes.listaDeClientes.remove(result);
			
		}
	}
	
	public static void remover(Cliente cliente) {
		listaDeClientes.remove(cliente);
	}
	
	public static List<Cliente> getListaDeClientes(){
		return GerenciadorClientes.listaDeClientes;
	}

	public static Cliente getCliente(Integer id) {
		Cliente cliente =GerenciadorClientes.listaDeClientes.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return cliente;
	}
	
	public static List<Cliente> buscar(String nome){
		List<Cliente> list = new ArrayList<>();
		for(Cliente c: listaDeClientes) {
			if(c.getNome().equals(nome)) {
				list.add(c);
			}
		}
		
		return list;
	}
	
	public static String listagem() {
		String listagem=" ";
		//Percorre a lista concatenando as informações todas em uma variável do tipo String
		for(Cliente cliente : GerenciadorClientes.listaDeClientes) {
			listagem +="\nCódigo: "+cliente.getId()+"\nNome: "+cliente.getNome()+
					"\nEmai: "+cliente.getEmail()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "+cliente.getTelefone();
		}
		
		return listagem;
	}
	
	public static Integer qtd() {
		return listaDeClientes.size();
	}
	
	public void limparLista() {
		GerenciadorClientes.listaDeClientes.clear();
	}
	
	

}
