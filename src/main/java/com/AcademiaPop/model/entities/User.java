package com.AcademiaPop.model.entities;

public class User {
	public String login;
	public String senha;
	public String email;
	public String telefone;
	public String nome;
	public String cpf;
	public String img;	
	private int id;
	public int status;
	
	public User(String login, String senha, String email, String telefone, String nome, String cpf) {		
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;		
	}
	
	public User(int status, int id, String login, String senha, String email, String telefone, String nome, String cpf, String img) {	
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;
		this.img = img;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
}
