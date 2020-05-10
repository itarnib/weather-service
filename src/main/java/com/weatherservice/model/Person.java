package com.weatherservice.model;

import com.weatherservice.service.WeatherService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import java.io.IOException;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Pattern(regexp="[a-zA-Z]+", message = "Name cannot be blank and can contain only letters")
    @Column(name="name")
    private String name;

    @Pattern(regexp="[a-zA-Z]+", message = "City cannot be blank and can contain only letters")
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

    public String tempInCity() throws IOException {
        City result = new WeatherService().getCityWeather(city);

        if(result.getMain() != null) {
            return String.valueOf(result.getMain().temp);
        }

        return "Not Found";
    }
}
