package by.realovka.telegrambot.controller;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping(path = "/city")
@RequiredArgsConstructor
public class CityController {
    private static final Logger logger = LogManager.getLogger();

    private final CityService cityService;

    @PostMapping
    public City saveNewCity(@RequestBody City city) {
        cityService.saveNewCity(city);
        logger.log(Level.INFO, "Save new city " + city);
        return city;
    }

    @GetMapping(path = "/{name}")
    public String getDescription(@PathVariable String name) {
        String description = cityService.getDescription(name).getDescription();
        logger.log(Level.INFO, "City description " + description);
        return description;
    }

    @PutMapping(path = "/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        City cityUpdate = cityService.update(id, city);
        logger.log(Level.INFO, "Update city " + cityUpdate);
        return cityUpdate;

    }

    @DeleteMapping(path = "/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
