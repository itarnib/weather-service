package com.weatherservice.service;

import com.weatherservice.model.Person;
import com.weatherservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    public Person getPersonById(Long id)
    {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            return person.get();
        }
        return null;
    }

    public Person savePerson(Person person)
    {
        return personRepository.save(person);
    }

    public Long deletePerson(Long id)
    {
        personRepository.deleteById(id);
        return id;
    }
}
