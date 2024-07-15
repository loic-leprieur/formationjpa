package fr.dawan.exercice_jpa.dao;

import java.util.List;

import fr.dawan.exercice_jpa.entities.BaseEntity;

public interface DaoInterface<T extends BaseEntity> {

    public void saveOrUpdate(T entity);
    
    public void delete(T entity);
    
    public void deleteById(long id);
    
    public T findById(long id);
    
    public List<T> findAll();
    
    public long count();
    
    
}
