package fr.dawan.exercice_jpa.dao;

import java.util.List;

import fr.dawan.exercice_jpa.entities.Auteur;
import fr.dawan.exercice_jpa.entities.Categorie;
import fr.dawan.exercice_jpa.entities.Nation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AuteurDao extends AbstractDao<Auteur> {

    public AuteurDao(EntityManager em) {
        super(em, Auteur.class);
    }

    public List<Auteur> findAlive() {
        TypedQuery<Auteur> query = em.createNamedQuery("Auteur.findAlive", Auteur.class);

        return query.getResultList();
    }

    public List<Auteur> findByNoBook() {
        TypedQuery<Auteur> query = em.createNamedQuery("Auteur.findByNoBook", Auteur.class);

        return query.getResultList();
    }

    public List<Auteur> findByNation(Nation nation) {
        TypedQuery<Auteur> query = em.createNamedQuery("Auteur.findByNation", Auteur.class);

        query.setParameter("nation", nation);

        return query.getResultList();
    }

    public List<Auteur> getTop5Auteur() {
        TypedQuery<Auteur> query = createQuery("SELECT a FROM Auteur a JOIN a.livres l GROUP BY a ORDER BY COUNT(l) DESC");
        query.setMaxResults(5);

        return query.getResultList();
    }

    public List<Auteur> getTop10AgeAuteur() {
        TypedQuery<Auteur> query = createQuery(
                "SELECT a FROM Auteur a ORDER BY (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM a.dateNaissance)) DESC");
        query.setMaxResults(10);
        
        return query.getResultList();
    }


    public List<Auteur> findByCategorie(Categorie categorie) {
        TypedQuery<Auteur> query = createQuery(
                "SELECT a FROM Auteur a JOIN a.livres l WHERE l.categorie = :cat GROUP BY a");
        query.setParameter("cat", categorie);

        query.setMaxResults(5);

        return query.getResultList();
    }
}
