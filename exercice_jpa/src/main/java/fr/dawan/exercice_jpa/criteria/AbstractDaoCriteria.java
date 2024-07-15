package fr.dawan.exercice_jpa.criteria;

import java.util.List;

import fr.dawan.exercice_jpa.dao.DaoInterface;
import fr.dawan.exercice_jpa.entities.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

public abstract class AbstractDaoCriteria<T extends BaseEntity> implements DaoInterface<T> {
    
    protected EntityManager em;
    
    protected CriteriaBuilder cb;
    
    protected Class<T> classEntity;
    
    public AbstractDaoCriteria(EntityManager em) {
        this.em = em;
        cb = em.getCriteriaBuilder();
    }
    
    
    public TypedQuery<T> createTypedQuery(CriteriaQuery<T> cq) {
       return em.createQuery(cq);
    }
    
    public TypedQuery<Long> createLongQuery(CriteriaQuery<Long> cq) {
        return em.createQuery(cq);
     }
    
    public void saveOrUpdate(T entity) {
        CriteriaUpdate<T> cq = cb.createCriteriaUpdate(classEntity);
        Root<T> ar = cq.from(classEntity);
        
        

    }

    @Override
    public void delete(BaseEntity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T findById(long id) {
        
        CriteriaQuery<T> cq = cb.createQuery(classEntity);
        Root<T> ar = cq.from(classEntity);
        
        cq.select(ar).where(cb.equal(ar.get("id"), id));
        
        return createTypedQuery(cq).getSingleResult();
    }

    @Override
    public List<T> findAll() {
        
        CriteriaQuery<T> cq = cb.createQuery(classEntity);
        Root<T> ar = cq.from(classEntity);
        
        cq.select(ar);
        
        return createTypedQuery(cq).getResultList();
        
    }

    @Override
    public long count() {
        
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> ar = cq.from(classEntity);
        
        cq.select(cb.count(ar));
        
        return createLongQuery(cq).getSingleResult();
    }
    
}
