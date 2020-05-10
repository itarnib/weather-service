package com.weatherservice.controller;

import com.google.gson.Gson;
import com.weatherservice.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class WeatherController {
    @RequestMapping(value = "weather/{city}", method = RequestMethod.GET)
    public String getWeather (@PathVariable String city, Model model) throws IOException {

        String apiKey = "df04ad59a46e9e3ffb04df4178548f9c";
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader json  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        City result = new Gson().fromJson(json, City.class);
        model.addAttribute("city", result);

        return "weather-result";
    }

    @RequestMapping(value = "weather", method = RequestMethod.GET)
    public String weather (City city) {
        return "weather";
    }

    @RequestMapping(value = "weather", method = RequestMethod.POST)
    public String checkWeather (@Valid City city, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "weather";
        }
        return "redirect:/weather/" + city.getName();
    }
}
