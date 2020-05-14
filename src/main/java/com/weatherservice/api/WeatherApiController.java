package com.weatherservice.api;

import com.weatherservice.model.City;
import com.weatherservice.model.Weather;
import com.weatherservice.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api("Get weather")
@RestController
public class WeatherApiController {
    @Autowired
    private WeatherService weatherService;

    private final static Logger logger = LoggerFactory.getLogger(WeatherApiController.class);

    @ApiOperation(value="Get weather",
            response= Weather.class,
            produces = "application/json")
    @RequestMapping(value = "api/weather/{city}", method = RequestMethod.GET)
    public Weather getWeather (@ApiParam(value = "City name", required = true) @PathVariable String city) throws IOException {
        logger.info("Searching weather for: " + city);
        City result = weatherService.getCityWeather(city);
        return result.getMain();
    }

}