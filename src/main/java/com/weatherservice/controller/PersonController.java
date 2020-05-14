package com.weatherservice.controller;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String getAllPeople(Model model) {
        logger.info("Searching for all people in the database");

        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Long id) {
        logger.info("Searching for person wth ID: " + id);
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.GET)
    public String addPerson(Person person) {
        return "add-person";
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.POST)
    public String savePerson(@Valid Person person, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            logger.error("Cannot save person, wrong input");
            return "add-person";
        }
        personService.savePerson(person);
        logger.info("Person successfully saved");

        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "people/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable Long id, Model model) {
        personService.deletePerson(id);
        logger.info("Successfully deleted person with ID: " + id);

        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }
}
