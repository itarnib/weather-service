package com.weatherservice.controller;

import com.google.gson.Gson;
import com.weatherservice.model.City;
import com.weatherservice.model.Weather;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class WeatherController {
    @RequestMapping(value = "weather/{city}", method = RequestMethod.GET)
    public Weather getWeather (@PathVariable String city) throws IOException {

        String apiKey = "df04ad59a46e9e3ffb04df4178548f9c";
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader json  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        City result = new Gson().fromJson(json, City.class);
        return result.getMain();
    }
}
