package com.weatherservice.api;

import com.weatherservice.model.Person;
import com.weatherservice.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PersonApiControllerTests {
    @InjectMocks
    PersonApiController personApiController;

    @Mock
    PersonService personService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPeople() {
        when(personService.getAllPeople()).thenReturn(null);
        List<Person> returned = personApiController.getAllPeople();
        assertEquals(null, returned);
    }

    @Test
    public void testGetPerson() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.getPersonById(1L)).thenReturn(person);
        Person returned = personApiController.getPerson(1L);
        assertEquals(person, returned);
    }

    @Test
    public void testSavePerson() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.savePerson(person)).thenReturn(person);
        Person returned = personApiController.savePerson(person);
        assertEquals(person, returned);
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person(1L, "John", "Washington");
        when(personService.deletePerson(1L)).thenReturn(1L);
        when(personService.getAllPeople()).thenReturn(null);
        Long returned = personApiController.deletePerson(1L);
        assertEquals(person.getId(), returned);
    }
}
