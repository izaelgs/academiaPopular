package com.AcademiaPop.model.entities;

public class Professor extends User{	
	int id;
	int id_user;
	
	public Professor() {
		
	}
	
	public Professor(String login, String senha, String email, String telefone, String nome, String cpf) {
		super(login, senha, email, telefone, nome, cpf);		
	}
	
	public Professor(int id, int id_user, int status,String login, String senha, String email, String telefone, String nome, String cpf, String url) {
		super(status, id, login, senha, email, telefone, nome, cpf, url);
		this.id = id;
		this.id_user = id_user;
	}

	public int getId_user() {
		return id_user;
	}	

}
