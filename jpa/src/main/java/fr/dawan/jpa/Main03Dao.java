package fr.dawan.jpa;

import java.time.LocalDate;

import fr.dawan.jpa.dao.PersonneDao;
import fr.dawan.jpa.entities.heritage.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main03Dao {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("formationjpa");
        EntityManager em = emf.createEntityManager();
        PersonneDao dao = new PersonneDao(em);

        Personne per1 = new Personne("John", "Doe", LocalDate.of(1990, 10, 10));
        dao.saveOrUpdate(new Personne("Jane", "Doe", LocalDate.of(2003, 10, 10)));
        dao.saveOrUpdate(new Personne("Jo", "Dalton", LocalDate.of(1991, 1, 10)));
        dao.saveOrUpdate(per1);
        dao.findAll().forEach(System.out::println);
        Personne per1a = dao.findById(per1.getId());
        System.out.println(per1a.toString());
        per1a.setDateNaissance(LocalDate.of(1990, 10, 11));
        dao.saveOrUpdate(per1a);
        System.out.println(per1a.toString());
        dao.delete(per1a);
        dao.findAll().forEach(System.out::println);

        em.close();
        emf.close();
    }

}
