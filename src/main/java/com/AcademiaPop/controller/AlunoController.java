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
	@GetMapping("/get/{id}")
	public Aluno obterAlunoP(@PathVariable int id) throws SQLException {
		Aluno aluno = AlunoDAO.getAluno2(id);
		System.out.println("id: "+ id +"; aluno: "+ aluno.nome);
		Aluno ra = new Aluno(aluno.getId(), aluno.status,aluno.login, aluno.email, aluno.telefone, aluno.nome, aluno.img);
		return ra;
	}
	
	@GetMapping("/{id}")
	public Aluno obterAluno(@PathVariable int id) throws SQLException {
		Aluno aluno = AlunoDAO.getAluno(id);
		System.out.println("id: "+ id +"; aluno: "+ aluno.nome);
		return aluno;
	}
	
	@PostMapping("/insert")
	public String insertAluno(@RequestBody Aluno aluno) throws SQLException{
		
		if(AlunoDAO.insertAluno(aluno)) {	
			return "Aluno inserido com sucesso";
		}else {
			return "erro ao inserir corno";
		}
	}
}
