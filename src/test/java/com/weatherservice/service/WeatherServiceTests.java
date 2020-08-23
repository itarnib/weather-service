package com.weatherservice.service;

import com.weatherservice.model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTests {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void testGetCityWeather() throws IOException {

        City london = weatherService.getCityWeather("London");
        City ttt = weatherService.getCityWeather("ttt");

        assertEquals("London", london.getName());
        assertNotEquals(0L, london.getId());
        assertNotNull(london.getMain());

        assertNull(ttt.getName());
        assertEquals(0L, ttt.getId());
        assertNull(ttt.getMain());
    }

    @Test
    public void testGetRigaWeather() throws IOException {

        City riga = weatherService.getRigaWeather();

        assertNotNull(riga.getName());
        assertNotEquals(0L, riga.getId());
        assertNotNull(riga.getMain());
    }
}
