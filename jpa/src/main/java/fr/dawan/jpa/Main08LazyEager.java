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

public class Main08LazyEager {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Article a1 = new Article("TV 4K", 600, Emballage.CARTON);
        Article a2 = new Article("Souris", 30, Emballage.PLASTIQUE);
        Article a3 = new Article("Clavier", 30, Emballage.SANS);

        Marque ma = new Marque("Marque A", LocalDate.of(1983, 10, 20));
        ma.getArticles().add(a1);
        ma.getArticles().add(a2);
        a1.setMarque(ma);
        a2.setMarque(ma);

        Fournisseur f1 = new Fournisseur("Fournisseur 1");
        Fournisseur f2 = new Fournisseur("Fournisseur 2");
        f1.addArticle(a1);
        f1.addArticle(a2);
        f2.addArticle(a1);
        f2.addArticle(a3);
        try {
            tx.begin();
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(ma);
            em.persist(f1);
            em.persist(f2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.clear(); // retirer les objets de l'entité manager
        f1 = null;
        a1 = null;
        try {

            // Stratégie de chargement
            tx.begin();

            // Chargement immédiat :
            a1 = em.find(Article.class, 1L);
            System.out.println(a1);
            System.out.println("--------------------------------------");
            // @ManyToOne, @OneToOne -> EAGER : la variable d'instance marque est chargée au
            // moment du find
            System.out.println(a1.getMarque());

            // Chargement tardif :
            f1 = em.find(Fournisseur.class, 1L);
            System.out.println(f1);
            System.out.println("--------------------------------------");

            // @OneToMany, @ManyToMany -> LAZY : les éléments de la collection articles sont
            // chargés au moment où l'on y accéde
            System.out.println(f1.getArticles()); // ***1***
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.close();
        // Si on commente //***1*** et on décommente // ***2***
        // On accède à un élément de articles, mais l'entity manager est fermé
        // Il n'y a plus de connection à la base de donnée ->
        // LazyInitializationException
        
        // System.out.println(ml1.getArticles().get(0)); // ***2***
        emf.close();
    }
}
