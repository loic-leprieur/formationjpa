package fr.dawan.jpa;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main10Cache {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Article a = new Article("TV 4K", 600, Emballage.CARTON);

        try {
            tx.begin();
            em.persist(a);
            em.clear();
            
            Article a1 = em.find(Article.class, 1L);
            
            System.out.println(a1);
            System.out.println("--------------------------");
            
            Article a2 = em.find(Article.class, 1L);
            System.out.println(a2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
      
        em.close();
        emf.close();
    }
}
