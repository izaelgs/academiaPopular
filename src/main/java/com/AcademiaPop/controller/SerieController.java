package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.SerieDAO;
import com.AcademiaPop.model.entities.Serie;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	@GetMapping("/{id}")
	public Serie getSerie(@PathVariable int id) throws SQLException {		
		Serie serie = SerieDAO.getSerie(id);
		return serie;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public Serie insertSerie(@RequestBody Serie serie) throws SQLException {
		SerieDAO.insertSerie(serie);
		return serie;
	}
	
	@PutMapping("/update")
	public Serie updateSerie(@RequestBody Serie serie) throws SQLException {
		SerieDAO.updateSerie(serie);
		return serie;
	}
}
