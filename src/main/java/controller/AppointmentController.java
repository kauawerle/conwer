package controller;

import model.entities.AppointmentEntity;
import model.repositories.AppointmentRepository;
import model.services.AppointmentService;

import java.util.List;

public class AppointmentController {
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private AppointmentService appointmentService = new AppointmentService(appointmentRepository);

    public AppointmentEntity findDrugById(Long id) {
        return (AppointmentEntity) appointmentService.getAppointmentById(id);
    }

    public List<AppointmentEntity> findAll(){
        return appointmentService.getAllAppointment();
    }

    public boolean createDrug(AppointmentEntity app) {
        try {
            appointmentService.createAppointment(app);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar agenda " + e);
            return false;
        }
    }

    public AppointmentEntity updateDrug(AppointmentEntity app) {
        return appointmentService.updateAppointment(app);
    }

    public void deleteDrug(Long id) {
        appointmentService.deleteAppointment(id);
    }
}
