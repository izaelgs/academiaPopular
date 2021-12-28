package com.AcademiaPop.model.entities;

public class Dia {
	int id;
	public int id_serie;
	public int id_semana;
	public int dia;
	
	public Dia() {
		
	}
	
	public Dia(int id_serie, int id_semana, int dia) {		
		this.id_serie = id_serie;
		this.id_semana = id_semana;
		this.dia = dia;
	}
	
	public Dia(int id, int id_serie, int id_semana, int dia) {		
		this.id = id;
		this.id_serie = id_serie;
		this.id_semana = id_semana;
		this.dia = dia;
	}

	public int getId() {
		return id;
	}	
}
