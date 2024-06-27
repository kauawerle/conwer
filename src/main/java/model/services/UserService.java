package model.services;

import java.util.List;

import model.dto.LoginDto;
import model.entities.UserEntity;
import model.repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println(userRepository);
    }

    public UserEntity createUser(UserEntity user) {
        return (UserEntity) userRepository.create(user);
    }

    public UserEntity updateUser(UserEntity user) {
        return (UserEntity) userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public UserEntity login(LoginDto login) {
        UserEntity user = userRepository.findByEmail(login.getEmail());
        if(user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        if (!user.getPassword().equals(login.getPassword())) {
            throw new RuntimeException("Usuario ou Senha inválida");
        }
        return user;
    }

    public UserEntity getUserById(Long id) {
        return (UserEntity) userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
