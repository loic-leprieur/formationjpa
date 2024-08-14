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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Article a1=new Article("TV 4K",600,Emballage.CARTON); 
        Article a2=new Article("Souris",30,Emballage.PLASTIQUE); 
        Article a3=new Article("Clavier",30,Emballage.SANS); 
      
        Fournisseur f1=new Fournisseur("Fournisseur 1");
        Fournisseur f2=new Fournisseur("Fournisseur 2");
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
