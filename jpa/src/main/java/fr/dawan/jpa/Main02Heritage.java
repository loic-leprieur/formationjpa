package fr.dawan.jpa;

import fr.dawan.jpa.entities.heritage.CompteBancaire;
import fr.dawan.jpa.entities.heritage.CompteEpargne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main02Heritage {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        CompteBancaire cb1 = new CompteBancaire("John Doe", 200.0);
        CompteBancaire cb2 = new CompteBancaire("Jane Doe", 500.0);

        CompteEpargne ce1 = new CompteEpargne("John Doe", 50.0, 6.0);
        CompteEpargne ce2 = new CompteEpargne("Alan Smithee", 500.0, 3.0);

        try {
            tx.begin();
            em.persist(cb1);
            em.persist(cb2);
            em.persist(ce1);
            em.persist(ce2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close(); 
        emf.close();
    }

}
