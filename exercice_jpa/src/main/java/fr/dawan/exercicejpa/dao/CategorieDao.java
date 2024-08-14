package fr.dawan.exercicejpa.dao;

import java.util.List;

import fr.dawan.exercicejpa.entities.bibliotheque.Categorie;
import jakarta.persistence.EntityManager;

public class CategorieDao extends AbstractDao<Categorie> {

    public CategorieDao(EntityManager em) {
        super(em, Categorie.class);
    }

    public List<Categorie> findByNom(String nom){
        return createQuery("FROM Categorie c WHERE c.nom =:nom")
                .setParameter("nom", nom)
                .getResultList();
    }
}
