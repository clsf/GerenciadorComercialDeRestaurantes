package model.entities;

public class Cliente {
	private static Integer ultimoId=1;
	private String nome;
	private String email;
	private Integer cpf;
	private Integer telefone;
	private Integer id;
	
	
	public Cliente(String nome, String email, Integer cpf, Integer telefone) {	
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id= ultimoId;
		ultimoId+=1;
	}


	public Cliente(String nome, String email, Integer cpf, Integer telefone, Integer id) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getCpf() {
		return cpf;
	}


	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}


	public Integer getTelefone() {
		return telefone;
	}


	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}


	public Integer getId() {
		return id;
	}


	public static Integer getUltimoId() {		
		return ultimoId;
	}
	
	
}
