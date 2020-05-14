package com.weatherservice.service;

import com.google.gson.Gson;
import com.weatherservice.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    String apiKey = "df04ad59a46e9e3ffb04df4178548f9c";

    @Cacheable("weather")
    public City getCityWeather(String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        City result = new City();

        if(connection.getResponseCode() == 200) {
            BufferedReader json  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            result = new Gson().fromJson(json, City.class);
        }

        return result;
    }

    @Cacheable("riga")
    public City getRigaWeather() throws IOException {
        logger.info("Weather service was called");
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Riga&units=metric&appid=" + apiKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        City result = new City();

        if(connection.getResponseCode() == 200) {
            BufferedReader json  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            result = new Gson().fromJson(json, City.class);
        }

        return result;
    }
}
