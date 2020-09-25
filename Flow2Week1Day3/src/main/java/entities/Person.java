package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date lastEdited;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setPerson(this);
        }
    }

    public Person() {
    }

//    public Person(String firstName, String lastName, String phone, String street, String zip, String city) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.created = new Date();
//        this.lastEdited = new Date();
//        this.address = new Address(street, zip, city);
//    }
//
//    public Person(String firstName, String lastName, String phone, Address a) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.created = new Date();
//        this.lastEdited = new Date();
//        this.address = new Address(a.getStreet(), a.getZip(), a.getZip());
//    }

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = new Date();
        this.lastEdited = new Date();
    }

    public Address getAddress() {
        return address;
    }

    public String getStreet() {
        return this.address.getStreet();
    }

    public String getZip() {
        return this.address.getZip();
    }

    public String getCity() {
        return this.address.getCity();
    }

    public void setLastEdited() {
        this.lastEdited = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

}
