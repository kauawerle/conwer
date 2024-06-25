package model.services;

import java.util.List;
import model.entities.ScheduleEntity;
import model.entities.UserEntity;
import model.repositories.ScheduleRepository;

public class ScheduleServices {
	private ScheduleRepository scheduleRepository;

    public ScheduleServices(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        System.out.println(scheduleRepository);
    }

    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return (ScheduleEntity) scheduleRepository.create(schedule);
    }

    public ScheduleEntity updateSchedule(ScheduleEntity schedule) {
        return (ScheduleEntity) scheduleRepository.update(schedule);
    }

    public void deleteSchedule(Long id) {
    	scheduleRepository.delete(id);
    }

    public ScheduleEntity getScheduleById(Long id) {
        return (ScheduleEntity) scheduleRepository.findById(id);
    }

    public List<ScheduleEntity> getAllSchedule() {
        return scheduleRepository.findAll();
    }
}
