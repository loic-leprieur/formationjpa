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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Article a1 = new Article("TV 4K", 600, Emballage.CARTON);
        Article a2 = new Article("Souris", 30, Emballage.PLASTIQUE);
        Marque ma = new Marque("Marque A", LocalDate.of(1983, 10, 20));
        ma.getArticles().add(a1);
        ma.getArticles().add(a2);
        a1.setMarque(ma);
        a2.setMarque(ma);

        System.out.println(a1);
        System.out.println(ma);
        try {
            tx.begin();
            em.persist(a1);
            em.persist(a2);
            em.persist(ma);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.close();
        emf.close();
    }
}
