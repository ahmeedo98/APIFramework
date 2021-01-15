package com.sparta.ahmed.framework;

import com.sparta.ahmed.framework.dtos.*;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Properties;


public class FrameworkTest {
    static PersonDTO personDTO;
    static FilmsDTO filmsDTO;
    static SpeciesDTO speciesDTO;
    static StarshipsDTO starshipsDTO;
    static VehiclesDTO vehiclesDTO;
    static PlanetsDTO planetsDTO;
    static String url = ConnectionManager.getUrl();
    static Properties properties;

    @BeforeAll
    static void setup() {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/url.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        personDTO = (PersonDTO) ConnectionManager.injectDTO(url, properties.getProperty("personEndpoint"));
        filmsDTO = (FilmsDTO) ConnectionManager.injectDTO(url, properties.getProperty("filmEndpoint"));
        speciesDTO = (SpeciesDTO) ConnectionManager.injectDTO(url, properties.getProperty("speciesEndpoint"));
        starshipsDTO = (StarshipsDTO) ConnectionManager.injectDTO(url, properties.getProperty("starshipsEndpoint"));
        vehiclesDTO = (VehiclesDTO) ConnectionManager.injectDTO(url, properties.getProperty("vehiclesEndpoint"));
        planetsDTO = (PlanetsDTO) ConnectionManager.injectDTO(url, properties.getProperty("planetsEndpoint"));
    }


    @Nested
    @DisplayName("Check Connection Status")
    class CheckConnectionStatus {
        @Test
        @DisplayName("Check Status Code 200")
        void checkStatusCode200() {
            Assertions.assertEquals(200, ConnectionManager.getStatusCode());
        }

        @Test
        @DisplayName("Get Headers")
        void getHeaders() {
            System.out.println(ConnectionManager.getHeaders());
            Assertions.assertNotNull(ConnectionManager.getHeaders());
        }

        @Test
        @DisplayName("Get Map of Headers")
        void getMapOfHeaders() {
//            System.out.println(ConnectionManager.getMappedHeaders());
            Assertions.assertNotNull(ConnectionManager.getMappedHeaders());
        }

        @Test
        @DisplayName("Check Server Type is Correct")
        void checkServerTypeIsCorrect() {
            HttpHeaders headers = ConnectionManager.getHeaders();
            List<String> value = headers.allValues("server");
            Assertions.assertEquals("[nginx/1.16.1]", value.toString());
        }
    }

    @Nested
    @DisplayName("Check Person Values have Passed")
    class CheckPersonValueTypes {

        @Test
        @DisplayName("does Person have a name?")
        void doesPersonHaveName() {
            Assertions.assertTrue(personDTO.hasName());
        }

        @Test
        @DisplayName("does Person have name formatted")
        void doesPersonHaveNameFormatted() {
            Assertions.assertTrue(personDTO.hasNameFormatted());
        }

        @Test
        @DisplayName("does Person have a height?")
        void doesPersonHaveHeight() {
            Assertions.assertTrue(personDTO.hasHeight());
        }

        @Test
        @DisplayName("does Person have at least One Film")
        void doesPersonHaveAtLeastOneFilm() {
            Assertions.assertTrue(personDTO.hasOneFilmMinimum());
        }

    }

    @Nested
    @DisplayName("Check Person Creates Film DTO")
    class CheckPersonCreatesFilmDTO {

        @Test
        @DisplayName("does Luke have four films?")
        void doesLukeHaveFourFilms() {
            MatcherAssert.assertThat(personDTO.convertFilmsListToDTO(personDTO.getFilms()).size(), is(4));
        }

        @Test
        @DisplayName("does Luke have a New Hope")
        void doesLukeHaveANewHope() {
            FilmsDTO filmsDTO = personDTO.convertFilmsListToDTO(personDTO.getFilms()).get(0);
            MatcherAssert.assertThat(filmsDTO.getTitle(), is("A New Hope"));
        }

    }

    @Nested
    @DisplayName("Check Film Values are Valid")
    class CheckFilmsAreValid {

        @Test
        @DisplayName("Check Episode ID is valid")
        void checkEpisodeIdIsValid() {
            Assertions.assertTrue(filmsDTO.isEpisodeIdValid(filmsDTO.getEpisodeId()));
        }

        @Test
        @DisplayName("Check Director Name Uppercase")
        void checkDirectorNameUppercase() {
            Assertions.assertTrue(filmsDTO.isDirectorFormatted());
        }

    }

}
