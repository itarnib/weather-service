package com.weatherservice.service;

import com.weatherservice.model.Person;
import com.weatherservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<Person>();
        personRepository.findAll().forEach(person -> people.add(person));  ;
        return people;
    }

    public Person getPersonById(Long id)
    {
        return personRepository.findById(id).get();
    }

    public Person savePerson(Person person)
    {
        personRepository.save(person);
        return person;
    }

    public Long deletePerson(Long id)
    {
        personRepository.deleteById(id);
        return id;
    }
}
