package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.repository.CityRepository;
import by.realovka.telegrambot.service.exception.CityAlreadyExistException;
import by.realovka.telegrambot.service.exception.NoSuchCityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public boolean saveNewCity(City city) {
        if(cityRepository.findByName(city.getName()).isEmpty()) {
            cityRepository.save(city);
            return true;
        } else {
            throw new CityAlreadyExistException("Such a city is already exists in your guide");
        }
    }

    public City getDescription(String name) {
      return cityRepository.findByName(name).orElseThrow(NoSuchCityException::new);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name).orElseThrow(NoSuchCityException::new);
    }

    public City update(Long id, City city) {
        City cityFromDB = cityRepository.getById(id);
        cityFromDB.setName(city.getName());
        cityFromDB.setDescription(city.getDescription());
        return cityRepository.save(cityFromDB);
    }

    public void deleteCity(String name) {
        City city = cityRepository.findByName(name).orElseThrow(NoSuchCityException::new);
        cityRepository.delete(city);
    }
}
