package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.UserEntity;

public class UserRepository implements BasicCrud {
	
	EntityManager em = Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();
	 
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
	public Object findById(Long i) {
		try {
			UserEntity pessoaInBd = em.find(UserEntity.class, (Long) i);
	        return pessoaInBd;
		} catch (Exception e) {
			
		}
		return null;
	}

	public UserEntity findByEmail(String email) {
		try {
			TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
			query.setParameter("email", email);
			List<UserEntity> results = query.getResultList();

			if (results.size() == 1) {
				return results.get(0);
			} else if (results.size() > 1) {
				throw new NonUniqueResultException("Multiple users found with the same email");
			} else {
				return null; // Nenhum resultado encontrado
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
