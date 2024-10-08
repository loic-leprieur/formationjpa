package fr.dawan.jpa.dao;

import java.util.List;

import fr.dawan.jpa.entities.heritage.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class AbstractDao<T extends BaseEntity> {

    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

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
}
