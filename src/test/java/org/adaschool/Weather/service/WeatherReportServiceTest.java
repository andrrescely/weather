package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    void getWeatherReportTest() {

        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(50.0);
        weatherApiResponse.setMain(main);

        // Configurar el comportamiento simulado de RestTemplate cuando se llama a getForObject
        when(restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat=0.0&lon=0.0&appid=dccb77418268e4e8ae0b3ac33ed64ef2", WeatherApiResponse.class))
                .thenReturn(weatherApiResponse);

        // Llamar al método que deseas probar
        WeatherReport result = weatherReportService.getWeatherReport(5.0, 10.0);

        // Verificar que el resultado no sea nulo antes de realizar aserciones
        assertNotNull(result, "El objeto WeatherReport no debería ser nulo");

        // Verificar el resultado
        assertEquals(25.0, result.getTemperature(), 0.01, "La temperatura no coincide");
        assertEquals(50.0, result.getHumidity(), 0.01, "La humedad no coincide");


    }
}