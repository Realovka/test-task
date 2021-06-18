package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.repository.CityRepository;
import by.realovka.telegrambot.service.exception.CityAlreadyExistException;
import by.realovka.telegrambot.service.exception.NoSuchCityException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private static final Logger logger = LogManager.getLogger();

    private CityRepository cityRepository;

    public boolean saveNewCity(City city) {
        if (cityRepository.findByName(city.getName()).isEmpty()) {
            logger.log(Level.INFO, "Save city " + city);
            cityRepository.save(city);
            return true;
        } else {
            logger.log(Level.ERROR, "Such city already exists");
            throw new CityAlreadyExistException();
        }
    }

    public City getDescription(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> {
            logger.log(Level.ERROR, "No such city");
            throw new NoSuchCityException();
        });
    }

    public City findByName(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> {
            logger.log(Level.ERROR, "No such city");
            throw new NoSuchCityException();
        });
    }

    public City update(Long id, City city) {
        City cityFromDB = cityRepository.getCityById(id).orElseThrow(() -> {
            logger.log(Level.ERROR, "No such city");
            throw new NoSuchCityException();
        });
        cityFromDB.setName(city.getName());
        cityFromDB.setDescription(city.getDescription());
        return cityRepository.save(cityFromDB);
    }

    public void deleteCity(Long id) {
        logger.log(Level.INFO, "Delete city with id " + id);
        cityRepository.delete(cityRepository.getById(id));
    }
}
