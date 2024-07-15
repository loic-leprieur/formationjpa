package fr.dawan.exercice_jpa.criteria;

import java.util.List;

import fr.dawan.exercice_jpa.entities.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class LivreDaoCriteria extends AbstractDaoCriteria<Livre> {

    
    public LivreDaoCriteria(EntityManager em) {
        super(em);
    }

    public List<Livre> findByIntervalAnnnee(int anneeMin, int anneeMax) {
        CriteriaQuery<Livre> cq = cb.createQuery(Livre.class);
                
        Root<Livre> root = cq.from(Livre.class);
        
        cq.select(root)
            .where(cb.between(root.get("anneeSortie"), anneeMin, anneeMax))
            .orderBy(cb.asc(root.get("anneeSortie")));
        
        return createTypedQuery(cq).getResultList();

    }
    
    public long countByAnneeSortie(int anneeSortie) {
        
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        
        Root<Livre> root = cq.from(Livre.class);
        
        cq.select(cb.count(root))
            .where(cb.equal(root.get("anneeSortie"), anneeSortie));
        
        return createLongQuery(cq).getSingleResult();
    }
    
    public List<Livre> getTop10AncienLivre() {
        
        CriteriaQuery<Livre> cq = cb.createQuery(Livre.class);
        
        Root<Livre> root = cq.from(Livre.class);
        
        cq.select(root).orderBy(cb.asc(root.get("anneeSortie")));
        
        return createTypedQuery(cq).setMaxResults(10).getResultList();
    }
    

    public List<Livre> findDistinct() {
        CriteriaQuery<Livre> cq = cb.createQuery(Livre.class);
                
        Root<Livre> root = cq.from(Livre.class);
        
        cq.select(root)
            .groupBy(root.get("anneeSortie"))
            .orderBy(cb.asc(root.get("anneeSortie")));
        
        return createTypedQuery(cq).getResultList();

    }
    
}
