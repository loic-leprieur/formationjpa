package fr.dawan.exercicejpa.criteria;

import java.util.List;

import fr.dawan.exercicejpa.dao.AbstractDao;
import fr.dawan.exercicejpa.entities.bibliotheque.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class LivreDao extends AbstractDao<Livre> {

    public LivreDao(EntityManager em) {
        super(em, Livre.class);
    }

    public List<Livre> findByIntervalAnnee(int anneeMin, int anneeMax) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Livre> query = cb.createQuery(Livre.class);
        Root<Livre> root = query.from(Livre.class);
        query.select(root).where(cb.between(root.get("anneeSortie"), anneeMin, anneeMax));
        return em.createQuery(query).getResultList();
    }

    public long countByAnneeSortie(int anneeSortie) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Livre> root = query.from(Livre.class);
        query.select(cb.count(root)).where(cb.equal(root.get("anneeSortie"), anneeSortie));
        return em.createQuery(query).getSingleResult();
    }

    public List<Livre> getTop10AncienLivre() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Livre> query = cb.createQuery(Livre.class);
        Root<Livre> root = query.from(Livre.class);
        query.select(root).orderBy(cb.asc(root.get("anneeSortie")));
        return em.createQuery(query).setMaxResults(10).getResultList();
    }
    
    public List<Livre> findDictinct() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Livre> query = cb.createQuery(Livre.class);
        Root<Livre> root = query.from(Livre.class);
        query.select(root).distinct(true);
        return em.createQuery(query).getResultList();
    }  

}
