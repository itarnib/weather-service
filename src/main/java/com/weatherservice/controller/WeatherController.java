package com.weatherservice.controller;

import com.weatherservice.model.City;
import com.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "weather/{city}", method = RequestMethod.GET)
    public String getWeather (@PathVariable String city, Model model) throws IOException {
        City result = weatherService.getCityWeather(city);
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
