package controller;

import java.util.List;

import model.entities.ScheduleEntity;
import model.repositories.ScheduleRepository;
import model.services.ScheduleServices;

public class ScheduleController {
	private ScheduleRepository scheduleRepository = new ScheduleRepository();
	private ScheduleServices scheduleServices = new ScheduleServices(scheduleRepository);
	
	public ScheduleEntity findScheduleById(Long id) {
		return (ScheduleEntity) scheduleServices.getScheduleById(id);
	}
	
	public List<ScheduleEntity> findAll(){
		return scheduleServices.getAllSchedule();
	}
	
	public boolean createSchedule(ScheduleEntity schedule) {
		try {
			scheduleServices.createSchedule(schedule);
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao criar agenda " + e);
			return false;
		}
	}

	public ScheduleEntity updateSchedule(ScheduleEntity schedule) {
		return scheduleServices.updateSchedule(schedule);
	}
	
	public void deleteSchedule(Long id) {
		scheduleServices.deleteSchedule(id);
    }
}
