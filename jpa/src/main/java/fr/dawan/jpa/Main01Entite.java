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
        // Création Entity Manager factory
        // createEntityManagerFactory prend en paramètre le nom de la persistence-unit définie dans persitence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");

        // Création EntityManager
        EntityManager em = emf.createEntityManager();

        // Création de la transaction
        EntityTransaction tx = em.getTransaction();
        
        Adresse addrPerso=new Adresse("av du 4septembre","Lens","62300");
        Adresse addrLille=new Adresse("46 rue des cannoniers","Lille","59800");
        Employe emp1=Employe.builder().prenom("john").nom("Doe").dateNaissance(LocalDate.of(1992, 10, 3)).adressePerso(addrPerso)
                .email("jdoe@dawan.com").adressePro(addrLille).contrat(TypeContrat.CDI).medicament("aspirine").medicament("autre médicaments").build();
        Employe emp2=Employe.builder().prenom("Jane").nom("Doe").dateNaissance(LocalDate.of(2000, 10, 3)).adressePerso(addrPerso)
                .email("janedoe@dawan.com").salaire(1900.0).adressePro(addrLille).contrat(TypeContrat.CDI).telephone(TypeTelephone.PORTABLE_PERSO,"06000000").telephone(TypeTelephone.PORTABLE_PRO,"09000000").build();
        try {
            tx.begin(); // -> démarrer la transaction
           
            em.persist(emp1);    // Peristence de l'objet emp1 dans la bdd
            em.persist(emp2);
            
            // emp1 est géré par le entité manager -> quand on modifie l'état de emp1, elle est persitée dans la bdd
            emp1.setSalaire(1450);

            // Suppression de l'objet emp2 dans la bdd
            em.remove(emp2);
            
            tx.commit(); // transaction -> valider
        } catch (Exception e) {
            tx.rollback(); // transaction -> annuler
            e.printStackTrace();
        }
        em.close(); // fermeture de l'entity manager
        emf.close(); // fermeture de l'entity manager factory
    }

}
