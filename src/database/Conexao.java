package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	public static void teste() throws SQLException {
		Connection conexao = Fabrica.getConexao();
		
		String sql = "INSERT INTO user(status, nome, login, senha, email, telefone, cpf, img)\r\n"
				+ "VALUES(1, \"fulano 1\", \"fulano.teste\", \"senha\", \"email.com\", \"2798876654\", \"16654326785\", \"https:://www.google.com\")";
		
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		
		System.out.println("dado inserido com sucesso");
		conexao.close();
	}	
}
