package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.*;

import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import sn.dev.jee_locations_immeubles.Entities.Locataire;
import sn.dev.jee_locations_immeubles.dao.LocataireDao;

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

    // update le ueser correspondant au locataire

    public Utilisateur update(Utilisateur utilisateur) {
    EntityTransaction transaction = this.entityManager.getTransaction();
    try {
        transaction.begin();
        utilisateur = entityManager.merge(utilisateur);
        entityManager.flush();
        transaction.commit();
    } catch (RuntimeException e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw e;
    }
    return utilisateur;
}

    public boolean delete(int id) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
            if (utilisateur != null) {

                entityManager.remove(utilisateur);
                //entityManager.flush();
                transaction.commit();
                System.out.println("Utilisateur supprimé avec succès : " + utilisateur.getNomUtilisateur());
                return true; // Suppression réussie
            } else {
                System.out.println("Utilisateur non trouvé avec l'ID : " + id);
                transaction.rollback();
                return false; // L'utilisateur n'a pas été trouvé
            }
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la suppression de l'utilisateur avec l'ID : " + id);
            throw e; // Relancer l'exception pour être gérée par l'appelant
        }
    }




    public Utilisateur find(int id) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        Utilisateur utilisateur;
        try {
            transaction.begin();
            utilisateur = entityManager.find(Utilisateur.class, id);
            entityManager.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return utilisateur;

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

