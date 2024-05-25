package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.AddressEntity;

public abstract class AddressRepository implements BasicCrud{
EntityManager em = Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();
	
	@Override
	public Object create(Object obj) {
		var address = (AddressEntity)obj;
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
		return findById(address.getId());
	}
	
	@Override
	public Object updateById(Object object) {
		AddressEntity address = (AddressEntity) object;
		em.getTransaction().begin();
		em.merge(address);
		em.getTransaction().commit();
		return null;
	}
	
	public void delete(Long id) {
		em.getTransaction().begin();
		var address = (AddressEntity) findById(id);
		em.remove(address);
		em.getTransaction().commit();
	}
	
	@Override
	public Object findById(Long id) {
		try {
			AddressEntity address = em.find(AddressEntity.class, id);
			return address;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public Object update(Object obj) {
		AddressEntity address = (AddressEntity) obj;
		em.getTransaction().begin();
		em.merge(address);
		em.getTransaction().commit();
		return null;
	}
	
	public List<AddressEntity> findAll(){
		return em.createQuery("SELECT p FROM tb_address p where p.id >1",AddressEntity.class).getResultList();
	}
}
