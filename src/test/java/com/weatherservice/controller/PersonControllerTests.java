package com.weatherservice.controller;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

public class PersonControllerTests {
    @InjectMocks
    PersonController personController;

    @Mock
    PersonService personService;

    @Mock
    Model model;

    @Mock
    BindingResult result;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPeople() {
        when(personService.getAllPeople()).thenReturn(null);
        String returned = personController.getAllPeople(model);
        assertEquals("people", returned);
    }

    @Test
    public void testGetPerson() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.getPersonById(1L)).thenReturn(person);
        Person returned = personController.getPerson(1L);
        assertEquals(person, returned);
    }

    @Test
    public void testAddPerson() {
        Person person = new Person(1L, "John", "Washington");
        String returned = personController.addPerson(person);
        assertEquals("add-person", returned);
    }

    @Test
    public void testSavePersonWithoutErrors() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.savePerson(person)).thenReturn(person);
        when(result.hasErrors()).thenReturn(false);
        String returned = personController.savePerson(person, result, model);
        assertEquals("people", returned);
    }

    @Test
    public void testSavePersonWithErrors() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.savePerson(person)).thenReturn(person);
        when(result.hasErrors()).thenReturn(true);
        String returned = personController.savePerson(person, result, model);
        assertEquals("add-person", returned);
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.deletePerson(1L)).thenReturn(1L);
        when(personService.getAllPeople()).thenReturn(null);
        String returned = personController.deletePerson(1L, model);
        assertEquals("people", returned);
    }

}
