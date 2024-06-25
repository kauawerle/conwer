package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.ScheduleEntity;

public class ScheduleRepository implements BasicCrud {
	EntityManager em = Persistence.createEntityManagerFactory("myPersistenceUnit").createEntityManager();
	
	@Override
	public Object create(Object obj) {
		var schedule = (ScheduleEntity)obj;
		em.getTransaction().begin();
		em.persist(schedule);
		em.getTransaction().commit();
		return findById(schedule.getId());
	}
	
	@Override
	public Object updateById(Object object) {
		ScheduleEntity schedule = (ScheduleEntity) object;
		em.getTransaction().begin();
		em.merge(schedule);
		em.getTransaction().commit();
		return null;
	}
	
	public void delete(Long id) {
		em.getTransaction().begin();
		var pessoa = (ScheduleEntity) findById(id);
		em.remove(pessoa);
		em.getTransaction().commit();
	}
	
	@Override
	public Object findById(Long id) {
		try {
			ScheduleEntity schedule = em.find(ScheduleEntity.class, (Long) id);
	        return schedule;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public Object update(Object obj) {
		ScheduleEntity schedule = (ScheduleEntity) obj;
		em.getTransaction().begin();
		em.merge(schedule);
		em.getTransaction().commit();
		return null;
	}
	
	public List<ScheduleEntity> findAll(){
		return em.createQuery("SELECT s FROM ScheduleEntity s where s.id >1",ScheduleEntity.class).getResultList();
	}
}
