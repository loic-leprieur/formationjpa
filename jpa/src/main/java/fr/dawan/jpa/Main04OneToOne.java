package fr.dawan.jpa;

import fr.dawan.jpa.entities.relations.Maire;
import fr.dawan.jpa.entities.relations.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main04OneToOne {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        Ville stras = new Ville("Strasbourg");
        Maire maire = new Maire("Jeanne", "Barseghian");
        stras.setMaire(maire);
        maire.setVille(stras);

        try {
            tx.begin();

            em.persist(maire);
            em.persist(stras);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
