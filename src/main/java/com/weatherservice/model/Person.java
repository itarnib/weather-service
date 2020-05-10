package com.weatherservice.model;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public double tempInCity() throws IOException {
        String apiKey = "df04ad59a46e9e3ffb04df4178548f9c";
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader json  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        City result = new Gson().fromJson(json, City.class);

        return result.getMain().temp;
    }
}
