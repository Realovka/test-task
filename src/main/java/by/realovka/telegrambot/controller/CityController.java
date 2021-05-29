package by.realovka.telegrambot.controller;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import lombok.RequiredArgsConstructor;
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

    private final CityService cityService;

    @PostMapping(path = "/save")
    public City saveNewCity(@RequestBody City city) {
        cityService.saveNewCity(city);
        return city;
    }

    @GetMapping(path = "/desc/{name}")
    public String getDescription(@PathVariable String name) {
        return cityService.getDescription(name).getDescription();
    }

    @PutMapping(path = "/update/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.update(id, city);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }

}
