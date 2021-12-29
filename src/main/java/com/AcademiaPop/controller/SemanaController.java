package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.SemanaDAO;
import com.AcademiaPop.model.entities.Semana;

@RestController
@RequestMapping("/semana")
public class SemanaController {	
	
	@GetMapping("/{id}")
	public Semana getSemana(@PathVariable int id) throws SQLException {
		Semana semana = SemanaDAO.getSemana(id);
		return semana;
	}
	
	@PostMapping("/insert")
	public Semana insertSemana(@RequestBody Semana semana) throws SQLException{
		SemanaDAO.insertSemana(semana);		
		return semana;
	}
	
	@PutMapping("/update")
	public Semana updateSemana(@RequestBody Semana semana) throws SQLException {
		SemanaDAO.updateSemana(semana);
		return semana;
	}
}
