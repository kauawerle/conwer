package view;

import java.util.List;
import java.util.Scanner;

import controller.ScheduleController;
import controller.UserController;
import model.entities.ScheduleEntity;

public class Schedule {
	private Scanner sc = new Scanner(System.in);
	UserController userController = new UserController();
	ScheduleController scheduleController = new ScheduleController();

	public void createSchedule() {
		System.out.println("Digite a data da consulta");
		String date = sc.nextLine();
		System.out.println("Digite a hora da consulta");
		String hour = sc.nextLine();

		ShowData.showAllPeopleInBd();
		System.out.println("Digite o codigo do paciente");
		Long id_client = sc.nextLong();
		var client = userController.findPessoasById(id_client);
		
		System.out.println("Digite o codigo do profissional");
		var id_profissional = sc.nextLong();
		var profissional = userController.findPessoasById(id_profissional);
		
		
		ScheduleEntity newSchedule = 
				new ScheduleEntity(date, hour, client, profissional);
		
		scheduleController.createSchedule(newSchedule);
	}
}
