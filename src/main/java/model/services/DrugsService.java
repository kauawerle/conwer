package model.services;

import java.util.List;

import model.entities.DrugsEntity;
import model.repositories.DrugsRepository;

public class DrugsService {
	private DrugsRepository drugRepository;

    public DrugsService(DrugsRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public DrugsEntity createDrug(DrugsEntity drug) {
        return (DrugsEntity) drugRepository.create(drug);
    }

    public DrugsEntity updateDrugs(DrugsEntity drug) {
        return (DrugsEntity) drugRepository.update(drug);
    }

    public void deleteDrugs(Long id) {
    	drugRepository.delete(id);
    }

    public DrugsEntity getScheduleById(Long id) {
        return (DrugsEntity) drugRepository.findById(id);
    }

    public List<DrugsEntity> getAllDrugs() {
        return drugRepository.findAll();
    }
}
