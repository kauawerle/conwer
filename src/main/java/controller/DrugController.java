package controller;

import java.util.List;

import model.entities.DrugsEntity;
import model.repositories.DrugsRepository;
import model.services.DrugsService;

public class DrugController {
	private DrugsRepository drugRepository = new DrugsRepository();
	private DrugsService drugService = new DrugsService(drugRepository);
	
	public DrugsEntity findDrugById(Long id) {
		return (DrugsEntity) drugService.getScheduleById(id);
	}
	
	public List<DrugsEntity> findAll(){
		return drugService.getAllDrugs();
	}
	
	public boolean createDrug(DrugsEntity drug) {
		try {
			drugService.createDrug(drug);
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao criar agenda " + e);
			return false;
		}
	}

	public DrugsEntity updateDrug(DrugsEntity drug) {
		return drugService.updateDrugs(drug);
	}
	
	public void deleteDrug(Long id) {
		drugService.deleteDrugs(id);
    }
}
