package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;
import java.util.List;

public class UniteLocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UniteLocationDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        this.entityManager = factory.createEntityManager();
    }

    public Unitelocation save(Unitelocation unitelocation) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(unitelocation);
            entityManager.flush();
            transaction.commit();
            return unitelocation;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Unitelocation update(Unitelocation unitelocation) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            unitelocation = entityManager.merge(unitelocation);
            entityManager.flush();
            transaction.commit();
            return unitelocation;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Unitelocation unitelocation = entityManager.find(Unitelocation.class, id);
            if (unitelocation != null) {
                entityManager.remove(unitelocation);
                entityManager.flush();
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Unitelocation find(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Unitelocation unitelocation = entityManager.find(Unitelocation.class, id);
            transaction.commit();
            return unitelocation;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Unitelocation> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Unitelocation> result = entityManager.createQuery("SELECT u FROM Unitelocation u", Unitelocation.class).getResultList();
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
