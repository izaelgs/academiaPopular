package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AcademiaPop.model.entities.Dia;
import com.AcademiaPop.model.entities.Semana;
import com.AcademiaPop.model.entities.Serie;

public class SemanaDAO {
	
	public static void insertSemana(Semana s) throws SQLException {
		Connection conexao = Factory.getConexao();					
					
		String sql = "INSERT into semana(id_aluno, id_professor) VALUES(?, ?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, s.id_aluno);
		stmt.setInt(2, s.id_professor);
		
		stmt.execute();
		
		System.out.println("Semana inserida com sucesso");
		conexao.close();
		
	}
	
	public static void updateSemana(Semana s) throws SQLException {
		Connection conexao = Factory.getConexao();
		
		String sql = "UPDATE semana SET id_aluno = ?, id_professor = ? WHERE id = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
				
		stmt.setInt(1, s.id_aluno);
		stmt.setInt(2, s.id_professor);
		stmt.setInt(3, s.getId());
		
		stmt.execute();
		
		System.out.println("semana atualizada com sucesso");
		conexao.close();
	}
	
	public static boolean updateProfessorSemana(Map<String,Integer> valores) throws SQLException {
		if(checkSemanaAluno(valores.get("id_a"))) {
			Connection conexao = Factory.getConexao();
			System.out.println(valores.get("id_a"));
			String sql = "UPDATE semana SET id_professor = ? WHERE id_aluno = ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
					
			stmt.setInt(1, valores.get("id_p"));
			stmt.setInt(2, valores.get("id_a"));
		
			return !stmt.execute();
		}else {
			Connection conexao = Factory.getConexao();
			System.out.println(valores.get("id_a"));
			String sql = "INSERT INTO semana(id_professor, id_aluno) VALUES(?,?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
					
			stmt.setInt(1, valores.get("id_p"));
			stmt.setInt(2, valores.get("id_a"));
			
			return !stmt.execute();

		}
		
				
	}
	
	public static Semana getSemana(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT * FROM semana WHERE id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_aluno = 0,id_professor = 0;		
		
		if(r != null && r.next()){
            id = r.getInt("id");
            id_aluno = r.getInt("id_aluno");
            id_professor = r.getInt("id_professor");			
        }			
		
		conexao.close();
		
		Semana semana = new Semana(id, id_aluno, id_professor);
		
		return semana;
	}
	
	public static Semana getSemanaAluno(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT s.id,s.id_professor,s.id_aluno,d.id as idDia, d.id_serie,d.dia\r\n"
				+ "FROM semana s JOIN dia d ON s.id = d.id_semana\r\n"
				+ "WHERE s.id_aluno =" + id_q+ " ORDER BY d.dia";
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_aluno = 0,id_professor = 0,idDia = 0,id_serie = 0,dia= 0;		
        List<Dia> dias = new ArrayList<Dia>();

		while(r != null && r.next()){
            id = r.getInt("id");
            id_aluno = r.getInt("id_aluno");
            id_professor = r.getInt("id_professor");
            
            idDia = r.getInt("idDia");
            id_serie = r.getInt("id_serie");
            dia = r.getInt("dia");
            
    		List<Serie> series = new ArrayList<Serie>();

            Serie serie = SerieDAO.getSerieExercicios(id_serie);
            
            series.add(serie);
            
            Dia dia_o = new Dia(idDia,id_serie,id,dia,series);
    		System.out.println(idDia);
            dias.add(dia_o);
        }			
		
		conexao.close();
								
		Semana semana = new Semana(id, id_aluno, id_professor,dias);

		System.out.println(semana.getId());

		return semana;
	}
	
	public static boolean checkSemanaAluno(int id_q) throws SQLException {
		
		boolean auth = false;

		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT id FROM semana WHERE id_aluno =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_aluno = 0,id_professor = 0,idDia = 0,id_serie = 0,dia= 0;		
        List<Dia> dias = new ArrayList<Dia>();

		while(r != null && r.next()){
            id = r.getInt("id");
            
    		List<Serie> series = new ArrayList<Serie>();

            Serie serie = SerieDAO.getSerieExercicios(id_serie);
            
            series.add(serie);
            
            Dia dia_o = new Dia(idDia,id_serie,id,dia,series);
    		System.out.println(idDia);
            dias.add(dia_o);
        }			
		
		conexao.close();
								
		Semana semana = new Semana(id, id_aluno, id_professor,dias);
		if(semana.getId() > 0) {
			auth = true;
		}

		return auth;
	}
	
	public static List<Semana> getSemanaAlunos(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT s.id,s.id_professor,s.id_aluno,d.id as idDia, d.id_serie,d.dia\r\n"
				+ "FROM semana s LEFT JOIN dia d ON s.id = d.id_semana\r\n"
				+ "WHERE s.id_professor =" + id_q+ " ORDER BY d.dia";
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_aluno = 0,id_professor = 0,idDia = 0,id_serie = 0,dia= 0;		
        List<Semana> semanas = new ArrayList<Semana>();
        List<Dia> dias_c = new ArrayList<Dia>();

		while(r != null && r.next()){
			boolean auth = r.getInt("id") != id? true: false;
						
            id = r.getInt("id");
            id_aluno = r.getInt("id_aluno");
            id_professor = r.getInt("id_professor");
            
            idDia = r.getInt("idDia");
            id_serie = r.getInt("id_serie");
            dia = r.getInt("dia");
            
    		List<Serie> series = new ArrayList<Serie>();
            Serie serie = SerieDAO.getSerieExercicios(id_serie);

            series.add(serie);
            
            Dia dia_o = new Dia(idDia,id_serie,id,dia,series);
			if(auth) {
				//if(dia_o.id_semana == id) {
		        	List<Dia> dias = new ArrayList<Dia>();

		            dias.add(dia_o);
		            dias_c.add(dia_o);

					Semana semana = new Semana(id, id_aluno, id_professor,dias);
					semanas.add(semana);
		    		System.out.println("serie: "+id_serie);
	
		    		System.out.println("dia: "+dia_o.getId());
	    		//}else {}
				
			}else {
		        List<Dia> dias = new ArrayList<Dia>();
				dias.add(dias_c.get(dias_c.size()-1));

	            dias.add(dia_o);
				Semana semana = new Semana(id, id_aluno, id_professor,dias);
				semanas.remove(semanas.size()-1);
				semanas.add(semana);
	    		System.out.println("dias: "+semana.dias.size());

				//System.out.println("semana: "+semana.getId());
			}
        }			
		
		conexao.close();
				
		return semanas;
	}
}