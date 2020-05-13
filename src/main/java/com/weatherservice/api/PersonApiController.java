package com.weatherservice.api;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("Manage person entries")
@RestController
public class PersonApiController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Get a list of people.",
            response = Person.class,
            responseContainer = "List",
            produces = "application/json")
    @RequestMapping(value = "api/people", method = RequestMethod.GET)
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @ApiOperation(value="Get person by ID",
            response=Person.class,
            produces = "application/json")
    @RequestMapping(value = "api/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @ApiOperation(value = "Save new person in a database.",
            response = Person.class,
            produces = "application/json")
    @RequestMapping(value = "api/people/add", method = RequestMethod.POST)
    public Person savePerson(@ApiParam(value = "Person entity", required = true) @RequestBody Person person) {
        return personService.savePerson(person);
    }

    @ApiOperation(value="Delete person by ID",
            produces = "application/json")
    @RequestMapping(value = "api/people/delete/{id}", method = RequestMethod.GET)
    public Long deletePerson(@ApiParam(value = "Person ID", required = true) @PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
