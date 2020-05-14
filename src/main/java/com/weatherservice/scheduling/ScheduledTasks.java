package com.weatherservice.scheduling;

import com.weatherservice.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("scheduledTasks")
public class ScheduledTasks {

    private final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron="${cron.expression}")
    @CacheEvict(value = "riga", allEntries = true)
    public void updateCache() throws IOException {
        logger.info("Updating cache");
        new WeatherService().getRigaWeather();
    }
}
