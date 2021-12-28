package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.AlunoDAO;
import com.AcademiaPop.model.entities.Aluno;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	@GetMapping(path="/qualquer")
	public Aluno obterCliente() {
		return new Aluno("login.pica", "senha_forte", "email@brabo", "40028922", "meuNomeEhJhon", "16656747854");
	}
	
	@GetMapping("/{id}")
	public Aluno obterAluno(@PathVariable int id) throws SQLException {
		Aluno aluno = AlunoDAO.getAluno(id);
		System.out.println("id: "+ id +"; aluno: "+ aluno.nome);
		return aluno;
	}
	
	@PostMapping("/insert")
	public Aluno insertAluno(@RequestBody Aluno aluno) throws SQLException{
		AlunoDAO.insertAluno(aluno);		
		return aluno;
	}
}
