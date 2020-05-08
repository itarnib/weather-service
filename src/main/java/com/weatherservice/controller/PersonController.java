package com.weatherservice.controller;

import com.weatherservice.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Person> getList() {
        // TODO Add code
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person get() {
        // TODO Add code
        return null;
    }
}
