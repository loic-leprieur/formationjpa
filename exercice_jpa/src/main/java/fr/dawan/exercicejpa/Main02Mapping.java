package fr.dawan.exercicejpa;

import java.time.LocalDateTime;

import fr.dawan.exercicejpa.entities.pizzeria.Commande;
import fr.dawan.exercicejpa.entities.pizzeria.CommandePizza;
import fr.dawan.exercicejpa.entities.pizzeria.Ingredient;
import fr.dawan.exercicejpa.entities.pizzeria.Pizza;
import fr.dawan.exercicejpa.enums.Base;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Main02Mapping {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicejpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        
        Commande cmd1=new Commande(LocalDateTime.of(2024,9,7,11,55),LocalDateTime.of(2024,9,7,12,30));
//        CommandePizza cmdPizza=new CommandePizza();
//        cmdPizza.setCommande(cmd1);
        Ingredient tomate=new Ingredient("Sauce tomate");
        Ingredient cm=new Ingredient("Crème fraiche");
        Ingredient saumon=new Ingredient("Saumon");
        Pizza pizza=Pizza.builder().nom("Pizza 4 fromages").prix(13.0).base(Base.ROSE)
                .ingredient(tomate).build();
        Pizza pizza2=Pizza.builder().nom("Pizza au saumon").prix(16.0).base(Base.BLANCHE)
                .ingredient(cm).ingredient(saumon).build();
        tomate.getPizzas().add(pizza);
        cm.getPizzas().add(pizza2);
        saumon.getPizzas().add(pizza2);
//        cmdPizza.setPizza(pizza);
//        cmdPizza.setQuantite(5);
        CommandePizza cmdPizza=cmd1.addPizza(pizza, 6);
        
        
        try {
            tx.begin();
            em.persist(tomate);
            em.persist(cm);
            em.persist(saumon);
            em.persist(pizza);
            em.persist(pizza2);
            em.persist(cmd1);
            em.persist(cmdPizza);
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        em.close();
        emf.close();
    }

}
