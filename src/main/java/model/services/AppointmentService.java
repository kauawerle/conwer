package model.services;

import model.entities.AppointmentEntity;
import model.repositories.AppointmentRepository;
import model.repositories.AppointmentRepository
;

import java.util.List;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentEntity createAppointment(AppointmentEntity drug) {
        return (AppointmentEntity) appointmentRepository.create(drug);
    }

    public AppointmentEntity updateAppointment(AppointmentEntity drug) {
        return (AppointmentEntity) appointmentRepository.update(drug);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.delete(id);
    }

    public AppointmentEntity getAppointmentById(Long id) {
        return (AppointmentEntity) appointmentRepository.findById(id);
    }

    public List<AppointmentEntity> getAllAppointment() {
        return appointmentRepository.findAll();
    }
}
