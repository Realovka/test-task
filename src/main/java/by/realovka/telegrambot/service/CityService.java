package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;

public interface CityService {

    City saveNewCity(City city);
    City getDescription(String name);
    City findByName(String name);
    City update(Long id, City city);
    void deleteCity(Long id);
}
