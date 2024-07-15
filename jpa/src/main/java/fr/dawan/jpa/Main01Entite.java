package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.entities.Adresse;
import fr.dawan.jpa.entities.Employe;
import fr.dawan.jpa.enums.TypeContrat;
import fr.dawan.jpa.enums.TypeTelephone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main01Entite {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        Adresse addrPerso = new Adresse("rue Henri Loux", "Strasbourg", "67200");
        Adresse addrPro = new Adresse("av de l'Europe", "Entzheim", "67960");

        Employe emp1 = Employe.builder().prenom("John").nom("Doe").email("john.doe@gmail.com").contrat(TypeContrat.CDI)
                .dateNaissance(LocalDate.of(1996, 5, 17)).adressePerso(addrPerso).adressePro(addrPro).salaire(2000)
                .medicament("Ibuprofen").medicament("Autre médicament").build();

        Employe emp2 = Employe.builder().prenom("Jane").nom("Doe").email("jane.doe@gmail.com").contrat(TypeContrat.CDD)
                .dateNaissance(LocalDate.of(2000, 11, 2)).adressePerso(addrPerso).adressePro(addrPro)
                .telephone(TypeTelephone.FIXE_PRO, "03 04 05 06 07")
                .telephone(TypeTelephone.PORTABLE_PRO, "06 07 08 09 10").build();

        try {
            tx.begin();

            // Sauvegarde dans la base
            em.persist(emp1);
            em.persist(emp2);
            emp1.setSalaire(1450);

//            emp1 = em.find(Employe.class, 1000L, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
//            em.detach(emp1);
//            emp1.setSalaire(1600);
//            emp1.setDateNaissance(LocalDate.of(1995, 6, 14));
//            emp1.setEmail("j.doe@gmail.com");
//            em.merge(emp1);

            // em.find(Employe.class, 1011L);
//            em.detach(emp1);

//            emp1.setDateNaissance(LocalDate.of(1998, 3, 6));

//            em.merge(emp1);

            System.out.println(emp1);
            emp1.setEmail("j@dawan.com");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
