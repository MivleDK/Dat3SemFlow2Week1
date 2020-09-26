package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        Address a1 = new Address("Grækergade 2", "2000", "Olympus");
        Address a2 = new Address("Strandgade", "1000", "Algebraby");
        Address a3 = new Address("Styxvej 13", "0000", "Underby");

        Person p1 = new Person("Hercules", "Gudesøn", "87654321");
        Person p2 = new Person("Archimedes", "Svendsen", "12345678");
        Person p3 = new Person("Hades", "Flammegreel", "13467964");

        p1.setAddress(a1);
        p2.setAddress(a2);
        p3.setAddress(a3);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM Address").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getPersonCount(), "Expects three rows in the database");
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("hans", "testesen", "12345678");
        assertThat(person.getFirstName(), equalTo("hans"));
        assertThat(person.getLastName(), equalTo("testesen"));
        assertThat(person.getPhone(), equalTo("12345678"));
    }

    @Test
    public void testGetPersonById() throws PersonNotFoundException {
        Person p1 = new Person("Hercules", "Testmand", "12345678");
        assertEquals("Testmand", p1.getLastName());
    }

    @Test
    public void testGetPersonStreetDTO() {
        PersonDTO p1 = new PersonDTO("Hercules", "Testmand", "12345678", "testgade 2", "3000", "testCity");
        assertEquals("testgade 2", p1.getStreet());
    }
}
