package model.repositories;

import model.entities.DrugsEntity;
import model.entities.ServiceEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ServiceRepository implements BasicCrud {
    EntityManager em = Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();

    @Override
    public Object create(Object obj) {
        var service = (ServiceEntity)obj;
        em.getTransaction().begin();
        em.persist(service);
        em.getTransaction().commit();
        return findById(service.getId());
    }

    @Override
    public Object updateById(Object object) {
        ServiceEntity service = (ServiceEntity) object;
        em.getTransaction().begin();
        em.merge(service);
        em.getTransaction().commit();
        return null;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        var service = (ServiceEntity) findById(id);
        em.remove(service);
        em.getTransaction().commit();
    }

    @Override
    public Object findById(Long id) {
        try {
            ServiceEntity service = em.find(ServiceEntity.class, id);
            return service;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Object update(Object obj) {
        ServiceEntity service = (ServiceEntity) obj;
        em.getTransaction().begin();
        em.merge(service);
        em.getTransaction().commit();
        return null;
    }

    public List<ServiceEntity> findAll(){
        return em.createQuery("SELECT p FROM ServiceEntity p where p.id > 0",ServiceEntity.class).getResultList();
    }
}
