package fr.dawan.jpa;

import java.util.List;

import fr.dawan.jpa.entities.relations.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Main11cache {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");

        // Première requête
        EntityManager em1 = emf.createEntityManager();
        CriteriaBuilder cb1 = em1.getCriteriaBuilder();
        CriteriaQuery<Article> critQuery = cb1.createQuery(Article.class);
        Root<Article> articleRoot = critQuery.from(Article.class);
        critQuery.select(critQuery.getSelection()).where(cb1.lessThan(articleRoot.get("prix"), 400));

        long startTime1 = System.nanoTime();
        TypedQuery<Article> tq1 = em1.createQuery(critQuery).setHint("org.hibernate.cacheable", true).setHint("jakarta.persistence.cache.retrieveMode", "USE");;
        List<Article> result1 = tq1.getResultList();
        long endTime1 = System.nanoTime();
        result1.forEach(System.out::println);
        em1.close();

        // Deuxième requête
        EntityManager em2 = emf.createEntityManager();

        long startTime2 = System.nanoTime();
        TypedQuery<Article> tq2 = em2.createQuery(critQuery).setHint("org.hibernate.cacheable", true).setHint("jakarta.persistence.cache.retrieveMode", "USE");
        List<Article> result2 = tq2.getResultList();
        long endTime2 = System.nanoTime();
        result2.forEach(System.out::println);
        em2.close();

        // Calculer le temps d'exécution
        float duration1 = ((float) (endTime1 - startTime1)) / 1000000;
        float duration2 = ((float) (endTime2 - startTime2)) / 1000000;

        System.out.println("Temps d'exécution de la première requête: " + duration1 + " millisecondes");
        System.out.println("Temps d'exécution de la deuxième requête: " + duration2 + " millisecondes");

        emf.close();
    }
}
