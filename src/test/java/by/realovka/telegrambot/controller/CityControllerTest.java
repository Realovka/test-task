package by.realovka.telegrambot.controller;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@JsonTest
class CityControllerTest {

    @Mock
    private CityService cityService;
    private City city;

    @InjectMocks
    private CityController cityController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        city = new City(1L, "Moscow", "Visit Red square");
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveNewCity() throws Exception{
        when(cityService.saveNewCity(any())).thenReturn(city);
        mockMvc.perform(post("/city")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Moscow")));
    }

    @Test
    void getDescription() throws Exception {
        when(cityService.getDescription(anyString())).thenReturn(city);
        mockMvc.perform(get("/city/Moscow")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("Moscow")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("Visit Red square")));
    }

//    @Test
//    void updateCity() throws Exception {
//        when(cityService.update(anyLong(),any())).thenReturn(city);
//        mockMvc.perform(put("/city/1")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(1L)))
//                .andExpect(status().isOk())
//    }

    @Test
    void deleteCity() throws Exception {
        mockMvc.perform(delete("/city/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(1)))
                .andExpect(status().isOk());
    }
}