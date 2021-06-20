package by.realovka.telegrambot.repository;

import by.realovka.telegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
    Optional<City> getCityById(Long id);
    Optional<City> deleteCityById(Long id);
}
