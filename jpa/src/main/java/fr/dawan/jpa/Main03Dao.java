package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.dao.PersonneDao;
import fr.dawan.jpa.entities.heritage.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.java.Log;

@Log
public class Main03Dao {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();

        PersonneDao dao = new PersonneDao(em);

        Personne per1 = new Personne("John", "Doe", LocalDate.of(1999, 12, 31));
        dao.saveOrUpdate(new Personne("Jane", "Doe", LocalDate.of(2003, 3, 9)));
        dao.saveOrUpdate(new Personne("Jo", "Dalton", LocalDate.of(2007, 11, 4)));
        dao.saveOrUpdate(per1);
        dao.findAll().forEach(p -> log.info(p.toString()));

        Personne per1a = dao.findById(per1.getId());

        log.info(per1a.toString());

        per1a.setDateNaissance(LocalDate.of(2000, 1, 1));
        dao.saveOrUpdate(per1a);
        log.info(per1.toString());
        dao.delete(per1a);

        dao.findAll().forEach(p -> log.info(p.toString()));

        em.close();
        emf.close();
    }
}
