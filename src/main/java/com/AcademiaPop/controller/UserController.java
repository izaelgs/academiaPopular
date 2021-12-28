package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public User insertDia(@RequestBody User user){
		//UserDAO.insertUser(user);		
		return user;
	}
}
