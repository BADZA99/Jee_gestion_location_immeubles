package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.dev.jee_locations_immeubles.Entities.Utilisateur;
import java.util.List;

public class UtilisateurDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Utilisateur save(Utilisateur utilisateur) {
        entityManager.persist(utilisateur);
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
}