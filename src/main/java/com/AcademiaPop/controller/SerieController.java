package com.AcademiaPop.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.SerieDAO;
import com.AcademiaPop.model.entities.Exercicio;
import com.AcademiaPop.model.entities.Serie;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	@GetMapping("/{id}")
	public Serie getSerie(@PathVariable int id) throws SQLException {		
		Serie serie = SerieDAO.getSerie(id);
		return serie;
	}
	
	@GetMapping("/exercicios/{id}")
	public Serie getSerieExercicios(@PathVariable int id) throws SQLException {		
		Serie serie = SerieDAO.getSerieExercicios(id);
		return serie;
	}
	
	@GetMapping("/recentes")
	public List<Serie> getSerie() throws SQLException {		
		List<Serie> serie = SerieDAO.getSerieRecentes();
		return serie;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public Serie insertSerie(@RequestBody Serie serie) throws SQLException {
		SerieDAO.insertSerie(serie);
		return serie;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "exercicio/insert")
	public Serie insertSerieExercicio(@RequestBody Serie serie) throws SQLException {
		SerieDAO.insertSerieExercicio(serie);
		return serie;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "exercicio/insere/{id}")
	public boolean insertSerieExercicio(@RequestBody Exercicio exercicio, @PathVariable int id) throws SQLException {
		SerieDAO.insertSerieExercicio(exercicio,id);
		return true;
	}
	
	@PutMapping("/update")
	public Serie updateSerie(@RequestBody Serie serie) throws SQLException {
		SerieDAO.updateSerie(serie);
		return serie;
	}
}
