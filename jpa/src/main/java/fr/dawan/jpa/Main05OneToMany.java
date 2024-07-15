package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.entities.relations.Marque;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main05OneToMany {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        Article a1 = new Article("Airpods Pro Max", 570, Emballage.CARTON);
        Article a2 = new Article("iPhone 15", 1000, Emballage.CARTON);

        Marque ma = new Marque("Apple", LocalDate.of(1976, 4, 1));

        ma.getArticles().add(a1);
        ma.getArticles().add(a2);
        a1.setMarque(ma);
        a2.setMarque(ma);

        System.out.println(a1);

        System.out.println(ma);

        try {
            tx.begin();

            em.persist(ma);
            em.persist(a1);
            em.persist(a2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
