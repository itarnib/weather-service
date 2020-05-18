package com.weatherservice.scheduling;

import com.weatherservice.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        when(weatherService.getRigaWeather()).thenReturn(null);
        scheduledTasks.updateCache();
    }
}
