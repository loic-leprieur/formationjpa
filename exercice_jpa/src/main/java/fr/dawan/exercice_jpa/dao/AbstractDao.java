package fr.dawan.exercice_jpa.dao;

import java.util.List;

import fr.dawan.exercice_jpa.entities.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class AbstractDao<T extends BaseEntity> {

    @Getter(value = AccessLevel.PROTECTED)
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
        delete(em.find(classEntity, id));
    }

    public T findById(long id) {
        return em.find(classEntity, id);
    }

    public List<T> findAll() {

        TypedQuery<T> query = em.createQuery("SELECT e FROM " + classEntity.getName() + " e", classEntity);
        return query.getResultList();
    }

    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT count(t) FROM " + classEntity.getName() + " t", Long.class);
        return query.getSingleResult();
    }

    protected TypedQuery<T> createQuery(String requete) {
        return em.createQuery(requete, classEntity);
    }

    protected <U> TypedQuery<U> createResultQuery(String requete, Class<U> clazz) {
        return em.createQuery(requete, clazz);
    }

}
