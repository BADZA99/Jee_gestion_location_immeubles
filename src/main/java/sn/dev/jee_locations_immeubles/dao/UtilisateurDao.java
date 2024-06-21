package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.*;

import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.Entities.Locataire;
import java.util.List;

public class UtilisateurDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UtilisateurDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        this.entityManager = factory.createEntityManager();
    }

    public Utilisateur save(Utilisateur utilisateur) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(utilisateur);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        //System.out.println("apres save "+ utilisateur);
        return utilisateur;
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return entityManager.merge(utilisateur);
    }

    public void delete(int id) {
        Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
        if (utilisateur != null) {
            entityManager.remove(utilisateur);
        }
    }

    public Utilisateur find(int id) {
        return entityManager.find(Utilisateur.class, id);
    }

    public List<Utilisateur> findAll() {
        return entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }

    public Utilisateur findUserByNameAndPassword(String nom, String password) {
        try {
            return this.entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.nomUtilisateur = :name AND u.motDePasse = :password", Utilisateur.class)
                    .setParameter("name", nom)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

