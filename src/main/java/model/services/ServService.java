package model.services;

import model.entities.DrugsEntity;

import model.entities.ServiceEntity;
import model.repositories.DrugsRepository;
import model.repositories.ServiceRepository;

import java.util.List;

public class ServService {
    private ServiceRepository serviceRepository;

    public ServService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceEntity createService(ServiceEntity service) {
        return (ServiceEntity) serviceRepository.create(service);
    }

    public ServiceEntity updateService(ServiceEntity service) {
        return (ServiceEntity) serviceRepository.update(service);
    }

    public void deleteServices(Long id) {
        serviceRepository.delete(id);
    }

    public ServiceEntity getServiceById(Long id) {
        return (ServiceEntity) serviceRepository.findById(id);
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }
}
