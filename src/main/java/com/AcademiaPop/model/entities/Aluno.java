package com.AcademiaPop.model.entities;

public class Aluno extends User{
	int id;
	int id_user_aluno;
	
	public Aluno() {
		super();
	}
	
	public Aluno(String login, String senha, String email, String telefone, String nome, String cpf) {
		super(login, senha, email, telefone, nome, cpf);		
	}
	
	public Aluno(int id, int id_user, int status,String login, String senha, String email, String telefone, String nome, String cpf, String img) {
		super(status, id, login, senha, email, telefone, nome, cpf, img);
		this.id = id;
		this.id_user_aluno = id_user;
	}
	
	//sem senha e cpf
	public Aluno(int id, int status,String login, String email, String telefone, String nome, String img) {
		super(status, id, login, email, telefone, nome, img);
		this.id = id;
	}
	
	public Aluno(int modulo,int id, int id_user, int status,String login, String senha, String email, String telefone, String nome, String cpf, String img) {
		super(modulo,status, id, login, senha, email, telefone, nome, cpf, img);
		this.id = id;
		this.id_user_aluno = id_user;
	}

	public int getId_user() {
		return id_user_aluno;
	}
	
}
