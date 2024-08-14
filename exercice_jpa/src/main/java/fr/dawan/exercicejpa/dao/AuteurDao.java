package fr.dawan.exercicejpa.dao;

import java.util.List;

import fr.dawan.exercicejpa.entities.bibliotheque.Auteur;
import fr.dawan.exercicejpa.entities.bibliotheque.Categorie;
import fr.dawan.exercicejpa.entities.bibliotheque.Nation;
import jakarta.persistence.EntityManager;

public class AuteurDao extends AbstractDao<Auteur> {

    public AuteurDao(EntityManager em) {
        super(em, Auteur.class);
    }
    
 // Méthode  qui retourne tous les auteurs vivant
    public List<Auteur> findAlive() {
        return createNamedQuery("Auteur.vivant", Auteur.class)
                .getResultList();
    }
    
    // Méthode qui retourne tous les auteurs qui n'ont pas écrit de livre
    public List<Auteur> findByNoBook() {
        return createNamedQuery("Auteur.sanslivre", Auteur.class)
        .getResultList();
    }

    // Méthode qui retourne les auteurs en fonction de la nation passée en paramètre
    public List<Auteur> findByNation(Nation nation) {
        return createNamedQuery("Auteur.parNation", Auteur.class)
        .setParameter("pays", nation)
        .getResultList();
    }

    // Méthode qui retourner le top 5 des auteurs qui ont écrit le plus de livre
    public List<Auteur> getTop5Auteur(){
        return createQuery("SELECT a FROM Auteur a JOIN a.livres l GROUP BY a ORDER BY count(l) DESC", Auteur.class)
                .setMaxResults(5)
                .getResultList();
    }

    // Méthode qui retourne le top 10 des auteurs les plus anciens
    public List<Auteur> getTop10AgeAuteur(){
        return createQuery("SELECT a FROM Auteur a ORDER BY a.naissance", Auteur.class)
                .setMaxResults(10)
                .getResultList();
    }

    // Méthode qui retourne tous les auteurs qui ont écrit un livre  de la catégorie passé en paramètre
    public List<Auteur> findByCategorie(Categorie categorie){
        return createQuery("SELECT a FROM Auteur a JOIN a.livres l WHERE l.categorie= :cat", Auteur.class)
               .setParameter("cat",categorie)
                .getResultList();
    }
}
