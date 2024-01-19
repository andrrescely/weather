package org.adaschool.Weather.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;

@Service
public class WeatherReportService {

    private static final String API_KEY = "dccb77418268e4e8ae0b3ac33ed64ef2";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;

    public WeatherReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        System.out.println("URL: " + url);

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response != null && response.getMain() != null) {
                WeatherReport report = new WeatherReport();
                WeatherApiResponse.Main main = response.getMain();
                report.setTemperature(main.getTemperature());
                report.setHumidity(main.getHumidity());

                return report;
            } else {
                System.out.println("Response or response.getMain() is null");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeatherReport getWeatherReport(String city) {
        String url = API_URL + "?q=" + city + "&appid=" + API_KEY;
        System.out.println("URL: " + url);

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response != null && response.getMain() != null) {
                WeatherReport report = new WeatherReport();
                WeatherApiResponse.Main main = response.getMain();
                report.setTemperature(main.getTemperature());
                report.setHumidity(main.getHumidity());

                return report;
            } else {
                System.out.println("Response or response.getMain() is null");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
