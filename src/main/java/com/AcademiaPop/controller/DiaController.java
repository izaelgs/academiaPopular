package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.DiaDAO;
import com.AcademiaPop.model.entities.Dia;

@RestController
@RequestMapping("/dia")
public class DiaController {
	
	@GetMapping(path="/qualquer")
	public Dia obterCliente() {
		return new Dia(1, 1, 0);
	}
	
	@PostMapping("/insert")
	public Dia insertDia(@RequestBody Dia dia) throws SQLException{
		DiaDAO.insertDia(dia);		
		return dia;
	}
}
