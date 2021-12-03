package view;

import java.sql.SQLException;

import database.DiaDAO;
import database.SemanaDAO;
import database.SerieDAO;
import modelos.Dia;
import modelos.Semana;
import modelos.Serie;

/*import database.AdministradorDAO;
import database.AlunoDAO;
import database.ProfessorDAO;
import modelos.Administrador;
import modelos.Aluno;
import modelos.Professor;*/

public class Main {
	public static void main(String[] args) throws SQLException {
		
										/****************************STAKEHOLDERS****************************/
				
										/*ALUNO*********************/
		
		/*Aluno aluno = new Aluno("loginPica", "senhaPinto", "sexo@busetinha.cum", "2797783164", "usuarioFoda", "15564576915");		
		AlunoDAO.insertAluno(aluno);		
		
		Aluno novo = AlunoDAO.getAluno(31);		
		System.out.println("nome: " + novo.nome + ", email: " + novo.email + ", id usuário: " + novo.getId_user());*/
		
		
										/*PROFESSOR*********************/
		
		/*Professor professor = new Professor("loginProfessor", "ProfessorBombado", "profbao@propinagratis.coin", "2797783164", "ProfessorGirafal", "15231213215");		
		ProfessorDAO.insertProfessor(professor)		
		
		Professor novo = ProfessorDAO.getProfessor(32);		
		System.out.println("nome: " + novo.nome + ", email: " + novo.email + ", id usuário: " + novo.getId_user());*/
		
		
										/*ADMINISTRADOR*********************/
		
		/*Administrador administrador = new Administrador("loginAdministrador", "AdministradoraGostosa", "administrasaoEhVida@penisLongo.cown", "2797321464", "administradorDaBoca", "1236513215");		
		AdministradorDAO.insertAdministrador(administrador);		
		
		Administrador novo = AdministradorDAO.getAdministrador(34);		
		System.out.println("nome: " + novo.nome + ", email: " + novo.email + ", id usuário: " + novo.getId_user());*/
		
		
										/*SEMANA*********************/
		
		/*Semana semana = new Semana(11, 2);		
		SemanaDAO.insertSemana(semana);		
		
		Semana novo = SemanaDAO.getSemana(1);		
		System.out.println("aluno: " + novo.id_aluno + ", professor: " + novo.id_professor);*/
		
		
										/*Dia*********************/
		
		/*Dia dia = new Dia(1, 1, 0);		
		DiaDAO.insertDia(dia);		
		
		Dia novo = DiaDAO.getDia(2);		
		System.out.println("semana: " + novo.id_semana + ", serie: " + novo.id_serie + ", dia: "+ novo.dia);*/
		
										/*Serie*********************/
		
		/*Serie dia = new Serie(0, "agachamento de pica", "Agachar a pica suavemente sobre a xerequina da ninfeta");		
		SerieDAO.insertSerie(dia);	*/	
		
		Serie novo = SerieDAO.getSerie(1);		
		System.out.println("Título: " + novo.titulo + ", Descrição: " + novo.descricao + ", Progresso: "+ novo.progresso);
	}
}
