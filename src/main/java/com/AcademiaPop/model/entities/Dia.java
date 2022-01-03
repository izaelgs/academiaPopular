package com.AcademiaPop.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Dia {
	int id;
	public int id_serie;
	public int id_semana;
	public int dia;
	public List<Serie> series = new ArrayList<Serie>();
	
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
	
	public Dia(int id, int id_serie, int id_semana, int dia, List<Serie> series) {		
		this.id = id;
		this.id_serie = id_serie;
		this.id_semana = id_semana;
		this.dia = dia;
		this.series = series;
	}

	public int getId() {
		return id;
	}	
}
