package fr.dawan.exercice_jpa.dao;

import java.util.List;

import fr.dawan.exercice_jpa.entities.Categorie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CategorieDao extends AbstractDao<Categorie> {

    public CategorieDao(EntityManager em) {
        super(em, Categorie.class);
    }

    public List<Categorie> findByCategorie(Categorie categorie) {
        TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE = c :categorie", Categorie.class);

        query.setParameter("categorie", categorie);
        return query.getResultList();
    }
}
