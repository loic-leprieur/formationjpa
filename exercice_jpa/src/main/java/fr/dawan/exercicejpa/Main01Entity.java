package fr.dawan.exercicejpa;

import fr.dawan.exercicejpa.entities.pizzeria.Ingredient;
import fr.dawan.exercicejpa.entities.pizzeria.Pizza;
import fr.dawan.exercicejpa.enums.Base;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main01Entity {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicejpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Ingredient tomate = new Ingredient("Tomate");
        Pizza pizza = Pizza.builder().nom("Pizza 4 fromages").prix(13.0).base(Base.ROSE).build();
        Pizza pizza2 = Pizza.builder().nom("Pizza au saumon").prix(16.0).base(Base.BLANCHE).build();
        try {
            tx.begin();
            em.persist(tomate);
            em.persist(pizza);
            em.persist(pizza2);
            pizza.setPrix(pizza.getPrix() * 1.1);
            pizza2.setPrix(pizza2.getPrix() * 1.1);
            em.remove(tomate);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        em.close();
        emf.close();
    }

}
