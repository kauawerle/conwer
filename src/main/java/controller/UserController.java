package controller;

import java.util.List;

import model.entities.UserEntity;
import model.repositories.UserRepository;
import model.services.UserService;

public class UserController {
	
	private UserRepository pessoasRepository = new UserRepository();
	private UserService pessoasService = new UserService(null);
	
	public UserEntity findPessoasById(Long id) {
		return (UserEntity) pessoasRepository.findById(id);
	}
	
	public List<UserEntity> findAll(){
		return pessoasRepository.findAll();
	}

	public UserEntity updateUser(UserEntity user) {
		try {
			return pessoasService.updateUser(user);
		} catch(Exception e) {
			System.out.println("Erro ao atualizar usuário" + e);
			return null;
		}
		}
	
}
