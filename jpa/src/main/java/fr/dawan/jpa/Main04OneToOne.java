package fr.dawan.jpa;

import fr.dawan.jpa.entities.relations.Maire;
import fr.dawan.jpa.entities.relations.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main04OneToOne {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Ville lille = new Ville("Lille");
        Maire maire = new Maire("Martine", "Aubry");
        lille.setMaire(maire);
        maire.setVille(lille);

        try {
            tx.begin(); 
            em.persist(maire);
            em.persist(lille);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        em.close();
        emf.close();
    }

}
