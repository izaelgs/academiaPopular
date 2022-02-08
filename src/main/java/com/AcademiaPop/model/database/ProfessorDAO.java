package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.AcademiaPop.model.entities.Professor;

public class ProfessorDAO {

	public static void insertProfessor(Professor p) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		UserDAO.insertUser(p);		
					
		String sql = "INSERT into professor(id_user) SELECT id FROM user WHERE login = '"+p.login+"'";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);				
		
		stmt.execute();
		
		System.out.println("professor inserido com sucesso");
		conexao.close();
		
	}
	
	public static void updateProfessor(Professor p) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		UserDAO.insertUser(p);		
					
		String sql = "UPDATE user SET nome = 'nomeUpdate'  WHERE id = 1";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);				
		
		stmt.execute();
		
		System.out.println("professor inserido com sucesso");
		conexao.close();
		
	}
	
	public static Professor getProfessor(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT a.id, a.id_user, u.nome, u.login, u.senha, u.email, u.telefone, u.cpf, u.img, u.status FROM user u INNER JOIN professor a ON u.id = a.id_user WHERE u.id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_user = 0,status = 0;
		String login = null, senha = null, email = null, telefone = null, nome = null, cpf = null, url = null;
		
		if(r != null && r.next()){
            id = r.getInt("id");
            id_user = r.getInt("id_user");
			status = r.getInt("status");
			login = r.getString("login");
			senha = r.getString("senha");
			email = r.getString("email");
			telefone = r.getString("telefone");
			nome = r.getString("nome");
			cpf = r.getString("cpf");
			url = r.getString("img");
        }			
		
		conexao.close();
		
		Professor professor = new Professor(id, id_user, status, login, senha, email, telefone, nome, cpf, url);
		
		return professor;
	}
	
	public static Professor getProfessorAluno(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT p.id, u.nome, u.img, u.id as id_user\r\n"
				+ "FROM user u INNER \r\n"
				+ "JOIN professor p ON u.id = p.id_user \r\n"
				+ "JOIN semana s ON p.id = s.id_professor\r\n"
				+ "JOIN aluno a ON s.id_aluno = a.id\r\n"
				+ "WHERE a.id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_user = 0;
		String nome = null, url = null;
		
		if(r != null && r.next()){
            id = r.getInt("id");
            id_user = r.getInt("id_user");
			nome = r.getString("nome");
			url = r.getString("img");
        }			
		
		conexao.close();
		
		Professor professor = new Professor(id, id_user,nome, url);
		
		return professor;
	}
	
	public static List<Professor> getProfessorList() throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT p.id, u.nome, u.img, u.id as id_user\r\n"
				+ "FROM user u INNER \r\n"
				+ "JOIN professor p ON u.id = p.id_user \r\n"
				+ "WHERE u.status = 1;";
		
		Statement stmt1 = conexao.createStatement();	
		
		
		ResultSet r = stmt1.executeQuery(sql);		
		
		List<Professor> professores = new ArrayList<Professor>();

		
		int id = 0,id_user = 0;
		String nome = null, url = null;
		
		while(r != null && r.next()){
			id = r.getInt("id");
			id_user = r.getInt("id_user");
			nome = r.getString("nome");
			url = r.getString("img");
            
			Professor p = new Professor(id, id_user,nome, url);
            
            professores.add(p);
            
    		//System.out.println("o id desse professor é: "+id+", ou: "+p.id);

        }			
		
		conexao.close();
		System.out.println("o id desse professor é: "+professores.get(0).id);

		return professores;
	}
}