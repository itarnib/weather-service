package com.weatherservice.controller;

import com.weatherservice.model.Person;
import com.weatherservice.model.PersonDTO;
import com.weatherservice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private static final String PEOPLE = "people";

    @GetMapping(value = "/people")
    public String getAllPeople(Model model) {
        logger.info("Searching for all people in the database");

        model.addAttribute(PEOPLE, personService.getAllPeople());
        return PEOPLE;
    }

    @GetMapping(value = "/people/{id}")
    public Person getPerson(@PathVariable Long id) {
        String message = "Searching for person wth ID: " + id;
        logger.info(message);
        return personService.getPersonById(id);
    }

    @GetMapping(value = "/people/add")
    public String addPerson(PersonDTO personDTO) {
        return "add-person";
    }

    @PostMapping(value = "/people/add")
    public String savePerson(@Valid PersonDTO personDTO, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            logger.error("Cannot save person, wrong input");
            return "add-person";
        }
        Person person = new Person(personDTO.getId(), personDTO.getName(), personDTO.getCity());
        personService.savePerson(person);
        logger.info("Person successfully saved");

        model.addAttribute(PEOPLE, personService.getAllPeople());
        return PEOPLE;
    }

    @GetMapping(value = "people/delete/{id}")
    public String deletePerson(@PathVariable Long id, Model model) {
        personService.deletePerson(id);
        String message = "Successfully deleted person with ID: " + id;
        logger.info(message);

        model.addAttribute(PEOPLE, personService.getAllPeople());
        return PEOPLE;
    }
}
