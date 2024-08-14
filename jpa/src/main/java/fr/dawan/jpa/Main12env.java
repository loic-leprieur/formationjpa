package fr.dawan.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.dawan.jpa.entities.relations.Article;
import fr.dawan.jpa.enums.Emballage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main12env {

    public static void main(String[] args) {
        // Map qui contient les propriétées à ajouter à ceux de persistence.xml
        Map<String, Object> config = new HashMap<String, Object>();
        
        // Pour récuper les variables d'environnement
        Set<Entry<String,String>> env=System.getenv().entrySet();
        
        // L'utilisateur de la bdd est stocké dans la variable d'environnement DB_USER
        // L'utilisateur de la bdd est stocké dans la variable d'environnement DB_PASSWORD
        for(Entry<String,String> e :env) {
            if(e.getKey().equals("DB_USER")) {
                System.out.println(e.getValue());
                config.put("jakarta.persistence.jdbc.user", e.getValue());
            }
            else if(e.getKey().equals("DB_PASSWORD")) {
                config.put("jakarta.persistence.jdbc.password", e.getValue());
            }
        }
        // Dans l'entity manager factory, on passe la map avec les propriétés suplémentaires.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa", config);
        EntityManager em = emf.createEntityManager();    
        EntityTransaction tx = em.getTransaction();
        Article a=new Article( "Stylo",1.5,Emballage.SANS); 
        try {
            tx.begin();
            em.persist(a);
            Article a1=em.find(Article.class, 1L);
            System.out.println(a1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.close();
        emf.close();

    }

}
