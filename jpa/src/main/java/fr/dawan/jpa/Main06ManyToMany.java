package fr.dawan.jpa;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.entities.relations.Fournisseur;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main06ManyToMany {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();
        Article a1 = new Article("Airpods Pro Max", 570, Emballage.CARTON);
        Article a2 = new Article("iPhone 15", 1000, Emballage.CARTON);
        Article a3 = new Article("Apple Watch 9", 700, Emballage.CARTON);

        Fournisseur f1 = new Fournisseur("Samsung");
        Fournisseur f2 = new Fournisseur("TSMC");

        f1.addArticle(a1);
        f1.addArticle(a2);
        f2.addArticle(a1);
        f2.addArticle(a3);

        try {
            tx.begin();

            em.persist(a1);
            em.persist(a2);
            em.persist(a3);

            em.persist(f1);
            em.persist(f2);

            System.out.println(a1);
            System.out.println(f1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
