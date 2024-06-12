package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.dev.jee_locations_immeubles.Entities.Immeuble;
import java.util.List;

public class ImmeubleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Immeuble save(Immeuble immeuble) {
        entityManager.persist(immeuble);
        return immeuble;
    }

    public Immeuble update(Immeuble immeuble) {
        return entityManager.merge(immeuble);
    }

    public void delete(int id) {
        Immeuble immeuble = entityManager.find(Immeuble.class, id);
        if (immeuble != null) {
            entityManager.remove(immeuble);
        }
    }

    public Immeuble find(int id) {
        return entityManager.find(Immeuble.class, id);
    }

    public List<Immeuble> findAll() {
        return entityManager.createQuery("SELECT i FROM Immeuble i", Immeuble.class).getResultList();
    }
}