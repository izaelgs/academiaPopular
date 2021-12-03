package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Semana;

public class SemanaDAO {
	public static void insertSemana(Semana s) throws SQLException {
		Connection conexao = Fabrica.getConexao();					
					
		String sql = "INSERT into semana(id_aluno, id_professor) VALUES(?, ?)";
				
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, s.id_aluno);
		stmt.setInt(2, s.id_professor);
		
		stmt.execute();
		
		System.out.println("Semana inserida com sucesso");
		conexao.close();
		
	}
	
	public static Semana getSemana(int id_q) throws SQLException {
		Connection conexao = Fabrica.getConexao();		
		
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
}
