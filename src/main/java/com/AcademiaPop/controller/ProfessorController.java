package com.AcademiaPop.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.ProfessorDAO;
import com.AcademiaPop.model.entities.Professor;

@RestController
@RequestMapping("/professor")
public class ProfessorController {	
	
	@GetMapping(path="/qualquer")
	public Professor obterProfessor() {
		return new Professor("login.professorDAO", "senha_profedao", "email@professorbrabo", "27654231453", "pRofesorAnofabeto", "7556453419");
	}
	
	@GetMapping(path="/recomendados")
	public List<Professor> obterRecomendados() throws SQLException {
		List<Professor> professores = ProfessorDAO.getProfessorList();
		List<Professor> retorno = new ArrayList<Professor>();
		for(Professor p : professores){
            System.out.println("id: "+p.id);
            Professor p_retorno = new Professor(p.id, p.getId_user(), 0, null, null, null, null, p.nome,null, p.img);
            retorno.add(p_retorno);
        }
		//System.out.println("o id desse professor é: "+professores.get(1).id);
		return retorno;
	}
	
	@GetMapping("/aluno/{id}")
	public Professor obterProfessorAluno(@PathVariable int id) throws SQLException {
		Professor professor = ProfessorDAO.getProfessorAluno(id);
		System.out.println("id: "+ id +"; professor: "+ professor.nome);
		return professor;
	}
	
	@PostMapping("/insert")
	public String insertProfessor(@RequestBody Professor professor) throws SQLException{
		ProfessorDAO.insertProfessor(professor);		
		return "Professor inserido com sucesso";
	}
	
	@PutMapping("/update")
	public Professor updatetProfessor(@RequestBody Professor professor) throws SQLException{
		ProfessorDAO.insertProfessor(professor);		
		return professor;
	}
	
}
