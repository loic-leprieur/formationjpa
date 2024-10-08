package fr.dawan.requetejpa;

import fr.dawan.requetejpa.entities.Article;
import fr.dawan.requetejpa.entities.Marque;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;

public class Main02EntityGraph {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("requetejpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        Marque mB = null; 

        try {
            tx.begin();
            
//            Logo l = null;
//            Fournisseur f = null;
//            
//            for(int i = 1; i < 5;i++) {
//                
//
//                l = em.find(Logo.class, i);
//                f = em.find(Fournisseur.class, i);
//                
//                Query query = em.createQuery("UPDATE Fournisseur f SET f.logo = :logo WHERE f = :fournisseur");
//                query.setParameter("logo", l);
//                query.setParameter("fournisseur", f);
//                query.executeUpdate();
//            }
            
            Marque mA = em.find(Marque.class, 1L);
            
            System.out.println("--------------");
            var it = mA.getArticles().iterator();
            
            while(it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println("--------------");
            
            em.clear();
            
            mB = em.createQuery("FROM Marque m JOIN FETCH m.articles WHERE m.id = 2", Marque.class).getSingleResult();
            
            System.out.println("--------------");
            
            // Entity graph
            
//            EntityGraph<?> graph  = em.createEntityGraph("marqueGraph");
//            TypedQuery<Marque> marqueQuery = em.createQuery("SELECT m FROM Marque m WHERE m.id = :id", Marque.class)
//                    .setParameter("id", 2L)
//                    .setHint("jakarta.persistence.loadgraph", graph); // Les attributs par défaut @OneToOne ou @ManyToOne reste chargé en LAZY (ne change pas la stratégie de chargement)
//                    .setHint("jakarta.persistence.fetchgraph", graph); // Tous les attributs non définis dans le graph sont chargés en LAZY
//            mB = marqueQuery.getSingleResult();
            
            // Entity graph déclaré explicitement
            EntityGraph<Marque> graphDyn = em.createEntityGraph(Marque.class);
            graphDyn.addAttributeNodes("articles");
            Subgraph<Article> sub = graphDyn.addSubgraph("articles");
            sub.addAttributeNodes("fournisseurs");
            sub.addSubgraph("fournisseurs").addAttributeNodes("logo");
            TypedQuery<Marque> marqueQuery = em.createQuery("SELECT m FROM Marque m WHERE m.id = :id", Marque.class)
                  .setParameter("id", 2L)
                  .setHint("jakarta.persistence.fetchgraph", graphDyn);
            
            mB = marqueQuery.getSingleResult();
            

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        
//        var it2 = mB.getArticles().iterator();
//        while(it2.hasNext()) {
//            System.out.println(it2.next());
//        }
        
        System.out.println(mB.getNom());
        System.out.println(mB.getDateCreation());
        
        var it = mB.getArticles().iterator();
        Article af = it.next();
        System.out.println(af);
        System.out.println(af.getFournisseurs());
        
        emf.close();
    }
}
