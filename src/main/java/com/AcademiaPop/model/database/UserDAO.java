package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.AcademiaPop.model.entities.User;


public class UserDAO {	
	
	public static void insertUser(User u) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "INSERT INTO user(status, nome, login, senha, email, telefone, cpf) VALUES (1, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);		
		stmt.setString(1, u.nome);
		stmt.setString(2, u.login);
		stmt.setString(3, u.senha);
		stmt.setString(4, u.email);
		stmt.setString(5, u.telefone);
		stmt.setString(6, u.cpf);		
		
		stmt.execute();
		
		System.out.println("usuario inserido com sucesso");
		conexao.close();
	}
	
	public static void updateUser(User u) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "UPDATE user SET nome = ?,login = ?,senha = ?,email = ?,telefone = ?,cpf = ?,img = ?,status = ?  WHERE id = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);		
		stmt.setString(1, u.nome);
		stmt.setString(2, u.login);
		stmt.setString(3, u.senha);
		stmt.setString(4, u.email);
		stmt.setString(5, u.telefone);
		stmt.setString(6, u.cpf);
		stmt.setString(7, u.img);
		stmt.setInt(8, u.status);
		stmt.setInt(9, u.getId());
		
		stmt.execute();
		
		System.out.println("usuario atualizado com sucesso");
		conexao.close();
	}
	
	public static User gettUser(int id_q) throws SQLException {
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT * FROM user WHERE id =" + id_q;
		
		Statement stmt = conexao.createStatement();		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0, status = 0;
		String login = null, senha = null, email = null, telefone = null, nome = null, cpf = null, url = null;
		
		if(r != null && r.next()){
			status = r.getInt("status");
            id = r.getInt("id");			
			login = r.getString("login");
			senha = r.getString("senha");
			email = r.getString("email");
			telefone = r.getString("telefone");
			nome = r.getString("nome");
			cpf = r.getString("cpf");
			url = r.getString("img");
        }		
		
		System.out.println("dado consultado com sucesso");
		conexao.close();
		
		User user = new User(status, id, login, senha, email, telefone, nome, cpf, url);
		
		return user;
	}
}