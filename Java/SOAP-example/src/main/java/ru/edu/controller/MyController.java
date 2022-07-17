package ru.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.dto.CityInfoResponse;
import ru.edu.dto.CityInformation;
import ru.edu.dto.HelloResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/example")
public class MyController {
    @GetMapping("/hello")
    public HelloResponse helloWorld() {

        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMessage("Hi!");
        return helloResponse;

    }

    @GetMapping("/city")
    public CityInfoResponse getCityInfo(@RequestParam("id") String cityId) {

        CityInfoResponse response = new CityInfoResponse();
        response.setCityId(cityId);
        response.setName("Moscow");
        response.setResponseTime(LocalDateTime.now().toString());
        return response;

    }
}
