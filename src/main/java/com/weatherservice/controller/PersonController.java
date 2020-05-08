package com.weatherservice.controller;

import com.weatherservice.model.Person;
import com.weatherservice.repository.PersonRepository;
import com.weatherservice.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    private Long savePerson(@RequestBody Person person)
    {
        personService.saveOrUpdate(person);
        return person.getId();
    }

    @RequestMapping(value = "/people", method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        personService.saveOrUpdate(person);
        return person;
    }

    @RequestMapping(value = "people/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
