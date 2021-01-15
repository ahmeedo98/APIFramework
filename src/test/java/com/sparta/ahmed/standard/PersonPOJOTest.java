package com.sparta.ahmed.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PersonPOJOTest {

    static ObjectMapper objectMapper = new ObjectMapper();
    static PersonPOJO personPOJO = new PersonPOJO();
    static HttpClient httpClient = HttpClient.newHttpClient();
    static HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://swapi.dev/api/people/1/")).build();
    static int statusCode;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        personPOJO = new PersonPOJO();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            personPOJO = objectMapper.readValue(httpResponse.body(), PersonPOJO.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Check Status Code 200")
    void checkStatusCode200() {
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        statusCode = httpResponse.statusCode();
        System.out.println(statusCode);
    }

    @Test
    @DisplayName("Check Person Name")
    void checkStudentName() {
        System.out.println(personPOJO.getName());
        Assertions.assertEquals("Luke Skywalker", personPOJO.getName());
    }


}
