package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.UserDAO;
import com.AcademiaPop.model.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/{id}")
	public User obterUser(@PathVariable int id) throws SQLException {
		User user = UserDAO.gettUser(id);
		return user;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws SQLException {
		User user_r = UserDAO.authLogin(user);
		return user_r;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public String insertUser(@RequestBody User user) throws SQLException{
		if(UserDAO.insertUser(user)) {		
			return "usuario inserido com sucesso";
		}else {
			return "erro ao inserir corno";
		}
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) throws SQLException{
		UserDAO.updateUser(user);		
		return user;
	}
}
