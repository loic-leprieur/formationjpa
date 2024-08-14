package fr.dawan.exercicejpa;

import java.util.List;

import fr.dawan.exercicejpa.dao.AuteurDao;
import fr.dawan.exercicejpa.dao.CategorieDao;
import fr.dawan.exercicejpa.dao.LivreDao;
import fr.dawan.exercicejpa.dao.NationDao;
import fr.dawan.exercicejpa.entities.bibliotheque.Auteur;
import fr.dawan.exercicejpa.entities.bibliotheque.Categorie;
import fr.dawan.exercicejpa.entities.bibliotheque.Nation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main03JPQL {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicejpa");
        EntityManager em = emf.createEntityManager();

        LivreDao livreDao = new LivreDao(em);
        CategorieDao categorieDao = new CategorieDao(em);
        AuteurDao auteurDao = new AuteurDao(em);

        // Tous les livres sortie en 1992
        livreDao.findByAnnee(1992).forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Tous les livres sortie entre 1970 et 1980
        livreDao.findByIntervalAnnee(1970, 1980).forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Tous les livres qui sont sorti en 1992, 1954 et en 1982
        livreDao.findByAnnees(1964, 1992, 1982).forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Le nombre de livre
        System.out.println("Le nombre de livre = " + livreDao.count());
        System.out.println("----------------------------------------");
        
        // Tous les livres qui commencent par du
        livreDao.findStartWith("du").forEach(System.out::println);
        System.out.println("----------------------------------------");
        
        // Le nombre de livre sortie en 1974
        System.out.println(livreDao.countByAnneeSortie(1974));
        System.out.println("----------------------------------------");
       
        // Tous les livres qui ont pour catégorie drame
        List<Categorie> c = categorieDao.findByNom("Drame");
        if (c.size() == 1) {
            livreDao.findByCategorie(c.get(0)).forEach(System.out::println);
        } else {
            System.err.println("La categorie n'existe pas");
        }
        System.out.println("----------------------------------------");
        
        // Tous les livres de l'auteur qui a pour id=1
        Auteur a = auteurDao.findById(1L);
        livreDao.findByAuteur(a).forEach(System.out::println);
        System.out.println("----------------------------------------");
        
        // Tous les livres qui ont plusieurs auteurs
        livreDao.findMultiAuteur().forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Le nombre de livres écrit pour chaque auteur (prénom et nom)
        livreDao.getStatLivreByAuteur().forEach(System.out::println);
        System.out.println("----------------------------------------");

        // Le nombre de livres pour chaque nom de catégorie dans l'ordre décroissant
        livreDao.getStatLivreByCategorie().forEach(System.out::println);
        System.out.println("----------------------------------------");
        
        // L'année où est sortie le plus de livre
        System.out.println(livreDao.getMaxLivreAnnee());
        System.out.println("----------------------------------------");
       
        Categorie ca = categorieDao.findById(2L);
        // Les livres qui correspondent à l'année de sortie moyenne de la catégorie id=2
        livreDao.findByAvgAnnee(ca).forEach(System.out::println);
        System.out.println("----------------------------------------");
        
        // Les livres qui sont sortie les même années que les livres de la catégorie id=2
        livreDao.findBySameAnnee(ca).forEach(System.out::println);
        System.out.println("----------------------------------------");
        
     // Tous les auteurs vivant
        auteurDao.findAlive().forEach(System.out::println);

        // Tous les auteurs qui n'ont pas écrit de livre
        auteurDao.findByNoBook().forEach(System.out::println);

        NationDao nationDao = new NationDao(em);
        Nation nation = nationDao.findById(1L);
        
        // Les auteurs de la nation id=1
        auteurDao.findByNation(nation).forEach(System.out::println);

        // Le top 5 des auteurs qui ont écrit le plus de livre
        auteurDao.getTop5Auteur().forEach(System.out::println);
        
        // Le top 10 des auteurs les plus anciens
        auteurDao.getTop10AgeAuteur().forEach(System.out::println);
//

        Categorie cat7=categorieDao.findById(2L);
        auteurDao.findByCategorie(cat7).forEach(System.out::println);


        em.close();
        emf.close();
    }

}
