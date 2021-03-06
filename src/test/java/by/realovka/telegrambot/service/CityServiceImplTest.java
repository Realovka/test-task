package by.realovka.telegrambot.service;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.repository.CityRepository;
import by.realovka.telegrambot.service.exception.CityAlreadyExistException;
import by.realovka.telegrambot.service.exception.NoSuchCityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;
    private City city1;
    private City city2;

    @BeforeEach
    public void setUp() {
        city1 = new City(1L, "Moscow", "Visit Red square");
        city2 = new City(2L, "Minsk", "Visit National library");
    }

    @AfterEach
    public void tearDown() {
        cityRepository.deleteAll();
        city1 = null;
        city2 = null;
    }


    @Test
    public void saveNewCityTestTrue() {
        when(cityRepository.save(any())).thenReturn(city1);
        City city = cityService.saveNewCity(new City(1L, "Moscow", "Visit Red square"));
        assertEquals(city1, city);
    }

    @Test
    public void saveNewCityArgumentCaptorHelped() {
        cityService.saveNewCity(city2);
        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository).save(cityArgumentCaptor.capture());
        City capturedCity = cityArgumentCaptor.getValue();
        assertEquals(city2, capturedCity);
    }

    @Test
    public void saveNewCityCountingNumberCallsMethod() {
        when(cityRepository.save(any())).thenReturn(city1);
        cityService.saveNewCity(new City(1L, "Moscow", "Visit Red square"));
        verify(cityRepository, times(1)).save(any());
    }

    @Test
    public void saveNewCityException() {
        given(cityRepository.findByName(city2.getName())).willReturn(Optional.ofNullable(city2));
        assertThatThrownBy(() -> cityService.saveNewCity(city2))
                .isInstanceOf(CityAlreadyExistException.class);
    }

    @Test
    public void saveNewCityExceptionCountingNumberCallsMethod() {
        given(cityRepository.findByName(city2.getName())).willReturn(Optional.ofNullable(city2));
        assertThatThrownBy(() -> cityService.saveNewCity(city2))
                .isInstanceOf(CityAlreadyExistException.class);
        verify(cityRepository, never()).save(city2);
    }


    @Test
    public void getDescriptionTest() {
        when(cityRepository.findByName("Moscow")).thenReturn(Optional.ofNullable(city1));
        City city = cityService.findByName("Moscow");
        assertEquals("Visit Red square", city.getDescription());
    }

    @Test
    public void getDescriptionException() {
        try {
            CityService cityService = mock(CityService.class);
            when(cityService.getDescription(anyString())).thenThrow(NoSuchCityException.class);
            cityService.getDescription("Moscow");
        } catch (NoSuchCityException e) {
            assertTrue(e instanceof NoSuchCityException);
        }
    }

    @Test
    public void findByNameTest() {
        when(cityRepository.findByName("Moscow")).thenReturn(Optional.ofNullable(city1));
        City city = cityService.findByName("Moscow");
        assertEquals("Moscow", city.getName());
    }


    @Test
    public void updateTest() {
        doReturn(Optional.of(city1)).when(cityRepository).getCityById(1L);
        cityService.update(city1.getId(), city2);
        verify(cityRepository, times(1)).save(city1);
    }


    @Test
    public void deleteCityCountingNumberCallsMethod() {
        when(cityRepository.existsById(any())).thenReturn(true);
        cityService.deleteCity(any());
        verify(cityRepository, times(1)).delete(any());
    }

    @Test
    public void deleteCityException() {
        given(cityRepository.existsById(city2.getId())).willReturn(false);
        assertThatThrownBy(() -> cityService.deleteCity(city2.getId()))
                .isInstanceOf(NoSuchCityException.class);
    }

    @Test
    public void deleteCityExceptionCountingNumberCallsMethod() {
        given(cityRepository.existsById(city2.getId())).willReturn(false);
        assertThatThrownBy(() -> cityService.deleteCity(city2.getId()))
                .isInstanceOf(NoSuchCityException.class);
        verify(cityRepository, never()).delete(city2);
    }
}