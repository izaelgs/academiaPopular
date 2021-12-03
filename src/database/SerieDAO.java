package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Serie;

public class SerieDAO {
	public static void insertSerie(Serie s) throws SQLException {
		Connection conexao = Fabrica.getConexao();					
					
		String sql = "INSERT into serie(progresso, titulo, descricao) VALUES(?, ?, ?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, s.progresso);
		stmt.setString(2, s.titulo);
		stmt.setString(3, s.descricao);
		
		stmt.execute();
		
		System.out.println("Serie inserido com sucesso");
		conexao.close();
		
	}
	
	public static Serie getSerie(int id_q) throws SQLException {
		Connection conexao = Fabrica.getConexao();		
		
		String sql = "SELECT * FROM serie WHERE id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,progresso = 0;
		String titulo = null,descricao = null;		
		
		if(r != null && r.next()){
            id = r.getInt("id");
            progresso = r.getInt("progresso");
            titulo = r.getString("titulo");			
            descricao = r.getString("descricao");			
        }			
		
		conexao.close();
		
		Serie serie = new Serie(id, progresso, titulo, descricao);
		
		return serie;
	}
}
