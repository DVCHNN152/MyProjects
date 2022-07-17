package ru.edu.config;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.edu.dto.CityInfoRequest;
import ru.edu.dto.CityInfoResponse;
import ru.edu.dto.HelloWorldRequest;
import ru.edu.dto.HelloWorldResponse;

import java.time.LocalDateTime;

@Endpoint
public class MyWsEndpoint {

    @ResponsePayload
    @PayloadRoot(localPart = "helloWorldRequest")
    public HelloWorldResponse getHelloWorld(@RequestPayload HelloWorldRequest req) {
        HelloWorldResponse response = new HelloWorldResponse();
        response.setMessage("Hello World!");
        return response;
    }

    @ResponsePayload
    @PayloadRoot(localPart = "cityInfoReq")
    public CityInfoResponse getCityInfo(@RequestPayload CityInfoRequest request) {

        CityInfoResponse response = new CityInfoResponse();
        response.setCityId(request.getCityId());
        response.setName("Moscow");
        response.setResponseTime(LocalDateTime.now().toString());
        return response;
    }
}
