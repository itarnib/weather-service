package com.weatherservice.api;

import com.weatherservice.model.City;
import com.weatherservice.model.Weather;
import com.weatherservice.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherApiControllerTests {
    @InjectMocks
    WeatherApiController weatherApiController;

    @Mock
    WeatherService weatherService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeather() throws IOException {
        City city = new City(1, "Riga", null);
        when(weatherService.getCityWeather("Riga")).thenReturn(city);
        Weather returned = weatherApiController.getWeather("Riga");
        assertEquals(null, returned);
    }
}
