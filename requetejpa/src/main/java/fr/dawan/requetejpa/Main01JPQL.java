package fr.dawan.requetejpa;

import java.util.List;

import fr.dawan.requetejpa.entities.Article;
import fr.dawan.requetejpa.entities.ArticleResultat;
import fr.dawan.requetejpa.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

public class Main01JPQL {

    public static void main(String[] args) {
        // Création EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("requetejpa");
        // Création EntityManager
        EntityManager em = emf.createEntityManager();
        // Création de la transaction
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Query non typée
            Query query1 = em.createQuery("SELECT a FROM Article as a");
            List<Article> ar = query1.getResultList();
            ar.forEach(System.out::println);

            // Query Typée
            TypedQuery<Article> query2 = em.createQuery("FROM Article as a", Article.class);
//            TypedQuery<Article> query2 = em.createQuery("SELECT a FROM Article as a", Article.class);
            query2.getResultList().forEach(System.out::println);

            // Query select avec 1 champ de type String
            TypedQuery<String> query3 = em.createQuery("SELECT a.description FROM Article as a", String.class);
            query3.getResultList().forEach(System.out::println);

            // Select avec plusieurs champs différents
            TypedQuery<Object[]> query4 = em.createQuery("SELECT a.description, a.prix FROM Article as a",
                    Object[].class);
            query4.getResultList().forEach(tabObj -> System.out.println(tabObj[0] + " " + tabObj[1]));

            // Classe intermédiaire pour typer le résultat de la query
            TypedQuery<ArticleResultat> query5 = em.createQuery(
                    "SELECT new ArticleResultat(a.description, a.prix) FROM Article as a", ArticleResultat.class);
            query5.getResultList().forEach(System.out::println);

            TypedQuery<String> query6 = em.createQuery("SELECT DISTINCT a.description FROM Article as a", String.class);
            query6.getResultList().forEach(System.out::println);

            TypedQuery<Article> query7 = em.createQuery("SELECT a FROM Article as a WHERE a.prix < 50", Article.class);
            query7.getResultList().forEach(System.out::println);

            // Paramètres avec position (démarre à 1)
            TypedQuery<Article> query8 = em.createQuery("SELECT a FROM Article as a WHERE a.prix < ?1 AND a.prix > ?2",
                    Article.class);
            query8.setParameter(1, 100);
            query8.setParameter(2, 30);
            query8.getResultList().forEach(System.out::println);

            // Paramètres nommés
            TypedQuery<Article> query9 = em.createQuery(
                    "SELECT a FROM Article as a WHERE a.prix < :prixMax AND a.prix > :prixMin", Article.class);
            query9.setParameter("prixMax", 100);
            query9.setParameter("prixMin", 30);
            query9.getResultList().forEach(System.out::println);

            // Opérateur
            // Between
            TypedQuery<Article> queryOp = em.createQuery(
                    "SELECT a FROM Article as a WHERE a.prix BETWEEN :prixMin AND :prixMax", Article.class);
            queryOp.setParameter("prixMax", 100);
            queryOp.setParameter("prixMin", 30);
            queryOp.getResultList().forEach(System.out::println);

            // IN
            queryOp = em.createQuery("SELECT a FROM Article as a WHERE a.prix IN (40, 80, 120)", Article.class);
            queryOp.getResultList().forEach(System.out::println);

            // LIKE
            queryOp = em.createQuery("SELECT a FROM Article as a WHERE a.description LIKE :modele", Article.class);
            queryOp.setParameter("modele", "M____%");
            queryOp.getResultList().forEach(System.out::println);

            // Toutes les descriptions qui contiennent la chaine "100%" avec % "escapé"
            queryOp = em.createQuery("SELECT a FROM Article as a WHERE a.description LIKE '%100@%%' ESCAPE '@'",
                    Article.class);
            queryOp.getResultList().forEach(System.out::println);

            // IS NULL
            queryOp = em.createQuery("SELECT a FROM Article as a WHERE a.marque IS NULL", Article.class);
            queryOp.getResultList().forEach(System.out::println);

            // IS EMPTY
            TypedQuery<Marque> queryOp2 = em.createQuery("SELECT m FROM Marque as m WHERE m.articles IS EMPTY",
                    Marque.class);
            queryOp2.getResultList().forEach(System.out::println);

            // Expression de chemin
            // @ManyToOne ou @OneToOne
            TypedQuery<Article> queryPath = em.createQuery(
                    "SELECT a FROM Article as a WHERE a.marque.dateCreation < {d '1960-01-01'}", Article.class);
            queryPath.getResultList().forEach(System.out::println);

            // NE PEUT PAS MARCHER sur un @ManyToOne il faut une jointure explicite
//            TypedQuery<Marque> queryPath2 = em.createQuery("SELECT a FROM Article as a WHERE a.articles.prix < 50",
//                    Marque.class);
//            queryPath2.getResultList().forEach(System.out::println);

            // Fonctions d'agrégations
            // COUNT

            TypedQuery<Long> queryLong = em.createQuery("SELECT COUNT(a) FROM Article as a WHERE a.prix > 50",
                    Long.class);
            queryLong.getResultList().forEach(System.out::println);

            TypedQuery<Double> queryDouble = em.createQuery("SELECT ROUND(AVG(a.prix), 2) FROM Article as a",
                    Double.class);
            queryDouble.getResultList().forEach(System.out::println);

            // SIZE
            TypedQuery<Integer> queryInt = em.createQuery("SELECT SIZE(m.articles) FROM Marque as m where m.id = 1",
                    Integer.class);
            System.out.println(queryInt.getSingleResult());

            // Fonctions chaine de caractère
            TypedQuery<String> queryString = em.createQuery(
                    "SELECT CONCAT(a.description, ' : ' , LENGTH(a.description)) FROM Article a", String.class);
            queryString.getResultList().forEach(System.out::println);

            queryString = em.createQuery("SELECT UPPER(SUBSTRING(a.description, 1, 4)) FROM Article a", String.class);
            queryString.getResultList().forEach(System.out::println);

            queryInt = em.createQuery("SELECT LOCATE('Tv', a.description) FROM Article as a", Integer.class);
            queryInt.getResultList().forEach(System.out::println);

            // Fonctions temporelles

            queryInt = em.createQuery("SELECT EXTRACT(YEAR FROM m.dateCreation) FROM Marque as m", Integer.class);
            queryInt.getResultList().forEach(System.out::println);

            queryInt = em.createQuery(
                    "SELECT EXTRACT(YEAR FROM LOCAL_DATE()) - EXTRACT(YEAR FROM m.dateCreation) FROM Marque as m",
                    Integer.class);
            queryInt.getResultList().forEach(System.out::println);

            // Fonctions de tri
            TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a ORDER BY a.description, a.prix DESC",
                    Article.class);
            query.getResultList().forEach(System.out::println);

            // GROUP BY
            queryString = em.createQuery(
                    "SELECT CONCAT(a.marque.nom, ' : ', COUNT(a.id)) FROM Article a GROUP BY a.marque", String.class);
            queryString.getResultList().forEach(System.out::println);

            // HAVING
            queryString = em.createQuery(
                    "SELECT CONCAT(a.marque.nom, ' : ', COUNT(a.id)) FROM Article a GROUP BY a.marque HAVING COUNT(a.id) > 3",
                    String.class);
            queryString.getResultList().forEach(System.out::println);

            // Jointure interne
            queryString = em.createQuery(
                    "SELECT CONCAT(a.description, ' ', a.prix, ' ', m.nom) FROM Marque m JOIN m.articles a",
                    String.class);
            queryString.getResultList().forEach(System.out::println);

            queryString = em.createQuery(
                    "SELECT CONCAT(a.description, ' ', a.prix, ' ', a.marque.nom) FROM Article a JOIN a.fournisseurs f WHERE f.nom = 'Fournisseur 1'",
                    String.class);
            queryString.getResultList().forEach(System.out::println);

            // Jointure externe
            queryString = em.createQuery(
                    "SELECT CONCAT(a.description, ' ', a.prix) FROM Article a LEFT JOIN a.marque m WHERE m IS NULL",
                    String.class);
            queryString.getResultList().forEach(System.out::println);

            // Sous-requêtes
            query = em.createQuery("SELECT a FROM Article a WHERE a.prix < (SELECT AVG(a2.prix) FROM Article as a2)",
                    Article.class);
            query.getResultList().forEach(System.out::println);

            // Exist
            query = em.createQuery(
                    "SELECT a FROM Article a WHERE NOT EXISTS (SELECT m FROM Marque m JOIN m.articles ar WHERE ar = a)",
                    Article.class);
            query.getResultList().forEach(System.out::println);

            // Any
            query = em.createQuery(
                    "SELECT a FROM Article a WHERE a.prix <= ANY (SELECT a.prix FROM Article a1 WHERE a1.marque.nom = :marqueNom)",
                    Article.class);
            query.setParameter("marqueNom", "Marque A");
            query.getResultList().forEach(System.out::println);

            // ALL
            query = em.createQuery(
                    "SELECT a FROM Article a WHERE a.prix <= ALL (SELECT a.prix FROM Article a1 WHERE a1.marque.nom = :marqueNom)",
                    Article.class);
            query.setParameter("marqueNom", "Marque A");
            query.getResultList().forEach(System.out::println);

            // SOME
            query = em.createQuery(
                    "SELECT a FROM Article a WHERE a.prix <= SOME (SELECT a.prix FROM Article a1 WHERE a1.marque.nom = :marqueNom)",
                    Article.class);
            query.setParameter("marqueNom", "Marque A");
            query.getResultList().forEach(System.out::println);

            // Requêtes nommées

            TypedQuery<Article> queryNamed = em.createNamedQuery("Article.prixLess", Article.class);
            queryNamed.setParameter(1, 25);
            queryNamed.getResultList().forEach(System.out::println);

            queryNamed = em.createNamedQuery("Article.descriptionPrefix", Article.class);
            queryNamed.setParameter("modele", "Câble");
            queryNamed.getResultList().forEach(System.out::println);

            query = em.createQuery("SELECT a FROM Article a ORDER BY a.prix DESC", Article.class);
            query.setMaxResults(5);
            query.getResultList().forEach(System.out::println);

            // Pagination

            long nbArticle = em.createQuery("SELECT COUNT(a) FROM Article a", Long.class).getSingleResult();

            for (int i = 0; i < nbArticle; i += 5) {
                em.createQuery("SELECT a FROM Article a ORDER BY a.prix DESC", Article.class).setFirstResult(i)
                        .setMaxResults(i + 5).getResultList().forEach(System.out::println);
            }

            // Update

            Query queryUpdate = em.createQuery(
                    "UPDATE Article a SET a.prix = a.prix * :pourcentAugmentation WHERE a.prix BETWEEN :prixMin AND :prixMax");

            queryUpdate.setParameter("pourcentAugmentation", 1.1);
            queryUpdate.setParameter("prixMin", 35);
            queryUpdate.setParameter("prixMax", 200);
            int nbMajPrix = queryUpdate.executeUpdate();
            System.out.println(nbMajPrix);

            // Delete

            Query queryDelete = em.createQuery("DELETE Article a WHERE a.marque IS NULL");
            int nbSuppr = queryDelete.executeUpdate();
            System.out.println(nbSuppr);

            // Procédures stockées

            StoredProcedureQuery procedureQuery = em.createStoredProcedureQuery("addition");
            procedureQuery.registerStoredProcedureParameter("a", Integer.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter("b", Integer.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter("somme", Integer.class, ParameterMode.OUT);

            procedureQuery.setParameter("a", 2);
            procedureQuery.setParameter("b", 3);
            procedureQuery.execute();

            int somme = (Integer) procedureQuery.getOutputParameterValue("somme");
            System.out.println(somme);

            procedureQuery = em.createNamedStoredProcedureQuery("Article.nbArticle");
            procedureQuery.setParameter("id_marque", 3L);
            procedureQuery.execute();

            int nb_Article = (Integer) procedureQuery.getOutputParameterValue("nb_article");
            System.out.println(nb_Article);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
