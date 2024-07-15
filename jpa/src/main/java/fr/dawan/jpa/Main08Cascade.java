package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.entities.relations.Fournisseur;
import fr.dawan.jpa.entities.relations.Marque;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main08Cascade {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        // Articles APPLE
        Article a1 = new Article("Airpods Pro Max", 570, Emballage.CARTON);
        Article a2 = new Article("iPhone 15", 1000, Emballage.CARTON);
        Article a3 = new Article("MacBook Pro M3", 2000, Emballage.CARTON);
        Marque ma = new Marque("Apple", LocalDate.of(1976, 4, 1));

        ma.getArticles().add(a1);
        ma.getArticles().add(a2);
        a1.setMarque(ma);
        a2.setMarque(ma);
        a3.setMarque(ma);

        // Article TOSHIBA
        Article a4 = new Article("Disque DUR 10 To", 90, Emballage.PLASTIQUE);
        Marque mt = new Marque("Toshiba", LocalDate.of(1956, 7, 8));
        mt.getArticles().add(a4);
        a4.setMarque(mt);

        System.out.println(a4);
        System.out.println(mt);

        // ManyToMany

        Fournisseur f1 = new Fournisseur("Samsung");
        Fournisseur f2 = new Fournisseur("TSMC");
        f1.addArticle(a1);
        f1.addArticle(a2);
        f2.addArticle(a3);
        f2.addArticle(a1);
        f2.addArticle(a4);

        try {
            tx.begin();

            em.persist(ma);
            em.persist(mt);

            em.persist(a1);
            em.persist(a2);
            em.persist(a3);

            em.persist(f1);
            em.persist(f2);

            // Si cascade.delete alors les articles associés à la marque seront aussi
            // supprimés
//            Marque mt2 = em.find(Marque.class, 2L);
//            em.remove(mt2);

            // Les articles qui ne sont plus associés à une marque sont supprimés
            // (orphanRemoval)
//            ma.getArticles().remove(a1);
//            ma.getArticles().remove(a2);
            ma.getArticles().clear();

            em.merge(ma);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
