package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.DiaDAO;
import com.AcademiaPop.model.entities.Dia;

@RestController
@RequestMapping("/dia")
public class DiaController {
	
	@GetMapping(path="/{id}")
	public Dia getDia(@PathVariable int id) throws SQLException {
		Dia dia = DiaDAO.getDia(id);
		return dia;
	}
	
	@GetMapping(path="/aluno/{a}/{d}")
	public Dia getDiaSerie(@PathVariable int a,@PathVariable int d) throws SQLException {
		Dia dia = DiaDAO.getDiaSerie(a,d);
		return dia;
	}
	
	@PostMapping("/insert")
	public String insertDia(@RequestBody Dia dia) throws SQLException{
		DiaDAO.insertDia(dia);		
		return "dia inserido com sucesso";
	}
	
	@PostMapping("/insert/serie")
	public int insertDiaSerie(@RequestBody Dia dia) throws SQLException{
		int id_dia = DiaDAO.insertDiaSerieNova(dia);		
		return id_dia;
	}
	
	@PutMapping("/update")
	public String updateData(@RequestBody Dia dia) throws SQLException {
		DiaDAO.updateDia(dia);
		return "dia atualizado com sucesso";
	}
}
