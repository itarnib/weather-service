package com.weatherservice.controller;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
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

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String getAllPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.GET)
    public String addPerson(Person person) {
        return "add-person";
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.POST)
    private String savePerson(@Valid Person person, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            return "add-person";
        }
        personService.save(person);
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "people/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable Long id, Model model) {
        personService.delete(id);
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }
}
