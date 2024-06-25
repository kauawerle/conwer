package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.entities.DrugsEntity;

public class DrugsRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();
		
		@Override
		public Object create(Object obj) {
			var address = (DrugsEntity)obj;
			em.getTransaction().begin();
			em.persist(address);
			em.getTransaction().commit();
			return findById(address.getId());
		}
		
		@Override
		public Object updateById(Object object) {
			DrugsEntity address = (DrugsEntity) object;
			em.getTransaction().begin();
			em.merge(address);
			em.getTransaction().commit();
			return null;
		}
		
		public void delete(Long id) {
			em.getTransaction().begin();
			var address = (DrugsEntity) findById(id);
			em.remove(address);
			em.getTransaction().commit();
		}
		
		@Override
		public Object findById(Long id) {
			try {
				DrugsEntity address = em.find(DrugsEntity.class, id);
				return address;
			} catch (Exception e) {
				
			}
			return null;
		}

		@Override
		public Object update(Object obj) {
			DrugsEntity address = (DrugsEntity) obj;
			em.getTransaction().begin();
			em.merge(address);
			em.getTransaction().commit();
			return null;
		}
		
		public List<DrugsEntity> findAll(){
			return em.createQuery("SELECT p FROM DrugsEntity p where p.id > 0",DrugsEntity.class).getResultList();
		}
	

}
