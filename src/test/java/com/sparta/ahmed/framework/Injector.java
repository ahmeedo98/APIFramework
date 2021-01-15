package com.sparta.ahmed.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.ahmed.framework.dtos.StarWarsDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Injector {


    public static StarWarsDTO injectDTO(String URL, String endpoint) {
        DTOFactory dtoFactory = new DTOFactory();
        StarWarsDTO chosenDTO = dtoFactory.getDTO(endpoint);
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL + endpoint)).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            chosenDTO = objectMapper.readValue(httpResponse.body(), chosenDTO.getClass());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return chosenDTO;
    }

}
