package fr.dawan.jpa;

import java.util.Scanner;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.entities.relations.Fournisseur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main07LazyEager {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        Fournisseur f1 = null;
        Article a1 = null;
        try {
            tx.begin();

//            f1 = em.find(Fournisseur.class, 1L);
//            System.out.println(f1);
//            System.out.println(f1.getArticles());
            a1 = em.find(Article.class, 1L);
            System.out.println(a1.getFournisseurs());
            System.out.println(a1.getMarque());
            System.out.println(a1);
            Scanner sc = new Scanner(System.in);
            sc.next();
            sc.close();
            tx.commit();

            tx.begin();
            em.refresh(a1);
            tx.commit();
            System.out.println(a1.getPrix());

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();

        // Deuxième transaction

        EntityManager em2 = emf.createEntityManager();

        tx = em2.getTransaction();

        try {
            tx.begin();

            a1.setPrix(a1.getPrix() + 10);
            em2.merge(a1);
            System.out.println(a1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        emf.close();
    }
}
