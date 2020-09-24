package dto;

import entities.Person;
import java.util.Date;

public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    
    private String street;
    private int zip;
    private String city;

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public PersonDTO(String fName, String lName, String phone) {
        this.firstName = fName;
        this.lastName = lName;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

}
