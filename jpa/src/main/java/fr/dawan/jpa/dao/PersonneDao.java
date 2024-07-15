package fr.dawan.jpa.dao;

import fr.dawan.jpa.entities.heritage.Personne;
import jakarta.persistence.EntityManager;

public class PersonneDao extends AbstractDao<Personne> {

    public PersonneDao(EntityManager em) {
        super(em, Personne.class);
    }

}
