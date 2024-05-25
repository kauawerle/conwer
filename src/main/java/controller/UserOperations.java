package controller;

import model.entities.UserEntity;
import model.services.UserService;

public class UserOperations {
	
	private UserService criarRegistroPessoas = new UserService(null);
	
	public UserEntity criarConta(UserEntity pessoa) {
		return criarRegistroPessoas.createUser(pessoa);
	}
	
	

}
