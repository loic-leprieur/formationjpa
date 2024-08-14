package fr.dawan.jpa;

import java.time.LocalDate;
import java.util.Scanner;

import fr.dawan.jpa.entities.Adresse;
import fr.dawan.jpa.entities.Employe;
import fr.dawan.jpa.enums.TypeContrat;
import fr.dawan.jpa.enums.TypeTelephone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main07CycleDevie {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Adresse addrPerso = new Adresse("1, rue Esquermoise", "Lille", "59800");
        Adresse addrLille = new Adresse("46, rue des cannoniers", "Lille", "59800");
        Employe emp1 = Employe.builder().prenom("john").nom("Doe").dateNaissance(LocalDate.of(1992, 10, 3))
                .adressePerso(addrPerso).email("jdoe@dawan.com").adressePro(addrLille).contrat(TypeContrat.CDI)
                .medicament("aspirine").medicament("autre médicaments").build();
        Employe emp2 = Employe.builder().prenom("Jane").nom("Doe").dateNaissance(LocalDate.of(2000, 10, 3))
                .adressePerso(addrPerso).email("janedoe@dawan.com").salaire(1900.0).adressePro(addrLille)
                .contrat(TypeContrat.CDI).telephone(TypeTelephone.PORTABLE_PERSO, "06000000")
                .telephone(TypeTelephone.PORTABLE_PRO, "09000000").build();

        System.out.println(emp1);
        try {
            tx.begin();
            // Persister
            // em.persist(emp1);
            
            // Detacher
            // em.detach(emp1);
            // emp1.setSalaire(2002);
            
            // Rattacher
            // em.merge(emp1);
            
            // System.out.println(emp1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        // vider le context de persistance
        em.clear(); 
        emp1 = null;
        emp1 = em.find(Employe.class, 3L);
        System.out.println(emp1);

        // On attend la saisie au clavier, on modifie l'employé qui a pour id 3
        // dans la bdd 
        // On saisie une valeur pour redémmarer l'exécution du programme
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        sc.close();
        // On affiche l'employé -> les données ne sont mises à jour 
        System.out.println(emp1);
        em.refresh(emp1); // On synchronise le context de persistence avec la bbd
        // On affiche l'employé -> les données correspondent à celles de la bdd 
        System.out.println(emp1);
        em.close(); // on ferme l'entitymanager -> emp1 est détaché 
       
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        try {
            tx2.begin();
            emp1.setSalaire(2020.0);
            // avec merge emp1 est à nouveau gerer par l'entityManager
            em2.merge(emp1);
            System.out.println(emp1);
            tx2.commit();
        } catch (Exception e) {
            tx2.rollback();
            e.printStackTrace();
        }
        em2.close();
        emf.close();

    }
}
