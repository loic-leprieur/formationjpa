package fr.dawan.exercicejpa.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.dawan.exercicejpa.entities.bibliotheque.Auteur;
import fr.dawan.exercicejpa.entities.bibliotheque.Categorie;
import fr.dawan.exercicejpa.entities.bibliotheque.Livre;
import fr.dawan.exercicejpa.entities.bibliotheque.StatLivre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LivreDao extends AbstractDao<Livre> {

    public LivreDao(EntityManager em) {
        super(em, Livre.class);
    }

    // Méthode qui retourne tous les livres sortie l'année passé en paramètre
    public List<Livre> findByAnnee(int anneeSortie) {
        TypedQuery<Livre> query = createQuery("SELECT l FROM Livre l WHERE l.anneeSortie = :annee");
        query.setParameter("annee", anneeSortie);
        return query.getResultList();
    }

    // Méthode qui retourne tous les livres sortie entre les 2 années passés en
    // paramètre
    public List<Livre> findByIntervalAnnee(int anneeMin, int anneeMax) {
        TypedQuery<Livre> query = createQuery("SELECT l FROM Livre l WHERE l.anneeSortie BETWEEN :min AND :max");
        query.setParameter("min", anneeMin);
        query.setParameter("max", anneeMax);
        return query.getResultList();
    }

    // Méthode qui retourne tous les livres sortie pour les années passées en
    // paramètre
    public List<Livre> findByAnnees(int... anneesSortie) {
        if (anneesSortie.length > 0) {
            String str = Arrays.toString(anneesSortie);
            String req = String.format("SELECT l FROM Livre l WHERE l.anneeSortie IN (%s)",
                    str.substring(1, str.length() - 1));
            TypedQuery<Livre> query = createQuery(req);
            return query.getResultList();
        }
        return new ArrayList<>();

    }

    // Méthode qui retourne tous les livres qui commencent par le préfixe passé en
    // paramètre (en utilisant CONCAT)
    public List<Livre> findStartWith(String prefix) {
        return createQuery("SELECT l FROM Livre l WHERE l.titre LIKE CONCAT(:pre,'%')")
                .setParameter("pre", prefix)
                .getResultList();
    }

    // Méthode qui retourne le nombre de livre sortie l'année passé en paramètre
    public long countByAnneeSortie(int anneeSortie) {
        return createQuery("SELECT COUNT(l) FROM Livre l WHERE l.anneeSortie=:annee", Long.class)
                .setParameter("annee", anneeSortie)
                .getSingleResult();
    }

    // Méthode qui retourne tous les livres pour une catégorie trié par année de
    // sortie décroissant
    public List<Livre> findByCategorie(Categorie categorie) {
        return createQuery("FROM Livre l WHERE l.categorie= :categorie ORDER BY l.anneeSortie DESC")
                .setParameter("categorie", categorie)
                .getResultList();
    }

    // Méthode qui retourne tous les livres écrit par un auteur
    public List<Livre> findByAuteur(Auteur auteur) {
        return  createQuery("FROM Livre l JOIN l.auteurs a WHERE a=:auteur")
        .setParameter("auteur", auteur)
        .getResultList();
    }

    // Méthode qui retourne tous les livres qui ont plusieurs auteurs
    public List<Livre> findMultiAuteur() {
        return  createQuery("FROM Livre l JOIN l.auteurs a GROUP BY l HAVING COUNT(a)>1")
                .getResultList();
    }

    // Méthode qui retourne le nombre de livres écrit (count) pour chaque auteur
    // (concaténation du prénom et du nom => data)
    public List<StatLivre> getStatLivreByAuteur() {
        return createQuery("SELECT new StatLivre(CONCAT(a.prenom, ' ', a.nom),Count(l)) FROM Auteur a LEFT JOIN a.livres l GROUP BY a ORDER BY count(l) DESC",StatLivre.class)
        .getResultList();
    }

    // Méthode qui retourne le nombre de livres(count) pour chaque catégorie (nom de  la catégorie => data) dans l'ordre décroissant
    public List<StatLivre> getStatLivreByCategorie() {
        return  createQuery("SELECT new StatLivre(l.categorie.nom,COUNT(l)) FROM Livre l GROUP BY l.categorie ORDER BY count(l) DESC",StatLivre.class)
        .getResultList();
    }
    
    // Méthode qui retourne l'année où est sortie le plus de livre
    public int getMaxLivreAnnee() {
        return createQuery("SELECT l.anneeSortie FROM Livre l GROUP BY l.anneeSortie ORDER BY COUNT(l) DESC",Integer.class)
                .setMaxResults(1).getSingleResult();
    }
    

    // Sous-requête   
    // Méthode qui retourne les livres qui correspondent à l'année de sortie moyenne d'une catégorie
    public List<Livre> findByAvgAnnee(Categorie c) {
        return createQuery("FROM Livre li WHERE li.anneeSortie = (SELECT ROUND(AVG(l.anneeSortie),0) FROM Livre l WHERE l.categorie=:cat)")
        .setParameter("cat", c)
        .getResultList();
    }

    // Méthode qui retourne les livres qui sont sortie les même année que les livres de la catégorie passé en paramètre
    public List<Livre> findBySameAnnee(Categorie c) {
        return createQuery("SELECT li FROM Livre li WHERE li.anneeSortie= ANY (SELECT l.anneeSortie FROM Livre l WHERE l.categorie=:cat)")
       .setParameter("cat", c)
       .getResultList();
    }

}