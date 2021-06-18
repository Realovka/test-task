package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.repository.CityRepository;
import by.realovka.telegrambot.service.exception.CityAlreadyExistException;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;
    private City city1;
    private City city2;
    private List<City> cities;

    @BeforeEach
    public void setUp() {
        cities = new ArrayList<>();
        city1 = new City(1L, "Moscow", "Visit Red square");
        city2 = new City(2L, "Minsk", "Visit National library");
        cities.add(city1);
        cities.add(city2);
    }


    @Test
    public void saveNewCityTestTrue() {
        when(cityRepository.save(any())).thenReturn(city1);
        boolean result = cityService.saveNewCity(new City(1L, "Moscow", "Visit Red square"));
        assertTrue(result);

    }

    @Test
    public void saveNewCityTest() {
        when(cityRepository.save(any())).thenReturn(city1);
        cityService.saveNewCity(new City(1L, "Moscow", "Visit Red square"));
        verify(cityRepository, times(1)).save(any());
    }

    @Test
    public void getDescriptionTest() {
        when(cityRepository.findByName("Moscow")).thenReturn(Optional.ofNullable(city1));
        City city = cityService.findByName("Moscow");
        assertEquals("Visit Red square", city.getDescription());
    }

    @Test
    public void findByNameTest() {
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void deleteCityTest() {
    }
}