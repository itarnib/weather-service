package com.weatherservice.service;

import com.google.gson.Gson;
import com.weatherservice.model.City;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {
    public City getCityWeather(String city) throws IOException {
        String apiKey = "df04ad59a46e9e3ffb04df4178548f9c";
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
}
