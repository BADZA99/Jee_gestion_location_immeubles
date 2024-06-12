package sn.dev.jee_locations_immeubles.dao;

import sn.dev.jee_locations_immeubles.Entities.Contratlocation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ContratLocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Contratlocation findById(int id) {
        return entityManager.find(Contratlocation.class, id);
    }

    public List<Contratlocation> findAll() {
        return entityManager.createQuery("SELECT c FROM Contratlocation c", Contratlocation.class).getResultList();
    }

    public Contratlocation save(Contratlocation contratlocation) {
        if (contratlocation != null && contratlocation.getId() == 0) {
            entityManager.persist(contratlocation);
        } else if (contratlocation != null) {
            contratlocation = entityManager.merge(contratlocation);
        }
        return contratlocation;
    }

    public void delete(int id) {
        Contratlocation contratlocation = findById(id);
        if (contratlocation != null) {
            entityManager.remove(contratlocation);
        }
    }
}