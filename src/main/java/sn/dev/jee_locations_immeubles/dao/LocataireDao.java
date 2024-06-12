package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.dev.jee_locations_immeubles.Entities.Locataire;
import java.util.List;

public class LocataireDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Locataire save(Locataire locataire) {
        entityManager.persist(locataire);
        return locataire;
    }

    public Locataire update(Locataire locataire) {
        return entityManager.merge(locataire);
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

    public List<Locataire> findAll() {
        return entityManager.createQuery("SELECT l FROM Locataire l", Locataire.class).getResultList();
    }
}