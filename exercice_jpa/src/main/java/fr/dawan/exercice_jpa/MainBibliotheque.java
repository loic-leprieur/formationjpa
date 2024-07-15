package fr.dawan.exercice_jpa;

import fr.dawan.exercice_jpa.criteria.LivreDaoCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainBibliotheque {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//
//            LivreDao dao = new LivreDao(em);
//
//            dao.findByAnnee(1977).forEach(System.out::println);
//            dao.findByAnnees(2004, 1982, 1969).forEach(System.out::println);
//            dao.findByIntervalAnnee(1990, 2000).forEach(System.out::println);
//            dao.findStartWith("Harry Potter").forEach(System.out::println);
//
//            System.out.println("Il y a [" + dao.count() + "] livres dans la bibliothèque");
//
//            System.out.println(dao.countByAnnee(1990) + " livre(s) est/sont sortie(s) en 1990");
//
//            Categorie catSF = em.find(Categorie.class, 7L);
//            dao.findByCategorie(catSF).forEach(System.out::println);
//
//            Auteur lovecraft = em.find(Auteur.class, 20L);
//            dao.findByAuteur(lovecraft).forEach(System.out::println);
//
//            dao.findMultiAuteur().forEach(System.out::println);
//
//            dao.getStatLivreByAuteur().forEach(System.out::println);
//
//            dao.getStatLivreByCategorie().forEach(System.out::println);
//
//            System.out.println(dao.getMaxLivreAnnee());
//
//            dao.findByAvgAnnee(catSF).forEach(System.out::println);
//
//            dao.findBySameAnnee(catSF).forEach(System.out::println);
//
//            AuteurDao aDao = new AuteurDao(em);
//
//            aDao.findAlive().forEach(System.out::println);
//
//            aDao.findByNoBook().forEach(System.out::println);
//
//            Nation nation = em.find(Nation.class, 1L);
//            aDao.findByNation(nation);
//
//            aDao.getTop5Auteur().forEach(System.out::println);
//            aDao.getTop10AgeAuteur().forEach(System.out::println);
//
//            aDao.findByCategorie(catSF).forEach(System.out::println);
            
            LivreDaoCriteria daoCrit = new LivreDaoCriteria(em);
            
            daoCrit.findByIntervalAnnnee(1970, 2000).forEach(System.out::println);
            
            System.out.println(daoCrit.countByAnneeSortie(1992));
            
            daoCrit.getTop10AncienLivre().forEach(System.out::println);

            

            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }

}
