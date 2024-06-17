package sn.dev.jee_locations_immeubles.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sn.dev.jee_locations_immeubles.Entities.Immeuble;
import sn.dev.jee_locations_immeubles.Entities.Unitelocation;

import java.util.ArrayList;
import java.util.List;

public class ImmeubleDao {

    @PersistenceContext
    private EntityManager entityManager;

      public ImmeubleDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = emf.createEntityManager();
    }

    public Immeuble save(Immeuble immeuble) {
        entityManager.getTransaction().begin();
        entityManager.persist(immeuble);
        entityManager.getTransaction().commit();
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

    // public List<Immeuble> AllImmeubles() {
    //     return entityManager.createQuery("SELECT i FROM Immeuble  i", Immeuble.class).getResultList();
    // }

    public List<Immeuble> AllImmeubles() {
    List<Immeuble> immeubles = entityManager.createQuery("SELECT i FROM Immeuble i", Immeuble.class).getResultList();

        return immeubles;
    }
    

    // fonction qui recupere tous les uniteLocation d'un immeuble par son id 
public List<Unitelocation> getUnitLocationsByImmeubleId(int id) {
    // Récupérer l'objet Immeuble correspondant à l'ID
    Immeuble immeuble = entityManager.find(Immeuble.class, id);
    
    // Utiliser l'objet Immeuble dans la requête
    List<Unitelocation> unitelocationsByImmeuble = entityManager.createQuery(
        "SELECT u FROM Unitelocation u WHERE u.immeubleByImmeubleId = :immeuble", Unitelocation.class)
        .setParameter("immeuble", immeuble)
        .getResultList();

    return unitelocationsByImmeuble;
}
}


