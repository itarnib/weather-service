package com.weatherservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;

    @NotBlank(message = "City is mandatory")
    @Column(name="city")
    private String city;

    public Person() { }

    public Person(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
