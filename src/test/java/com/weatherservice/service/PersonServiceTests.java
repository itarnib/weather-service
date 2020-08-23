package com.weatherservice.service;

import com.weatherservice.model.Person;

import com.weatherservice.scheduling.SchedulingConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonServiceTests {

    @Autowired
    private PersonService personService;

    @Test
    public void test1SavePerson() {
        Person person1 = new Person(1L, "John", "Washington");
        Person person2 = new Person(2L, "Kate", "Riga");

        Person savedPerson1 = personService.savePerson(person1);
        Person savedPerson2 = personService.savePerson(person2);

        assertEquals(person1.getId(), savedPerson1.getId());
        assertEquals(person1.getName(), savedPerson1.getName());

        assertEquals(person2.getId(), savedPerson2.getId());
        assertEquals(person2.getName(), savedPerson2.getName());
    }

    @Test
    public void test2GetPersonById() {
        Person person1 = personService.getPersonById(1L);
        Person person2 = personService.getPersonById(2L);

        assertEquals("John", person1.getName());
        assertEquals("Washington", person1.getCity());

        assertEquals("Kate", person2.getName());
        assertEquals("Riga", person2.getCity());
    }

    @Test
    public void test3DeletePerson() {
        Long id = personService.deletePerson(1L);
        assertEquals(1L, id);
    }

    @Test
    public void test4GetAllPeople() {
        List<Person> people = personService.getAllPeople();
        assertEquals(1, people.size());
    }
}
