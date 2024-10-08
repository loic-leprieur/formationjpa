package fr.dawan.requetejpa;

import java.util.List;

import fr.dawan.requetejpa.entities.Article;
import fr.dawan.requetejpa.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main03SQL {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("requetejpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<Article> listArticle = em.createNativeQuery("SELECT * FROM articles a WHERE a.prix > :prix LIMIT 2", Article.class)
                .setParameter("prix", 50)
                .getResultList();
            
            listArticle.forEach(System.out::println);
            
            List<Marque> listMarque = em.createNamedQuery("Marque.findByNom", Marque.class)
                    .setParameter("nom", "Marque A")
                    .getResultList();
            
            listMarque.forEach(System.out::println);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
