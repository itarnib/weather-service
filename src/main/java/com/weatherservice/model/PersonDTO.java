package com.weatherservice.model;

import javax.validation.constraints.Pattern;

public class PersonDTO {
    private Long id;

    @Pattern(regexp="[a-zA-Z]+", message = "Name cannot be blank and can contain only letters")
    private String name;

    @Pattern(regexp="[a-zA-Z]+", message = "City cannot be blank and can contain only letters")
    private String city;

    public PersonDTO(Long id, String name, String city) {
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
