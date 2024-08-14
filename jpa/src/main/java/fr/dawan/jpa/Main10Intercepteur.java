package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.entities.interceptor.Contrat;
import fr.dawan.jpa.entities.interceptor.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main10Intercepteur {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Contrat contrat1=new Contrat("John Doe",LocalDate.of(2022,1,10),5);
        Utilisateur u1=new Utilisateur("Jane","Doe","jd@dawan.com","123456");
        try {
            tx.begin();
            em.persist(u1);
            em.persist(contrat1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        try {
            tx.begin();

            Thread.sleep(10000);
            u1.setPassword("azerty");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.clear();
        em.createQuery("FROM Contrat c",Contrat.class).getResultList().forEach(System.out::println);
        em.close();
        emf.close();
    }

}
