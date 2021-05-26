package by.realovka.telegrambot.controller;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/city")
@AllArgsConstructor
public class CityController {

    private CityService cityService;

    @PostMapping(path = "/save")
    public City saveNewCity(@RequestBody City city) {
        cityService.saveNewCity(city);
        return city;
    }

    @GetMapping(path = "/desc/{name}")
    public String getDescription(@PathVariable String name) {
        return cityService.getDescription(name).getDescription();
    }

    @PostMapping(path = "/update/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.update(id, city);
    }

    @GetMapping(path = "/delete/{name}")
    public void deleteCity(@PathVariable String name) {
        cityService.deleteCity(name);
    }

}
