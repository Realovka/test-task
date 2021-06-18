package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.repository.CityRepository;
import by.realovka.telegrambot.service.exception.CityAlreadyExistException;
import by.realovka.telegrambot.service.exception.NoSuchCityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    public boolean saveNewCity(City city) {
        if(cityRepository.findByName(city.getName()).isEmpty()) {
            cityRepository.save(city);
            return true;
        } else {
            throw new CityAlreadyExistException();
        }
    }

    public City getDescription(String name) {
      return cityRepository.findByName(name).orElseThrow(NoSuchCityException::new);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name).orElseThrow(NoSuchCityException::new);
    }

    public City update(Long id, City city) {
        City cityFromDB = cityRepository.getCityById(id).orElseThrow(NoSuchCityException::new);
        cityFromDB.setName(city.getName());
        cityFromDB.setDescription(city.getDescription());
        return cityRepository.save(cityFromDB);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteCityById(id);
    }
}
