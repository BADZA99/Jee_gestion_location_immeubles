package sn.dev.jee_locations_immeubles.dao;

import sn.dev.jee_locations_immeubles.Entities.Contratlocation;

import javax.persistence.*;
import java.util.List;

public class ContratLocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ContratLocationDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        this.entityManager = factory.createEntityManager();
    }
    public Contratlocation findById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Contratlocation contratlocation = null;
        try {
            transaction.begin();
            contratlocation = entityManager.find(Contratlocation.class, id);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return contratlocation;
    }

    public List<Contratlocation> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        List<Contratlocation> contratlocations = null;
        try {
            transaction.begin();
            contratlocations = entityManager.createQuery("SELECT c FROM Contratlocation c", Contratlocation.class).getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return contratlocations;
    }

    public Contratlocation save(Contratlocation contratlocation) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (contratlocation != null && contratlocation.getId() == 0) {
                entityManager.persist(contratlocation);
            } else if (contratlocation != null) {
                contratlocation = entityManager.merge(contratlocation);
            }
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return contratlocation;
    }

    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Contratlocation contratlocation = findById(id);
            if (contratlocation != null) {
                entityManager.remove(contratlocation);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
