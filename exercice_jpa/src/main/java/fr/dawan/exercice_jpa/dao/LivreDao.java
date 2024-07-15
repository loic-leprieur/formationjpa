package fr.dawan.exercice_jpa.dao;

import java.util.Arrays;
import java.util.List;

import fr.dawan.exercice_jpa.entities.Auteur;
import fr.dawan.exercice_jpa.entities.Categorie;
import fr.dawan.exercice_jpa.entities.Livre;
import fr.dawan.exercice_jpa.results.StatLivre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LivreDao extends AbstractDao<Livre> {

    public LivreDao(EntityManager em) {
        super(em, Livre.class);
    }

    public List<Livre> findByAnnee(int anneeSortie) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre as l WHERE l.anneeSortie = ?1", Livre.class);

        query.setParameter(1, anneeSortie);
        return query.getResultList();

    }

    public List<Livre> findByIntervalAnnee(int anneeMin, int anneeMax) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre as l WHERE l.anneeSortie BETWEEN ?1 AND ?2",
                Livre.class);

        query.setParameter(1, anneeMin);
        query.setParameter(2, anneeMax);
        return query.getResultList();
    }

    public List<Livre> findByAnnees(int... anneesSortie) {

        String[] param = Arrays.stream(anneesSortie).mapToObj(Integer::toString).toArray(String[]::new);

        String paramAnnee = String.join(",", param);
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre as l WHERE l.anneeSortie IN(" + paramAnnee + ")",
                Livre.class);

        return query.getResultList();
    }

    public List<Livre> findStartWith(String prefix) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre as l WHERE l.titre LIKE CONCAT(:pre, '%')",
                Livre.class);

        query.setParameter("pre", prefix);
        return query.getResultList();
    }

    public long countByAnnee(int anneeSortie) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(l) FROM Livre as l WHERE l.anneeSortie = ?1", Long.class);
        query.setParameter(1, anneeSortie);

        return query.getSingleResult();
    }

    public List<Livre> findByCategorie(Categorie categorie) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.categorie = :categorie", Livre.class);

        query.setParameter("categorie", categorie);
        return query.getResultList();
    }

    public List<Livre> findByAuteur(Auteur auteur) {

        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l JOIN l.auteurs a WHERE a=:auteur", Livre.class);

        query.setParameter("auteur", auteur);
        return query.getResultList();
    }

    public List<Livre> findMultiAuteur() {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE SIZE(l.auteurs) > 1", Livre.class);

        return query.getResultList();
    }

    public List<StatLivre> getStatLivreByAuteur() {

        TypedQuery<StatLivre> query = em.createQuery(
                "SELECT new StatLivre(CONCAT(a.prenom, ' ', a.nom), COUNT(a)) l FROM Livre l LEFT JOIN l.auteurs a GROUP BY a ORDER BY COUNT(a)",
                StatLivre.class);

        return query.getResultList();
    }

    public List<StatLivre> getStatLivreByCategorie() {
        TypedQuery<StatLivre> query = em.createQuery(
                "SELECT new StatLivre(l.categorie.nom, COUNT(l.categorie)) l FROM Livre l GROUP BY l.categorie ORDER BY COUNT(l) DESC",
                StatLivre.class);

        return query.getResultList();
    }

    public int getMaxLivreAnnee() {

        TypedQuery<Integer> query = createResultQuery("SELECT l.anneeSortie FROM Livre l GROUP BY l.anneeSortie ORDER BY COUNT(l) DESC", Integer.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Livre> findByAvgAnnee(Categorie categorie) {

        TypedQuery<Livre> query = createQuery(
                "FROM Livre li WHERE li.anneeSortie = (SELECT ROUND(AVG(l.anneeSortie),0) FROM Livre l WHERE l.categorie=:cat)");
        query.setParameter("cat", categorie);

        return query.getResultList();
    }

    public List<Livre> findBySameAnnee(Categorie categorie) {
        return createQuery("SELECT l FROM Livre l WHERE l.anneeSortie = ANY (SELECT l2.anneeSortie FROM Livre l2 WHERE l2.categorie=:cat)")
                .setParameter("cat", categorie)
                .getResultList();
    }

}
