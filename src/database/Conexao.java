package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	public static void insertUser(Integer status, String nome, String login, String senha,String email, String telefone, String cpf, String url) throws SQLException {
		Connection conexao = Fabrica.getConexao();		
		
		String sql = "INSERT INTO user(status, nome, login, senha, email, telefone, cpf, img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, status);
		stmt.setString(2, nome);
		stmt.setString(3, login);
		stmt.setString(4, senha);
		stmt.setString(5, email);
		stmt.setString(6, telefone);
		stmt.setString(7, cpf);
		stmt.setString(8, url);
		
		stmt.execute();
		
		System.out.println("dado inserido com sucesso");
		conexao.close();
	}
	
	public static void deletetUser(Integer id) throws SQLException {
		Connection conexao = Fabrica.getConexao();
		
		String sql = "DELETE FROM user WHERE id = "+id+"";
		
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		
		System.out.println("dado deletado com sucesso");
		conexao.close();
	}
}
