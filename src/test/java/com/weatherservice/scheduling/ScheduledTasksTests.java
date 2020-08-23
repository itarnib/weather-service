package com.weatherservice.scheduling;

import com.weatherservice.WeatherServiceApplication;
import com.weatherservice.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;


public class ScheduledTasksTests {
    @InjectMocks
    ScheduledTasks scheduledTasks;

    @Mock
    WeatherService weatherService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateCacheTest() throws IOException {
        WeatherServiceApplication.main(new String[] {});
        when(weatherService.getRigaWeather()).thenReturn(null);
        assertNull(weatherService.getRigaWeather());
        scheduledTasks.updateCache();
    }
}
