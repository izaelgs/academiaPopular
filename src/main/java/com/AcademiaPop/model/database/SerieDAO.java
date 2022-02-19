package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.AcademiaPop.model.entities.Dia;
import com.AcademiaPop.model.entities.Exercicio;
import com.AcademiaPop.model.entities.Serie;

public class SerieDAO {
	public static Boolean insertSerie(Serie s) throws SQLException {
		try {
			Connection conexao = Factory.getConexao();					
					
			String sql = "INSERT into serie(titulo, descricao) VALUES(?, ?)";
					
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, s.titulo);
			stmt.setString(2, s.descricao);
			
			stmt.execute();
			
			System.out.println("Serie inserido com sucesso");
			conexao.close();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	public static int insertDiaSerie(Dia d) throws SQLException {
		Serie s = new Serie("Nova Serie","descricao");
		
		if(insertSerie(s)) {
			s.setId(getUltimaSerie());
			d.id_serie = s.getId();
			if(DiaDAO.insertDiaSerie(d)) {
				return s.getId();
			}else {
				return -2;
			}
		}else {
			return -1;
		}
		
		
	}
	
	public static boolean insertExercicio(Exercicio e) throws SQLException {
		
		boolean auth = false;
		
		try {
			Connection conexao = Factory.getConexao();
			
			String sql = "INSERT into exercicio(titulo, descricao) VALUES(?, ?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.titulo);
			stmt.setString(2, e.descricao);
			
			stmt.execute();
			
			System.out.println("Exercicio inserido com sucesso");
			 
			conexao.close();
			
			auth = true;
			
			return auth;
		} catch (Exception e2) {
			return auth;
		}
		
		
	}
	
	public static boolean insertSerieExercicio(Exercicio e, int id_serie) throws SQLException {
		
		boolean auth = false;
		
		try {
			Connection conexao = Factory.getConexao();
			
			String sql = "INSERT into exercicio(titulo, descricao) VALUES(?, ?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, e.titulo);
			stmt.setString(2, e.descricao);
			stmt.execute();
			conexao.close();
			System.out.println("Exercicio inserido com sucesso");
			

			Connection conexao2 = Factory.getConexao();
			String insertControle = "INSERT into controle_serie(id_serie, id_ex) VALUES(?, (SELECT max(id) FROM exercicio))";
			
			PreparedStatement controle = conexao2.prepareStatement(insertControle);
			controle.setInt(1, id_serie);			
			controle.execute();
			
			System.out.println("controle inserido com sucesso2");
			 
			conexao2.close();
			
			auth = true;
			
			return auth;
		} catch (Exception e2) {
			return auth;
		}
		
		
	}
	
	public static void insertSerieExercicio(Serie s) throws SQLException {
		Connection conexao = Factory.getConexao();
		
		List<Exercicio> exercicios = s.exercicios;
		
		for (Exercicio exercicio : exercicios) {
			
			insertExercicio(exercicio);
			String sql = "INSERT into controle_serie(id_serie, id_ex) VALUES(?, (SELECT MAX(id) FROM exercicio))";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, s.getId());
			
			stmt.execute();
			
			System.out.println("Exercicio inserido no controle_serie com sucesso");
		} 
					
		
		conexao.close();
		
	}
	
	public static void updateSerie(Serie s) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "UPDATE serie SET titulo = ?, descricao = ? WHERE id = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);		
				
		stmt.setString(1, s.titulo);
		stmt.setString(2, s.descricao);
		stmt.setInt(4, s.getId());
		
		stmt.execute();
		
		System.out.println("serie atualizada com sucesso");
		conexao.close();
	}
	
	public static Serie getSerie(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT * FROM serie WHERE id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0;
		String titulo = null,descricao = null;		
		
		if(r != null && r.next()){
            id = r.getInt("id");
            titulo = r.getString("titulo");			
            descricao = r.getString("descricao");			
        }			
		
		conexao.close();
		
		Serie serie = new Serie(id, titulo, descricao);
		
		return serie;
	}
	
	public static int getUltimaSerie() throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT MAX(id) as id FROM serie";
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0;		
		
		if(r != null && r.next()){
            id = r.getInt("id");		
        }			
		
		conexao.close();
		
		return id;
	}
	
	public static Serie getSerieExercicios(int id_q) throws SQLException {
		
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT s.id,s.titulo,s.descricao,e.id as id_e,e.titulo as titulo_e, e.descricao as desc_e\r\n"
				+ "FROM serie s LEFT JOIN controle_serie cs ON s.id = cs.id_serie \r\n"
				+ "LEFT JOIN exercicio e ON cs.id_ex = e.id WHERE s.id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_e = 0;
		String titulo = null,descricao = null, titulo_e = null, desc_e = null;		
		
		//if (r.next()) {
		while(r != null && r.next()){ 
	        id_e = r.getInt("id_e");
	        titulo_e = r.getString("titulo_e");	   		
	        desc_e = r.getString("desc_e");
	                
	        Exercicio ex = new Exercicio(id_e,titulo_e,desc_e);
	        
	        exercicios.add(ex);
				//}		    
		
			id = r.getInt("id");
	        titulo = r.getString("titulo");			
	        descricao = r.getString("descricao");
	        		
		}
		
		conexao.close();		

		Serie serie = new Serie(id, titulo, descricao, exercicios);
		return serie;
		/* else {
			Serie serie = new Serie(0, null, null, null);
			return serie;
		}*/
		
	}
	
	public static List<Serie> getSerieRecentes() throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT id,titulo, descricao FROM serie ORDER BY id LIMIT 2";
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0;
		String titulo = null,descricao = null;	
		
		List<Serie> series = new ArrayList<Serie>();	
		
		
		while(r != null && r.next()){			
            id = r.getInt("id");
            titulo = r.getString("titulo");			
            descricao = r.getString("descricao");
			Serie s = new Serie(id, titulo, descricao);
			series.add(s);
        }			
		
		conexao.close();	
		
		return series;
	}
}