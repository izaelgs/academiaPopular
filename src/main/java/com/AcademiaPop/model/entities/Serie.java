package com.AcademiaPop.model.entities;

public class Serie {
	int id;
	public int progresso;
	public String titulo;
	public String descricao;
	
	public Serie() {
		
	}
	
	public Serie(int progresso, String titulo, String descricao) {		
		this.progresso = progresso;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Serie(int id, int progresso, String titulo, String descricao) {		
		this.id = id;
		this.progresso = progresso;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}
}
