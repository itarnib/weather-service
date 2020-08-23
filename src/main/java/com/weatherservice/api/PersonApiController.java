package com.weatherservice.api;

import com.weatherservice.model.Person;
import com.weatherservice.model.PersonDTO;
import com.weatherservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api("Manage person entries")
@RestController
public class PersonApiController {

    @Autowired
    private PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonApiController.class);

    @ApiOperation(value = "Get a list of people.",
            response = Person.class,
            responseContainer = "List",
            produces = "application/json")
    @GetMapping(value = "api/people")
    public List<Person> getAllPeople() {
        logger.info("Searching for all people in the database");
        return personService.getAllPeople();
    }

    @ApiOperation(value="Get person by ID",
            response=Person.class,
            produces = "application/json")
    @GetMapping(value = "api/people/{id}")
    public Person getPerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        String message = "Searching for person wth ID: " + id;
        logger.info(message);
        return personService.getPersonById(id);
    }

    @ApiOperation(value = "Save new person in a database.",
            response = Person.class,
            produces = "application/json")
    @PostMapping(value = "api/people/add")
    public Person savePerson(@ApiParam(value = "Person entity", required = true) @RequestBody PersonDTO personDTO) {
        logger.info("Trying to save person");
        Person person = new Person(personDTO.getId(), personDTO.getName(), personDTO.getCity());
        return personService.savePerson(person);
    }

    @ApiOperation(value="Delete person by ID",
            produces = "application/json")
    @GetMapping(value = "api/people/delete/{id}")
    public Long deletePerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        String message = "Trying to delete person wth ID: " + id;
        logger.info(message);
        return personService.deletePerson(id);
    }
}
