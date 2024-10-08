package fr.dawan.requetejpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import fr.dawan.requetejpa.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main05Envers {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("requetejpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

//        Marque me = new Marque("Marque E", LocalDate.now());
        
        try {
            tx.begin();
            
            Marque am = em.find(Marque.class, 1L);
            am.setDateCreation(LocalDate.now().minusDays(5));
            
//            me.setDateCreation(LocalDate.of(1900, 1, 1));
            
            LocalDateTime created = LocalDateTime.now();
            
            AuditReader auditReader = AuditReaderFactory.get(em);
            List<Number> rList = auditReader.getRevisions(Marque.class, am.getId());
            
            for(Number r : rList) {
                Marque m = auditReader.find(Marque.class, am.getId(), r);
                System.out.println(r + " " + created + " " + m);
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
