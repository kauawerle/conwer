package model.services;

import java.util.List;

import model.entities.UserEntity;
import model.repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public UserEntity getUserById(Long id) {
        return (UserEntity) userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
