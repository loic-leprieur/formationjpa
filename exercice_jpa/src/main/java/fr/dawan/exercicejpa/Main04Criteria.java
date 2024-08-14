package fr.dawan.exercicejpa;

import java.util.List;

import fr.dawan.exercicejpa.criteria.LivreDao;
import fr.dawan.exercicejpa.dao.NationDao;
import fr.dawan.exercicejpa.entities.bibliotheque.Auteur;
import fr.dawan.exercicejpa.entities.bibliotheque.Categorie;
import fr.dawan.exercicejpa.entities.bibliotheque.Nation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main04Criteria {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicejpa");
        EntityManager em = emf.createEntityManager();

        LivreDao livreDao = new LivreDao(em);

        // Tous les livres sortie entre 1970 et 1980
        livreDao.findByIntervalAnnee(1970, 1980).forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Le nombre de livre sortie en 1974
        System.out.println(livreDao.countByAnneeSortie(1992));
        System.out.println("----------------------------------------");
       
        livreDao.getTop10AncienLivre().forEach(System.out::println);

        em.close();
        emf.close();
    }
}
