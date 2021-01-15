package com.sparta.ahmed.framework;

import com.sparta.ahmed.framework.dtos.StarWarsDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
    private static final String URL = "https://swapi.dev/api/";
    private static String endpoint = "people/1/";
    private static HttpClient httpClient = HttpClient.newHttpClient();
    private static HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).build();
    private static HttpResponse<String> httpResponse;

    public static String getUrl() {
        return URL;
    }

    public static int getStatusCode() {
        int statusCode = 0;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            statusCode = httpResponse.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    public static StarWarsDTO injectDTO(String url, String endpoint) {
        return Injector.injectDTO(url, endpoint);
    }

    public static HttpHeaders getHeaders() {
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers =httpResponse.headers();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return httpResponse.headers();
    }

    public static Map<String, List<String>> getMappedHeaders() {
        Map<String, List<String>> headerMap = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers = httpResponse.headers();
            headerMap = headers.map();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return headerMap;
    }

}
