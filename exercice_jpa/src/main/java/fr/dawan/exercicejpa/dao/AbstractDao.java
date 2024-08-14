package fr.dawan.exercicejpa.dao;

import java.util.List;

import fr.dawan.exercicejpa.entities.bibliotheque.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
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
        TypedQuery<T> query = em.createQuery("SELECT e FROM " + classEntity.getName() + " e", classEntity);
        return query.getResultList();
    }
    
    public long count() {
        return em.createQuery("SELECT COUNT(e) FROM " + classEntity.getName() + " e",Long.class).getSingleResult();
    }
    
    protected TypedQuery<T> createQuery(String requete){
        return em.createQuery(requete, classEntity);
    }
    
    protected <U> TypedQuery<U> createQuery(String requete,Class<U> clazz){
        return em.createQuery(requete, clazz);
    }
    
    protected TypedQuery<T> createNamedQuery(String requete){
        return em.createNamedQuery(requete, classEntity);
    }
    
    protected <U> TypedQuery<U> createNamedQuery(String requete,Class<U> clazz){
        return em.createNamedQuery(requete, clazz);
    }
    
}
