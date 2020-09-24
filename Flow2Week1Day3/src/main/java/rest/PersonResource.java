package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("persons")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Something went right\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getPersonCount();
        return "{\"count\":" + count + "}";
    }

    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAlllPersons() {
        PersonsDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }    

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(GSON.toJson(FACADE.getPerson(id))).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPerson = FACADE.addPerson(p.getFirstName(), p.getLastName(), p.getPhone());
        return GSON.toJson(newPerson);
    }
    
    @PUT
    @Path("update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") Long id, String person){
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        PersonDTO updatePerson = FACADE.editPerson(personDTO);
        return GSON.toJson(updatePerson);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id){
        PersonDTO personToDelete = FACADE.getPerson(id);
        String name = personToDelete.getFirstName();
        PersonDTO deletePerson = FACADE.deletePerson(personToDelete.getId());
        //return GSON.toJson(deletePerson);
        return "{\"status\" : \"deleted " + name + "\"}";
    }
    
}
