package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.dev.jee_locations_immeubles.Entities.Paiement;
import java.util.List;

public class PaiementDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Paiement save(Paiement paiement) {
        entityManager.persist(paiement);
        return paiement;
    }

    public Paiement update(Paiement paiement) {
        return entityManager.merge(paiement);
    }

    public void delete(int id) {
        Paiement paiement = entityManager.find(Paiement.class, id);
        if (paiement != null) {
            entityManager.remove(paiement);
        }
    }

    public Paiement find(int id) {
        return entityManager.find(Paiement.class, id);
    }

    public List<Paiement> findAll() {
        return entityManager.createQuery("SELECT p FROM Paiement p", Paiement.class).getResultList();
    }
}