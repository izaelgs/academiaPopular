package view;

import java.sql.SQLException;

import database.Conexao;

public class Main {
	public static void main(String[] args) throws SQLException {
		Conexao.insertUser(1, "inserção segura", "insert", "pinto", "disgramera.sexo", "2797783164", "15564576915", "https://xvideos.com/imagem.png");
		
		Conexao.deletetUser(3);
	}
}
