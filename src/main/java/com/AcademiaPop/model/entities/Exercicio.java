package com.AcademiaPop.model.entities;

public class Exercicio {	
	public int id;
	public String titulo;
	public String descricao;
	
	public Exercicio() {
		
	}
	
	public Exercicio(int id_e, String titulo_e, String desc_e) {
		this.id = id_e;
		this.titulo = titulo_e;
		this.descricao = desc_e;
	}
}
