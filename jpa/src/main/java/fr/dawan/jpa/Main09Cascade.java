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

public class Main09Cascade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Article a1 = new Article("TV 4K", 600, Emballage.CARTON);
        Article a2 = new Article("Souris", 30, Emballage.PLASTIQUE);
        Article a3 = new Article("Souris Gamer", 80, Emballage.CARTON);
        Marque ma = new Marque("Marque A", LocalDate.of(1983, 10, 20));
        ma.getArticles().add(a1);
        ma.getArticles().add(a2);
        ma.getArticles().add(a3);
        a1.setMarque(ma);
        a2.setMarque(ma);
        a3.setMarque(ma);
        Article a4 = new Article("Disque Dur 10 t0", 90, Emballage.PLASTIQUE);
        Marque md = new Marque("Marque D", LocalDate.of(1999, 10, 20));
        md.getArticles().add(a4);
        a4.setMarque(md);

        Fournisseur f1 = new Fournisseur("Fournisseur 1");
        Fournisseur f2 = new Fournisseur("Fournisseur 2");
        // f1.addArticle(a1);
        f1.addArticle(a2);
        f1.addArticle(a4);
        f2.addArticle(a1);
        f2.addArticle(a3);

        try {
            tx.begin();
            em.persist(ma);
            em.persist(md);
            em.persist(f1);
            em.persist(f2);
//            Marque ma2=em.find(Marque.class, 1L);
//            em.remove(ma2);
            // ma.getArticles().remove(a1);
            ma.getArticles().clear();
            em.merge(ma);

            em.remove(f2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.close();
        emf.close();
    }

}
