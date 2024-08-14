package fr.dawan.exercicejpa.dao;

import fr.dawan.exercicejpa.entities.bibliotheque.Nation;
import jakarta.persistence.EntityManager;

public class NationDao extends AbstractDao<Nation> {

    public NationDao(EntityManager em) {
        super(em, Nation.class);
        // TODO Auto-generated constructor stub
    }

}
