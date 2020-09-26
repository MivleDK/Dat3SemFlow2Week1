package dto;

import entities.Person;

public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;

    private String street;
    private String zip;
    private String city;

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public PersonDTO(String fName, String lName, String phone, String street, String zip, String city) {
        this.firstName = fName;
        this.lastName = lName;
        this.phone = phone;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
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

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

}
