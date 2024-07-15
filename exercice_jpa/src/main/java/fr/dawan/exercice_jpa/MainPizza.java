package fr.dawan.exercice_jpa;

import java.time.LocalDateTime;

import fr.dawan.exercice_jpa.entities.Client;
import fr.dawan.exercice_jpa.entities.Commande;
import fr.dawan.exercice_jpa.entities.CommandePizza;
import fr.dawan.exercice_jpa.entities.Ingredient;
import fr.dawan.exercice_jpa.entities.Livreur;
import fr.dawan.exercice_jpa.entities.Pizza;
import fr.dawan.exercice_jpa.enums.Base;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Création de commandes de pizzas pour un restaurant
 *
 */
public class MainPizza {
    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        Ingredient lardons = new Ingredient("Lardons");
        Ingredient oignons = new Ingredient("Oignons");
        Ingredient parmesan = new Ingredient("Parmesan");

//        CommandePizza cmdPizza = new CommandePizza();

        Pizza margarita = Pizza.builder().nom("Pizza Margarita").prix(12).ingredient(parmesan).build();
        Pizza flam = Pizza.builder().base(Base.BLANCHE).nom("Tarte flambée flammekueche").prix(13.5).ingredient(oignons)
                .ingredient(lardons).build();

        lardons.addPizza(flam);
        oignons.addPizza(margarita);
        parmesan.addPizza(margarita);

        parmesan.getPizzas().add(margarita);

        LocalDateTime heureCommande = LocalDateTime.of(2024, 7, 10, 19, 30);
        LocalDateTime heureLivraison = LocalDateTime.of(2024, 7, 10, 20, 00);

        Commande commande1 = new Commande(heureCommande, heureLivraison);

//        cmdPizza.setCommande(commande1);
//        cmdPizza.setPizza(flam);
//        cmdPizza.setQuantite(5);

        CommandePizza cmdPizza = commande1.addPizza(flam, 6);

        Livreur livreur1 = new Livreur("John Doe", "0607080910");

        Client client = new Client("Jean-Luc M.", "1 place de la république, 75000 Paris");

        livreur1.addCommande(commande1);
        client.addCommande(commande1);

        try {
            tx.begin();

            em.persist(margarita);
            em.persist(flam);

            em.persist(lardons);
            em.persist(parmesan);
            em.persist(oignons);

            em.persist(commande1);

            em.persist(livreur1);

            em.persist(client);

            em.persist(cmdPizza);

            margarita.setPrix(margarita.getPrix() * 1.1);
            flam.setPrix(flam.getPrix() * 1.1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
