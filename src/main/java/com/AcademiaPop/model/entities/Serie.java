package com.AcademiaPop.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Serie {
	int id;
	public String titulo;
	public String descricao;
	public List<Exercicio> exercicios = new ArrayList<Exercicio>();
	
	public Serie() {
		
	}
	
	public Serie(String titulo, String descricao) {		
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Serie(int id, String titulo, String descricao) {		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Serie(int id, String titulo, String descricao, List<Exercicio> exercicios) {		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.exercicios = exercicios;
	}

	public int getId() {
		return id;
	}
}
