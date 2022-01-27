package com.AcademiaPop.model.entities;

public class User {
	public int status;
	public String login;
	private String senha;
	public String email;
	public String telefone;
	public String nome;
	public String cpf;
	public String img;	
	public int modulo;
	private int id;
	
	public User(String login, String senha, String email, String telefone, String nome, String cpf) {		
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;		
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public User(int status, int id, String login, String senha, String email, String telefone, String nome, String cpf, String img) {	
		this.status = status;
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;
		this.img = img;
	}
	
	//sem senha e cpf
	public User(int status, int id, String login, String email, String telefone, String nome, String img) {	
		this.status = status;
		this.id = id;
		this.login = login;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.img = img;
	}
	
	public User(String nome, String img) {			
		this.nome = nome;
		this.img = img;
	}
	
	public User(int modulo,int status, int id, String login, String senha, String email, String telefone, String nome, String cpf, String img) {	
		this.status = status;
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;
		this.img = img;
		this.modulo = modulo;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
}
