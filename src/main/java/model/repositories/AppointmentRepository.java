package model.repositories;

import model.entities.AppointmentEntity;
import model.entities.DrugsEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class AppointmentRepository implements BasicCrud {
    EntityManager em = Persistence.createEntityManagerFactory("BancoConsultorio").createEntityManager();

    @Override
    public Object create(Object obj) {
        var ap = (AppointmentEntity)obj;
        em.getTransaction().begin();
        em.persist(ap);
        em.getTransaction().commit();
        return findById(ap.getId());
    }

    @Override
    public Object updateById(Object object) {
        AppointmentEntity ap = (AppointmentEntity) object;
        em.getTransaction().begin();
        em.merge(ap);
        em.getTransaction().commit();
        return null;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        var ap = (AppointmentEntity) findById(id);
        em.remove(ap);
        em.getTransaction().commit();
    }

    @Override
    public Object findById(Long id) {
        try {
            AppointmentEntity ap = em.find(AppointmentEntity.class, id);
            return ap;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Object update(Object obj) {
        AppointmentEntity ap = (AppointmentEntity) obj;
        em.getTransaction().begin();
        em.merge(ap);
        em.getTransaction().commit();
        return null;
    }

    public List<AppointmentEntity> findAll(){
        return em.createQuery("SELECT p FROM AppointmentEntity p where p.id > 0",AppointmentEntity.class).getResultList();
    }
}
