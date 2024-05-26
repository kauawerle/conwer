package view;

import java.time.LocalTime;
import java.util.Scanner;

import controller.UserController;
import model.entities.UserEntity;
import model.repositories.UserRepository;
import model.services.UserService;


public class SaveUser {
	UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);
    UserController userController = new UserController();   
	
	private Scanner sc = new Scanner(System.in);

	/*
	 * Função para termos um objeto de pessoas
	 */
	public void criarUmaPessoaNome() {
		System.out.println("Nome da nova pessoa");
		String nome = sc.nextLine();
		System.out.println("Email da nova pessoa");
		String email = sc.nextLine();
		System.out.println("Senha");
		String password = sc.nextLine();
		System.out.println("Cellphone");
		String cellphone = sc.nextLine();
		System.out.println("Idade");
		int age = sc.nextInt();
		
		LocalTime work_hour = LocalTime.of(23, 0, 0);
		
		UserEntity newUser = 
				new UserEntity(
						nome, 
						email, 
						password, 
						cellphone, 
						age, 
						"111.111.111-02", 
						false, 
						work_hour, 
						"teste", 
						null, 
						null);
		
		userService.createUser(newUser);
	}
	
	public void updateUser() {
		boolean updated = true;
		while(updated) {
			try {
				System.out.println("Editar usuário");
				System.out.println("Digite o ID do usuário");
				Long id = sc.nextLong();
				sc.nextLine();
				
				System.out.println("Nome da nova pessoa");
				String nome = sc.nextLine();
				System.out.println("Email da nova pessoa");
				String email = sc.nextLine();
				System.out.println("Senha");
				String password = sc.nextLine();
				System.out.println("Cellphone");
				String cellphone = sc.nextLine();
				System.out.println("Idade");
				int age = sc.nextInt();
				LocalTime work_hour = LocalTime.of(23, 0, 0);
				
				UserEntity currentUser =  userController.findPessoasById(id);
				 if (currentUser == null) {
					 System.out.println("Usuário não encontrado.");
		         return;
		         }
				 
				if(nome.isEmpty())
					nome = currentUser.getName();
				if(email.isEmpty())
					email = currentUser.getEmail();
				if(password.isEmpty())
					password = currentUser.getPassword();
				if(cellphone.isEmpty())
					cellphone = currentUser.getCellphone();
				
				
				UserEntity user = new UserEntity(nome, email, password, cellphone, age, "111.111.111-02", false, work_hour, "teste", null, null);
				
				System.out.println(user);
				if(user != null) {
					System.out.println("Atualizado");
					updated = false;
					userService.updateUser(user);
				}
				else
					System.out.println("não atualizado");
			} catch (Exception e) {
				System.out.println("Erro:" + e);
			}
		}
	}
}
