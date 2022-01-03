package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.AcademiaPop.model.entities.Dia;
import com.AcademiaPop.model.entities.Serie;

public class DiaDAO {
	
	public static void insertDia(Dia d) throws SQLException {
		Connection conexao = Factory.getConexao();					
					
		String sql = "INSERT into dia(id_serie, id_semana, dia) VALUES(?, ?, ?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, d.id_serie);
		stmt.setInt(2, d.id_semana);
		stmt.setInt(3, d.dia);
		
		stmt.execute();
		
		System.out.println("Dia inserido com sucesso");
		conexao.close();
		
	}
	
	public static void updateDia(Dia d) throws SQLException {
		Connection conexao = Factory.getConexao();					
					
		String sql = "UPDATE dia SET id_serie = ?, id_semana = ?, dia = ? WHERE id = ?";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, d.id_serie);
		stmt.setInt(2, d.id_semana);
		stmt.setInt(3, d.dia);
		stmt.setInt(4, d.getId());
		
		stmt.execute();
		
		System.out.println("Dia atualizado com sucesso");
		conexao.close();
		
	}
	
	public static Dia getDia(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT * FROM dia WHERE id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_serie = 0,id_semana = 0, dia = 0;		
		
		if(r != null && r.next()){
            id = r.getInt("id");
            id_serie = r.getInt("id_serie");
            id_semana = r.getInt("id_semana");			
            dia = r.getInt("dia");			
        }			
		
		conexao.close();
		
		Dia dia_o = new Dia(id, id_serie, id_semana, dia);
		
		return dia_o;
	}
	
	public static Dia getDiaSerie(int aluno,int id_q) throws SQLException {
		
			
		Connection conexao = Factory.getConexao();		
		
		String sql1 = "SELECT * FROM dia WHERE id =" + id_q;
		
		Statement stmt1 = conexao.createStatement();	
		
		
		ResultSet rs = stmt1.executeQuery(sql1);		
		
		List<Serie> series = new ArrayList<Serie>();

		
		int id = 0,id_serie = 0,id_semana = 0, dia = 0;	
		
		while(rs != null && rs.next()){
            id = rs.getInt("id");
            id_serie = rs.getInt("id_serie");
            id_semana = rs.getInt("id_semana");			
            dia = rs.getInt("dia");	
            
            Serie serie = SerieDAO.getSerieExercicios(id_serie);
            
            series.add(serie);
            
    		System.out.println(id_serie);

        }			
		
		conexao.close();
		
		Dia dia_o = new Dia(id, id_serie, id_semana, dia, series);
		
		return dia_o;
	}
}
