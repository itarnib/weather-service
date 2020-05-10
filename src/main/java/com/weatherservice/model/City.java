package com.weatherservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class City {
    int id;

    @Pattern(regexp="[a-zA-Z]+", message = "City cannot be blank and can contain only letters")
    String name;

    Weather main;

    public City() { }

    public City(int id, String name, Weather main) {
        this.id = id;
        this.name = name;
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }
}