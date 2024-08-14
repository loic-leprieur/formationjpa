package fr.dawan.exercicejpa.criteria;

import java.util.List;

import fr.dawan.exercicejpa.entities.bibliotheque.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractDao<T extends BaseEntity> {

    protected EntityManager em;

    private Class<T> classEntity;
    
    public void saveOrUpdate(T entity) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (entity.getId() == 0) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public void delete(T entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public void deleteById(long id) {
        delete(findById(id));
    }

    public T findById(long id) {
        return em.find(classEntity, id);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(classEntity);
        Root<T> root = query.from(classEntity);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
    
    public long count() {
        return em.createQuery("SELECT COUNT(e) FROM " + classEntity.getName() + " e",Long.class).getSingleResult();
    }
    

  
    
}
