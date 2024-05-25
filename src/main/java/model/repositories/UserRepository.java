package model.repositories;

import controller.UserController;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.UserEntity;

public class UserRepository implements BasicCrud {
	
	EntityManager em = Persistence.createEntityManagerFactory("myPersistenceUnit").createEntityManager();
	
	@Override
	public Object create(Object obj) {
		var user = (UserEntity)obj;
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return findById(user.getId());
	}
	
	@Override
	public Object updateById(Object object) {
		System.out.println(object);
		UserEntity userUpdated = (UserEntity) object;
		em.getTransaction().begin();
		em.merge(userUpdated);
		em.getTransaction().commit();
		return null;
	}
	
	public void delete(Long id) {
		em.getTransaction().begin();
		var pessoa = (UserEntity) findById(id);
		em.remove(pessoa);
		em.getTransaction().commit();
	}
	
	@Override
	public Object findById(Long id) {
		try {
			UserEntity pessoaInBd = em.find(UserEntity.class, (Long) id);
	        return pessoaInBd;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public Object update(Object obj) {
		UserEntity userUpdated = (UserEntity) obj;
		em.getTransaction().begin();
		em.merge(userUpdated);
		em.getTransaction().commit();
		return null;
	}
	
	public List<UserEntity> findAll(){
		return em.createQuery("SELECT p FROM UserEntity p where p.id >1",UserEntity.class).getResultList();
	}
}
