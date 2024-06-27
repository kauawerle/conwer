package controller;

import model.entities.DrugsEntity;
import model.entities.ServiceEntity;
import model.repositories.DrugsRepository;
import model.repositories.ServiceRepository;
import model.services.DrugsService;
import model.services.ServService;

import java.util.List;

public class ServiceController {
    private ServiceRepository serviceRepository = new ServiceRepository();
    private ServService servService = new ServService(serviceRepository);

    public ServiceEntity findDrugById(Long id) {
        return (ServiceEntity) servService.getServiceById(id);
    }

    public List<ServiceEntity> findAll(){
        return servService.getAllServices();
    }

    public boolean createService(ServiceEntity service) {
        try {
            servService.createService(service);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar serviço " + e);
            return false;
        }
    }

    public ServiceEntity updateDrug(ServiceEntity service) {
        return servService.updateService(service);
    }

    public void deleteService(Long id) {
        servService.deleteServices(id);
    }
}
