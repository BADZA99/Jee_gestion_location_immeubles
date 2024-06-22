package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.*;

import sn.dev.jee_locations_immeubles.Entities.Locataire;
import java.util.List;

public class LocataireDao {

    @PersistenceContext
    private EntityManager entityManager;
    public LocataireDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        this.entityManager = factory.createEntityManager();
    }
    public Locataire save(Locataire locataire) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(locataire);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        //System.out.println("apres save "+ utilisateur);
        return locataire;
    }

    public Locataire update(Locataire locataire) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            locataire = entityManager.merge(locataire);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return locataire;
    }

    public void delete(int id) {
        Locataire locataire = entityManager.find(Locataire.class, id);
        if (locataire != null) {
            entityManager.remove(locataire);
        }
    }

    public Locataire find(int id) {
        return entityManager.find(Locataire.class, id);
    }

    public Locataire findLocataireByUtilisateurId(int idUtilisateur) {
        return entityManager.createQuery("SELECT l FROM Locataire l WHERE l.idUtilisateur = :idUtilisateur", Locataire.class)
                .setParameter("idUtilisateur", idUtilisateur)
                .getSingleResult();
    }

    public List<Locataire> findAll() {
        return entityManager.createQuery("SELECT l FROM Locataire l", Locataire.class).getResultList();
    }
}