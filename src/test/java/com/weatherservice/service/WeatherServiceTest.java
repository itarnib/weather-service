package com.weatherservice.service;

import com.weatherservice.model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void testGetCityWeather() throws IOException {

        City london = weatherService.getCityWeather("London");
        City ttt = weatherService.getCityWeather("ttt");

        assertEquals(london.getName(), "London");
        assertNotNull(london.getId());
        assertNotNull(london.getMain());

        assertNull(ttt.getName());
        assertEquals(ttt.getId(), 0L);
        assertNull(ttt.getMain());
    }
}
