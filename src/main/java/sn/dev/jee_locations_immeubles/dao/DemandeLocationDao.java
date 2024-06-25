package sn.dev.jee_locations_immeubles.dao;

import sn.dev.jee_locations_immeubles.Entities.Demandelocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DemandeLocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public DemandeLocationDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        this.entityManager = factory.createEntityManager();
    }

    public Demandelocation save(Demandelocation demande) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(demande);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return demande;
    }

    public Demandelocation update(Demandelocation demande) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            demande = entityManager.merge(demande);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return demande;
    }

    public boolean delete(int id) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            Demandelocation demande = entityManager.find(Demandelocation.class, id);
            if (demande != null) {
                entityManager.remove(demande);
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

    public Demandelocation find(int id) {
        return entityManager.find(Demandelocation.class, id);
    }

    public List<Demandelocation> findAll() {
        return entityManager.createQuery("SELECT d FROM Demandelocation d", Demandelocation.class).getResultList();
    }

    public List<Demandelocation> findByLocataireId(int locataireId) {
        return entityManager.createQuery("SELECT d FROM Demandelocation d WHERE d.locataireId = :locataireId", Demandelocation.class)
                .setParameter("locataireId", locataireId)
                .getResultList();
    }

    public List<Demandelocation> findByUniteLocationId(int uniteLocationId) {
        return entityManager.createQuery("SELECT d FROM Demandelocation d WHERE d.uniteLocationId = :uniteLocationId", Demandelocation.class)
                .setParameter("uniteLocationId", uniteLocationId)
                .getResultList();
    }
}
