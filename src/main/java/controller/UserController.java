package controller;

import java.util.List;

import model.dto.LoginDto;
import model.entities.UserEntity;
import model.repositories.UserRepository;
import model.services.UserService;

public class UserController {
	
	private UserRepository pessoasRepository = new UserRepository();
	private UserService pessoasService = new UserService(pessoasRepository);
	
	public UserEntity findPessoasById(Long id) {
		return (UserEntity) pessoasRepository.findById(id);
	}
	
	public List<UserEntity> findAll(){
		return pessoasRepository.findAll();
	}
	
	public boolean createUser(UserEntity user) {
		try {
			pessoasService.createUser(user);
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao criar usuário" + e);
			return false;
		}
	}

	public UserEntity updateUser(UserEntity user) {
		return pessoasService.updateUser(user);
	}
	
	public void deleteUser(Long id) {
		pessoasService.deleteUser(id);
    }

	public UserEntity login(LoginDto login) {
		try {
			return pessoasService.login(login);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
