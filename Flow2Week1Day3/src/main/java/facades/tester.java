package facades;

import entities.Address;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Hercules", "Gudesøn", "87654321");
        Person p2 = new Person("Archimedes", "Svendsen", "12345678");
        Person p3 = new Person("Hades", "Flammegreel", "13467964");

        Address a1 = new Address("Grækergade 2", "2000", "Olympus");
        Address a2 = new Address("Strandgade", "1000", "Algebraby");
        Address a3 = new Address("Styxvej 13", "0000", "Underby");

        p1.setAddress(a1);
        p2.setAddress(a2);
        p3.setAddress(a3);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();

    }

}
