package view;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import controller.DrugController;
import controller.ScheduleController;
import controller.UserController;
import model.entities.DrugsEntity;
import model.entities.ScheduleEntity;
import model.entities.UserEntity;

public class Drugs {
	private Scanner sc = new Scanner(System.in);
	DrugController drugController = new DrugController();

	public void createDrugs() {
		System.out.println("Digite o nome do remédio");
		String name = sc.nextLine();
		System.out.println("Digite a descrição do remédio");
		String description = sc.nextLine();
		System.out.println("Digite o preço do remédio");
		Double price = sc.nextDouble();

		DrugsEntity newDrug = new DrugsEntity(name, description, price);

		drugController.createDrug(newDrug);
		System.out.println("Remédio criado");
	}

	public void updateDrugs() {
		boolean updated = true;
		while (updated) {
			try {
				System.out.println("Editar remédio");
				System.out.println("Digite o ID do remédio");
				Long id = sc.nextLong();
				sc.nextLine();

				System.out.println("Nome do remédio");
				String nome = sc.nextLine();
				System.out.println("Descrição do remédio");
				String description = sc.nextLine();
				System.out.println("Preço do remédio");
				Double price = sc.nextDouble();

				DrugsEntity currentDrug = drugController.findDrugById(id);
				if (currentDrug == null) {
					System.out.println("Remédio não encontrado.");
					return;
				}

				if (nome.isEmpty())
					nome = currentDrug.getName();
				if (description.isEmpty())
					description = currentDrug.getDescription();

				currentDrug.setName(nome);
				currentDrug.setDescription(description);
				currentDrug.setPrice(price);

				if (currentDrug != null) {
					System.out.println("Atualizado");
					updated = false;
					drugController.updateDrug(currentDrug);
				} else {
					System.out.println("não atualizado");
				}
			} catch (Exception e) {
				System.out.println("Erro:" + e);
			}
		}
	}

	public void deleteDrugs() {
		System.out.println("Remover usuário");
		System.out.println("Digite o ID do usuário");

		Long id = sc.nextLong();
		sc.nextLine();

		DrugsEntity currentDrug = drugController.findDrugById(id);
		if (currentDrug == null) {
			System.out.println("Usuário não encontrado.");
			return;
		}

		drugController.deleteDrug(currentDrug.getId());
		System.out.println("Usuário deletado!");
	}
	
	public static void showAllDrugsInBd() {
		DrugController drugsController = new DrugController();
		for(DrugsEntity x : drugsController.findAll()) {
			System.out.println("ID:"+x.getId()+" - Nome: "+x.getName());
		}
	}
	
	public static void showDrugsById(Long id) {
		DrugController drugsController = new DrugController();
		var drugs = drugsController.findDrugById(id);
		System.out.println(drugs.getName());
		
	}
}
