package com.weatherservice.api;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api("Manage person entries")
@RestController
public class PersonApiController {

    @Autowired
    private PersonService personService;

    private final static Logger logger = LoggerFactory.getLogger(PersonApiController.class);

    @ApiOperation(value = "Get a list of people.",
            response = Person.class,
            responseContainer = "List",
            produces = "application/json")
    @RequestMapping(value = "api/people", method = RequestMethod.GET)
    public List<Person> getAllPeople() {
        logger.info("Searching for all people in the database");
        return personService.getAllPeople();
    }

    @ApiOperation(value="Get person by ID",
            response=Person.class,
            produces = "application/json")
    @RequestMapping(value = "api/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        logger.info("Searching for person wth ID: " + id);
        return personService.getPersonById(id);
    }

    @ApiOperation(value = "Save new person in a database.",
            response = Person.class,
            produces = "application/json")
    @RequestMapping(value = "api/people/add", method = RequestMethod.POST)
    public Person savePerson(@ApiParam(value = "Person entity", required = true) @RequestBody Person person) {
        logger.info("Trying to save person");
        return personService.savePerson(person);
    }

    @ApiOperation(value="Delete person by ID",
            produces = "application/json")
    @RequestMapping(value = "api/people/delete/{id}", method = RequestMethod.GET)
    public Long deletePerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        logger.info("Trying to delete person with ID: " + id);
        return personService.deletePerson(id);
    }
}
