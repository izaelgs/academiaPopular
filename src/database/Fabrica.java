package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Fabrica {
	public static Connection getConexao() {
		try {
			final String url = "jdbc:mysql://localhost/ezersisios?verifyServerCertificate=false&useSSL=true";
			final String usuario = "root";
			final String senha = "senha";
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
