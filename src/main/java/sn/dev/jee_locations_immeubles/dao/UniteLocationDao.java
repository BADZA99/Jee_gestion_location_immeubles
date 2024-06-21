package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        entityManager.persist(unitelocation);
        return unitelocation;
    }

    public Unitelocation update(Unitelocation unitelocation) {
        return entityManager.merge(unitelocation);
    }

    public void delete(int id) {
        Unitelocation unitelocation = entityManager.find(Unitelocation.class, id);
        if (unitelocation != null) {
            entityManager.remove(unitelocation);
        }
    }

    public Unitelocation find(int id) {
        return entityManager.find(Unitelocation.class, id);
    }

    public List<Unitelocation> findAll() {
        return entityManager.createQuery("SELECT u FROM Unitelocation u", Unitelocation.class).getResultList();
    }
}