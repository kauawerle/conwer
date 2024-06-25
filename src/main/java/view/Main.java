package view;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Seja bem vindo");
		System.out.println("Escolha uma opção");
		System.out.println("1 - Modulo usuário");
		System.out.println("2 - Modulo remédio");
		System.out.println("3 - Modulo agenda");
		
		boolean rodar = true;
		while (rodar) {
			System.out.println("Escolha uma opção");
			int escolha = sc.nextInt();
			switch (escolha) {
			case 1: {
				UserSave();
			}
			case 2: {
				DrugSave();
			}
			case 3: {
				ScheduleSave();
			}
			}
		}
	}

	public static void UserSave() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Seja bem vindo");
		System.out.println("Escolha uma opção");
		System.out.println("1 - novo cadastro");
		System.out.println("2 - Ver todos os registros");
		System.out.println("3 - Ver dados de um usuario");
		System.out.println("4 - Atualizar usuario");
		System.out.println("5 - Remover usuario");
		System.out.println("0 - Para sair");

		boolean rodar = true;
		while (rodar) {
			System.out.println("Escolha uma opção");
			int escolha = sc.nextInt();
			switch (escolha) {
			case 1: {
				SaveUser salvarUser = new SaveUser();
				salvarUser.criarUmaPessoaNome();
				break;
			}
			case 2: {
				ShowData.showAllPeopleInBd();
				break;
			}
			case 3: {
				System.out.println("Escolha pelo id");
				ShowData.showPeopleById(sc.nextLong());
				break;
			}
			case 4: {
				SaveUser updateUser = new SaveUser();
				updateUser.updateUser();
				break;
			}
			case 5: {
				SaveUser updateUser = new SaveUser();
				updateUser.deleteUser();
				break;
			}
			case 6: {
				Schedule createSchedule = new Schedule();
				createSchedule.createSchedule();
				break;
			}
			case 0: {
				rodar = false;
			}
			}
		}
	}

	public static void DrugSave() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha uma opção");
		System.out.println("1 - novo remédio");
		System.out.println("2 - Atualizar remédio");
		System.out.println("3 - remover remédio");
		System.out.println("4 - Ver todos os remédios");
		System.out.println("5 - Buscar remédio por id");
		System.out.println("0 - Para voltar");

		boolean rodar = true;
		while (rodar) {
			System.out.println("Escolha uma opção");
			int escolha = sc.nextInt();
			Drugs saveDrugs = new Drugs();
			switch (escolha) {
			case 1: {

				saveDrugs.createDrugs();
				break;
			}
			case 2: {
				saveDrugs.updateDrugs();
				break;
			}
			case 3: {
				saveDrugs.deleteDrugs();
				break;
			}
			case 4: {
				saveDrugs.showAllDrugsInBd();
				break;
			}
			case 5: {
				System.out.println("Escola eplo id");
				saveDrugs.showDrugsById(sc.nextLong());
				break;
			}
			case 0: {
				rodar = false;
			}
			}
		}
	}

	public static void ScheduleSave() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Seja bem vindo");
		System.out.println("Escolha uma opção");
		System.out.println("1 - nova agenda");
		System.out.println("0 - Para sair");

		boolean rodar = true;
		while (rodar) {
			System.out.println("Escolha uma opção");
			int escolha = sc.nextInt();
			switch (escolha) {
			case 1: {
				SaveUser salvarUser = new SaveUser();
				salvarUser.criarUmaPessoaNome();
				break;
			}

			case 0: {
				rodar = false;
			}
			}
		}

//		LocalTime work_hour = LocalTime.of(23, 0, 0);
//		UserRepository userRepository = new UserRepository();
//        UserService userService = new UserService(userRepository);
//       
//        UserEntity newUser = new UserEntity("nome", "email@example.com", "password", "123456789", 20, "111.111.111-02", false, work_hour, "teste", null, null);
//        userService.createUser(newUser);
	}
}
