package fr.dawan.requetejpa;

import java.util.List;

import fr.dawan.requetejpa.entities.Article;
import fr.dawan.requetejpa.entities.Fournisseur;
import fr.dawan.requetejpa.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class Main04Criteria {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("requetejpa");
        EntityManager em = emf.createEntityManager();
        
        
        // EN JPQL : SELECT a FROM Article a
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Article> cq = cb.createQuery(Article.class);
        Root<Article> ar = cq.from(Article.class);
        cq.select(cq.getSelection());
        
        TypedQuery<Article> tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // EN JPQL : SELECT a FROM Article WHERE a.prix < ?1
        CriteriaQuery<Article> cq2 = cb.createQuery(Article.class);
        Root<Article> ar2 = cq2.from(Article.class);
        cq2.select(ar2)
           .where(cb.lessThan(ar2.get("prix"), 150)); // ar2.get(Article_.prix)
                   
        tq = em.createQuery(cq2);
        tq.getResultList().forEach(System.out::println);
        
        // EN JPQL : SELECT a.description FROM Article a
        CriteriaQuery<String> cq3 = cb.createQuery(String.class);
        Root<Article> ar3 = cq3.from(Article.class);
        cq3.select(ar3.get("description"));
        
        TypedQuery<String> tStr = em.createQuery(cq3);
        tStr.getResultList().forEach(System.out::println);
        
        // EN JPQL : SELECT DISTINCT a.description FROM Article a
        cq3 = cb.createQuery(String.class);
        ar3 = cq3.from(Article.class);
        cq3.select(ar3.get("description"));
        cq3.distinct(true);
        
        tStr = em.createQuery(cq3);
        tStr.getResultList().forEach(System.out::println);
        
        // Predicate
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        Predicate predicateInfPrix = cb.lessThan(ar.get("prix"), 200);
        Predicate predicateSupPrix = cb.greaterThan(ar.get("prix"), 50);
        Predicate predicatePrix = cb.and(predicateInfPrix, predicateSupPrix);
        cq.select(ar).where(predicatePrix);
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // Between        
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar).where(cb.between(ar.get("prix"), 50, 200));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // IN
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar).where(ar.get("prix").in(40, 70, 650));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // LIKE
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar).where(cb.like(ar.get("description"), "M______ %"));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // ESCAPE
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar).where(cb.like(ar.get("description"), "%100@%%", '@'));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // Order By       
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar)
          .where(cb.between(ar.get("prix"), 50, 200))
          .orderBy(cb.asc(ar.get("description")), cb.desc(ar.get("prix")));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // Count
        CriteriaQuery<Long> cqLong = cb.createQuery(Long.class);
        ar = cqLong.from(Article.class);
        cqLong.select(cb.count(ar));        
        TypedQuery<Long> tqLong = em.createQuery(cqLong);
        System.out.println(tqLong.getSingleResult());

        // Group By
        cqLong = cb.createQuery(Long.class);
        ar = cqLong.from(Article.class);
        cqLong.select(cb.count(ar))
        .groupBy(ar.get("marque"));

        tqLong = em.createQuery(cqLong);
        tqLong.getResultList().forEach(System.out::println);
        
        // MultiSelect et HAVING
        CriteriaQuery<Object[]> cqObj = cb.createQuery(Object[].class);
        ar = cqObj.from(Article.class);
        cqObj.multiselect(cb.avg(ar.get("prix")), cb.count(ar))
            .groupBy(ar.get("marque"))
            .having(cb.greaterThan(cb.count(ar), 3L));
        
        TypedQuery<Object[]> tqObj = em.createQuery(cqObj);
        tqObj.getResultList().forEach(o -> System.out.println(o[0] + " " + o[1]));
        
        // TUPLE
        CriteriaQuery<Tuple> cqt = cb.createTupleQuery();
        ar = cqt.from(Article.class);
        cqt.multiselect(ar.get("marque").get("nom").alias("nomMarque"), cb.avg(ar.get("prix")).alias("moyennePrix"), cb.count(ar).alias("nombreArticle"))
            .groupBy(ar.get("marque"));
        List<Tuple> tt = em.createQuery(cqt).getResultList();
        tt.forEach(t -> System.out.println(t.get("nomMarque") + " : " + t.get("moyennePrix") + " (" + t.get("nombreArticle") + ")"));
        
        // Expression Path
        
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        cq.select(ar)
            .where(cb.equal(ar.get("marque").get("nom"), "Marque A"));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // JOIN (par défaut : INNER)
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        Join<Article, Fournisseur> fj = ar.join("fournisseurs", JoinType.INNER);
        cq.select(ar)
        .where(cb.equal(fj.get("nom"), "Fournisseur 1"));
        
        // LEFT JOIN
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        Join<Article, Marque> fm = ar.join("marque", JoinType.LEFT);
        cq.select(ar)
        .where(cb.isNull(fm));
        
        tq = em.createQuery(cq);
        tq.getResultList().forEach(System.out::println);
        
        // Sous-requête
        // SELECT a FROM Article a  WHERE a.prix < (SELECT AVG(a2.prix) FROM Article a2)
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        Subquery<Double> sub1 = cq.subquery(Double.class);
        Root<Article> subroot1 = sub1.from(Article.class);
        sub1.select(cb.avg(subroot1.get("prix")));
        cq.select(ar)
            .where(cb.greaterThan(ar.get("prix"), sub1));
        em.createQuery(cq).getResultList().forEach(System.out::println);
        
        // SELECT a FROM Article a WHERE a.prix <= ALL (SELECT a.prix FROM Article a1 WHERE a1.marque.nom = :marqueNom)
        
        cq = cb.createQuery(Article.class);
        ar = cq.from(Article.class);
        
        Subquery<Double> sub2 = cq.subquery(Double.class);
        Root<Article> subroot2 = sub2.from(Article.class);
        
        /// Sous-requête
        sub2.select(subroot2.get("prix"))
            .where(cb.equal(subroot2.get("marque").get("nom"), "Marque B"));
        
        /// Requête principale 
        cq.select(ar).where(cb.le(ar.get("prix"), cb.all(sub2)));
//        cq.select(ar).where(cb.le(ar.get("prix"), cb.any(sub2)));
//        cq.select(ar).where(cb.le(ar.get("prix"), cb.some(sub2)));        
        
        em.createQuery(cq).getResultList().forEach(System.out::println);
        
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // UPDATE
            CriteriaUpdate<Article> cu = cb.createCriteriaUpdate(Article.class);
            Root<Article> rootA = cu.from(Article.class);
            cu.set("prix", 15)
                .where(cb.lessThan(rootA.get("prix"), 20));
            int nbArticle = em.createQuery(cu).executeUpdate();
            System.out.println(nbArticle);
            
            
            CriteriaDelete<Article> cd = cb.createCriteriaDelete(Article.class);
            rootA = cd.from(Article.class);
            cd.where(cb.isNull(rootA.get("marque")));
            nbArticle = em.createQuery(cd).executeUpdate();
            System.out.println(nbArticle);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
