package view;

import controller.UserController;
import model.entities.UserEntity;

public class ShowData {

	public static void showPeopleById(Long id) {
		UserController pessoasController = new UserController();
		var pessoas = pessoasController.findPessoasById(id);
		System.out.println(pessoas.getName());
		
	}
	
	public static void showAllPeopleInBd() {
		UserController pessoasController = new UserController();
		for(UserEntity x : pessoasController.findAll()) {
			System.out.println("ID:"+x.getId()+" - Nome: "+x.getName());
		}
	}

}
