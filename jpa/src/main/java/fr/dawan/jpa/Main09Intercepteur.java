package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.entities.interceptor.Contrat;
import fr.dawan.jpa.entities.interceptor.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main09Intercepteur {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Contrat contrat1 = new Contrat("John Doe", LocalDate.of(2022, 1, 10), 5);
        Utilisateur user1 = new Utilisateur("Doe", "Jane", "jd@dawan.com", "123456");

        try {
            tx.begin();
            
            em.persist(contrat1);
            
            em.persist(user1);
            
            tx.commit();
            
            Thread.sleep(1000);
            
            tx.begin();
            
            user1.setPassword("azerty");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.clear();
        em.createQuery("FROM Contrat c",  Contrat.class).getResultList().forEach(System.out::println);
        em.close();
        emf.close();
    }
}
