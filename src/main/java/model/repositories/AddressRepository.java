package model.repositories;

import model.entities.AddressEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressRepository implements BasicCrud{
    private final EntityManager em =
            Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();

    @Override
    public Object create(Object obj) {
        AddressEntity a = (AddressEntity) obj;
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        return findById(a.getId());
    }

    @Override
    public Object updateById(Object object) {
        AddressEntity updated = (AddressEntity) object;
        em.getTransaction().begin();
        AddressEntity merged = em.merge(updated);
        em.getTransaction().commit();
        return merged;
    }

    @Override
    public Object update(Object obj) {
        AddressEntity updated = (AddressEntity) obj;
        em.getTransaction().begin();
        AddressEntity merged = em.merge(updated);
        em.getTransaction().commit();
        return merged;
    }

    @Override
    public Object findById(Long id) {
        return em.find(AddressEntity.class, id);
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        AddressEntity ref = em.find(AddressEntity.class, id);
        if (ref != null) em.remove(ref);
        em.getTransaction().commit();
    }

    public List<AddressEntity> findAll() {
        return em.createQuery("SELECT a FROM AddressEntity a", AddressEntity.class).getResultList();
    }

    // Helpers
    public List<AddressEntity> findByCep(String cep) {
        TypedQuery<AddressEntity> q = em.createQuery(
                "SELECT a FROM AddressEntity a WHERE a.cep = :c", AddressEntity.class);
        q.setParameter("c", cep);
        return q.getResultList();
    }
}
