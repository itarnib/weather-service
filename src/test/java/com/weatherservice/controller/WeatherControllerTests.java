package com.weatherservice.controller;

import com.weatherservice.model.City;
import com.weatherservice.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

public class WeatherControllerTests {
    @InjectMocks
    WeatherController weatherController;

    @Mock
    WeatherService weatherService;

    @Mock
    Model model;

    @Mock
    BindingResult result;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeather() throws IOException {
        when(weatherService.getCityWeather("Riga")).thenReturn(null);
        String returned = weatherController.getWeather("Riga", model);
        assertEquals("weather-result", returned);
    }

    @Test
    public void testWeather() {
        City city = new City();
        String returned = weatherController.weather(city);
        assertEquals("weather", returned);
    }

    @Test
    public void testCheckWeatherWithoutErrors() {
        City city = new City();
        city.setName("Riga");
        when(result.hasErrors()).thenReturn(false);
        String returned = weatherController.checkWeather(city, result, model);
        assertEquals("redirect:/weather/Riga", returned);
    }

    @Test
    public void testCheckWeatherWithErrors() {
        City city = new City();
        city.setName("Riga");
        when(result.hasErrors()).thenReturn(true);
        String returned = weatherController.checkWeather(city, result, model);
        assertEquals("weather", returned);
    }

    @Test
    public void testGetRigaWeather() throws IOException {
        when(weatherService.getRigaWeather()).thenReturn(null);
        String returned = weatherController.getRigaWeather(model);
        assertEquals("weather-result", returned);
    }

}
